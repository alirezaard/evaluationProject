package com.gamelectronics.evaluateProject;

import com.gamelectronics.evaluateProject.business.PersonServices;
import com.gamelectronics.evaluateProject.business.UnitsServices;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.PersonInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsFullInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.UnitsInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.PersonOutputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.UnitsOutputDto;
import com.gamelectronics.evaluateProject.exceptions.DuplicateElementException;
import com.gamelectronics.evaluateProject.exceptions.ResourceNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
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
public class UnitsTest implements CrudTest {
    static private List<String> fakeUniqueId = new ArrayList<>();
    @Autowired
    private UnitsServices service;
    @Autowired
    private PersonServices perSerivce;

    @Override
    @Test
    public void createTest() throws DuplicateElementException {
        PersonInputDto ownerDto = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDto = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto unitsInputDto = new UnitsFullInputDto(ownerDto, tenantDto, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        UnitsOutputDto outputDto = service.create(unitsInputDto);
        Assert.assertNotNull(outputDto);
        fakeUniqueId.add(outputDto.getUuid());
    }

    @Test
    public void createWithOwnerAndTenant() throws DuplicateElementException, ResourceNotFoundException {
        PersonInputDto ownerDto = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonOutputDto ownerOutDto = perSerivce.create(ownerDto);
        PersonInputDto tenantDto = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");
        PersonOutputDto tenantOutDto = perSerivce.create(tenantDto);
        UnitsInputDto inputDto = new UnitsInputDto(String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        UnitsOutputDto outputDto = service.create(inputDto, ownerOutDto.getUuid(), tenantOutDto.getUuid());
        Assert.assertNotNull(outputDto);
        fakeUniqueId.add(outputDto.getUuid());
    }

    @Override
    @Test(expected = DuplicateElementException.class)
    public void duplicateInsertTest() throws DuplicateElementException {
        PersonInputDto ownerDto = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDto = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto unitsInputDto = new UnitsFullInputDto(ownerDto, tenantDto, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        UnitsOutputDto o1 = service.create(unitsInputDto);
        fakeUniqueId.add(o1.getUuid());
        UnitsOutputDto o2 = service.create(unitsInputDto);
    }

    @Override
    @Test
    public void getAllTest() throws DuplicateElementException {
        PersonInputDto ownerDto = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDto = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto inputDto = new UnitsFullInputDto(ownerDto, tenantDto, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        UnitsOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        PageAbleDto<UnitsOutputDto> all = service.findAll(new PageDto(0, 10, 0L, "id", true));
        Assert.assertEquals(all.getDto().get(0).getLocation(), inputDto.getLocation());
        Assert.assertEquals(all.getDto().get(0).getOwner().getPhone(), inputDto.getOwner().getPhone());
        Assert.assertEquals(all.getDto().get(0).getTenant().getPhone(), inputDto.getTenant().getPhone());
    }

    @Override
    @Test
    public void validReadTest() throws ResourceNotFoundException, DuplicateElementException {
        PersonInputDto ownerDto = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDto = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto inputDto = new UnitsFullInputDto(ownerDto, tenantDto, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        UnitsOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        UnitsOutputDto read = service.read(new UniqueIdDto(outputDto.getUuid()));
        Assert.assertEquals(read.getLocation(), inputDto.getLocation());
        Assert.assertEquals(read.getOwner().getPhone(), inputDto.getOwner().getPhone());
        Assert.assertEquals(read.getTenant().getPhone(), inputDto.getTenant().getPhone());
    }

    @Override
    @Test(expected = ResourceNotFoundException.class)
    public void notValidReadTest() throws ResourceNotFoundException {
        service.read(new UniqueIdDto(UUID.randomUUID().toString()));
    }

    @Override
    @Test
    public void updateTest() throws ResourceNotFoundException, DuplicateElementException {
        PersonInputDto ownerDto = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDto = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");

        UnitsFullInputDto inputDto = new UnitsFullInputDto(ownerDto, tenantDto, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);

        UnitsOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        UnitsOutputDto update = service.update(new UnitsOutputDto(outputDto.getUuid(), null, null, "Tehran Pars", null, 1_000L, 3, outputDto.getChargeAmount()));
        Assert.assertEquals(update.getUuid(), outputDto.getUuid());
        Assert.assertEquals(update.getLocation(), "Tehran Pars");
        Assert.assertEquals(update.getTenant().getMobile(), inputDto.getTenant().getMobile());
        Assert.assertEquals(update.getTenant().getPhone(), inputDto.getTenant().getPhone());
        Assert.assertEquals(update.getOwner().getMobile(), inputDto.getOwner().getMobile());
        Assert.assertEquals(update.getOwner().getPhone(), inputDto.getOwner().getPhone());
    }

    @Override
    @Test(expected = ResourceNotFoundException.class)
    public void deleteTest() throws ResourceNotFoundException, DuplicateElementException {
        PersonInputDto ownerDto = new PersonInputDto("OWNER", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonInputDto tenantDto = new PersonInputDto("TENANT", String.valueOf(Math.random()), "09125757532", "22145104");
        UnitsFullInputDto inputDto = new UnitsFullInputDto(ownerDto, tenantDto, String.valueOf(Math.random()), 1000L, 800L, 5, 5000L, null);
        UnitsOutputDto outputDto = service.create(inputDto);
        try {
            service.delete(new UniqueIdDto(outputDto.getUuid()));
        } catch (ResourceNotFoundException e) {
            fakeUniqueId.add(outputDto.getUuid());
        }
        service.read(new UniqueIdDto(outputDto.getUuid()));
    }

    @Override
    @After
    public void deleteAll() {
        service.deleteAll(fakeUniqueId);
    }
}
