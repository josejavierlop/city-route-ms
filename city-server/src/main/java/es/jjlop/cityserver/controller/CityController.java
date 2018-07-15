package es.jjlop.cityserver.controller;

import es.jjlop.cityserver.controller.vo.CityVO;
import es.jjlop.cityserver.controller.vo.ResponseVO;
import es.jjlop.cityserver.service.CitySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
public class CityController {
    @Autowired
    private CitySearchService citySearchService;

    @RequestMapping(path = "/cities/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO> find(@PathVariable(name = "name") String name){
        return ResponseEntity.ok(new ResponseVO<>(citySearchService.findCity(name)));
    }
}
