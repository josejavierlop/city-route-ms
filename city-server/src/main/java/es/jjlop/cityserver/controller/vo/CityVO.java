package es.jjlop.cityserver.controller.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityVO {
    private final Long id;
    private final String name;
}
