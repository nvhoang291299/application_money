package com.example.demo.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ConfigAutoSaveMoney {

    // @Autowired
    // private IAccountRepository accountRepository;
    //
    // @Bean
    // @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    // private void autoDepositSavings() {
    // List<Account> listAccount = accountRepository.findAll();
    // CompletableFuture<Void> allFutures =
    // CompletableFuture.completedFuture(null);
    //
    // for (Account account : listAccount) {
    // if (account.getSaveMoney() > 0) {
    // CompletableFuture<Void> accountFuture = CompletableFuture.supplyAsync(()
    // -> {
    // try {
    // double interest = account.getSaveMoney() * Math.pow((1 + (0.065 / 365)),
    // 365);
    // account.setSaveMoney(interest);
    // accountRepository.save(account);
    // } catch (Exception e) {
    // e.getStackTrace();
    // }
    // return null;
    // });
    //
    // allFutures = allFutures.thenCompose(v -> accountFuture);
    // }
    // }
    // }
}
