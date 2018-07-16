package es.jjlop.calculationserver.service;

import es.jjlop.calculationserver.contoller.vo.RouteVO;

import java.util.Optional;

public interface RouteCalculatorService {

    public Optional<RouteVO> calculateLessConnectionsRoute(String origin, String destination);
    public Optional<RouteVO> calculateLessDurationsRoute(String origin, String destination);
}
