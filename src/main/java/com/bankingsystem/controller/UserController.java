package com.bankingsystem.controller;

import com.bankingsystem.model.User;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.sql.PreparedStatement;

import java.sql.SQLException;

public class UserController extends SQLiteConnection {

    public void saveUser(User user) {
        try {
            String sql = "INSERT INTO tb_user VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getCpf());
            preparedStatement.setString(4, user.getBirthDate());
            preparedStatement.setInt(5, user.getAccounts().get(0).getNumber());
            if (user.getAccounts().get(1) != null) {
                preparedStatement.setInt(6, user.getAccounts().get(1).getNumber());
            } else{
                preparedStatement.setString(6, "-");
            }
            preparedStatement.setString(7, user.getPassword());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
