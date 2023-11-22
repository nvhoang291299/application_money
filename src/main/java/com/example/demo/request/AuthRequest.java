package com.example.demo.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotNull
    private String username;

    @NotNull
    @Min(value = 6)
    private String password;
}
