package com.example.demo.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.repository.IAccountRepository;
import com.example.demo.request.Request;
import com.example.demo.service.IAccountService;

@Service
@Transactional
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    @Transactional
    public void sendMoney(Request request) {

        String username = request.getUsername();
        Account account = accountRepository.findByUsername(username);
        account.setBalance(account.getBalance() + request.getMoney());
        accountRepository.save(account);

    }

    @Override
    @Transactional
    public void withdrawMoney(Request request) {
        String username = request.getUsername();
        Account account = accountRepository.findByUsername(username);
        account.setBalance(account.getBalance() - request.getMoney());
        accountRepository.save(account);

    }

    @Override
    @Transactional
    public Account getBalance(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void saveMoney(@Valid Request request) {
        String message = null;
        String username = request.getUsername();
        Account account = accountRepository.findByUsername(username);
        account.setSaveMoney(request.getMoney());
        accountRepository.save(account);

    }

}
