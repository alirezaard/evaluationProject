package com.gamelectronics.evaluateProject.controller.impl;

import com.gamelectronics.evaluateProject.business.AccountServices;
import com.gamelectronics.evaluateProject.controller.AccountController;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.AccountInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AccountOutputDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import com.gamelectronics.evaluateProject.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountControllerImpl implements AccountController {
    @Autowired
    private AccountServices service;

    @Override
    @PutMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<AccountOutputDto> create(@RequestBody AccountInputDto dto) throws DuplicateElementException {
        return RestResponse.ok(service.create(dto));
    }

    @Override
    @PostMapping(value = "/read", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<AccountOutputDto> read(@RequestBody UniqueIdDto dto) throws ResourceNotFoundException {
        return RestResponse.ok(service.read(dto));
    }

    @Override
    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<AccountOutputDto> update(@RequestBody AccountOutputDto dto) throws ResourceNotFoundException {
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
    public RestResponse<PageAbleDto<AccountOutputDto>> findAll(@RequestBody PageDto dto) {
        return RestResponse.ok(service.findAll(dto));
    }
}
