package es.jjlop.calculationserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.jjlop.calculationserver.contoller.vo.ResponseVO;
import es.jjlop.calculationserver.contoller.vo.RouteVO;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class JsonResourceLoader {

    public static ResponseVO loadFromJSONResource(String resourceName, Class accessor) throws IOException {
        String text = new Scanner(accessor.getClassLoader().getResourceAsStream(resourceName), "UTF-8").useDelimiter("\\A").next();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseVO<List<RouteVO>> response = (ResponseVO<List<RouteVO>>)objectMapper.readValue(text, ResponseVO.class);
        return response;
    }
}
