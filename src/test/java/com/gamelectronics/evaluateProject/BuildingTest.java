package com.gamelectronics.evaluateProject;

import com.gamelectronics.evaluateProject.business.*;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.*;
import com.gamelectronics.evaluateProject.domain.dtos.output.*;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ExceedingLimitException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BuildingTest implements CrudTest {
    static private List<String> fakeUniqueId = new ArrayList<>();
    @Autowired
    private BuildingServices service;
    @Autowired
    private UnitsServices unitsServices;
    @Autowired
    private AccountServices accountServices;
    @Autowired
    private AddressServices addressServices;
    @Autowired
    private PersonServices personServices;


    @Override
    @Test
    public void createTest() throws DuplicateElementException {
        AccountInputDto account = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AddressInputDto address = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        PersonInputDto manager = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        BuildingInputDto inputDto = new BuildingInputDto(1_000_000L, 4, manager, account, address);
        BuildingOutputDto outputDto = service.create(inputDto);

        Assert.assertNotNull(outputDto);
        fakeUniqueId.add(outputDto.getUuid());
    }

    @Override
    @Test(expected = DuplicateElementException.class)
    public void duplicateInsertTest() throws DuplicateElementException {
        AccountInputDto account = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AddressInputDto address = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        PersonInputDto manager = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        BuildingInputDto inputDto = new BuildingInputDto(1_000_000L, 4, manager, account, address);
        BuildingOutputDto o1 = service.create(inputDto);
        fakeUniqueId.add(o1.getUuid());
        BuildingOutputDto o2 = service.create(inputDto);
    }

    @Override
    @Test
    public void getAllTest() throws DuplicateElementException {
        AccountInputDto account = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AddressInputDto address = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        PersonInputDto manager = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        BuildingInputDto inputDto = new BuildingInputDto(1_000_000L, 4, manager, account, address);
        BuildingOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        PageAbleDto<BuildingOutputDto> all = service.findAll(new PageDto(0, 10, 0L, "id", true));
        Assert.assertEquals(all.getDto().get(0).getAccount().getAccount(), inputDto.getAccount().getAccount());
        Assert.assertEquals(all.getDto().get(0).getAccount().getCard(), inputDto.getAccount().getCard());
        Assert.assertEquals(all.getDto().get(0).getAddress().getPostalCode(), inputDto.getAddress().getPostalCode());
        Assert.assertEquals(all.getDto().get(0).getManager().getMobile(), inputDto.getManager().getMobile());
    }

    @Override
    @Test
    public void validReadTest() throws ResourceNotFoundException, DuplicateElementException {
        AccountInputDto account = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AddressInputDto address = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        PersonInputDto manager = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        BuildingInputDto inputDto = new BuildingInputDto(1_000_000L, 4, manager, account, address);
        BuildingOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());

        BuildingOutputDto read = service.read(new UniqueIdDto(outputDto.getUuid()));
        Assert.assertEquals(read.getAccount().getCard(), inputDto.getAccount().getCard());
        Assert.assertEquals(read.getManager().getMobile(), inputDto.getManager().getMobile());
        Assert.assertEquals(read.getAddress().getPostalCode(), inputDto.getAddress().getPostalCode());
    }

    @Override
    @Test(expected = ResourceNotFoundException.class)
    public void notValidReadTest() throws ResourceNotFoundException {
        service.read(new UniqueIdDto(UUID.randomUUID().toString()));
    }

    @Override
    @Test
    public void updateTest() throws ResourceNotFoundException, DuplicateElementException {
        AccountInputDto account = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AddressInputDto address = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        PersonInputDto manager = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        BuildingInputDto inputDto = new BuildingInputDto(1_000_000L, 4, manager, account, address);
        BuildingOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());

        AccountInputDto updateAccountDto = new AccountInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AccountOutputDto updateAccount = accountServices.create(updateAccountDto);
        AddressInputDto updateAddressDto = new AddressInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AddressOutputDto updateAddress = addressServices.create(updateAddressDto);
        PersonInputDto updateManagerDto = new PersonInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        PersonOutputDto updateManager = personServices.create(updateManagerDto);

        BuildingOutputDto update = service.update(new BuildingOutputDto(outputDto.getUuid(), 5_000L, outputDto.getUnitsNumber(), updateManager, updateAccount, updateAddress, null));
        Assert.assertEquals(update.getUuid(), outputDto.getUuid());
        Assert.assertNotEquals(update.getAddress().getPostalCode(), outputDto.getAddress().getPostalCode());
        Assert.assertEquals(update.getAddress(), updateAddress);
        Assert.assertNotEquals(update.getManager().getMobile(), outputDto.getManager().getMobile());
        Assert.assertEquals(update.getManager(), updateManager);
        Assert.assertNotEquals(update.getAccount().getCard(), outputDto.getAccount().getCard());
        Assert.assertEquals(update.getAccount(), updateAccount);
    }

    @Override
    @Test(expected = ResourceNotFoundException.class)
    public void deleteTest() throws ResourceNotFoundException, DuplicateElementException {
        AccountInputDto account = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AddressInputDto address = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        PersonInputDto manager = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        BuildingInputDto inputDto = new BuildingInputDto(1_000_000L, 4, manager, account, address);
        BuildingOutputDto outputDto = service.create(inputDto);
        try {
            service.delete(new UniqueIdDto(outputDto.getUuid()));
        } catch (ResourceNotFoundException e) {
            fakeUniqueId.add(outputDto.getUuid());
        }
        service.read(new UniqueIdDto(outputDto.getUuid()));
    }

    @Test
    public void addUnit() throws DuplicateElementException, ResourceNotFoundException, ExceedingLimitException {
        AccountInputDto account = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AddressInputDto address = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        PersonInputDto manager = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        BuildingInputDto inputDto = new BuildingInputDto(1_000_000L, 2, manager, account, address);
        BuildingOutputDto building = service.create(inputDto);
        fakeUniqueId.add(building.getUuid());

        PersonInputDto ownerDtoU1 = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDtoU1 = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto unitsInputDto = new UnitsFullInputDto(ownerDtoU1, tenantDtoU1, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        PersonInputDto ownerDtoU2 = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDtoU2 = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto units2InputDto = new UnitsFullInputDto(ownerDtoU2, tenantDtoU2, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        AddUnitInBuildingInputDto dto = new AddUnitInBuildingInputDto(new ArrayList<UnitsFullInputDto>() {{
            add(unitsInputDto);
            add(units2InputDto);
        }}, building.getUuid());
        service.addUnits(dto);

        BuildingOutputDto updateBuilding = service.read(new UniqueIdDto(building.getUuid()));
        Assert.assertNotNull(updateBuilding.getUnits().get(0));
        Assert.assertNotNull(updateBuilding.getUnits().get(1));
    }

    @Test(expected = ExceedingLimitException.class)
    public void addUnitOutOfRange() throws DuplicateElementException, ResourceNotFoundException, ExceedingLimitException {
        AccountInputDto account = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AddressInputDto address = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        PersonInputDto manager = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        BuildingInputDto inputDto = new BuildingInputDto(1_000_000L, 1, manager, account, address);
        BuildingOutputDto building = service.create(inputDto);
        fakeUniqueId.add(building.getUuid());

        PersonInputDto ownerDtoU1 = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDtoU1 = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto unitsInputDto = new UnitsFullInputDto(ownerDtoU1, tenantDtoU1, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        PersonInputDto ownerDtoU2 = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDtoU2 = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto units2InputDto = new UnitsFullInputDto(ownerDtoU2, tenantDtoU2, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        AddUnitInBuildingInputDto dto = new AddUnitInBuildingInputDto(new ArrayList<UnitsFullInputDto>() {{
            add(unitsInputDto);
            add(units2InputDto);
        }}, building.getUuid());
        BuildingOutputDto updateBuilding = service.addUnits(dto);
    }

    @Test
    public void deleteUnit() throws ResourceNotFoundException, ExceedingLimitException, DuplicateElementException {
        AccountInputDto account = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AddressInputDto address = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        PersonInputDto manager = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        BuildingInputDto inputDto = new BuildingInputDto(1_000_000L, 2, manager, account, address);
        BuildingOutputDto building = service.create(inputDto);
        fakeUniqueId.add(building.getUuid());

        PersonInputDto ownerDtoU1 = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDtoU1 = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto unitsInputDto = new UnitsFullInputDto(ownerDtoU1, tenantDtoU1, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        PersonInputDto ownerDtoU2 = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDtoU2 = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto units2InputDto = new UnitsFullInputDto(ownerDtoU2, tenantDtoU2, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        AddUnitInBuildingInputDto dto = new AddUnitInBuildingInputDto(new ArrayList<UnitsFullInputDto>() {{
            add(unitsInputDto);
            add(units2InputDto);
        }}, building.getUuid());
        BuildingOutputDto updateBuilding = service.addUnits(dto);
        List<String> listId = new ArrayList<>();
        listId.add(updateBuilding.getUnits().get(0).getUuid());
        DeleteUnitFromBuildingInputDto deleteDto = new DeleteUnitFromBuildingInputDto(listId, building.getUuid());
        service.deleteUnits(deleteDto);
        BuildingOutputDto read = service.read(new UniqueIdDto(updateBuilding.getUuid()));
        Assert.assertEquals(read.getUnits().size(), 1);
    }

    @Test
    public void getAllUnit() throws DuplicateElementException, ResourceNotFoundException, ExceedingLimitException {
        AccountInputDto account = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AddressInputDto address = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        PersonInputDto manager = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        BuildingInputDto inputDto = new BuildingInputDto(1_000_000L, 1, manager, account, address);
        BuildingOutputDto building = service.create(inputDto);
        fakeUniqueId.add(building.getUuid());

        PersonInputDto ownerDtoU1 = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDtoU1 = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");
        UnitsFullInputDto unitsInputDto = new UnitsFullInputDto(ownerDtoU1, tenantDtoU1, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        AddUnitInBuildingInputDto dto = new AddUnitInBuildingInputDto(new ArrayList<UnitsFullInputDto>() {{
            add(unitsInputDto);
        }}, building.getUuid());
        BuildingOutputDto newBuilding = service.addUnits(dto);
        List<UnitsOutputDto> allUnite = service.getAllUnite(new UniqueIdDto(newBuilding.getUuid()));
        for (UnitsOutputDto unit : allUnite) {
            Assert.assertEquals(unit.getLocation(), unitsInputDto.getLocation());
            Assert.assertEquals(unit.getOwner().getMobile(), unitsInputDto.getOwner().getMobile());
            Assert.assertEquals(unit.getTenant().getMobile(), unitsInputDto.getTenant().getMobile());
        }
    }

    @Override
    @After
    public void deleteAll() {
        service.deleteAll(fakeUniqueId);
    }
}
