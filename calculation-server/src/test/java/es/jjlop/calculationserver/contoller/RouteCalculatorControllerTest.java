package es.jjlop.calculationserver.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.jjlop.calculationserver.contoller.vo.ResponseVO;
import es.jjlop.calculationserver.contoller.vo.RouteVO;
import es.jjlop.calculationserver.contoller.vo.StepVO;
import es.jjlop.calculationserver.service.RouteCalculatorService;
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
public class RouteCalculatorControllerTest {

    @Mock
    private RouteCalculatorService routeCalculatorService;
    @InjectMocks
    private RouteCalculatorController routeCalculatorController;
    @LocalServerPort
    private int port;
    private RouteVO route1;

    private MockMvc mvc;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(routeCalculatorController)
                .addFilters(new CorsFilter()).build();

        List<StepVO> steps1 = new ArrayList<>();
        steps1.add(new StepVO(1L,1,"A","B",1.0));
        steps1.add(new StepVO(2L,2,"B","Z",1.0));
        route1 = new RouteVO(1L, "A", "Z", 2, 10.2, steps1);
    }

    @Test
    public void testFindLessConnectionsOK() throws Exception {
        when(routeCalculatorService.calculateLessConnectionsRoute("A","B")).thenReturn(Optional.of(route1));

        mvc.perform(get("/calculations/connections/A/B"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new ResponseVO<>(route1))));
        verify(routeCalculatorService, times(1)).calculateLessConnectionsRoute("A", "B");
    }

    @Test
    public void testFindLessTimeOK() throws Exception {
        when(routeCalculatorService.calculateLessDurationsRoute("A","B")).thenReturn(Optional.of(route1));

        mvc.perform(get("/calculations/duration/A/B"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new ResponseVO<>(route1))));
        verify(routeCalculatorService, times(1)).calculateLessDurationsRoute("A", "B");
    }

    @Test
    public void testFindLessConnectionsNotFound() throws Exception {
        when(routeCalculatorService.calculateLessConnectionsRoute("A","B")).thenReturn(Optional.empty());

        mvc.perform(get("/calculations/connections/A/B"))
                .andExpect(status().is(404));
        verify(routeCalculatorService, times(1)).calculateLessConnectionsRoute("A", "B");
    }

    @Test
    public void testFindLessTimeNotFound() throws Exception {
        when(routeCalculatorService.calculateLessDurationsRoute("A","B")).thenReturn(Optional.empty());

        mvc.perform(get("/calculations/duration/A/B"))
                .andExpect(status().is(404));
        verify(routeCalculatorService, times(1)).calculateLessDurationsRoute("A", "B");
    }

    private String asJsonString(Object data) throws JsonProcessingException {
        return mapper.writeValueAsString(data);
    }
}