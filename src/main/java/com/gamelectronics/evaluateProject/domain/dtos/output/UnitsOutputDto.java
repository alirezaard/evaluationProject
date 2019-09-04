package com.gamelectronics.evaluateProject.domain.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitsOutputDto {
    private String uuid;
    private PersonOutputDto owner;
    private PersonOutputDto tenant;
    private String location;
    private Long balance;
    private Long area;
    private int numOfPeople;
    private Long chargeAmount;
}
