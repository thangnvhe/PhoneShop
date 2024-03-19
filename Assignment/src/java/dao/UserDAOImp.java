/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaces.UserDAO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author thang
 */
public class UserDAOImp extends DBContext implements UserDAO {


    @Override
    public User getUserById(int id) {
        String sql = "SELECT [user_id]\n"
                + "      ,[name]\n"
                + "      ,[dob]\n"
                + "      ,[gender]\n"
                + "      ,[email]\n"
                + "      ,[number]\n"
                + "      ,[address]\n"
                + "  FROM [dbo].[Users]\n"
                + "  where user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                User user = new User(r.getInt("user_id"), r.getString("name"), r.getDate("dob"),
                        r.getBoolean("gender"), r.getString("email"),
                        r.getString("number"), r.getString("address"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    

    @Override
    public void addUser(String name, Date dob, boolean gender, String email, String number, String address) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([name]\n"
                + "           ,[dob]\n"
                + "           ,[gender]\n"
                + "           ,[email]\n"
                + "           ,[number]\n"
                + "           ,[address])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setDate(2, dob);
            st.setBoolean(3, gender);
            st.setString(4, email);
            st.setString(5, number);
            st.setString(6, address);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [name] = ?\n"
                + "      ,[dob] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[number] = ?\n"
                + "      ,[address] = ?\n"
                + " WHERE user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setDate(2, user.getDob());
            st.setBoolean(3, user.isGender());
            st.setString(4, user.getEmail());
            st.setString(5, user.getNumber());
            st.setString(6, user.getAddress());
            st.setInt(7, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public User getUserByName(String name) {
        String sql = "SELECT [user_id]\n"
                + "      ,[name]\n"
                + "      ,[dob]\n"
                + "      ,[gender]\n"
                + "      ,[email]\n"
                + "      ,[number]\n"
                + "      ,[address]\n"
                + "  FROM [dbo].[Users]\n"
                + "  where [name] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                User user = new User(r.getInt("user_id"), r.getString("name"), r.getDate("dob"),
                        r.getBoolean("gender"), r.getString("email"),
                        r.getString("number"), r.getString("address"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public static void main(String[] args) {
        UserDAOImp udi = new UserDAOImp();
        User u = udi.getUserByName("Nguyễn Văn Bảo");
        System.out.println(u.getAddress());
    }
}
