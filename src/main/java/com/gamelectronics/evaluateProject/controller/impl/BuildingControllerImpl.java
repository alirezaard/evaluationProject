package com.gamelectronics.evaluateProject.controller.impl;

import com.gamelectronics.evaluateProject.business.BuildingServices;
import com.gamelectronics.evaluateProject.controller.BuildingController;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingControllerImpl implements BuildingController {
    @Autowired
    BuildingServices service;

    @Override
    @PutMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<BuildingOutputDto> create(@RequestBody BuildingInputDto dto) throws DuplicateElementException {
        return RestResponse.ok(service.create(dto));
    }

    @Override
    @PostMapping(value = "/read", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<BuildingOutputDto> read(@RequestBody UniqueIdDto dto) throws ResourceNotFoundException {
        return RestResponse.ok(service.read(dto));
    }

    @Override
    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<BuildingOutputDto> update(@RequestBody BuildingOutputDto dto) throws ResourceNotFoundException {
        return RestResponse.ok(service.update(dto));
    }

    @Override
    @DeleteMapping(value = "/delete", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<Void> delete(@RequestBody UniqueIdDto dto) throws ResourceNotFoundException {
        service.delete(dto);
        return RestResponse.ok();
    }

    @Override
    @PostMapping(value = "/findAll", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<PageAbleDto<BuildingOutputDto>> findAll(@RequestBody PageDto dto) {
        return RestResponse.ok(service.findAll(dto));
    }

    @Override
    @PutMapping(value = "/addUnits", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<BuildingOutputDto> addUnits(@RequestBody AddUnitInBuildingInputDto dto) throws ResourceNotFoundException, ExceedingLimitException, DuplicateElementException {
        return RestResponse.ok(service.addUnits(dto));
    }

    @Override
    @DeleteMapping(value = "/deleteUnits", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<Void> deleteUnits(@RequestBody DeleteUnitFromBuildingInputDto dto) throws ResourceNotFoundException {
        service.deleteUnits(dto);
        return RestResponse.ok();
    }

    @Override
    @PostMapping(value = "/getAllUnits", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<List<UnitsOutputDto>> getAllUnite(@RequestBody UniqueIdDto buildingUUID) throws ResourceNotFoundException {
        return RestResponse.ok(service.getAllUnite(buildingUUID));
    }
}