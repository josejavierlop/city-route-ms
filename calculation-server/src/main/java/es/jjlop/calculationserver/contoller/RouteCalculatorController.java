package es.jjlop.calculationserver.contoller;

import es.jjlop.calculationserver.contoller.vo.ResponseVO;
import es.jjlop.calculationserver.contoller.vo.RouteVO;
import es.jjlop.calculationserver.service.RouteCalculatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RefreshScope
@RestController
@Api(value="Route Calculation", description = "Calculates best routes from one city to another")
public class RouteCalculatorController {

    @Autowired
    private RouteCalculatorService routeCalculatorService;

    @RequestMapping(path = "/calculations/connections/{origin}/{destination}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Finds the route between origin and destination with less connections", response = ResponseVO.class )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successful response."),
            @ApiResponse(code = 404, message = "Either Origin, Destination or Route not found."),
            @ApiResponse(code = 405, message = "Action verb is not allowed.")
    })
    public ResponseEntity<ResponseVO> findLessConnections(@PathVariable(name = "origin") String origin,
                                           @PathVariable(name = "destination") String destination){

        Optional<RouteVO> route = routeCalculatorService.calculateLessConnectionsRoute(origin, destination);

        if (!route.isPresent()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new ResponseVO<>(route.get()));

    }

    @RequestMapping(path = "/calculations/duration/{origin}/{destination}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Finds the route between origin and destination with less duration", response = ResponseVO.class )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successful response."),
            @ApiResponse(code = 404, message = "Either Origin, Destination or Route not found."),
            @ApiResponse(code = 405, message = "Action verb is not allowed.")
    })
    public ResponseEntity<ResponseVO> findLessTime(@PathVariable(name = "origin") String origin,
                                           @PathVariable(name = "destination") String destination){
        Optional<RouteVO> route = routeCalculatorService.calculateLessDurationsRoute(origin, destination);

        if (!route.isPresent()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseVO<>(route.get()));
    }
}
