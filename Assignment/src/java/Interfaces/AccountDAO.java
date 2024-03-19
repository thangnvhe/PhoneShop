/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import model.Account;

public interface AccountDAO {

    public void addAccount(String username, String password);//su dung cho tao tai khoan moi 

    public boolean login(String username);//kiem tra account co ton tai khong khi dang nhap

    public void changePassword(Account account,String newpass);//thay doi mat khau

    public Account getAccount(String username, String password);//lay role trong account ==>owner or customer

}
