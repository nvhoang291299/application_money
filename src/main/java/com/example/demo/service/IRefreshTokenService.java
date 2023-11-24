package com.example.demo.service;

import com.example.demo.entity.RefreshToken;

public interface IRefreshTokenService {

    RefreshToken findByRefreshToken(String token);
    
    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyExpiration(RefreshToken token);

    int deleteByUserId(Long userId);
}
