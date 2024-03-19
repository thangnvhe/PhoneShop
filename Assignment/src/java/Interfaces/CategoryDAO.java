/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.util.List;
import model.Category;

/**
 *
 * @author thang
 */
public interface CategoryDAO {
    public List<Category> getAllCategory();//dung cho thanh cong cu tim kiem
    
    public Category getCategoryByID(int cid);//table product dat la cid int
    
    public void addCategory(Category c);//khi addProduct or update product
}
