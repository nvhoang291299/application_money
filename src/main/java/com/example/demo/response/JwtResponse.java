package com.example.demo.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String accessToken;

    private String username;
    
    private String refreshToken;

    private List<String> roles;

    public JwtResponse(String token, String requestRefreshToken) {
        this.accessToken = token;
        this.refreshToken = requestRefreshToken;
    }
}
