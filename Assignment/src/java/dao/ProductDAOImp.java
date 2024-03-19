/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaces.ProductDAO;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Category;

/**
 *
 * @author thang
 */
public class ProductDAOImp extends DBContext implements ProductDAO {

    @Override
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "      ,[releaseDate]\n"
                + "      ,[describe]\n"
                + "      ,[image]\n"
                + "      ,[cid]\n"
                + "  FROM [dbo].[Products]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet r = st.executeQuery();
            while (r.next()) {
                Product ne = new Product();
                ne.setId(r.getString("id"));
                ne.setName(r.getString("name"));
                ne.setQuantity(r.getInt("quantity"));
                ne.setPrice(r.getDouble("price"));
                ne.setReleaseDate(r.getDate("releaseDate"));
                ne.setImage(r.getString("image"));
                ne.setDescribe(r.getString("describe"));
                CategoryDAOImp cdi = new CategoryDAOImp();
                Category c = cdi.getCategoryByID(r.getInt("cid"));
                ne.setCategory(c);
                list.add(ne);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public List<Product> findProduct(int year, double lowprice, double highprice, String name, Category category) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "      ,[releaseDate]\n"
                + "      ,[describe]\n"
                + "      ,[image]\n"
                + "      ,[cid]\n"
                + "  FROM [dbo].[Products]\n"
                + "  where (name like ?) or (price between ? and ?) or (cid = ?) or (year(releaseDate) = ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            st.setDouble(2, lowprice);
            st.setDouble(3, highprice);
            if (category != null) {
                st.setInt(4, category.getId());
            } else {
                st.setInt(4, 0);
            }
            st.setInt(5, year);
            ResultSet r = st.executeQuery();
            while (r.next()) {
                Product ne = new Product();
                ne.setId(r.getString("id"));
                ne.setName(r.getString("name"));
                ne.setQuantity(r.getInt("quantity"));
                ne.setPrice(r.getDouble("price"));
                ne.setReleaseDate(r.getDate("releaseDate"));
                ne.setImage(r.getString("image"));
                ne.setDescribe(r.getString("describe"));
                CategoryDAOImp cdi = new CategoryDAOImp();
                Category c = cdi.getCategoryByID(r.getInt("cid"));
                ne.setCategory(c);
                list.add(ne);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public Product getProductById(String id) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "      ,[releaseDate]\n"
                + "      ,[describe]\n"
                + "      ,[image]\n"
                + "      ,[cid]\n"
                + "  FROM [dbo].[Products]\n"
                + "  where  id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet r = st.executeQuery();
            while (r.next()) {
                Product ne = new Product();
                ne.setId(r.getString("id"));
                ne.setName(r.getString("name"));
                ne.setQuantity(r.getInt("quantity"));
                ne.setPrice(r.getDouble("price"));
                ne.setReleaseDate(r.getDate("releaseDate"));
                ne.setImage(r.getString("image"));
                ne.setDescribe(r.getString("describe"));
                CategoryDAOImp cdi = new CategoryDAOImp();
                Category c = cdi.getCategoryByID(r.getInt("cid"));
                ne.setCategory(c);
                return ne;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void addProduct(Product p) {
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([id]\n"
                + "           ,[name]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[releaseDate]\n"
                + "           ,[describe]\n"
                + "           ,[image]\n"
                + "           ,[cid])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getId());
            st.setString(2, p.getName());
            st.setInt(3, p.getQuantity());
            st.setDouble(4, p.getPrice());
            st.setDate(5, p.getReleaseDate());
            st.setString(6, p.getDescribe());
            st.setString(7, p.getImage());
            st.setInt(8, p.getCategory().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateProduct(String id, Product p) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [id] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[releaseDate] = ?\n"
                + "      ,[describe] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[cid] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getId());
            st.setString(2, p.getName());
            st.setInt(3, p.getQuantity());
            st.setDouble(4, p.getPrice());
            st.setDate(5, p.getReleaseDate());
            st.setString(6, p.getDescribe());
            st.setString(7, p.getImage());
            st.setInt(8, p.getCategory().getId());
            st.setString(9, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteProduct(String id) {
        String sql = "DELETE FROM [dbo].[Products]\n"
                + "      WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Product getLastProduct() {
        String sql = "SELECT top 1*\n"
                + "  FROM [dbo].[Products]\n"
                + "  order by Products.releaseDate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet r = st.executeQuery();
            while (r.next()) {
                Product ne = new Product();
                ne.setId(r.getString("id"));
                ne.setName(r.getString("name"));
                ne.setQuantity(r.getInt("quantity"));
                ne.setPrice(r.getDouble("price"));
                ne.setReleaseDate(r.getDate("releaseDate"));
                ne.setImage(r.getString("image"));
                ne.setDescribe(r.getString("describe"));
                CategoryDAOImp cdi = new CategoryDAOImp();
                Category c = cdi.getCategoryByID(r.getInt("cid"));
                ne.setCategory(c);
                return ne;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //get count products
    public int countProduct() {
        String sql = "SELECT count(*)\n"
                + "  FROM [dbo].[Products]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet r = st.executeQuery();
            while (r.next()) {
                int a = r.getInt(1);
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //function divide page
    public List<Product> divide(int index) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Products]\n"
                + "order by id\n"
                + "offset ? rows fetch next 8 rows only;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index-1)*8);
            ResultSet r = st.executeQuery();
            while(r.next()){
                Product ne = new Product();
                ne.setId(r.getString("id"));
                ne.setName(r.getString("name"));
                ne.setQuantity(r.getInt("quantity"));
                ne.setPrice(r.getDouble("price"));
                ne.setReleaseDate(r.getDate("releaseDate"));
                ne.setImage(r.getString("image"));
                ne.setDescribe(r.getString("describe"));
                CategoryDAOImp cdi = new CategoryDAOImp();
                Category c = cdi.getCategoryByID(r.getInt("cid"));
                ne.setCategory(c);
                list.add(ne);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAOImp p = new ProductDAOImp();
        List<Product> list = p.divide(1);
        for (Product product : list) {
            System.out.println(product);
        }
    }

}
