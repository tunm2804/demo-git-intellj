<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Chi Tiết Sản Phẩm</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container my-4">
  <div class="row">
    <div class="col-6">
      <img alt="${product.name}" class="product-detail-image img-fluid" src="/static/images/${product.image}" />
    </div>
    <div class="col-6 mt-5">
      <div class="product-detail-title"><h2>${product.name}</h2></div>
      <br />
      <table class="table">
        <tbody>
        <tr>
          <td>Loại sản phẩm:</td>
          <td>${product.category.name}</td>
        </tr>
        <tr>
          <td>Giá bán:</td>
          <td><b>${product.price} ₫</b></td>
        </tr>
        </tbody>
      </table>
      <br />
      <a class="btn btn-secondary" href="/">Quay lại</a>
      <a class="btn btn-primary" href="/add-to-cart/${product.id}">Thêm vào giỏ hàng</a>
    </div>
  </div>
</div>
</body>
</html>
