package es.jjlop.cityserver.controller.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityVO {
    final String name;
    final String destinationCity;

}
