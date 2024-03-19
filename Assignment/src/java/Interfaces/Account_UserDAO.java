/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import model.Account;
import model.User;

/**
 *
 * @author thang
 */
public interface Account_UserDAO {
    public void addAccount_User(User user, Account account);
    
    public User getUserByAccount(Account account);
}
