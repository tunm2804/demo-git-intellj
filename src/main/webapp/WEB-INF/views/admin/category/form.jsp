<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<h3>Thêm/sửa nhóm SP</h3>
<form:form method="post" modelAttribute="cate">
  <p><label class="form-label">Id</label>
    <form:input class="form-control" path="id"/>
    <form:errors path="id" style="color:red; font-style:italic"/>
  </p>
  <p><label class="form-label">Name</label>
    <form:input class="form-control" path="name"/>
    <form:errors path="name" style="color:red; font-style:italic"/>
  </p>
<%--  <c:if test="${!isUpdate}">--%>
<%--    --%>
<%--  </c:if>--%>
  <button class="btn btn-success" formaction="/admin/category/create">Add</button>
<%--  <c:if test="${isUpdate}">--%>
<%--    <button class="btn btn-warning" formaction="/admin/category/update/${cate.id}">Update</button>--%>
<%--  </c:if>--%>
</form:form>