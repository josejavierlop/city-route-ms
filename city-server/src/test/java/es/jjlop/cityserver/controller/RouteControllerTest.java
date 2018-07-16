package es.jjlop.cityserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.jjlop.cityserver.controller.vo.ResponseVO;
import es.jjlop.cityserver.controller.vo.RouteVO;
import es.jjlop.cityserver.controller.vo.StepVO;
import es.jjlop.cityserver.service.RouteSearchService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RouteControllerTest {

    @Mock
    private RouteSearchService routeSearchService;
    @InjectMocks
    private RouteController routeController;

    @LocalServerPort
    private int port;
    private RouteVO route1;
    private RouteVO route2;
    private List<RouteVO> routes;
    private List<StepVO> stepList1;
    private List<StepVO> stepList2;
    private MockMvc mvc;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(routeController)
                .addFilters(new CorsFilter()).build();

        routes = new ArrayList<>();
        stepList1 = new ArrayList<>();
        stepList2 = new ArrayList<>();

        stepList1.add(new StepVO(1L, 1, "A", "B", 1.0));
        stepList1.add(new StepVO(2L, 2, "B", "Z", 1.0));
        stepList2.add(new StepVO(1L, 1, "B", "Z", 1.0));

        route1 = new RouteVO(1L, "A", "Z", 2, 2.0, stepList1);
        route1 = new RouteVO(2L, "B", "Z", 1, 1.0, stepList2);

        routes.add(route1);
        routes.add(route2);
    }

    @Test
    public void testFindByOriginOK() throws Exception {
        when(routeSearchService.findRoutes("A")).thenReturn(routes);

        mvc.perform(get("/routes/A"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new ResponseVO<>(routes))));
        verify(routeSearchService, times(1)).findRoutes("A");
    }

    @Test
    public void testFindByOriginNotFound() throws Exception {
        when(routeSearchService.findRoutes("A")).thenReturn(Collections.emptyList());

        mvc.perform(get("/routes/A"))
                .andExpect(status().is(404));
        verify(routeSearchService, times(1)).findRoutes("A");
    }

    @Test
    public void testFindAllOK() throws Exception {
        when(routeSearchService.findAll()).thenReturn(routes);

        mvc.perform(get("/routes"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new ResponseVO<>(routes))));
        verify(routeSearchService, times(1)).findAll();
    }

    @Test
    public void testFindAllNoData() throws Exception {
        when(routeSearchService.findAll()).thenReturn(Collections.emptyList());

        mvc.perform(get("/routes"))
                .andExpect(status().is(404));
        verify(routeSearchService, times(1)).findAll();
    }

    private String asJsonString(Object data) throws JsonProcessingException {
        return mapper.writeValueAsString(data);
    }
}