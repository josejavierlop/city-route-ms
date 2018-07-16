package es.jjlop.cityserver.controller;

import es.jjlop.cityserver.controller.vo.CityVO;
import es.jjlop.cityserver.controller.vo.ResponseVO;
import es.jjlop.cityserver.service.CitySearchService;
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

import java.util.List;
import java.util.Optional;

@RefreshScope
@RestController
@Api(value="City Search API", description = "Access to information about cities")
public class CityController {
    @Autowired
    private CitySearchService citySearchService;

    @RequestMapping(path = "/cities/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Finds the requested City", response = ResponseVO.class )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successful response."),
            @ApiResponse(code = 404, message = "City not found."),
            @ApiResponse(code = 405, message = "Action verb is not allowed.")
    })
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
    @ApiOperation(value = "Returns the list of Cities", response = ResponseVO.class )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successful response."),
            @ApiResponse(code = 404, message = "City not found."),
            @ApiResponse(code = 405, message = "Action verb is not allowed.")
    })
    public ResponseEntity<ResponseVO> findAll(){
        List<CityVO> data = citySearchService.findAll();
        if (data.isEmpty()) {
            return new ResponseEntity<ResponseVO>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseVO<>(data));
    }
}
