package com.demo.repo;

import com.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, String> {

    Optional<Account> findByUsernameAndPassword(String username, String password);

    @Query("SELECT a FROM Account a WHERE a.username LIKE :username")
    Account findByUsername(@Param("username") String username);

}
