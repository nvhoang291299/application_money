package com.example.demo.service;

import javax.validation.Valid;

import com.example.demo.entity.Account;
import com.example.demo.request.Request;

public interface IAccountService {

    void sendMoney(Request request);

    void withdrawMoney(Request request);

    Account getBalance(String username);

	void saveMoney(@Valid Request request);

}
