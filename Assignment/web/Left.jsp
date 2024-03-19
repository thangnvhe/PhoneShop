<%-- 
    Document   : Left
    Created on : Mar 14, 2024, 8:28:29 PM
    Author     : thang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:if test="${not empty sessionScope.listC}">
                <c:forEach items="${sessionScope.listC}"  var="o">
                    <li class="list-group-item text-white ${(requestScope.inte==o.id)?'active':''} "><a href="category?cid=${o.id}">${o.name}</a></li>
                    </c:forEach>
                </c:if>

        </ul>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase">Last product</div>
        <div class="card-body">
            <c:set var="p" value="${requestScope.product}"/>
            <img class="img-fluid" src="${p.image}" />
            <h5 class="card-title">${p.name}</h5>
            <p class="card-text">${p.describe}</p>
            <p class="bloc_left_price">${p.price} VND</p>
        </div>
    </div>
</div>
