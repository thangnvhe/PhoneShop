/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author thang
 */
public interface ProductDAO {
    public List<Product> getAllProduct();
    
    public List<Product> findProduct(int year, double lowprice, double highprice ,String name, Category category);//customer find product
    
    public Product getProductById(String id);//check product has exist when we add new product
    
    public void addProduct(Product product);//owner use add new product
    
    public void updateProduct(String id, Product product);//owner use update product
    
    public void deleteProduct(String id);//owner use delete product
    
}
