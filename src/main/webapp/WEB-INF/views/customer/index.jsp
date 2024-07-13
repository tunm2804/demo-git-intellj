<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="my-4">
  <div class="row">
    <div class="col-3 p-3 card">
      <form>
        <div class="product-search-info mt-3">
          <label class="mb-1"><b>Tên sản phẩm:</b></label>
          <input name="keyword" value="${param.keyword}" class="form-control" placeholder="Nhập tên sản phẩm để tìm" />
        </div>
        <div class="brand-search-info mt-3">
          <label><b>Loại sản phẩm:</b></label>
          <div class="mt-2">
            <input type="radio" name="categoryId" checked value="" />
            <span>Tất cả</span>
          </div>
          <c:forEach var="c" items="${categoryList}">
            <div class="mt-1">
              <input name="categoryId" type="radio" value="${c.id}"
                     <c:if test="${param.categoryId==c.id}">checked</c:if>
              />
              <span>${c.name}</span>
            </div>
          </c:forEach>
        </div>

        <div class="price-search-info mt-3">
          <label><b>Mức giá:</b></label>
          <c:forEach var="pr" items="${priceRangeList}">
            <div class="mt-2">
              <input type="radio" name="priceRangeId" value="${pr.id}"
                     <c:if test="${param.priceRangeId==pr.id}">checked</c:if>
              />
              <span>${pr.display}</span>
            </div>
          </c:forEach>
        </div>
        <button type="submit" class="btn btn-primary mt-4 mb-4">Tìm kiếm</button>
      </form>
    </div>

    <div class="col-9">
      <ul class="list-unstyled row">
        <c:forEach var="p" items="${page.content}">
          <li class="list-item col-sm-4 mt-3">
            <div class="item-container">
              <a href="/detail/${p.id}" class="product-item">
                <img src="/static/images/${p.image}" class="product-image" alt="" />
                <div class="item-info">
                  <div>
                    <span class="product-name">${p.name}</span>
                  </div>
                  <div>
                    <span class="price-title">Giá bán :</span>
                    <span class="price">${p.price} ₫</span>
                  </div>
                </div>
              </a>
            </div>
          </li>
        </c:forEach>
      </ul>
      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item"><a class="page-link" href="?p=0">&laquo;</a></li>
          <c:if test="${page.number > 0}">
            <li class="page-item"><a class="page-link" href="?p=${page.number - 1}">&lsaquo;</a></li>
          </c:if>
          <li class="page-item"><a class="page-link" href="?p=${page.number + 1}" <c:if test="${page.number == page.totalPages - 1}">style="display: none;"</c:if>>&rsaquo;</a></li>
          <li class="page-item"><a class="page-link" href="?p=${page.totalPages - 1}" <c:if test="${page.number == page.totalPages - 1}">style="display: none;"</c:if>>&raquo;</a></li>
        </ul>
        <span><div>Trang số: ${page.number + 1}/${page.totalPages}</div></span>
      </nav>
    </div>
  </div>
</div>