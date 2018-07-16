package es.jjlop.cityserver.service.impl;

import es.jjlop.cityserver.controller.vo.CityVO;
import es.jjlop.cityserver.dao.CitiesRepository;
import es.jjlop.cityserver.entity.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CitySearchServiceImpTest {

    @Mock
    private CitiesRepository citiesRepository;

    private CitySearchServiceImp component;
    private List<City> cities;

    @Before
    public void setUp() throws Exception {
        cities = new ArrayList<>();
        cities.add(new City(1L,"A"));
        cities.add(new City(2L,"B"));
        cities.add(new City(3L,"C"));
    }

    @Test
    public void testFindCityModelToVO() {
        citiesRepository = Mockito.mock(CitiesRepository.class);
        when(citiesRepository.getCityByName("A")).thenReturn(cities.get(0));

        component = new CitySearchServiceImp(citiesRepository);

        Optional<CityVO> data = component.findCity("A");

        Assert.assertThat(data.get().getId(), is(1L));
        Assert.assertThat(data.get().getName(), is("A"));

        verify(citiesRepository, times(1)).getCityByName("A");
    }

    @Test
    public void testFindAllModelToVO() {
        citiesRepository = Mockito.mock(CitiesRepository.class);
        when(citiesRepository.findAll()).thenReturn(cities);

        component = new CitySearchServiceImp(citiesRepository);

        List<CityVO> data = component.findAll();

        Assert.assertThat(data.size(), is(3));
        Assert.assertThat(data.get(0).getId(), is(1L));
        Assert.assertThat(data.get(0).getName(), is("A"));
        Assert.assertThat(data.get(1).getId(), is(2L));
        Assert.assertThat(data.get(1).getName(), is("B"));
        Assert.assertThat(data.get(2).getId(), is(3L));
        Assert.assertThat(data.get(2).getName(), is("C"));
        verify(citiesRepository, times(1)).findAll();
    }
}