package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId")
    private long transactionId;

    @Column(name = "typeTransaction")
    private String typeTransaction;

    @Column(name = "amountMoney")
    private double amountMoney;

    @Column(name = "timeTransaction")
    private LocalDateTime timeTransaction;

    @ManyToOne
    @JoinColumn(name = "idAccount")
    private Account account;

    public Transaction() {
    }

    public Transaction(long transactionId, String typeTransaction, double amountMoney, LocalDateTime timeTransaction,
            Account account) {
        super();
        this.transactionId = transactionId;
        this.typeTransaction = typeTransaction;
        this.amountMoney = amountMoney;
        this.timeTransaction = timeTransaction;
        this.account = account;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }

    public LocalDateTime getTimeTransaction() {
        return timeTransaction;
    }

    public void setTimeTransaction(LocalDateTime timeTransaction) {
        this.timeTransaction = timeTransaction;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
