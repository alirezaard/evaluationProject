package com.gamelectronics.evaluateProject.controller;

import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import com.gamelectronics.evaluateProject.utils.RestResponse;

public interface CrudController<Out, In> {
    RestResponse<Out> create(In dto) throws DuplicateElementException;
    RestResponse<Out> read(UniqueIdDto dto) throws ResourceNotFoundException;
    RestResponse<Out> update(Out dto) throws ResourceNotFoundException;
    RestResponse<Void> delete(UniqueIdDto dto) throws ResourceNotFoundException;
    RestResponse<PageAbleDto<Out>> findAll(PageDto dto);
}