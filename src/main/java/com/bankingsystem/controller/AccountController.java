package com.bankingsystem.controller;

import com.bankingsystem.model.Account;
import com.bankingsystem.model.User;
import com.bankingsystem.util.SQLiteConnection;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountController extends SQLiteConnection {

    public boolean saveAccount(Account account) {
        connect();

        String sql = "INSERT INTO tb_account(" +
                "kind_account," +
                "balance, " +
                "branch, " +
                "number) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = createPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            preparedStatement.setString(1, account.getClass().getName().substring(24));
            preparedStatement.setBigDecimal(2, account.getBalance());
            preparedStatement.setInt(3, account.getBranch());
            preparedStatement.setInt(4, account.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        disconnect();
        return true;
    }
}
