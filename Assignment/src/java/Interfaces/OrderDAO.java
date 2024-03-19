/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.util.List;
import model.Order;
import model.User;

/**
 *
 * @author thang
 */
public interface OrderDAO {
    
    public void addOrder(Order order);
    
    public List<Order> getOrderByUserId(User user);
}
