package com.gamelectronics.evaluateProject.controller.impl;

import com.gamelectronics.evaluateProject.business.PersonServices;
import com.gamelectronics.evaluateProject.controller.PersonController;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.PersonInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.PersonOutputDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import com.gamelectronics.evaluateProject.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonControllerImpl implements PersonController {
    @Autowired
    private PersonServices service;

    @Override
    @PutMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<PersonOutputDto> create(@RequestBody PersonInputDto dto) throws DuplicateElementException {
        return RestResponse.ok(service.create(dto));
    }

    @Override
    @PostMapping(value = "/read", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<PersonOutputDto> read(@RequestBody UniqueIdDto dto) throws ResourceNotFoundException {
        return RestResponse.ok(service.read(dto));
    }

    @Override
    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<PersonOutputDto> update(@RequestBody PersonOutputDto dto) throws ResourceNotFoundException {
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
    public RestResponse<PageAbleDto<PersonOutputDto>> findAll(@RequestBody PageDto dto) {
        return RestResponse.ok(service.findAll(dto));
    }
}
