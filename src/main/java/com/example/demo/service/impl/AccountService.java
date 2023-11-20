package com.example.demo.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.repository.IAccountRepository;
import com.example.demo.request.Request;
import com.example.demo.service.IAccountService;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public void sendMoney(Request request) {
        String message = null;
        String username = request.getUsername();
        Account account = accountRepository.findByUsername(username);
        account.setBalance(account.getBalance() + request.getMoney());
        accountRepository.save(account);

    }

    @Override
    public void withdrawMoney(Request request) {
        String message = null;
        String username = request.getUsername();
        Account account = accountRepository.findByUsername(username);
        account.setBalance(account.getBalance() - request.getMoney());
        accountRepository.save(account);

    }

    @Override
    public Account getBalance(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void saveMoney(@Valid Request request) {
        String message = null;
        String username = request.getUsername();
        Account account = accountRepository.findByUsername(username);
        account.setSaveMoney(request.getMoney());
        accountRepository.save(account);

    }

    @PostConstruct
    public void autoDepositSavings() {
        List<Account> listAccount = accountRepository.findAll();
        for (Account account : listAccount) {
            if (account.getSaveMoney() > 0) {
                Thread threadAccount = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (account.getSaveMoney() > 0) {
                            try {
                                Thread.sleep(86400000);
                                double interest = account.getSaveMoney() * Math.pow((1 + (0.065 / 365)), 365);
                                account.setSaveMoney(interest);
                                accountRepository.save(account);
                            } catch (Exception e) {
                                e.getStackTrace();
                            }
                        }
                    }
                });
                threadAccount.start();
            }
        }
    }

}
