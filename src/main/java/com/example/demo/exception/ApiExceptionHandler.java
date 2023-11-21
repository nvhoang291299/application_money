package com.example.demo.exception;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.request.ErrorMessage;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage nullException(Exception ex, WebRequest request) {
        return new ErrorMessage(10100, "request có giá trị null");
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage accountNotFountException(Exception ex, WebRequest request) {
        return new ErrorMessage(404, "tài khoản không tồn tại");
    }
}
