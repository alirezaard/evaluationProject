package com.gamelectronics.evaluateProject.controller;

import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsCreateInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsFullInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.UnitsOutputDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import com.gamelectronics.evaluateProject.utils.RestResponse;

public interface UnitsController extends CrudController<UnitsOutputDto, UnitsFullInputDto> {
    RestResponse<UnitsOutputDto> create(UnitsCreateInputDto dto) throws ResourceNotFoundException, DuplicateElementException;
}
