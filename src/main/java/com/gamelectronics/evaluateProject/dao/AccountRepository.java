package com.gamelectronics.evaluateProject.dao;

import com.gamelectronics.evaluateProject.domain.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findFirstByUniqueId(String uuid);
    boolean existsByUniqueId(String uuid);
    boolean existsByAccountNumberAndCardNumberAndBankName(String accountNumber, String cardNumber, String BankName);
    void deleteAllByUniqueId(String uniqueId);
    Page<Account> findAll(Pageable pageable);
    void deleteAllByAccountNumberAndCardNumber(String account,String card);
    void deleteAllByUniqueIdIn(List<String> uniqueIds);
}