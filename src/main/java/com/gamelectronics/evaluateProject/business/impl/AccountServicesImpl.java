package com.gamelectronics.evaluateProject.business.impl;

import com.gamelectronics.evaluateProject.business.AccountServices;
import com.gamelectronics.evaluateProject.business.mapper.AccountMapper;
import com.gamelectronics.evaluateProject.dao.AccountRepository;
import com.gamelectronics.evaluateProject.domain.dtos.PageAbleDto;
import com.gamelectronics.evaluateProject.domain.dtos.PageDto;
import com.gamelectronics.evaluateProject.domain.dtos.UniqueIdDto;
import com.gamelectronics.evaluateProject.domain.dtos.input.AccountInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AccountOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Account;
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
public class AccountServicesImpl implements AccountServices {
    @Autowired
    private AccountRepository repo;

    @Override
    public AccountOutputDto create(AccountInputDto dto) throws DuplicateElementException {
        Account account = AccountMapper.dtoToAccount(dto);
        if (repo.existsByAccountNumberAndCardNumberAndBankName(dto.getAccount(), dto.getCard(), dto.getBankName()))
            throw new DuplicateElementException();
        Account save = repo.save(account);
        return AccountMapper.accountToDto(save);
    }

    @Override
    public AccountOutputDto read(UniqueIdDto dto) throws ResourceNotFoundException {
        Account account = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        return AccountMapper.accountToDto(account);
    }

    @Override
    public AccountOutputDto update(AccountOutputDto dto) throws ResourceNotFoundException {
        Account account = repo.findFirstByUniqueId(dto.getUuid()).orElseThrow(ResourceNotFoundException::new);
        if (!StringUtils.isEmpty(dto.getAccount())) {
            account.setAccountNumber(dto.getAccount());
        }
        if (!StringUtils.isEmpty(dto.getBankName())) {
            account.setBankName(dto.getBankName());
        }
        if (!StringUtils.isEmpty(dto.getCard())) {
            account.setCardNumber(dto.getCard());
        }
        Account save = repo.save(account);
        return AccountMapper.accountToDto(save);
    }

    @Override
    @Transactional
    public void delete(UniqueIdDto dto) throws ResourceNotFoundException {
        if (!repo.existsByUniqueId(dto.getUuid()))
            throw new ResourceNotFoundException();
        repo.deleteAllByUniqueId(dto.getUuid());
    }

    @Override
    public PageAbleDto<AccountOutputDto> findAll(PageDto dto) {
        Sort.Direction dir = Sort.Direction.ASC;
        if (dto.isDecs())
            dir = Sort.Direction.DESC;
        Page<Account> all = repo.findAll(new PageRequest(dto.getPageNumber(), dto.getPageSize(), dir, dto.getSortBy()));
        PageAbleDto<AccountOutputDto> outputPageDto = new PageAbleDto<>();
        for (Account account : all) {
            outputPageDto.getDto().add(AccountMapper.accountToDto(account));
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
    public Account read(String uuid) throws ResourceNotFoundException {
        return repo.findFirstByUniqueId(uuid).orElseThrow(ResourceNotFoundException::new);
    }
}
