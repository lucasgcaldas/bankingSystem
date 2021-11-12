package com.bankingsystem.model;

import com.bankingsystem.controller.ExtractAccountController;
import com.bankingsystem.controller.UserController;
import com.bankingsystem.util.SlideMenu;
import com.bankingsystem.view.Login;
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
        Login.destiny.setBalance(Login.destiny.getBalance().add(value));
    }

    public void sendTransfer(String kindTransfer, BigDecimal value) {

        if (Login.origin.getBalance().compareTo(value) >= 0) {

            Login.origin.setBalance(this.balance.subtract(value));
            Login.destiny.receivedTransfer(value);

            UserController uc = new UserController();
            ExtractAccountController eAC = new ExtractAccountController();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date date = new Date();
            Extract extract = new Extract();

            User user = uc.checkIfExistUserToTrans(Login.destiny.getNumber());

            extract.setUser(Login.user);
            extract.setUserDestiny(user);
            extract.setValue(value);
            extract.setDate(sdf.format(date));

            eAC.saveTransfer(extract, kindTransfer, Login.destiny.getNumber());
        }
    }
}
