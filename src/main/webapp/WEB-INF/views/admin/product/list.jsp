<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<h3>Danh sách SP</h3>
<form action="product">
<p>Keyword:<input class="form-control" name="keyword" value="${param.keyword}"></p>
    <button>Search</button>
</form>
<a class="btn btn-success" href="/admin/product/create">Add Product</a>
<%--<a href="/product/sort-by-price-asc">Sort</a>--%>
<table class="table" border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Category</th>
        <th>Image</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.content}" var="p">
        <tr>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.category.name}</td>
            <td>
            <img src="/static/images/${p.image}"  alt="" style="width: 20%; border-radius: 2px"></td>
            </td>
            <td>
                <a class="btn btn-warning" href="/admin/product/update/${p.id}">Update</a>
                <a onclick="return confirm('Do you want delete Product!')"
                   href="/admin/product/delete/${p.id}" class="btn btn-danger">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <a href="product?keyword=${keyword}&p=0">First</a>
    <a href="product?keyword=${keyword}&p=0"class="disabled" disabled="${page.number == 0}">Frev</a>
    <a href="product?keyword=${keyword}&p=${page.totalPages-1}"class="disabled" disabled="${page.number == page.totalPages-1}">Next</a>
    <a href="product?keyword=${keyword}&p=${page.totalPages-1}">Last</a>
</div>
<div>Trang so: ${page.number+1}/${page.totalPages}</div>