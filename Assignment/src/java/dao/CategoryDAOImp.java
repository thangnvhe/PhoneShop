/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaces.CategoryDAO;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thang
 */
public class CategoryDAOImp extends DBContext implements CategoryDAO {

    @Override
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT [category_id]\n"
                + "      ,[name]\n"
                + "      ,[describe]\n"
                + "  FROM [dbo].[Categories]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet r = st.executeQuery();
            while (r.next()) {
                Category c = new Category();
                c.setId(r.getInt("category_id"));
                c.setName(r.getString("name"));
                c.setDescribe(r.getString("describe"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public Category getCategoryByID(int cid) {
        String sql = "SELECT [category_id]\n"
                + "      ,[name]\n"
                + "      ,[describe]\n"
                + "  FROM [dbo].[Categories]\n"
                + "  where category_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                Category c = new Category();
                c.setId(cid);
                c.setName(r.getString("name"));
                c.setDescribe(r.getString("describe"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void addCategory(Category c) {
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([category_id]\n"
                + "           ,[name]\n"
                + "           ,[describe])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getId());
            st.setString(2, c.getName());
            st.setString(3, c.getDescribe());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
