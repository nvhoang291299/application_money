package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.Request;
import com.example.demo.service.IAccountService;

@RestController
@RequestMapping("/transaction/api")
public class TransactionController {

    @Autowired
    private IAccountService accountService;

    // public TransactionController() {
    // // TODO Auto-generated constructor stub
    // }

    @PostMapping("/sendMoney")
    public ResponseEntity<?> sendMoney(@RequestBody Request request) {
        accountService.sendMoney(request);
        return ResponseEntity.ok("send money success!!!");
    }

}
