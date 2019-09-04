package com.gamelectronics.evaluateProject.business;

import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;

import java.util.List;

public interface CrudServices<M, N> {
    M create(N dto) throws DuplicateElementException;
    M read(UniqueIdDto dto) throws ResourceNotFoundException;
    M update(M dto) throws ResourceNotFoundException;
    void delete(UniqueIdDto dto) throws ResourceNotFoundException;
    PageAbleDto<M> findAll(PageDto dto);
    void deleteAll(List<String> fakeUniqueId);
}