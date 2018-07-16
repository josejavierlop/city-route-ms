package es.jjlop.cityserver.service.impl;

import es.jjlop.cityserver.controller.vo.CityVO;
import es.jjlop.cityserver.dao.CitiesRepository;
import es.jjlop.cityserver.entity.City;
import es.jjlop.cityserver.service.CitySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitySearchServiceImp implements CitySearchService {

    private CitiesRepository cityRepository;

    @Autowired
    public CitySearchServiceImp(CitiesRepository citiesRepository){
        this.cityRepository = citiesRepository;
    }

    public Optional<CityVO> findCity(String city) {
        CityVO data = mapToVO(cityRepository.getCityByName(city));
        return Optional.ofNullable(data);
    }

    @Override
    public List<CityVO> findAll() {
        return cityRepository.findAll().stream()
                .map(this::mapToVO)
                .collect(Collectors.toList());
    }

    private CityVO mapToVO(City city) {
        if (city == null) {
            return null;
        }
        return CityVO.builder().name(city.getName()).id(city.getId()).build();
    }
}
