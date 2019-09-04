package com.gamelectronics.evaluateProject.business.mapper;

import com.gamelectronics.evaluateProject.domain.dtos.input.PersonInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsFullInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.UnitsOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Person;
import com.gamelectronics.evaluateProject.domain.entities.Units;

public class UnitsMapper {
    public static Units dtoToUnits(UnitsFullInputDto dto) {
        Units units = new Units();
        units.setTenant(PersonMapper.dtoToPerson(dto.getTenant()));
        units.setOwner(PersonMapper.dtoToPerson(dto.getOwner()));
        units.setNumOfPeople(dto.getNumOfPeople());
        units.setLocation(dto.getLocation());
        units.setChargeAmount(dto.getChargeAmount());
        units.setBalance(dto.getBalance());
        units.setArea(dto.getArea());
        return units;
    }

    public static Units dtoToUnits(UnitsInputDto dto) {
        Units units = new Units();
        units.setNumOfPeople(dto.getNumOfPeople());
        units.setLocation(dto.getLocation());
        units.setChargeAmount(dto.getChargeAmount());
        units.setBalance(dto.getBalance());
        units.setArea(dto.getArea());
        return units;
    }

    public static UnitsOutputDto unitsToDto(Units units) {
        UnitsOutputDto dto = new UnitsOutputDto();
        dto.setArea(units.getArea());
        dto.setBalance(units.getBalance());
        dto.setUuid(units.getUniqueId());
        dto.setTenant(PersonMapper.personToDto(units.getTenant()));
        dto.setOwner(PersonMapper.personToDto(units.getOwner()));
        dto.setNumOfPeople(units.getNumOfPeople());
        dto.setLocation(units.getLocation());
        dto.setChargeAmount(units.getChargeAmount());
        return dto;
    }
}