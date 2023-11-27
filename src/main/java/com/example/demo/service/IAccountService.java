package com.example.demo.service;

import javax.validation.Valid;

import com.example.demo.entity.Account;
import com.example.demo.request.SignupRequest;
import com.example.demo.request.TransactionRequest;

public interface IAccountService {

    void sendMoney(TransactionRequest request);

    void withdrawMoney(TransactionRequest request);

    Account getBalance(String username);

	void saveMoney(@Valid TransactionRequest request);

    boolean checkUsername(SignupRequest signupRequest);

    boolean checkPhoneNumber(SignupRequest signupRequest);

    void save(Account account);

    void handleSignup(SignupRequest signupRequest);
}
