package es.jjlop.cityserver.service.impl;

import es.jjlop.cityserver.controller.vo.RouteVO;
import es.jjlop.cityserver.dao.CitiesRepository;
import es.jjlop.cityserver.dao.RoutesRepository;
import es.jjlop.cityserver.entity.City;
import es.jjlop.cityserver.entity.Route;
import es.jjlop.cityserver.entity.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RouteSearchServiceImplTest {

    @Mock
    private RoutesRepository routesRepository;
    @Mock
    private CitiesRepository citiesRepository;

    private RouteSearchServiceImpl component;
    private List<Route> routes;
    private Route route1;
    private Route route2;
    private List<Step> listSteps1;
    private List<Step> listSteps2;

    @Before
    public void setUp() throws Exception {
        routes = new ArrayList<>();
        listSteps1 = new ArrayList<>();
        listSteps2 = new ArrayList<>();

        listSteps1.add(new Step(1L, route1, 1, new City(1L,"A"), new City(1L,"B"), 0.5));
        listSteps1.add(new Step(2L, route1, 2, new City(2L,"B"), new City(99L,"Z"), 1.5));

        listSteps2.add(new Step(3L, route2, 1, new City(2L,"B"), new City(3L,"C"), 2.5));
        listSteps2.add(new Step(4L, route2, 2, new City(3L,"C"), new City(99L,"Z"), 5.5));

        route1 = new Route(1L, new City(1L,"A"), new City(2L, "Z"), listSteps1);
        route2 = new Route(2L, new City(1L,"B"), new City(2L, "Z"), listSteps2);

        routes.add(route1);
        routes.add(route2);
    }

    @Test
    public void testFindRoutesModelToVO() {
        routesRepository = Mockito.mock(RoutesRepository.class);
        citiesRepository = Mockito.mock(CitiesRepository.class);

        when(routesRepository.getRoutesByOrigin(new City(1L,"A"))).thenReturn(routes);
        when(citiesRepository.getCityByName("A")).thenReturn(new City(1L, "A"));

        component = new RouteSearchServiceImpl(routesRepository, citiesRepository);

        List<RouteVO> data = component.findRoutes("A");

        testMapping(data);
        verify(routesRepository, times(1)).getRoutesByOrigin(new City(1L,"A"));
        verify(citiesRepository, times(1)).getCityByName("A");
    }

    @Test
    public void testFindRoutesCityNotFound() {
        routesRepository = Mockito.mock(RoutesRepository.class);
        citiesRepository = Mockito.mock(CitiesRepository.class);

        when(citiesRepository.getCityByName("A")).thenReturn(null);

        component = new RouteSearchServiceImpl(routesRepository, citiesRepository);

        List<RouteVO> data = component.findRoutes("A");

        Assert.assertThat(data, is(Collections.emptyList()));
        verify(citiesRepository, times(1)).getCityByName("A");
    }

    @Test
    public void testFindAllModelToVO() {
        routesRepository = Mockito.mock(RoutesRepository.class);
        citiesRepository = Mockito.mock(CitiesRepository.class);

        when(routesRepository.findAll()).thenReturn(routes);

        component = new RouteSearchServiceImpl(routesRepository, citiesRepository);

        List<RouteVO> data = component.findAll();
        testMapping(data);
        verify(routesRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllNoData() {
        routesRepository = Mockito.mock(RoutesRepository.class);
        citiesRepository = Mockito.mock(CitiesRepository.class);

        when(routesRepository.findAll()).thenReturn(null);

        component = new RouteSearchServiceImpl(routesRepository, citiesRepository);

        List<RouteVO> data = component.findAll();
        Assert.assertThat(data, is(Collections.emptyList()));
        verify(routesRepository, times(1)).findAll();
    }

    private void testMapping(List<RouteVO> data) {
        Assert.assertThat(data.size(), is(2));
        Assert.assertThat(data.get(0).getId(), is(1L) );
        Assert.assertThat(data.get(1).getId(), is(2L) );
        Assert.assertThat(data.get(0).getOrigin(), is("A") );
        Assert.assertThat(data.get(1).getOrigin(), is("B") );
        Assert.assertThat(data.get(0).getDestination(), is("Z") );
        Assert.assertThat(data.get(1).getDestination(), is("Z") );
        Assert.assertThat(data.get(0).getStepNumber(), is(2) );
        Assert.assertThat(data.get(1).getStepNumber(), is(2) );
        Assert.assertThat(data.get(0).getTotalDuration(), is(2.0) );
        Assert.assertThat(data.get(1).getTotalDuration(), is(8.0) );
        Assert.assertThat(data.get(0).getSteps().size(), is(2) );
        Assert.assertThat(data.get(1).getSteps().size(), is(2) );

        Assert.assertThat(data.get(0).getSteps().get(0).getDestination(), is("B") );
        Assert.assertThat(data.get(0).getSteps().get(0).getHours(), is(0.5) );
        Assert.assertThat(data.get(0).getSteps().get(0).getId(), is(1L) );
        Assert.assertThat(data.get(0).getSteps().get(0).getOrder(), is(1) );
        Assert.assertThat(data.get(0).getSteps().get(0).getOrigin(), is("A") );

        Assert.assertThat(data.get(0).getSteps().get(1).getDestination(), is("Z") );
        Assert.assertThat(data.get(0).getSteps().get(1).getHours(), is(1.5) );
        Assert.assertThat(data.get(0).getSteps().get(1).getId(), is(2L) );
        Assert.assertThat(data.get(0).getSteps().get(1).getOrder(), is(2) );
        Assert.assertThat(data.get(0).getSteps().get(1).getOrigin(), is("B") );

        Assert.assertThat(data.get(1).getSteps().get(0).getDestination(), is("C") );
        Assert.assertThat(data.get(1).getSteps().get(0).getHours(), is(2.5) );
        Assert.assertThat(data.get(1).getSteps().get(0).getId(), is(3L) );
        Assert.assertThat(data.get(1).getSteps().get(0).getOrder(), is(1) );
        Assert.assertThat(data.get(1).getSteps().get(0).getOrigin(), is("B") );

        Assert.assertThat(data.get(1).getSteps().get(1).getDestination(), is("Z") );
        Assert.assertThat(data.get(1).getSteps().get(1).getHours(), is(5.5) );
        Assert.assertThat(data.get(1).getSteps().get(1).getId(), is(4L) );
        Assert.assertThat(data.get(1).getSteps().get(1).getOrder(), is(2) );
        Assert.assertThat(data.get(1).getSteps().get(1).getOrigin(), is("C") );
    }
}