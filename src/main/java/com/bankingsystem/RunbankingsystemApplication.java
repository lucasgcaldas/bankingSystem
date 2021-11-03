package com.bankingsystem;

import com.bankingsystem.controller.AccountController;
import com.bankingsystem.controller.UserController;
import com.bankingsystem.model.CheckingAccount;
import com.bankingsystem.model.SavingsAccount;
import com.bankingsystem.model.User;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.TotalCrossApplication;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RunbankingsystemApplication {


    public static void main(String [] args) {
        TotalCrossApplication.run(bankingsystem.class, "/r", "5443444B5AAEEB90306B00E4");

//        UserController userController = new UserController();
//        User u1 = new User();
//        User u2 = new User();
//
//        CheckingAccount cA1 = new CheckingAccount(new BigDecimal("2000"), 111, 123456);
//        SavingsAccount sA1 = new SavingsAccount(new BigDecimal("1000"), 222, 78910);
//
//        u1.setName("Joao");
//        u1.setCpf("707.395.611-97");
//        u1.setAccount(cA1);
//        userController.saveUser(u1);
//        accountController.saveAccount(cA1);
//
//        u2.setName("Felipe");
//        u2.setCpf("706.397.123.88");
//        u2.setAccount(sA1);
//        userController.saveUser(u2);
//        accountController.saveAccount(sA1);
//
//        cA1.sendTransfer(new BigDecimal("500"), sA1);
//
//        SQLiteConnection sqLiteConnection = new SQLiteConnection();
//        System.out.println(sqLiteConnection.getConnection());
    }
}
