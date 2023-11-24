package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "Balance", columnDefinition = "default 0")
    private double balance;

    @Column(name = "saveMoney", columnDefinition = "default 0")
    private double saveMoney;

    @Column(name = "password")
    private String password;

    @Column(name = "code", columnDefinition = "default 0")
    private int code;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactionSet;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "accounts_roles", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Account(String username2, String phoneNumber2, String encode) {
        this.username = username2;
        this.phoneNumber = phoneNumber2;
        this.password = encode;
    }

}
