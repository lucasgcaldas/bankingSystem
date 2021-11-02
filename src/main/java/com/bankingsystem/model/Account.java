package com.bankingsystem.model;

import java.math.BigDecimal;

public abstract class Account {
    
    private BigDecimal balance;
    private Integer branch;
    private Integer number;

    public Account(BigDecimal balance, Integer branch, Integer number) {
        this.balance = balance;
        this.branch = branch;
        this.number = number;
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

    public void deposit(BigDecimal value) {
        this.balance.add(value);
    }

    public boolean transferToAccount(BigDecimal value, Account account){
        if (this.getBalance().compareTo(value) >= 0){
            this.getBalance().subtract(value);
            account.deposit(value);
            return true;
        }
        throw new IllegalArgumentException();
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
