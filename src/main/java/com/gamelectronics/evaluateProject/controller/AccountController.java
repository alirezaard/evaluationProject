package com.gamelectronics.evaluateProject.controller;

import com.gamelectronics.evaluateProject.domain.dtos.input.AccountInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AccountOutputDto;

public interface AccountController extends CrudController<AccountOutputDto, AccountInputDto> {
}