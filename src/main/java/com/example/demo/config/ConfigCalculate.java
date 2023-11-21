package com.example.demo.config;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Account;
import com.example.demo.repository.IAccountRepository;

@Component
@EnableScheduling
public class ConfigCalculate {

    @Autowired
    private IAccountRepository accountRepository;

    @Bean
    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    private void autoDepositSavings() {
        List<Account> listAccount = accountRepository.findAll();
        CompletableFuture<Void> allFutures = CompletableFuture.completedFuture(null);

        for (Account account : listAccount) {
            if (account.getSaveMoney() > 0) {
                CompletableFuture<Void> accountFuture = CompletableFuture.supplyAsync(() -> {
                    try {
                        double interest = account.getSaveMoney() * Math.pow((1 + (0.065 / 365)), 365);
                        account.setSaveMoney(interest);
                        accountRepository.save(account);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                    return null;
                });

                allFutures = allFutures.thenCompose(v -> accountFuture);
            }
        }
    }
}
