package com.bankingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String cpf;
    private String birthDate;
    private List<Account> accounts;
    private String password;

    public User(String name, String cpf, String birthDate, ArrayList<Account> accounts, String password) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.accounts = accounts;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}


