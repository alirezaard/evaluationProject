package com.gamelectronics.evaluateProject.controller.impl;

import com.gamelectronics.evaluateProject.business.AddressServices;
import com.gamelectronics.evaluateProject.controller.CrudController;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.AddressInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AddressOutputDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import com.gamelectronics.evaluateProject.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressControllerImpl implements CrudController<AddressOutputDto, AddressInputDto> {
    @Autowired
    private AddressServices service;

    @Override
    @PutMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<AddressOutputDto> create(@RequestBody AddressInputDto dto) throws DuplicateElementException {
        return RestResponse.ok(service.create(dto));
    }

    @Override
    @PostMapping(value = "/read", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<AddressOutputDto> read(@RequestBody UniqueIdDto dto) throws ResourceNotFoundException {
        return RestResponse.ok(service.read(dto));
    }

    @Override
    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<AddressOutputDto> update(@RequestBody AddressOutputDto dto) throws ResourceNotFoundException {
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
    public RestResponse<PageAbleDto<AddressOutputDto>> findAll(@RequestBody PageDto dto) {
        return RestResponse.ok(service.findAll(dto));
    }
}
