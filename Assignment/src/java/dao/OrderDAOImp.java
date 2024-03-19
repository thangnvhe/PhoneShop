/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaces.OrderDAO;
import java.util.List;
import model.Order;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thang
 */
public class OrderDAOImp extends DBContext implements OrderDAO {

    @Override
    public void addOrder(Order order) {
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([user_id]\n"
                + "           ,[product_id]\n"
                + "           ,[Quantity]\n"
                + "           ,[total_price]\n"
                + "           ,[date])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, order.getUser().getId());
            st.setString(2, order.getProduct().getId());
            st.setInt(3, order.getQuantity());
            st.setDouble(4, order.getTotalprice());
            st.setDate(5, order.getDate());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Order> getOrderByUserId(User user) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT [order_id]\n"
                + "      ,[user_id]\n"
                + "      ,[product_id]\n"
                + "      ,[Quantity]\n"
                + "      ,[total_price]\n"
                + "      ,[date]\n"
                + "  FROM [dbo].[Orders]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet r = st.executeQuery();
            while(r.next()){
                Order o = new Order();
                o.setId(r.getInt("order_id"));
                UserDAOImp udi = new UserDAOImp();
                ProductDAOImp pdi = new ProductDAOImp();
                o.setUser(udi.getUserById(r.getInt("user_id")));
                o.setProduct(pdi.getProductById(r.getString("product_id")));
                o.setQuantity(r.getInt("Quantity"));
                o.setTotalprice(r.getDouble("total_price"));
                o.setDate(r.getDate("date"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

}
