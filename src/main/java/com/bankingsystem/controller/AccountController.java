package com.bankingsystem.controller;

import com.bankingsystem.model.Account;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;

import java.sql.SQLException;

public class AccountController extends SQLiteConnection {

    public void saveAccount(Account account) {
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
        }
    }

    public void checkIfExistAccount(Account account) {
        try {
            String sql = "SELECT * FROM tb_account WHERE pk_number = ?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst()) {
                saveAccount(account);
            } else {
                account.setBalance(rs.getBigDecimal("balance"));
                account.setBranch(rs.getInt("branch"));
                account.setNumber(rs.getInt("pk_number"));
                updateAccount(account);
            }
            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("akguma coisa");
        }
    }

    public void updateAccount(Account account){
        try {
            String sql = "UPDATE tb_account" +
                    " SET " +
                    " balance = ?" +
                    " WHERE pk_number = ?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setBigDecimal(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
