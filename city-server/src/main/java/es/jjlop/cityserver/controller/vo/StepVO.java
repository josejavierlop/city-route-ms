package es.jjlop.cityserver.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepVO {

    private Long id;
    private Integer order;
    private String origin;
    private String destination;
    private Double hours;

}
