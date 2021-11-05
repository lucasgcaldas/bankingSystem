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

    public boolean saveTransfer() {

        boolean success = true;

        try {
            String sql = "INSERT INTO tb_transfer VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setString(1, this.origin.getClass().getName().substring(24));
            preparedStatement.setBigDecimal(2, this.value);
            preparedStatement.setString(3, this.destiny.getClass().getName().substring(24));
            preparedStatement.setBigDecimal(4, this.origin.getBalance());
            preparedStatement.setBigDecimal(5, this.destiny.getBalance());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
}