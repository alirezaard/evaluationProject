package com.gamelectronics.evaluateProject.business.mapper;

import com.gamelectronics.evaluateProject.domain.dtos.input.BuildingInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.BuildingOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Building;
import com.gamelectronics.evaluateProject.domain.entities.Units;

public class BuildingMapper {
    public static Building dtoToBuilding(BuildingInputDto dto) {
        Building building = new Building();
        building.setUnitsNumber(dto.getUnitsNumber());
        building.setBalance(dto.getBalance());
        building.setManager(PersonMapper.dtoToPerson(dto.getManager()));
        building.setAddress(AddressMapper.dtoToAddress(dto.getAddress()));
        building.setAccount(AccountMapper.dtoToAccount(dto.getAccount()));
        return building;
    }

    public static BuildingOutputDto buildingToDto(Building building) {
        BuildingOutputDto dto = new BuildingOutputDto();
        dto.setAccount(AccountMapper.accountToDto(building.getAccount()));
        dto.setAddress(AddressMapper.addressToDto(building.getAddress()));
        dto.setManager(PersonMapper.personToDto(building.getManager()));
        dto.setBalance(building.getBalance());
        dto.setUnitsNumber(building.getUnitsNumber());
        dto.setUuid(building.getUniqueId());
        for (Units unit : building.getUnits()) {
            dto.getUnits().add(UnitsMapper.unitsToDto(unit));
        }
        return dto;
    }
}
