package es.jjlop.calculationserver.contoller;

import es.jjlop.calculationserver.client.CityClient;
import es.jjlop.calculationserver.contoller.vo.ResponseVO;
import es.jjlop.calculationserver.contoller.vo.RouteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RefreshScope
@RestController
public class RouteCalculatorController {

    @Autowired
    private CityClient cityClient;

    @RequestMapping(path = "/calculations/connections/{origin}/{destination}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO> findLessConnections(@PathVariable(name = "origin") String origin,
                                           @PathVariable(name = "destination") String destination){
        ResponseVO<List<RouteVO>> data = cityClient.getRoutesByOrigin(origin);
        if (data.getResults().isEmpty()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }
        Optional<RouteVO> route = data.getResults().stream()
                .filter(r -> r.getDestination().equalsIgnoreCase(destination))
                .sorted((o1, o2) -> o1.getStepNumber() - o2.getStepNumber())
                .findFirst();
        if (!route.isPresent()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseVO<>(route.get()));

    }

    @RequestMapping(path = "/calculations/duration/{origin}/{destination}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO> findLessTime(@PathVariable(name = "origin") String origin,
                                           @PathVariable(name = "destination") String destination){
        ResponseVO<List<RouteVO>> data = cityClient.getRoutesByOrigin(origin);
        if (data.getResults().isEmpty()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }
        Optional<RouteVO> route = data.getResults().stream()
                .filter(r -> r.getDestination().equalsIgnoreCase(destination))
                .sorted((o1, o2) -> (int) (o1.getTotalDuration() - o2.getTotalDuration()))
                .findFirst();
        if (!route.isPresent()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseVO<>(route.get()));
    }
}
