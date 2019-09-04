package com.gamelectronics.evaluateProject.domain.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingInputDto {
    private Long balance;
    private int unitsNumber;
    private PersonInputDto manager;
    private AccountInputDto account;
    private AddressInputDto address;
}
