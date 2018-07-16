package es.jjlop.calculationserver.service.impl;

import es.jjlop.calculationserver.client.CityClient;
import es.jjlop.calculationserver.contoller.vo.ResponseVO;
import es.jjlop.calculationserver.contoller.vo.RouteVO;
import es.jjlop.calculationserver.service.RouteCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteCalculatorServiceImpl implements RouteCalculatorService {


    private CityClient cityClient;

    @Autowired
    public RouteCalculatorServiceImpl(CityClient cityClient){
        this.cityClient = cityClient;
    }

    @Override
    public Optional<RouteVO> calculateLessConnectionsRoute(String origin, String destination) {
        ResponseVO<List<RouteVO>> data = cityClient.getRoutesByOrigin(origin);
        if (data.getResults().isEmpty()) {
            return Optional.empty();
        }

        return data.getResults().stream()
                .filter(r -> r.getDestination().equalsIgnoreCase(destination))
                .sorted((o1, o2) -> o1.getStepNumber() - o2.getStepNumber())
                .findFirst();
    }

    @Override
    public Optional<RouteVO> calculateLessDurationsRoute(String origin, String destination) {
        ResponseVO<List<RouteVO>> data = cityClient.getRoutesByOrigin(origin);
        if (data.getResults().isEmpty()) {
            return Optional.empty();
        }

        return data.getResults().stream()
                .filter(r -> r.getDestination().equalsIgnoreCase(destination))
                .sorted((o1, o2) ->
                { double aux = o1.getTotalDuration() - o2.getTotalDuration();
                    if (aux < 0) { return -1; }
                    else if (aux == 0) { return 0; }
                    else {return 1;}
                })
                .findFirst();
    }
}
