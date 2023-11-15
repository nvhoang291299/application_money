package com.example.demo.service.impl;

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

    // public AccountService() {
    // // TODO Auto-generated constructor stub
    // }

    @Override
    public void sendMoney(Request request) {
        String message = null;
        String username = request.getUsername();
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            message = "Tài khoản của bạn không đúng";
        } else {
            if (request.getMoney() < 10000) {
                message = "Số tiền phải lớn hơn 10000 và bé hơn 5000000";
            } else {
                account.setBalance(account.getBalance() + request.getMoney());
                accountRepository.save(account);
            }
        }
    }

    @Override
    public void withdrawMoney(Request request) {
        String message = null;
        String username = request.getUsername();
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            message = "Tài khoản của bạn không đúng";
        } else {
            if (request.getMoney() < 10000) {
                message = "Số tiền phải lớn hơn 10000 và bé hơn 5000000";
            } else {
                account.setBalance(account.getBalance() - request.getMoney());
                accountRepository.save(account);
            }
        }
    }
}
