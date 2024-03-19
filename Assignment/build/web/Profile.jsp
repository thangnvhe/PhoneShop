<%-- 
    Document   : Profile
    Created on : Mar 17, 2024, 5:21:24 PM
    Author     : thang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Profile <b></b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <c:set var="u" value="${requestScope.userC}"/>
                        <form action="profile" method="post">
                            <c:if test="${u==null}">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Profile</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">					
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input value="" name="name" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>DateOfBirth</label>
                                        <input value="" name="dob" type="date" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Gender</label>
                                        <input value="" name="gender" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input value="" name="email" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Number</label>
                                        <input value="" name="number" type="number" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Address</label>
                                        <input value="" name="address" type="text" class="form-control" required>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="submit" class="btn btn-success" value="Save">
                                </div>
                            </c:if>
                            <c:if test="${u!=null}">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Profile</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">					
                                    <div class="form-group">
                                        <label>ID</label>
                                        <input value="${u.id}" name="id" type="number" class="form-control"  readonly required>
                                    </div>
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input value="${u.name}" name="name" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>DateOfBirth</label>
                                        <input value="${u.dob}" name="dob" type="date" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Gender</label>
                                        <input value="${u.gender?"Male":"Female"}" name="gender" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input value="${u.email}" name="email" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Number</label>
                                        <input value="${u.number}" name="number" type="number" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Address</label>
                                        <input value="${u.address}" name="address" type="text" class="form-control" required>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="submit" class="btn btn-success" value="Change">
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>

        </div>


        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>
