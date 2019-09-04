package com.gamelectronics.evaluateProject.business.impl;

import com.gamelectronics.evaluateProject.business.PersonServices;
import com.gamelectronics.evaluateProject.business.mapper.PersonMapper;
import com.gamelectronics.evaluateProject.dao.PersonRepository;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.PersonInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.PersonOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Person;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PersonServicesImpl implements PersonServices {
    @Autowired
    private PersonRepository repo;

    @Override
    public PersonOutputDto create(PersonInputDto dto) throws DuplicateElementException {
        if (repo.existsByFirstNameAndLastNameAndMobile(dto.getFirstName(), dto.getLastName(), dto.getMobile()))
            throw new DuplicateElementException();
        Person person = repo.save(PersonMapper.dtoToPerson(dto));
        return PersonMapper.personToDto(person);
    }

    @Override
    public PersonOutputDto read(UniqueIdDto dto) throws ResourceNotFoundException {
        Person person = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        return PersonMapper.personToDto(person);
    }

    @Override
    public PersonOutputDto update(PersonOutputDto dto) throws ResourceNotFoundException {
        Person person = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        if (!StringUtils.isEmpty(dto.getMobile()))
            person.setMobile(dto.getMobile());
        if (!StringUtils.isEmpty(dto.getFirstName()))
            person.setFirstName(dto.getFirstName());
        if (!StringUtils.isEmpty(dto.getLastName()))
            person.setLastName(dto.getLastName());
        if (!StringUtils.isEmpty(dto.getPhone()))
            person.setPhone(dto.getPhone());
        return PersonMapper.personToDto(repo.save(person));
    }

    @Override
    @Transactional
    public void delete(UniqueIdDto dto) throws ResourceNotFoundException {
        if (!repo.existsByUniqueId(dto.getUuid()))
            throw new ResourceNotFoundException();
        repo.deleteAllByUniqueId(dto.getUuid());
    }

    @Override
    public PageAbleDto<PersonOutputDto> findAll(PageDto dto) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (dto.isDecs())
            dir = Sort.Direction.DESC;
        Page<Person> all = repo.findAll(new PageRequest(dto.getPageNumber(), dto.getPageSize(), dir, dto.getSortBy()));
        PageAbleDto<PersonOutputDto> outputPageDto = new PageAbleDto<>();
        for (Person person : all) {
            outputPageDto.getDto().add(PersonMapper.personToDto(person));
        }
        outputPageDto.getPage().setPageNumber(all.getPageable().getPageNumber());
        outputPageDto.getPage().setPageSize(all.getPageable().getPageSize());
        outputPageDto.getPage().setTotalElement(all.getTotalElements());
        return outputPageDto;
    }

    @Override
    public Person read(String uniqueId) throws ResourceNotFoundException {
        return repo.findFirstByUniqueId(uniqueId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteAll(List<String> fakeUniqueId) {
        repo.deleteAllByUniqueIdIn(fakeUniqueId);
    }
}
