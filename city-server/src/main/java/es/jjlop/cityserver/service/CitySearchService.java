package es.jjlop.cityserver.service;

import es.jjlop.cityserver.controller.vo.CityVO;

import java.util.List;

public interface CitySearchService {
    public List<CityVO> findCity(String city);
}
