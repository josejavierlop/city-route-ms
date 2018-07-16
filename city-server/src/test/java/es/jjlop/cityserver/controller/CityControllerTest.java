package es.jjlop.cityserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.jjlop.cityserver.controller.vo.CityVO;
import es.jjlop.cityserver.controller.vo.ResponseVO;
import es.jjlop.cityserver.controller.vo.RouteVO;
import es.jjlop.cityserver.service.CitySearchService;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CityControllerTest {

    @Mock
    private CitySearchService citySearchService;
    @InjectMocks
    private CityController cityController;
    @LocalServerPort
    private int port;
    private RouteVO route1;

    private MockMvc mvc;
    private ObjectMapper mapper;


    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(cityController)
                .addFilters(new CorsFilter()).build();
    }

    @Test
    public void testFindCityOK() throws Exception {
        when(citySearchService.findCity("A")).thenReturn(Optional.of(new CityVO(1L, "A")));

        mvc.perform(get("/cities/A"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new ResponseVO<>(new CityVO(1L, "A")))));
        verify(citySearchService, times(1)).findCity("A");
    }

    @Test
    public void testFindCityNotFound() throws Exception {
        when(citySearchService.findCity("A")).thenReturn(Optional.empty());

        mvc.perform(get("/cities/A"))
                .andExpect(status().is(404));
        verify(citySearchService, times(1)).findCity("A");
    }

    @Test
    public void testFindAllOK() throws Exception {
        List<CityVO> cities = new ArrayList<>();
        cities.add(CityVO.builder().id(1L).name("A").build());
        cities.add(CityVO.builder().id(2L).name("B").build());

        when(citySearchService.findAll()).thenReturn(cities);

        mvc.perform(get("/cities"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new ResponseVO<>(cities))));
        verify(citySearchService, times(1)).findAll();
    }

    @Test
    public void testFindAllNoData() throws Exception {
        when(citySearchService.findAll()).thenReturn(Collections.emptyList());

        mvc.perform(get("/cities"))
                .andExpect(status().is(404));
        verify(citySearchService, times(1)).findAll();
    }

    private String asJsonString(Object data) throws JsonProcessingException {
        return mapper.writeValueAsString(data);
    }
}