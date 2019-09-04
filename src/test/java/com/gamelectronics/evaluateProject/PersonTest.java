package com.gamelectronics.evaluateProject;

import com.gamelectronics.evaluateProject.business.PersonServices;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.PersonInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.PersonOutputDto;
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
public class PersonTest implements CrudTest {

    static private List<String> fakeUniqueId = new ArrayList<>();

    @Autowired
    private PersonServices service;


    @Override
    @Test
    public void createTest() throws DuplicateElementException {
        PersonInputDto inputDto = new PersonInputDto("alireza", String.valueOf(Math.random()), "09223719781", "22145103");
        PersonOutputDto outputDto = service.create(inputDto);
        Assert.assertNotNull(outputDto);
        fakeUniqueId.add(outputDto.getUuid());
    }

    @Override
    @Test(expected = DuplicateElementException.class)
    public void duplicateInsertTest() throws DuplicateElementException {
        PersonInputDto inputDto = new PersonInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        PersonOutputDto o1 = service.create(inputDto);
        fakeUniqueId.add(o1.getUuid());
        PersonOutputDto o2 = service.create(inputDto);
    }

    @Override
    @Test
    public void getAllTest() throws DuplicateElementException {
        PersonInputDto inputDto = new PersonInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        PersonOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        PageAbleDto<PersonOutputDto> all = service.findAll(new PageDto(0, 10, 0L, "id", true));
        Assert.assertEquals(all.getDto().get(0).getMobile(), inputDto.getMobile());
        Assert.assertEquals(all.getDto().get(0).getPhone(), inputDto.getPhone());
        Assert.assertEquals(all.getDto().get(0).getLastName(), inputDto.getLastName());
    }

    @Override
    @Test
    public void validReadTest() throws ResourceNotFoundException, DuplicateElementException {
        PersonInputDto inputDto = new PersonInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        PersonOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        PersonOutputDto read = service.read(new UniqueIdDto(outputDto.getUuid()));
        Assert.assertEquals(read.getPhone(), inputDto.getPhone());
        Assert.assertEquals(read.getMobile(), inputDto.getMobile());
    }

    @Override
    @Test(expected = ResourceNotFoundException.class)
    public void notValidReadTest() throws ResourceNotFoundException {
        PersonOutputDto read = service.read(new UniqueIdDto(UUID.randomUUID().toString()));
    }

    @Override
    @Test
    public void updateTest() throws ResourceNotFoundException, DuplicateElementException {
        PersonInputDto inputDto = new PersonInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        PersonOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        PersonOutputDto p2 = new PersonOutputDto(outputDto.getUuid(), String.valueOf(Math.random()), null, "09223719781", String.valueOf(Math.random()));
        PersonOutputDto update = service.update(p2);
        Assert.assertEquals(update.getUuid(), outputDto.getUuid());
        Assert.assertEquals(update.getMobile(), "09223719781");
        Assert.assertEquals(update.getPhone(), p2.getPhone());
        Assert.assertEquals(update.getLastName(), inputDto.getLastName());
    }

    @Override
    @Test(expected = ResourceNotFoundException.class)
    public void deleteTest() throws ResourceNotFoundException, DuplicateElementException {
        PersonInputDto inputDto = new PersonInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        PersonOutputDto outputDto = service.create(inputDto);
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
