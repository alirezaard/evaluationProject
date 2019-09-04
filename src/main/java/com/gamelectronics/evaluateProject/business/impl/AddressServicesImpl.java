package com.gamelectronics.evaluateProject.business.impl;

import com.gamelectronics.evaluateProject.business.AddressServices;
import com.gamelectronics.evaluateProject.business.mapper.AddressMapper;
import com.gamelectronics.evaluateProject.dao.AddressRepository;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.AddressInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AddressOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Address;
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
public class AddressServicesImpl implements AddressServices {
    @Autowired
    private AddressRepository repo;

    @Override
    public AddressOutputDto create(AddressInputDto dto) throws DuplicateElementException {
        if (repo.existsByStateAndCityAndPostalCode(dto.getState(), dto.getCity(), dto.getPostalCode()))
            throw new DuplicateElementException();
        Address address = repo.save(AddressMapper.dtoToAddress(dto));
        return AddressMapper.addressToDto(address);
    }

    @Override
    public AddressOutputDto read(UniqueIdDto dto) throws ResourceNotFoundException {
        Address address = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        return AddressMapper.addressToDto(address);
    }

    @Override
    public AddressOutputDto update(AddressOutputDto dto) throws ResourceNotFoundException {
        Address address = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        if (!StringUtils.isEmpty(dto.getAddressDetail()))
            address.setAddressDetail(dto.getAddressDetail());
        if (!StringUtils.isEmpty(dto.getCity()))
            address.setCity(dto.getCity());
        if (!StringUtils.isEmpty(dto.getState()))
            address.setState(dto.getState());
        if (!StringUtils.isEmpty(dto.getDistrict()))
            address.setDistrict(dto.getDistrict());
        if (!StringUtils.isEmpty(dto.getPostalCode()))
            address.setPostalCode(dto.getPostalCode());
        return AddressMapper.addressToDto(repo.save(address));
    }

    @Override
    @Transactional
    public void delete(UniqueIdDto dto) throws ResourceNotFoundException {
        if (!repo.existsByUniqueId(dto.getUuid()))
            throw new ResourceNotFoundException();
        repo.deleteAllByUniqueId(dto.getUuid());
    }

    @Override
    public PageAbleDto<AddressOutputDto> findAll(PageDto dto) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (dto.isDecs())
            dir = Sort.Direction.DESC;
        Page<Address> all = repo.findAll(new PageRequest(dto.getPageNumber(), dto.getPageSize(), dir, dto.getSortBy()));
        PageAbleDto<AddressOutputDto> outputPageDto = new PageAbleDto<>();
        for (Address address : all) {
            outputPageDto.getDto().add(AddressMapper.addressToDto(address));
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

    @Override
    public Address read(String uuid) throws ResourceNotFoundException {
        return repo.findFirstByUniqueId(uuid).orElseThrow(ResourceNotFoundException::new);
    }
}
