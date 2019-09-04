package com.gamelectronics.evaluateProject.business.mapper;

import com.gamelectronics.evaluateProject.domain.dtos.input.AddressInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AddressOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Address;

public class AddressMapper {
    public static AddressOutputDto addressToDto(Address address) {
        AddressOutputDto dto = new AddressOutputDto();
        dto.setUuid(address.getUniqueId());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        dto.setDistrict(address.getDistrict());
        dto.setCity(address.getCity());
        dto.setAddressDetail(address.getAddressDetail());
        return dto;
    }

    public static Address dtoToAddress(AddressInputDto dto) {
        Address address = new Address();
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setDistrict(dto.getDistrict());
        address.setCity(dto.getCity());
        address.setAddressDetail(dto.getAddressDetail());
        return address;
    }
}
