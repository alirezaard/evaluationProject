package com.gamelectronics.evaluateProject.business;

import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.AddUnitInBuildingInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.BuildingInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.DeleteUnitFromBuildingInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.BuildingOutputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.UnitsOutputDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ExceedingLimitException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;

import java.util.List;

public interface BuildingServices extends CrudServices<BuildingOutputDto, BuildingInputDto> {
    BuildingOutputDto addUnits(AddUnitInBuildingInputDto dto) throws ResourceNotFoundException, ExceedingLimitException, DuplicateElementException;
    void deleteUnits(DeleteUnitFromBuildingInputDto dto) throws ResourceNotFoundException;
    List<UnitsOutputDto> getAllUnite(UniqueIdDto buildingUUID) throws ResourceNotFoundException;
}