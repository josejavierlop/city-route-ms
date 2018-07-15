package es.jjlop.cityserver.service;

import es.jjlop.cityserver.controller.vo.CityVO;

import java.util.List;
import java.util.Optional;

public interface CitySearchService {
    Optional<CityVO> findCity(String city);

    List<CityVO> findAll();
}
