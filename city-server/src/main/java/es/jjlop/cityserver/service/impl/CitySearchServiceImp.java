package es.jjlop.cityserver.service.impl;

import es.jjlop.cityserver.controller.vo.CityVO;
import es.jjlop.cityserver.dao.CityRepository;
import es.jjlop.cityserver.service.CitySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitySearchServiceImp implements CitySearchService {
    @Autowired
    private CityRepository cityRepository;

    public List<CityVO> findCity(String city) {
        return cityRepository.getCitiesByName(city).stream()
                .map( c -> CityVO.builder().name(c.getName()).destinationCity(c.getDestination()).build())
                .collect(Collectors.toList());
    }
}
