<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>상품 등록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/user/resources/stylesheets/product/product.css">
    <%--template link--%>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
    <%--template link--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <c:if test="${productDeleteVo.result != SUCCESS}">

    </c:if>

</head>
<%--<body>
<%@ include file="../header.jsp"%>
<section>
    <div>
        <div>
            <a href="${pageContext.request.contextPath}/product/register">상품 등록</a>
        </div>
        <ul>
            &lt;%&ndash;@elvariable id="productVo" type="com.example.pfad1.vos.product.ProductVo"&ndash;%&gt;
            &lt;%&ndash;@elvariable id="imageUploadVo" type="com.example.pfad1.vos.board.ImageUploadVo"&ndash;%&gt;
            <c:forEach var="product" items="${productVo.productEntities}">
                <li>
                    <a href="/product/read/${product.index}">
                        <div class="imgContent">
                            <img src="${pageContext.request.contextPath}/resources/images/${product.thumbnail}" alt="">
                        </div>
                        <div>
                            <h4>${product.name}</h4>
                            <h5>$${product.price}</h5>
                            <p></p>
                        </div>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>

</section>
<%@ include file="../footer.jsp"%>
</body>--%>
<body>
<%@ include file="../header.jsp"%>
<div class="container">
    <div class="row">
        <div>
            <a href="${pageContext.request.contextPath}/product/register">상품 등록</a>
        </div>
        <c:forEach var="product" items="${productVo.productEntities}">
            <div class="col-md-3 col-sm-6">
                <div class="product-grid3">
                    <div class="product-image3">
                        <a href="/product/read/${product.index}">
                            <img class="pic-1" src="/resources/images/${product.thumbnail}">
                            <img class="pic-2" src="/resources/images/${product.thumbnail}">
                        </a>
                        <ul class="social">
                            <li><a href="#"><i class="fa fa-shopping-bag"></i></a></li>
                            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                        </ul>
                        <span class="product-new-label">New</span>
                    </div>
                    <div class="product-content">
                        <h3 class="title"><a href="#">${product.name}</a></h3>
                        <div class="price">
                            $${product.price}
                        </div>
                            <%--<ul class="rating">
                                <li class="fa fa-star"></li>
                                <li class="fa fa-star"></li>
                                <li class="fa fa-star"></li>
                                <li class="fa fa-star disable"></li>
                                <li class="fa fa-star disable"></li>
                            </ul>--%>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@ include file="../footer.jsp"%>
</body>
</html>