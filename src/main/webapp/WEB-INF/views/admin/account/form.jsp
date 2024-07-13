<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<h3>Thêm/sửa tài khoản</h3>
<form:form modelAttribute="a" method="post">
    <p><label class="form-label">Username</label>
        <form:input class="form-control" path="username"/>
        <form:errors path="username" style="color:red; font-style:italic"/>
    </p>
    <p><label class="form-label">Password</label>
        <form:input class="form-control" path="password"/>
        <form:errors path="password" style="color:red; font-style:italic"/>
    </p>
    <p><label class="form-label">Fullname</label>
        <form:input class="form-control" path="fullname"/>
        <form:errors path="fullname" style="color:red; font-style:italic"/>
    </p>
    <p><label class="form-label">Email</label>
        <form:input class="form-control" path="email"/>
        <form:errors path="email" style="color:red; font-style:italic"/>
    </p>
    <c:if test="${!isUpdate}">
        <button class="btn btn-success" formaction="/admin/account/create">Add</button>
    </c:if>
    <c:if test="${isUpdate}">
        <button class="btn btn-warning" formaction="/admin/account/update/${a.username}">Update</button>
    </c:if>
</form:form>