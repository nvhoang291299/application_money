package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.request.Request;

public interface IAccountService {

    void sendMoney(Request request);

    Account getAccountByUserName(String username);

}
