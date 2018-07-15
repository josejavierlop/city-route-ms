package es.jjlop.cityserver.service.impl;

import es.jjlop.cityserver.controller.vo.RouteVO;
import es.jjlop.cityserver.controller.vo.StepVO;
import es.jjlop.cityserver.dao.CitiesRepository;
import es.jjlop.cityserver.dao.RoutesRepository;
import es.jjlop.cityserver.entity.City;
import es.jjlop.cityserver.entity.Route;
import es.jjlop.cityserver.service.RouteSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteSearchServiceImpl implements RouteSearchService {

    @Autowired
    private RoutesRepository routesRepository;

    @Autowired
    private CitiesRepository citiesRepository;

    @Override
    public List<RouteVO> findRoutes(String origin) {
        City city = citiesRepository.getCityByName(origin);
        if (city == null) {
            return Collections.emptyList();
        }
        List<Route> data = routesRepository.getRoutesByOrigin(city);
        if (data == null){
            return Collections.emptyList();
        }
        return data.stream().map(this::mapToVO).collect(Collectors.toList());
    }

    @Override
    public List<RouteVO> findAll() {
        List<Route> data = routesRepository.findAll();
        if (data == null){
            return Collections.emptyList();
        }
        return data.stream().map(this::mapToVO).collect(Collectors.toList());
    }

    private RouteVO mapToVO(Route route) {
        RouteVO vo = new RouteVO();
        vo.setDestination(route.getDestination().getName());
        vo.setId(route.getId());
        vo.setOrigin(route.getOrigin().getName());
        if (route.getSteps() == null){
            vo.setSteps(Collections.emptyList());
            vo.setStepNumber(0);
            vo.setTotalDuration(0d);
        } else {
            vo.setStepNumber(route.getSteps().size());
            vo.setTotalDuration(route.getSteps().stream().mapToDouble(s -> s.getHours()).sum());
            vo.setSteps(route.getSteps().stream()
                    .map(s -> new StepVO(s.getId(), s.getOrder(), s.getOrigin().getName(), s.getDestination().getName(), s.getHours()))
                    .collect(Collectors.toList()));
        }
        return vo;
    }
}
