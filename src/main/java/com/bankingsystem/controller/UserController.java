package com.bankingsystem.controller;

import com.bankingsystem.model.Account;
import com.bankingsystem.model.CheckingAccount;
import com.bankingsystem.model.SavingsAccount;
import com.bankingsystem.model.User;
import com.bankingsystem.util.SQLiteConnection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;

import java.sql.SQLException;
import java.util.Objects;

public class UserController extends SQLiteConnection {

    private User user;
    private AccountController ac = new AccountController();

    public void saveUser(User user) {
        try {
            String sql = "INSERT INTO tb_user VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getCpf());
            preparedStatement.setString(4, user.getBirthDate());
            preparedStatement.setInt(5, user.getAccount1().getNumber());
            if (user.getAccount2() != null) {
                preparedStatement.setInt(6, user.getAccount2().getNumber());
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

    public User checkIfExistUserWithPassword(String senha) {
        try {
            String sql = "SELECT * FROM tb_user WHERE password = ?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);
            preparedStatement.setString(1, senha);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.isBeforeFirst()) {
                if (Objects.equals(rs.getString("password"), senha)) {
                    user = new User();
                }
                user.setName(rs.getString("name"));
                user.setCpf(rs.getString("cpf"));
                user.setBirthDate(rs.getString("birthdate"));
                user.setAccount1(ac.checkIfExistAccount(rs.getInt("account1")));
                user.setAccount2(ac.checkIfExistAccount(rs.getInt("account2")));
                user.setPassword(rs.getString("password"));
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User checkIfExistUser(String name, String senhaAntiga, String novaSenha) {
        try {
            String sql = "SELECT * FROM tb_user WHERE password = ? AND name = ?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);
            preparedStatement.setString(1, senhaAntiga);
            preparedStatement.setString(2, name);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.isBeforeFirst()) {
                if (Objects.equals(rs.getString("password"), senhaAntiga) && Objects.equals(rs.getString("name"), name)) {
                    user = new User();
                    user.setName(rs.getString("name"));
                    user.setCpf(rs.getString("cpf"));
                    user.setBirthDate(rs.getString("birthdate"));
                    user.setAccount1(ac.checkIfExistAccount(rs.getInt("account1")));
                    user.setAccount2(ac.checkIfExistAccount(rs.getInt("account2")));
                    user.setPassword(novaSenha);
                    updatePassword(user);
                }
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updatePassword(User user) {
        try {
            String sql = "UPDATE tb_user" +
                    " SET " +
                    " password = ?" +
                    " WHERE name = ?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);

            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
