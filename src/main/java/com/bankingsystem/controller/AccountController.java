package com.bankingsystem.controller;

import com.bankingsystem.model.Account;
import com.bankingsystem.model.CheckingAccount;
import com.bankingsystem.model.SavingsAccount;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.ui.dialog.MessageBox;

import java.sql.SQLException;
import java.util.Objects;

public class AccountController extends SQLiteConnection {

    Account account;
    private MessageBox mb;

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
        }
    }

    public void updateAccount(Account account) {
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

    public Account checkIfExistAccountToTransfer(Integer agencia, Integer conta, String kindTransfer) {
        try {
            String sql = "SELECT * FROM tb_account WHERE branch = ? AND pk_number = ?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);
            preparedStatement.setInt(1, agencia);
            preparedStatement.setInt(2, conta);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.isBeforeFirst()) {
                if (Objects.equals(rs.getString("kind_account"), "SavingsAccount")) {
                    account = new SavingsAccount();
                } else if (Objects.equals(rs.getString("kind_account"), "CheckingAccount")) {
                    account = new CheckingAccount();
                }
                account.setBalance(rs.getBigDecimal("balance"));
                account.setBranch(rs.getInt("branch"));
                account.setNumber(rs.getInt("pk_number"));
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
