package com.gamelectronics.evaluateProject;

import com.gamelectronics.evaluateProject.business.AddressServices;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.AddressInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AddressOutputDto;
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
public class AddressTest implements CrudTest {
    static private List<String> fakeUniqueId = new ArrayList<>();

    @Autowired
    private AddressServices service;

    @Test
    @Override
    public void createTest() throws DuplicateElementException {
        AddressInputDto inputDto = new AddressInputDto("Tehran", "Tehran", "Enqelab", String.valueOf(Math.random()), "azadi av. no 43");
        AddressOutputDto outputDto = service.create(inputDto);
        Assert.assertNotNull(outputDto);
        fakeUniqueId.add(outputDto.getUuid());
    }

    @Override
    @Test(expected = DuplicateElementException.class)
    public void duplicateInsertTest() throws DuplicateElementException {
        AddressInputDto inputDto = new AddressInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AddressOutputDto o1 = service.create(inputDto);
        fakeUniqueId.add(o1.getUuid());
        service.create(inputDto);

    }

    @Override
    @Test
    public void getAllTest() throws DuplicateElementException {
        AddressInputDto inputDto = new AddressInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AddressOutputDto out = service.create(inputDto);
        fakeUniqueId.add(out.getUuid());
        PageAbleDto<AddressOutputDto> all = service.findAll(new PageDto(0, 10, 0L, "id", true));
        Assert.assertEquals(all.getDto().get(0).getPostalCode(), inputDto.getPostalCode());
        Assert.assertEquals(all.getDto().get(0).getAddressDetail(), inputDto.getAddressDetail());
    }

    @Override
    @Test
    public void validReadTest() throws ResourceNotFoundException, DuplicateElementException {
        AddressInputDto inputDto = new AddressInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AddressOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        AddressOutputDto read = service.read(new UniqueIdDto(outputDto.getUuid()));
        Assert.assertEquals(read.getPostalCode(), inputDto.getPostalCode());
        Assert.assertEquals(read.getAddressDetail(), inputDto.getAddressDetail());
    }

    @Override
    @Test(expected = ResourceNotFoundException.class)
    public void notValidReadTest() throws ResourceNotFoundException {
        service.read(new UniqueIdDto(UUID.randomUUID().toString()));
    }

    @Override
    @Test
    public void updateTest() throws ResourceNotFoundException, DuplicateElementException {
        AddressInputDto inputDto = new AddressInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AddressOutputDto outputDto = service.create(inputDto);
        AddressOutputDto update = service.update
                (new AddressOutputDto(null, outputDto.getUuid(), "Tehran", inputDto.getDistrict(), inputDto.getPostalCode(), "azadi av. no 43"));
        Assert.assertEquals(update.getUuid(), outputDto.getUuid());
        Assert.assertEquals(update.getAddressDetail(), "azadi av. no 43");
        Assert.assertEquals(update.getPostalCode(), inputDto.getPostalCode());
        Assert.assertEquals(update.getCity(), inputDto.getCity());
        fakeUniqueId.add(update.getUuid());
    }


    @Override
    @Test(expected = ResourceNotFoundException.class)
    public void deleteTest() throws ResourceNotFoundException, DuplicateElementException {
        AddressInputDto inputDto = new AddressInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        String uniqueId = service.create(inputDto).getUuid();
        try {
            service.delete(new UniqueIdDto(uniqueId));
        } catch (ResourceNotFoundException e) {
        }
        service.read(new UniqueIdDto(uniqueId));
    }

    @Override
    @After
    public void deleteAll() {
        service.deleteAll(fakeUniqueId);
    }
}
