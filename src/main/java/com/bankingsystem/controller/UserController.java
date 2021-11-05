package com.bankingsystem.controller;

import com.bankingsystem.model.User;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.sql.PreparedStatement;

import java.sql.SQLException;

public class UserController extends SQLiteConnection {

    public boolean saveUser(User user) {

        boolean success = true;

        try {
            String sql = "INSERT INTO tb_user VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getCpf());
            preparedStatement.setInt(4, user.getAccount().getNumber());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
}
