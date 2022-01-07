package com.huyphungkien.repository;

import com.huyphungkien.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    @Query("select count(userName) from Account as a where a.userName=?1 and passWord=?2")
    public int login(String name,String pass);
}
