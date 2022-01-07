package com.huyphungkien.service;

import com.huyphungkien.domain.Account;
import com.huyphungkien.model.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> findAll();

    Account getById(String s);

    <S extends Account> S save(S entity);

    Optional<Account> findById(String s);

    boolean existsById(String s);

    void deleteById(String s);

    boolean login(AccountDto dto);
}
