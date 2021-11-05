package com.bankingsystem;

import com.bankingsystem.controller.AccountController;
import com.bankingsystem.controller.UserController;
import com.bankingsystem.main.Main;
import com.bankingsystem.model.CheckingAccount;
import com.bankingsystem.model.User;
import totalcross.TotalCrossApplication;
import totalcross.sys.InvalidNumberException;
import totalcross.util.BigDecimal;

public class RunMainApplication {


    public static void main(String[] args) throws InvalidNumberException {
        TotalCrossApplication.run(Main.class, "/scr", "360x639", "/r", "5443444B5AAEEB90306B00E4");

        User u1 = new User();
        UserController uc = new UserController();

        CheckingAccount cA1 = new CheckingAccount(new BigDecimal("5850.00"), 111, 123456);
        AccountController ac = new AccountController();
        ac.saveAccount(cA1);

        u1.setName("Lucas");
        u1.setCpf("707.395.611-97");
        u1.setAccount(cA1);
        uc.saveUser(u1);
    }
}
