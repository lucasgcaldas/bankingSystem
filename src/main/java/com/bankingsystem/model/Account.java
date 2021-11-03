package com.bankingsystem.model;

import com.bankingsystem.controller.AccountController;
import com.bankingsystem.controller.ExtractAccountController;

import java.math.BigDecimal;

public abstract class Account {

    private Long id;
    private BigDecimal balance;
    private Integer branch;
    private Integer number;

    public Account(BigDecimal balance, Integer branch, Integer number) {
        this.balance = balance;
        this.branch = branch;
        this.number = number;
        AccountController accountController = new AccountController();
        accountController.saveAccount(this);
    }

    public Account() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getBranch() {
        return branch;
    }

    public Integer getNumber() {
        return number;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void receivedTransfer(BigDecimal value) {
        this.setBalance(this.balance.add(value));
    }

    public void sendTransfer(BigDecimal value, Account destiny){
        if (this.getBalance().compareTo(value) >= 0){
            this.setBalance(this.balance.subtract(value));
            destiny.receivedTransfer(value);

            ExtractAccountController eAC = new ExtractAccountController();
            eAC.setOrigin(this);
            eAC.setDestiny(destiny);
            eAC.setValue(value);
            eAC.saveTransfer();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        return true;
    }
}