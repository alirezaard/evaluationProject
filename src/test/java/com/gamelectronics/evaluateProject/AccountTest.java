package com.gamelectronics.evaluateProject;

import com.gamelectronics.evaluateProject.business.AccountServices;
import com.gamelectronics.evaluateProject.dao.AccountRepository;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.AccountInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AccountOutputDto;
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
public class AccountTest implements CrudTest {
    static private List<String> fakeUniqueId = new ArrayList<>();
    @Autowired
    private AccountServices service;
    @Autowired
    private AccountRepository repo;

    @Override
    @Test
    public void createTest() throws DuplicateElementException {
        AccountInputDto inputDto = new AccountInputDto("bank", String.valueOf(Math.random()), "1234-5678-9012-3456");
        AccountOutputDto outputDto = service.create(inputDto);
        Assert.assertNotNull(outputDto);
        fakeUniqueId.add(outputDto.getUuid());
    }

    @Override
    @Test(expected = DuplicateElementException.class)
    public void duplicateInsertTest() throws DuplicateElementException {
        AccountInputDto inputDto = new AccountInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AccountOutputDto o1 = service.create(inputDto);
        fakeUniqueId.add(o1.getUuid());
        AccountOutputDto o2 = service.create(inputDto);
    }

    @Override
    @Test
    public void getAllTest() throws DuplicateElementException {
        AccountInputDto inputDto = new AccountInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AccountOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        PageAbleDto<AccountOutputDto> all = service.findAll(new PageDto(0, 10, 0L, "id", true));
        Assert.assertEquals(all.getDto().get(0).getAccount(), inputDto.getAccount());
        Assert.assertEquals(all.getDto().get(0).getCard(), inputDto.getCard());
    }

    @Override
    @Test
    public void validReadTest() throws ResourceNotFoundException, DuplicateElementException {
        AccountInputDto inputDto = new AccountInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AccountOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        AccountOutputDto read = service.read(new UniqueIdDto(outputDto.getUuid()));
        Assert.assertEquals(read.getAccount(), inputDto.getAccount());
        Assert.assertEquals(read.getCard(), inputDto.getCard());
    }

    @Override
    @Test(expected = ResourceNotFoundException.class)
    public void notValidReadTest() throws ResourceNotFoundException {
        AccountOutputDto read = service.read(new UniqueIdDto(UUID.randomUUID().toString()));
    }

    @Override
    @Test
    public void updateTest() throws ResourceNotFoundException, DuplicateElementException {
        AccountInputDto inputDto = new AccountInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AccountOutputDto outputDto = service.create(inputDto);
        fakeUniqueId.add(outputDto.getUuid());
        AccountOutputDto update = service.update(new AccountOutputDto(outputDto.getUuid(), null, inputDto.getAccount(), "4509-0990-0099-1122"));
        Assert.assertEquals(update.getUuid(), outputDto.getUuid());
        Assert.assertEquals(update.getCard(), "4509-0990-0099-1122");
        Assert.assertEquals(update.getAccount(), inputDto.getAccount());
        Assert.assertEquals(update.getBankName(), inputDto.getBankName());
    }

    @Override
    @Test(expected = ResourceNotFoundException.class)
    public void deleteTest() throws ResourceNotFoundException, DuplicateElementException {
        AccountInputDto inputDto = new AccountInputDto(String.valueOf(Math.random()), String.valueOf(Math.random()), String.valueOf(Math.random()));
        AccountOutputDto outputDto = service.create(inputDto);
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
