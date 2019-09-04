package com.gamelectronics.evaluateProject.business.mapper;

import com.gamelectronics.evaluateProject.domain.dtos.input.PersonInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.PersonOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Person;

public class PersonMapper {
    public static Person dtoToPerson(PersonInputDto dto) {
        Person person = new Person();
        person.setPhone(dto.getPhone());
        person.setMobile(dto.getMobile());
        person.setLastName(dto.getLastName());
        person.setFirstName(dto.getFirstName());
        return person;
    }

    public static PersonOutputDto personToDto(Person person) {
        PersonOutputDto dto = new PersonOutputDto();
        dto.setUuid(person.getUniqueId());
        dto.setPhone(person.getPhone());
        dto.setMobile(person.getMobile());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        return dto;
    }
}
