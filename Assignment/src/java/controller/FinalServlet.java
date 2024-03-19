/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAOImp;
import dao.ProductDAOImp;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import model.Item;
import model.Order;
import model.Product;
import model.User;

/**
 *
 * @author thang
 */
@WebServlet(name = "FinalServlet", urlPatterns = {"/final"})
public class FinalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Object ob = session.getAttribute("user");
        if (ob != null) {
            User user = (User) ob;
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
                            if (!st.isEmpty()) {
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
                        double total = 1;
                        for (Item item : list) {
                            total = item.getQuantity() * item.getProduct().getPrice();
                            int id = 1;
                            LocalDate currentDate = LocalDate.now();
                            Order order = new Order(id, user, item.getProduct(), item.getQuantity(), total, Date.valueOf(currentDate));
                            OrderDAOImp odi = new OrderDAOImp();
                            odi.addOrder(order);
                            ProductDAOImp pdi = new ProductDAOImp();
                            String pid = item.getProduct().getId();
                            Product product = item.getProduct();
                            product.setQuantity(product.getQuantity()-item.getQuantity());
                            pdi.updateProduct(pid, product);
                        }
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        foundCartCookie = true;
                        break;
                    }
                }

            }
            if (!foundCartCookie) {
                response.sendRedirect("home");
            }
            request.getRequestDispatcher("home").forward(request, response);
        }else{
            response.sendRedirect("home");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
