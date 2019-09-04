package com.gamelectronics.evaluateProject.controller;

import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.AddUnitInBuildingInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.BuildingInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.DeleteUnitFromBuildingInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.BuildingOutputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.UnitsOutputDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ExceedingLimitException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import com.gamelectronics.evaluateProject.utils.RestResponse;

import java.util.List;

public interface BuildingController extends CrudController<BuildingOutputDto, BuildingInputDto> {
    RestResponse<BuildingOutputDto> addUnits(AddUnitInBuildingInputDto dto) throws ResourceNotFoundException, ExceedingLimitException, DuplicateElementException;
    RestResponse<Void> deleteUnits(DeleteUnitFromBuildingInputDto dto) throws ResourceNotFoundException;
    RestResponse<List<UnitsOutputDto>> getAllUnite(UniqueIdDto buildingUUID) throws ResourceNotFoundException;
}