package es.jjlop.calculationserver.service.impl;

import es.jjlop.calculationserver.client.CityClient;
import es.jjlop.calculationserver.contoller.vo.ResponseVO;
import es.jjlop.calculationserver.contoller.vo.RouteVO;
import es.jjlop.calculationserver.contoller.vo.StepVO;
import es.jjlop.calculationserver.service.RouteCalculatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RouteCalculatorServiceImplTest {

    @Mock
    private CityClient cityClient;

    private RouteCalculatorService component;

    private List<RouteVO> routes;
    private RouteVO route1;
    private RouteVO route2;

    @Before
    public void setUp() throws Exception {

        routes = new ArrayList<>();

        List<StepVO> steps1 = new ArrayList<>();
        steps1.add(new StepVO(1L,1,"A","B",1.0));
        steps1.add(new StepVO(2L,2,"B","Z",1.0));
        route1 = new RouteVO(1L, "A", "Z", 2, 10.2, steps1);
        routes.add(route1);
        List<StepVO> steps2 = new ArrayList<>();
        steps2.add(new StepVO(1L,1,"A","B",1.0));
        steps2.add(new StepVO(2L,2,"B","C",1.0));
        steps2.add(new StepVO(2L,2,"C","D",1.0));
        steps2.add(new StepVO(2L,2,"D","E",1.0));
        steps2.add(new StepVO(2L,2,"E","Z",1.0));
        route2 = new RouteVO(2L, "A", "Z", 2, 10.1, steps2);
        routes.add(route2);
    }

    @Test
    public void testCalculateLessConnectionsRoute() {
        cityClient = Mockito.mock(CityClient.class);
        when(cityClient.getRoutesByOrigin("A")).thenReturn(new ResponseVO<>(routes));
        component = new RouteCalculatorServiceImpl(cityClient);

        Optional<RouteVO> route = component.calculateLessConnectionsRoute("A", "Z");

        Assert.assertThat(route.get().getId(), is(1L));
        Assert.assertThat(route.get().getSteps().size(), is(2));
        Assert.assertThat(route.get().getTotalDuration(), is(10.2));
        Assert.assertThat(route.get(), is(route1));
        verify(cityClient, times(1)).getRoutesByOrigin("A");
    }

    @Test
    public void testCalculateLessDurationsRoute() {
        cityClient = Mockito.mock(CityClient.class);
        when(cityClient.getRoutesByOrigin("A")).thenReturn(new ResponseVO<>(routes));
        component = new RouteCalculatorServiceImpl(cityClient);

        Optional<RouteVO> route = component.calculateLessDurationsRoute("A", "Z");

        Assert.assertThat(route.get().getId(), is(2L));
        Assert.assertThat(route.get().getSteps().size(), is(5));
        Assert.assertThat(route.get().getTotalDuration(), is(10.1));
        Assert.assertThat(route.get(), is(route2));
        verify(cityClient, times(1)).getRoutesByOrigin("A");
    }

    @Test
    public void testNoRoutesFoundFromCity() {
        cityClient = Mockito.mock(CityClient.class);
        when(cityClient.getRoutesByOrigin("A")).thenReturn(new ResponseVO<>(Collections.emptyList()));
        component = new RouteCalculatorServiceImpl(cityClient);

        Optional<RouteVO> route = component.calculateLessDurationsRoute("A", "Z");

        Assert.assertThat(route.isPresent(), is(false));
        verify(cityClient, times(1)).getRoutesByOrigin("A");
    }

    @Test
    public void testNoRoutesFoundToDestination() {
        cityClient = Mockito.mock(CityClient.class);
        when(cityClient.getRoutesByOrigin("A")).thenReturn(new ResponseVO<>(routes));
        component = new RouteCalculatorServiceImpl(cityClient);

        Optional<RouteVO> route = component.calculateLessDurationsRoute("A", "XXX");

        Assert.assertThat(route.isPresent(), is(false));
        verify(cityClient, times(1)).getRoutesByOrigin("A");
    }
}