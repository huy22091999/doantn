package com.huyphungkien.service.serviceImpl;

import com.huyphungkien.domain.Account;
import com.huyphungkien.model.AccountDto;
import com.huyphungkien.repository.AccountRepository;
import com.huyphungkien.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository ar;
    @Autowired
    BCryptPasswordEncoder bcp;

    @Override
    public List<Account> findAll() {
        return ar.findAll();
    }

    @Override
    public Account getById(String s) {
        return ar.getById(s);
    }

    @Override
    public <S extends Account> S save(S entity) {
        entity.setPassWord(bcp.encode(entity.getPassWord()));
        return ar.save(entity);
    }

    @Override
    public Optional<Account> findById(String s) {
        return ar.findById(s);
    }

    @Override
    public boolean existsById(String s) {
        return ar.existsById(s);
    }

    @Override
    public void deleteById(String s) {
        ar.deleteById(s);
    }

    @Override
    public boolean login(AccountDto dto)
    {
        Optional<Account> opt=findById(dto.getUserName());
        if(opt.isPresent()&&bcp.matches(dto.getPassWord(), opt.get().getPassWord()))
        {
            return true;
        }
        return false;
    }
}
