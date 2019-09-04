package com.gamelectronics.evaluateProject.business.mapper;

import com.gamelectronics.evaluateProject.domain.dtos.input.AccountInputDto;
import com.gamelectronics.evaluateProject.domain.dtos.output.AccountOutputDto;
import com.gamelectronics.evaluateProject.domain.entities.Account;

public class AccountMapper {
    public static Account dtoToAccount(AccountInputDto dto) {
        Account account = new Account();
        account.setCardNumber(dto.getCard());
        account.setBankName(dto.getBankName());
        account.setAccountNumber(dto.getAccount());
        return account;
    }

    public static AccountOutputDto accountToDto(Account account) {
        AccountOutputDto dto = new AccountOutputDto();
        dto.setUuid(account.getUniqueId());
        dto.setCard(account.getCardNumber());
        dto.setAccount(account.getAccountNumber());
        dto.setBankName(account.getBankName());
        return dto;
    }
}
