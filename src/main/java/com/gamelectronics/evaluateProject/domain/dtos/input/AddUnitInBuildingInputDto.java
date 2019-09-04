package com.gamelectronics.evaluateProject.domain.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUnitInBuildingInputDto {
    private List<UnitsFullInputDto> units = new ArrayList<>();
    private String buildingUUid;
}
