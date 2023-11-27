package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.RefreshToken;

public interface IRefreshTokenService {

    Optional<RefreshToken> findByRefreshToken(String token);
    
    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyExpiration(RefreshToken token);

    int deleteByUserId(Long userId);
}
