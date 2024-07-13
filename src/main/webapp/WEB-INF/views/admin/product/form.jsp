<%@ page pageEncoding="utf-8"%>
<h3>Thêm/sửa SP</h3>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form:form modelAttribute="p" method="post" enctype="multipart/form-data">
    <p>
        <label class="form-label">Name:</label>
        <form:input class="form-control" path="name"/>
        <form:errors path="name" style="color:red; font-style:italic"/>
    </p>
    <p>
        <label class="form-label">Price:</label>
        <form:input class="form-control" path="price"/>
        <form:errors path="price" style="color:red; font-style:italic"/>
    </p>
    <p>
        <label class="form-label">CategoryId:</label>
        <form:select class="form-control" path="category">
            <form:option value="">----</form:option>
            <form:options items="${categoryList}"/>
        </form:select>
        <form:errors path="category" style="color:red; font-style:italic"/>
    </p>
    <div class="mb-3">
        <label for="image" class="form-label">Ảnh sản phẩm</label>
        <input type="file" class="form-control" id="image" name="imageFile"/>
    </div>
    <c:if test="${!isUpdate}">
        <button class="btn btn-success" formaction="/admin/product/create">Add</button>
    </c:if>
    <c:if test="${isUpdate}">
        <button class="btn btn-warning" formaction="/admin/product/update/${p.id}">Update</button>
    </c:if>
</form:form>
