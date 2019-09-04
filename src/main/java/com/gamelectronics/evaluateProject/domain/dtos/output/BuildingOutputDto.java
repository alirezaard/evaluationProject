package com.gamelectronics.evaluateProject.domain.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingOutputDto {
    private String uuid;
    private Long balance;
    private int unitsNumber;
    private PersonOutputDto manager;
    private AccountOutputDto account;
    private AddressOutputDto address;
    private List<UnitsOutputDto> units = new ArrayList<>();
}
