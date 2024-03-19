/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaces.AccountDAO;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author thang
 */
public class AccountDAOImp extends DBContext implements AccountDAO {
    @Override
    public void addAccount(String username, String password) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[role])\n"
                + "     VALUES\n"
                + "           (?,?,2)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean login(String username) {
        String sql = "SELECT [account_id]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[role]\n"
                + "  FROM [dbo].[Account]\n"
                + "  where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                Account a = new Account(r.getInt("account_id"), r.getString("username"), r.getString("password"),
                        r.getInt("role"));
                if (a != null) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public void changePassword(Account account, String newpass) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [password] = ?\n"
                + " WHERE username = ? and [password] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newpass);
            st.setString(2, account.getUsername());
            st.setString(3, account.getPassword());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Account getAccount(String username, String password) {
        String sql = "SELECT [account_id]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[role]\n"
                + "  FROM [dbo].[Account]\n"
                + "  where username = ? and [password] =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                Account a = new Account(r.getInt("account_id"), r.getString("username"), r.getString("password"),
                        r.getInt("role"));
                if (a != null) {
                    return a;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
