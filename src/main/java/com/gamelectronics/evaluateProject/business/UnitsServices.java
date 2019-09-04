package com.gamelectronics.evaluateProject.business;

import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsFullInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.UnitsOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Units;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;

public interface UnitsServices extends CrudServices<UnitsOutputDto, UnitsFullInputDto> {
    UnitsOutputDto create(UnitsInputDto dto, String owner, String tenant) throws DuplicateElementException, ResourceNotFoundException;
    Units read(String uniqueId) throws ResourceNotFoundException;
    Units save(Units units);
}
