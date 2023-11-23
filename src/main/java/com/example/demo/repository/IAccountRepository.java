package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    Boolean existByUsername(String username);

}
