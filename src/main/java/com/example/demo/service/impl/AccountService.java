package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.repository.IAccountRepository;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.request.SignupRequest;
import com.example.demo.request.TransactionRequest;
import com.example.demo.service.IAccountService;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public void sendMoney(TransactionRequest request) {

        int code = request.getCode();
        Account account = accountRepository.findByCode(code);
        if (code == account.getCode()) {
            account.setBalance(account.getBalance() + request.getMoney());
            accountRepository.save(account);
        } ;

    }

    @Override
    @Transactional
    public void withdrawMoney(TransactionRequest request) {
        int code = request.getCode();
        Account account = accountRepository.findByCode(code);
        if (code == account.getCode()) {
            account.setBalance(account.getBalance() - request.getMoney());
            accountRepository.save(account);
        }
    }

    @Override
    @Transactional
    public Account getBalance(String username) {
        return accountRepository.findByUsername(username).get();
    }

    @Override
    @Transactional
    public void saveMoney(@Valid TransactionRequest request) {
        int code = request.getCode();
        Account account = accountRepository.findByCode(code);
        if (code == account.getCode()) {
            account.setSaveMoney(request.getMoney());
            accountRepository.save(account);
        }
    }

    @Override
    public boolean checkUsername(SignupRequest signupRequest) {
        return accountRepository.existsByUsername(signupRequest.getUsername());
    }

    @Override
    public boolean checkPhoneNumber(SignupRequest signupRequest) {
        return accountRepository.existsByPhoneNumber(signupRequest.getPhoneNumber());
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void handleSignup(SignupRequest signupRequest) {
        Account account = new Account(signupRequest.getUsername(), signupRequest.getPhoneNumber(),
                encoder.encode(signupRequest.getPassword()));

        String role = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (role == null) {
            Role userRole = roleRepository.findByNameRole("ROLE_USER");
            roles.add(userRole);
        }
        account.setRoles(roles);
        accountRepository.save(account);

    }

}
