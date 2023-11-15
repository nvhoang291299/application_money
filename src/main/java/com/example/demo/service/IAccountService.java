package com.example.demo.service;

import com.example.demo.request.Request;

public interface IAccountService {

    void sendMoney(Request request);

    void withdrawMoney(Request request);

}
