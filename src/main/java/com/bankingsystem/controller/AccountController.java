package com.bankingsystem.controller;

import com.bankingsystem.model.Account;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.sql.PreparedStatement;

import java.sql.SQLException;

public class AccountController extends SQLiteConnection {

    public boolean saveAccount(Account account) {

        boolean success = true;

        try {
            String sql = "INSERT INTO tb_account VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setString(1, account.getClass().getName().substring(24));
            preparedStatement.setBigDecimal(2, account.getBalance());
            preparedStatement.setInt(3, account.getBranch());
            preparedStatement.setInt(4, account.getNumber());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }
}