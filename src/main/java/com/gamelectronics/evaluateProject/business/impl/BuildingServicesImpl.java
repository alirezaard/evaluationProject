package com.gamelectronics.evaluateProject.business.impl;

import com.gamelectronics.evaluateProject.business.*;
import com.gamelectronics.evaluateProject.business.mapper.BuildingMapper;
import com.gamelectronics.evaluateProject.business.mapper.UnitsMapper;
import com.gamelectronics.evaluateProject.dao.BuildingRepository;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.AddUnitInBuildingInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.BuildingInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.DeleteUnitFromBuildingInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsFullInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.BuildingOutputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.UnitsOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.*;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ExceedingLimitException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServicesImpl implements BuildingServices {
    @Autowired
    private BuildingRepository repo;
    @Autowired
    private UnitsServices unitsServices;
    @Autowired
    private AccountServices accountServices;
    @Autowired
    private AddressServices addressServices;
    @Autowired
    private PersonServices personServices;


    @Override
    public BuildingOutputDto addUnits(AddUnitInBuildingInputDto dto) throws ResourceNotFoundException, ExceedingLimitException, DuplicateElementException {
        Building building = repo.findFirstByUniqueId(dto.getBuildingUUid()).orElseThrow(ResourceNotFoundException::new);
        if (building.getUnitsNumber() < building.getUnits().size() + dto.getUnits().size())
            throw new ExceedingLimitException();
        for (UnitsFullInputDto unitDto : dto.getUnits()) {
            unitDto.setBuildingUUID(building.getUniqueId());
            Units unit = UnitsMapper.dtoToUnits(unitDto);
            Units savedUnits = unitsServices.save(unit);
            building.getUnits().add(savedUnits);
        }
        Building save = repo.save(building);
        return BuildingMapper.buildingToDto(save);
    }

    @Override
    public void deleteUnits(DeleteUnitFromBuildingInputDto dto) throws ResourceNotFoundException {
        ArrayList<UnitsOutputDto> response = new ArrayList<>();
        Building building = repo.findFirstByUniqueId(dto.getBuildingUUid()).orElseThrow(ResourceNotFoundException::new);
        for (String uniqueId : dto.getUnits()) {

        }
        List<Units> filterUnits = building.getUnits().stream()
                .filter(each -> !dto.getUnits().contains(each.getUniqueId()))
                .collect(Collectors.toList());
        building.setUnits(filterUnits);
        repo.save(building);
        //unitsServices.deleteAll(dto.getUnits());
    }

    @Override
    public List<UnitsOutputDto> getAllUnite(UniqueIdDto dto) throws ResourceNotFoundException {
        ArrayList<UnitsOutputDto> response = new ArrayList<>();
        Building building = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        for (Units unit : building.getUnits()) {
            response.add(UnitsMapper.unitsToDto(unit));
        }
        return response;
    }

    @Override
    public BuildingOutputDto create(BuildingInputDto dto) throws DuplicateElementException {
        if (repo.existsByAccount_AccountNumberAndManager_Mobile(dto.getAccount().getAccount(), dto.getManager().getMobile()))
            throw new DuplicateElementException();
        Building building = repo.save(BuildingMapper.dtoToBuilding(dto));
        return BuildingMapper.buildingToDto(building);
    }

    @Override
    public BuildingOutputDto read(UniqueIdDto dto) throws ResourceNotFoundException {
        Building building = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        return BuildingMapper.buildingToDto(building);

    }

    @Override
    public BuildingOutputDto update(BuildingOutputDto dto) throws ResourceNotFoundException {
        Building building = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        if (!StringUtils.isEmpty(dto.getBalance()))
            building.setBalance(dto.getBalance());
        if (!StringUtils.isEmpty(dto.getManager())) {
            Person manager = personServices.read(dto.getManager().getUuid());
            building.setManager(manager);
        }
        if (!StringUtils.isEmpty(dto.getAccount())) {
            Account account = accountServices.read(dto.getAccount().getUuid());
            building.setAccount(account);
        }
        if (!StringUtils.isEmpty(dto.getAddress())) {
            Address address = addressServices.read(dto.getAddress().getUuid());
            building.setAddress(address);
        }


        Building update = repo.save(building);
        return BuildingMapper.buildingToDto(update);
    }

    @Override
    @Transactional
    public void delete(UniqueIdDto dto) throws ResourceNotFoundException {
        if (!repo.existsByUniqueId(dto.getUuid()))
            throw new ResourceNotFoundException();
        repo.deleteAllByUniqueId(dto.getUuid());
    }

    @Override
    public PageAbleDto<BuildingOutputDto> findAll(PageDto dto) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (dto.isDecs())
            dir = Sort.Direction.DESC;
        Page<Building> all = repo.findAll(new PageRequest(dto.getPageNumber(), dto.getPageSize(), dir, dto.getSortBy()));
        PageAbleDto<BuildingOutputDto> outputPageDto = new PageAbleDto<>();
        for (Building building : all) {
            outputPageDto.getDto().add(BuildingMapper.buildingToDto(building));
        }
        outputPageDto.getPage().setPageNumber(all.getPageable().getPageNumber());
        outputPageDto.getPage().setPageSize(all.getPageable().getPageSize());
        outputPageDto.getPage().setTotalElement(all.getTotalElements());
        return outputPageDto;
    }

    @Override
    @Transactional
    public void deleteAll(List<String> fakeUniqueId) {
        repo.deleteAllByUniqueIdIn(fakeUniqueId);
    }
}
