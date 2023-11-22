package com.example.demo.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {

    @NotBlank(message = "xin vui lòng nhập mã PIN")
    @Size(max = 6, message = "mã PIN không quá 6 ký tự")
    private int code;

    @NotNull
    @Min(value = 10000, message = "Số tiền phải lớn hơn 10000")
    @Max(value = 50000000, message = "Số tiền phải nhỏ hơn 50000000")
    private double money;
}
