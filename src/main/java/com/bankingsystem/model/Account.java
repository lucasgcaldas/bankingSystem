package com.bankingsystem.model;

import com.bankingsystem.controller.ExtractAccountController;
import com.bankingsystem.controller.UserController;
import com.bankingsystem.BankingSystem;
import totalcross.util.BigDecimal;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Account {

    private Long id;
    private BigDecimal balance;
    private Integer branch;
    private Integer number;

    public Account(BigDecimal balance, Integer branch, Integer number) {
        this.balance = balance;
        this.branch = branch;
        this.number = number;
    }

    public Account() {
    }

    public Integer getBranch() {
        return branch;
    }

    public Integer getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void receivedTransfer(BigDecimal value) {
        BankingSystem.destiny.setBalance(BankingSystem.destiny.getBalance().add(value));
    }

    public void sendTransfer(String kindTransfer, BigDecimal value) {

        if (BankingSystem.origin.getBalance().compareTo(value) >= 0) {

            BankingSystem.origin.setBalance(this.balance.subtract(value));
            BankingSystem.destiny.receivedTransfer(value);

            UserController uc = new UserController();
            ExtractAccountController eAC = new ExtractAccountController();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date date = new Date();
            Extract extract = new Extract();

            User user = uc.checkIfExistUserToTrans(BankingSystem.destiny.getNumber());

            extract.setUser(BankingSystem.user);
            extract.setUserDestiny(user);
            extract.setValue(value);
            extract.setDate(sdf.format(date));

            eAC.saveTransfer(extract, kindTransfer, BankingSystem.destiny.getNumber());
        }
    }
}
