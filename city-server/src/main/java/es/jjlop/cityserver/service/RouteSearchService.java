package es.jjlop.cityserver.service;

import es.jjlop.cityserver.controller.vo.RouteVO;

import java.util.List;

public interface RouteSearchService {
    List<RouteVO> findRoutes(String origin);

    List<RouteVO> findAll();
}
