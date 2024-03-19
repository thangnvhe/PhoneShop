/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAOImp;
import dao.Account_UserDAOImp;
import dao.UserDAOImp;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import model.Account;
import model.User;

/**
 *
 * @author thang
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet ProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
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
        AccountDAOImp adi = new AccountDAOImp();
        Account_UserDAOImp audi = new Account_UserDAOImp();
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        Account ac = adi.getAccount(user, pass);
        User us = audi.getUserByAccount(ac);
        request.setAttribute("userC", us);
        request.getRequestDispatcher("Profile.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String number = request.getParameter("number");
        String address = request.getParameter("address");

        // Check if user object is present
        Object u = request.getAttribute("u");
        if (u == null) {
            //b1 add into user table
            boolean geder;
            if (gender.equalsIgnoreCase("male")) {
                geder = true;
            } else {
                geder = false;
            }
            Date date = Date.valueOf(dob);
            UserDAOImp udi = new UserDAOImp();
            udi.addUser(name, date, geder, email, number, address);
            //b2 add into Account_UserDAO
            HttpSession session = request.getSession();
            Object acc = session.getAttribute("acc");
            if (acc != null && acc instanceof Account) {
                Account account = (Account) acc;
                User user = udi.getUserByName(name);
                Account_UserDAOImp audi = new Account_UserDAOImp();
                if (!user.equals(audi.getUserByAccount(account))) {
                    audi.addAccount_User(user, account);
                }
            }
            response.sendRedirect("home");
        } else if (u instanceof User) {
            User user = (User) u;
            UserDAOImp udi = new UserDAOImp();
            udi.updateUser(user);
            response.sendRedirect("home");
        }
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
