package com.gamelectronics.evaluateProject.business;

import com.gamelectronics.evaluateProject.domain.dtos.input.AccountInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AccountOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Account;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;

public interface AccountServices extends CrudServices<AccountOutputDto, AccountInputDto> {
    Account read(String uuid) throws ResourceNotFoundException;
}