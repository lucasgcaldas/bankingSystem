package com.bankingsystem.controller;

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
            } else {
                preparedStatement.setString(6, "-");
            }
            preparedStatement.setString(7, user.getPassword());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User checkIfExistUserWithPassword(String senha, Integer conta) {
        try {
            String sql = "SELECT * FROM tb_user WHERE password = ? AND account1 = ? OR account2 =?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);
            preparedStatement.setString(1, senha);
            preparedStatement.setInt(2, conta);
            preparedStatement.setInt(3, conta);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.isBeforeFirst()) {
                if (Objects.equals(rs.getString("password"), senha) && (conta == rs.getInt("account1") || conta == rs.getInt("account2"))) {
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

    public User checkIfExistUserToMessage(String name) {
        try {
            String sql = "SELECT * FROM tb_user WHERE name = ?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.isBeforeFirst()) {
                if (Objects.equals(rs.getString("name"), name)) {
                    user = new User();
                    user.setName(rs.getString("name"));
                    user.setCpf(rs.getString("cpf"));
                    user.setBirthDate(rs.getString("birthdate"));
                    user.setAccount1(ac.checkIfExistAccount(rs.getInt("account1")));
                    user.setAccount2(ac.checkIfExistAccount(rs.getInt("account2")));
                    user.setPassword(rs.getString("password"));
                }
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User checkIfExistUserToTrans(Integer account) {
        try {
            String sql = "SELECT * FROM tb_user WHERE account1 = ? OR account2 = ?";
            PreparedStatement preparedStatement = util.con().prepareStatement(sql);
            preparedStatement.setInt(1, account);
            preparedStatement.setInt(2, account);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.isBeforeFirst()) {
                if (rs.getInt("account1") == account || rs.getInt("account2") == account) {
                    user = new User();
                    user.setName(rs.getString("name"));
                    user.setCpf(rs.getString("cpf"));
                    user.setBirthDate(rs.getString("birthdate"));
                    user.setAccount1(ac.checkIfExistAccount(rs.getInt("account1")));
                    user.setAccount2(ac.checkIfExistAccount(rs.getInt("account2")));
                    user.setPassword(rs.getString("password"));
                }
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
