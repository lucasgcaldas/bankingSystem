package com.bankingsystem.model;

public class User {

    private String name;
    private String cpf;
    private String birthDate;
    private Account account1;
    private Account account2;
    private String password;

    public User(String name, String cpf, String birthDate, Account account1, Account account2, String password) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.account1 = account1;
        this.account2 = account2;
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

    public Account getAccount1() {
        return account1;
    }

    public void setAccount1(Account account1) {
        this.account1 = account1;
    }

    public Account getAccount2() {
        return account2;
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }
}


