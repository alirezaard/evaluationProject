package com.gamelectronics.evaluateProject.controller;

import com.gamelectronics.evaluateProject.domain.dtos.input.PersonInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.PersonOutputDto;

public interface PersonController extends CrudController<PersonOutputDto, PersonInputDto> {
}
