package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    Account findByCode(int code);

    boolean existsByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);

}
