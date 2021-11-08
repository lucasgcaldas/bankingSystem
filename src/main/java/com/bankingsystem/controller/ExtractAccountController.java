package com.bankingsystem.controller;

import com.bankingsystem.model.Account;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.sql.PreparedStatement;
import totalcross.util.BigDecimal;

import java.sql.SQLException;

public class ExtractAccountController extends SQLiteConnection {

    private Account origin;
    private Account destiny;
    private BigDecimal value;

    public Account getOrigin() {
        return origin;
    }

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public Account getDestiny() {
        return destiny;
    }

    public void setDestiny(Account destiny) {
        this.destiny = destiny;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void saveTransfer(String kindTransfer) {
        updateTableAccount();
        try {
            String sql = "INSERT INTO tb_transfer VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setString(1, kindTransfer);
            preparedStatement.setString(2, this.origin.getClass().getName().substring(24));
            preparedStatement.setInt(3, this.origin.getBranch());
            preparedStatement.setInt(4, this.origin.getNumber());
            preparedStatement.setBigDecimal(5, this.value);
            preparedStatement.setString(6, this.destiny.getClass().getName().substring(24));
            preparedStatement.setInt(7, this.destiny.getBranch());
            preparedStatement.setInt(8, this.destiny.getNumber());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTableAccount() {
        try {
            String sql = "UPDATE tb_account" +
                    " SET " +
                    " balance = ?" +
                    " WHERE pk_number = ?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setBigDecimal(1, this.origin.getBalance());
            preparedStatement.setInt(2, this.origin.getNumber());

            preparedStatement.executeUpdate();

            preparedStatement.setBigDecimal(1, this.destiny.getBalance());
            preparedStatement.setInt(2, this.destiny.getNumber());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
