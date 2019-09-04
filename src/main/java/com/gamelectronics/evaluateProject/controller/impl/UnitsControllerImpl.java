package com.gamelectronics.evaluateProject.controller.impl;

import com.gamelectronics.evaluateProject.business.UnitsServices;
import com.gamelectronics.evaluateProject.controller.UnitsController;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsCreateInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsFullInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.UnitsOutputDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import com.gamelectronics.evaluateProject.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/units")
public class UnitsControllerImpl implements UnitsController {
    @Autowired
    private UnitsServices service;

    @Override
    @PutMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<UnitsOutputDto> create(@RequestBody UnitsFullInputDto dto) throws DuplicateElementException {
        return RestResponse.ok(service.create(dto));
    }

    @Override
    @PutMapping(value = "/createWithOwnerAndTenant", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<UnitsOutputDto> create(@RequestBody UnitsCreateInputDto dto) throws ResourceNotFoundException, DuplicateElementException {
        return RestResponse.ok(service.create(dto.getUnit(), dto.getOwner(), dto.getTenant()));
    }

    @Override
    @PostMapping(value = "/read", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<UnitsOutputDto> read(@RequestBody UniqueIdDto dto) throws ResourceNotFoundException {
        return RestResponse.ok(service.read(dto));
    }

    @Override
    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<UnitsOutputDto> update(@RequestBody UnitsOutputDto dto) throws ResourceNotFoundException {
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
    public RestResponse<PageAbleDto<UnitsOutputDto>> findAll(@RequestBody PageDto dto) {
        return RestResponse.ok(service.findAll(dto));
    }
}
