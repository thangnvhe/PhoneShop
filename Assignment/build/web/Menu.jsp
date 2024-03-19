<%-- 
    Document   : Menu
    Created on : Mar 14, 2024, 8:26:53 PM
    Author     : thang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="java.util.ArrayList" %>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home">Shoes</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <c:if test="${sessionScope.acc.role == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Manager Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="manager">Manager Product</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="profile?user=${sessionScope.acc.username}&pass=${sessionScope.acc.password}">Hello ${sessionScope.acc.username}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="login">Login</a>
                    </li>
                </c:if>
            </ul>

            <form action="search" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input value="${requestScope.nec}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <a class="btn btn-success btn-sm ml-3" href="buy">
                    <i class="fa fa-shopping-cart"></i> Cart
                    
                        <%
                         Cookie[] cookies = request.getCookies(); // Lấy danh sách các cookie từ yêu cầu
                         String cookieValue = ""; // Giá trị cookie mặc định
                            // Duyệt qua danh sách cookie để tìm cookie cần lấy giá trị
                            if (cookies != null) {
                                 for (Cookie cookie : cookies) {
                                    if ("cart".equals(cookie.getName())) { // Thay "yourCookieName" bằng tên cookie của bạn
                                        cookieValue = cookie.getValue(); // Lấy giá trị của cookie
                                        String[] arr = cookieValue.split("/");
                                        ArrayList<String> tempList = new ArrayList<>();
                                        for (String element : arr) {
                                            if (!element.isEmpty()) { // Kiểm tra xem phần tử có rỗng không
                                            tempList.add(element); // Thêm phần tử vào danh sách tạm thời nếu không rỗng
                                            }
                                        }
                               %>
                               <span class="badge badge-light"><%=tempList.size()%></span>
                               <%
                                    }
                                }
                            }
                        %>
                     
                </a>
            </form>
        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Siêu thị điện thoại chất lượng cao</h1>
        <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu với hơn 10 năm cung cấp sản phầm</p>
    </div>
</section>
<!--end of menu-->

