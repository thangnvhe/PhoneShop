/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAOImp;
import dao.Account_UserDAOImp;
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
import model.Account;
import model.Item;
import model.Product;
import model.User;

/**
 *
 * @author thang
 */
@WebServlet(name = "ShowCart", urlPatterns = {"/show"})
public class ShowCart extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String id = request.getParameter("option");
        String quantityR = request.getParameter("quantity");

        HttpSession se = request.getSession();
        AccountDAOImp adi = new AccountDAOImp();
        ProductDAOImp pdi = new ProductDAOImp();

        int quantity = Integer.parseInt(quantityR);
        Product p = pdi.getProductById(id);
        Account_UserDAOImp audi = new Account_UserDAOImp();
        if (se.getAttribute("acc") != null) {
            Account acc = (Account) se.getAttribute("acc");
            User u = audi.getUserByAccount(acc);
            se.setAttribute("user", u);
            
            // Lấy danh sách cookie từ yêu cầu
            Cookie[] cookies = request.getCookies();

            // Tạo một flag để xác định xem có tìm thấy cookie "cart" trong danh sách không
            boolean foundCartCookie = false;

            if (cookies != null) {
                // Duyệt qua danh sách cookie để tìm cookie "cart"
                for (Cookie cookie : cookies) {
                    if ("cart".equals(cookie.getName())) {
                        // Đặt lại giá trị của cookie
                        cookie.setValue(cookie.getValue() + p.getId().trim() + ":" + quantity + "/");
                        // Đặt lại thời gian sống của cookie nếu cần
                        cookie.setMaxAge(60 * 60 * 24 * 3); // Ví dụ: 1 giờ
                        // Gắn cookie đã chỉnh sửa vào phản hồi HTTP
                        response.addCookie(cookie);
                        // Đánh dấu rằng đã tìm thấy cookie "cart"
                        foundCartCookie = true;
                        break;
                    }
                }

            }

            // Nếu không tìm thấy cookie "cart", tạo một cookie mới
            if (!foundCartCookie) {
                Cookie newCartCookie = new Cookie("cart", p.getId().trim() + ":" + quantity + "/");
                newCartCookie.setMaxAge(60 * 60 * 24 * 3); // Ví dụ: 1 giờ
                response.addCookie(newCartCookie);
            }
        }
        request.getRequestDispatcher("home").forward(request, response);
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
