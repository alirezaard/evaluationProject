package com.gamelectronics.evaluateProject.business;

import com.gamelectronics.evaluateProject.domain.dtos.input.PersonInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.PersonOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Person;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;

public interface PersonServices extends CrudServices<PersonOutputDto, PersonInputDto> {
    Person read(String uniqueId) throws ResourceNotFoundException;
}
