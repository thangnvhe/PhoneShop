/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.sql.Date;
import model.User;

/**
 *
 * @author thang
 */
public interface UserDAO{
    
    public User getUserById(int id);//lay duoc thong tin khi dang nhap thanh cong
    
    public void addUser(String name, Date dob, boolean gender, String email, String number, String address);//them nguoi dung khi dang ky moi
    
    public void updateUser(User user);//update user
    
    public User getUserByName(String name);//lay dc id cua user nham tao account_user moi
}
