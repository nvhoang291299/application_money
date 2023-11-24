package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RefreshToken;
import com.example.demo.exception.TokenRefreshException;
import com.example.demo.repository.IAccountRepository;
import com.example.demo.repository.RefreshTokenRepository;
import com.example.demo.service.IRefreshTokenService;

@Service
public class RefreshTokenService implements IRefreshTokenService {
    private long refreshTokenDurationSeconds = 120;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public RefreshToken findByRefreshToken(String token) {
        return refreshTokenRepository.findByRefreshToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setAccount(accountRepository.findById(userId).get());
        refreshToken.setExpDate(LocalDateTime.now().plusSeconds(refreshTokenDurationSeconds));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpDate().compareTo(LocalDateTime.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getRefreshToken(),
                    "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Override
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByAccount(accountRepository.findById(userId).get());
    }

}
