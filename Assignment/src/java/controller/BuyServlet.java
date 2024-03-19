/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ProductDAOImp;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author thang
 */
@WebServlet(name="BuyServlet", urlPatterns={"/buy"})
public class BuyServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuyServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         // Lấy danh sách cookie từ yêu cầu
            Cookie[] cookies = request.getCookies();

            // Tạo một flag để xác định xem có tìm thấy cookie "cart" trong danh sách không
            boolean foundCartCookie = false;

            if (cookies != null) {
                // Duyệt qua danh sách cookie để tìm cookie "cart"
                for (Cookie cookie : cookies) {
                    if ("cart".equals(cookie.getName())) {
                        String txt = cookie.getValue();
                        String[] arr = txt.split("/");
                        List<Item> list = new ArrayList<>();
                        for (String st : arr) {
                            if(!st.isEmpty()){
                                String[] str = st.split(":");
                                String id = str[0];
                                ProductDAOImp pdi = new ProductDAOImp();
                                Product p = pdi.getProductById(id);
                                
                                String quantityR = str[1];
                                int quantity = Integer.parseInt(quantityR);
                                
                                Item item = new Item(p, quantity);
                                list.add(item);
                            }
                        }
                        double total = 0;
                        for (Item item : list) {
                            total += item.getQuantity()*item.getProduct().getPrice();
                        }
                        request.setAttribute("total", total);
                        request.setAttribute("listItem", list);
                        foundCartCookie = true;
                        break;
                    }
                }

            }

        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
