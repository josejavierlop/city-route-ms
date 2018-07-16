package es.jjlop.cityserver.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CityVO {
    private final Long id;
    private final String name;
}
