package es.jjlop.cityserver.controller;

import es.jjlop.cityserver.controller.vo.ResponseVO;
import es.jjlop.cityserver.controller.vo.RouteVO;
import es.jjlop.cityserver.service.RouteSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
public class RouteController {

    @Autowired
    private RouteSearchService routeSearchService;

    @RequestMapping(path = "/routes/{origin}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO> find(@PathVariable(name = "origin") String origin){
        List<RouteVO> data = routeSearchService.findRoutes(origin);
        if (data.isEmpty()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseVO<>(data));
    }

    @RequestMapping(path = "/routes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO> findAll(){
        List<RouteVO> data = routeSearchService.findAll();
        if (data.isEmpty()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseVO<>(data));
    }
}
