package com.example.demo.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Request {

    @NotNull
    @NotBlank(message = "Tên tài khoản đang bị để trống")
    @Size(max = 6, message = "tên tài khoản không quá 6 ký tự")
    private String username;

    @NotNull
    @Min(value = 10000, message = "Số tiền phải lớn hơn 10000")
    @Max(value = 50000000, message = "Số tiền phải nhỏ hơn 50000000")
    private double money;

    public Request(String username, double money) {
        this.username = username;
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
