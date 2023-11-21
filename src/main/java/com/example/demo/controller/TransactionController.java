package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.request.Request;
import com.example.demo.service.IAccountService;

@RestController
@RequestMapping("/transaction/api")
public class TransactionController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/sendMoney")
    public ResponseEntity<?> sendMoney(@Valid @RequestBody Request request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        accountService.sendMoney(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/withdrawMoney")
    public ResponseEntity<?> withdrawMoney(@Valid @RequestBody Request request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        accountService.withdrawMoney(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/displayBalance/{username}")
    public ResponseEntity<?> displayBalance(@PathVariable("username") String username) {
        Account account = accountService.getBalance(username);
        return ResponseEntity.ok("Số dư hiện tại của bạn là:" + account.getBalance());
    }

    @PostMapping("/saveMoney")
    public ResponseEntity<?> saveMoney(@Valid @RequestBody Request request, BindingResult bindingResult) {
        accountService.saveMoney(request);
        return ResponseEntity.ok("Ban da gui tien thanh cong");
    }

}
