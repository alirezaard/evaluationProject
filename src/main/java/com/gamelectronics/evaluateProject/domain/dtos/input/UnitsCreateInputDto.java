package com.gamelectronics.evaluateProject.domain.dtos.input;

import lombok.Data;

@Data
public class UnitsCreateInputDto {
    private UnitsInputDto unit;
    private String owner;
    private String tenant;
}
