package es.jjlop.cityserver.controller;

import es.jjlop.cityserver.controller.vo.CityVO;
import es.jjlop.cityserver.controller.vo.ResponseVO;
import es.jjlop.cityserver.service.CitySearchService;
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
import java.util.Optional;

@RefreshScope
@RestController
public class CityController {
    @Autowired
    private CitySearchService citySearchService;

    @RequestMapping(path = "/cities/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO> find(@PathVariable(name = "name") String name){
        Optional<CityVO> data = citySearchService.findCity(name);
        if (!data.isPresent()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseVO<>(data.get()));
    }

    @RequestMapping(path = "/cities",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO> findAll(){
        List<CityVO> data = citySearchService.findAll();
        if (data.isEmpty()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseVO<>(data));
    }
}
