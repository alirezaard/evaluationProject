package com.gamelectronics.evaluateProject.domain.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitsInputDto {
    private String location;
    private Long balance;
    private Long area;
    private int numOfPeople;
    private Long chargeAmount;
    private String buildingUUID;
}
