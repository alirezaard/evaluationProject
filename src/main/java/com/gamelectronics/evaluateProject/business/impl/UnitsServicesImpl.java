package com.gamelectronics.evaluateProject.business.impl;

import com.gamelectronics.evaluateProject.business.PersonServices;
import com.gamelectronics.evaluateProject.business.UnitsServices;
import com.gamelectronics.evaluateProject.business.mapper.UnitsMapper;
import com.gamelectronics.evaluateProject.dao.UnitsRepository;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsFullInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.UnitsOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Person;
import com.gamelectronics.evaluateProject.domain.entities.Units;
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
public class UnitsServicesImpl implements UnitsServices {
    @Autowired
    private UnitsRepository repo;
    @Autowired
    private PersonServices personServices;

    @Override
    public UnitsOutputDto create(UnitsFullInputDto dto) throws DuplicateElementException {
        if (repo.existsByBuilding_UniqueIdAndLocation(dto.getBuildingUUID(), dto.getLocation()))
            throw new DuplicateElementException();
        Units units = repo.save(UnitsMapper.dtoToUnits(dto));
        return UnitsMapper.unitsToDto(units);
    }

    @Override
    public UnitsOutputDto create(UnitsInputDto dto, String ownerUUID, String tenantUUID) throws DuplicateElementException, ResourceNotFoundException {
        if (repo.existsByBuilding_UniqueIdAndLocation(dto.getBuildingUUID(), dto.getLocation()))
            throw new DuplicateElementException();
        Person tenant = personServices.read(tenantUUID);
        Person owner = personServices.read(ownerUUID);
        Units template = repo.save(UnitsMapper.dtoToUnits(dto));
        template.setTenant(tenant);
        template.setOwner(owner);
        Units units = repo.save(template);
        return UnitsMapper.unitsToDto(units);
    }

    @Override
    public Units save(Units units) {
        return repo.save(units);
    }

    @Override
    public UnitsOutputDto read(UniqueIdDto dto) throws ResourceNotFoundException {
        Units units = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        return UnitsMapper.unitsToDto(units);

    }

    @Override
    public UnitsOutputDto update(UnitsOutputDto dto) throws ResourceNotFoundException {
        Units units = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        if (!StringUtils.isEmpty(dto.getArea())) units.setArea(dto.getArea());
        if (!StringUtils.isEmpty(dto.getBalance())) units.setBalance(dto.getBalance());
        if (!StringUtils.isEmpty(dto.getChargeAmount())) units.setChargeAmount(dto.getChargeAmount());
        if (dto.getNumOfPeople() == 0) units.setNumOfPeople(dto.getNumOfPeople());
        if (!StringUtils.isEmpty(dto.getOwner()) && !StringUtils.isEmpty(dto.getOwner().getUuid()))
            units.setOwner(personServices.read(dto.getOwner().getUuid()));
        if (!StringUtils.isEmpty(dto.getTenant()) && !StringUtils.isEmpty(dto.getTenant().getUuid()))
            units.setTenant(personServices.read(dto.getTenant().getUuid()));
        if (!StringUtils.isEmpty(dto.getLocation())) units.setLocation(dto.getLocation());
        return UnitsMapper.unitsToDto(repo.save(units));
    }

    @Override
    @Transactional
    public void delete(UniqueIdDto dto) throws ResourceNotFoundException {
        if (!repo.existsByUniqueId(dto.getUuid()))
            throw new ResourceNotFoundException();
        repo.deleteAllByUniqueId(dto.getUuid());
    }

    @Override
    public PageAbleDto<UnitsOutputDto> findAll(PageDto dto) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (dto.isDecs())
            dir = Sort.Direction.DESC;
        Page<Units> all = repo.findAll(new PageRequest(dto.getPageNumber(), dto.getPageSize(), dir, dto.getSortBy()));
        PageAbleDto<UnitsOutputDto> outputPageDto = new PageAbleDto<>();
        for (Units units : all) {
            outputPageDto.getDto().add(UnitsMapper.unitsToDto(units));
        }
        outputPageDto.getPage().setPageNumber(all.getPageable().getPageNumber());
        outputPageDto.getPage().setPageSize(all.getPageable().getPageSize());
        outputPageDto.getPage().setTotalElement(all.getTotalElements());
        return outputPageDto;
    }

    @Override
    public Units read(String uniqueId) throws ResourceNotFoundException {
        return repo.findFirstByUniqueId(uniqueId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteAll(List<String> fakeUniqueId) {
        repo.deleteAllByUniqueIdIn(fakeUniqueId);
    }
}
