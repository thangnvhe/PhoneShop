/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaces.Account_UserDAO;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author thang
 */
public class Account_UserDAOImp  extends DBContext implements Account_UserDAO {
    @Override
    public void addAccount_User(User user, Account account) {
        String sql = "INSERT INTO [dbo].[Account_User]\n"
                + "           ([account_id]\n"
                + "           ,[user_id])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, account.getId());
            st.setInt(2, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public User getUserByAccount(Account account) {
        String sql = "SELECT [user_id]\n"
                + "  FROM [dbo].[Account_User]\n"
                + "  where account_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, account.getId());
            ResultSet r = st.executeQuery();
            if(r.next()){
                int userID = r.getInt("user_id");
                UserDAOImp udi = new UserDAOImp();
                User u = udi.getUserById(userID);
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
