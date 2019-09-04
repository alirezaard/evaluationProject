package com.gamelectronics.evaluateProject.business;

import com.gamelectronics.evaluateProject.domain.dtos.input.AddressInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AddressOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Address;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;

public interface AddressServices extends CrudServices<AddressOutputDto, AddressInputDto> {
    Address read(String uuid) throws ResourceNotFoundException;
}
