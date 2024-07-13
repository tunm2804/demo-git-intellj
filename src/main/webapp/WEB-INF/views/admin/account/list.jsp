<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<h3>Danh sách tài khoản</h3>
<a href="/admin/account/create" class="btn btn-success">Add Account</a>
<table class="table" border="1">
    <thead>
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th>Fullname</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="a" items="${listA}">
        <tr>
            <td>${a.username}</td>
            <td>${a.password}</td>
            <td>${a.fullname}</td>
            <td>${a.email}</td>
            <td>
                <a class="btn btn-warning" href="/admin/account/update/${a.username}">Update</a>
                <a onclick="confirm('Do you want to delete Account?')" href="/admin/account/delete/${a.username}" class="btn btn-danger">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
