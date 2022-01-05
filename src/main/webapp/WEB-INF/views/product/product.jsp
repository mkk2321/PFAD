<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="userEntity" type="com.example.pfad1.entities.user.UserEntity"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>PFAD</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product/resources/stylesheets/product.css">
    <%--template link--%>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
    <%--template link--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">

</head>
<body>
<%@ include file="../header.jsp"%>
<div class="container">
    <c:if test="${userEntity.admin}">
        <div>
            <a href="${pageContext.request.contextPath}/product/register">상품 등록</a>
        </div>
    </c:if>
    <div class="row">
        <c:forEach var="product" items="${productVo.productEntities}">
            <div class="col-md-3 col-sm-6">
                <div class="product-grid3">
                    <div class="product-image3">
                        <a href="/product/read/${product.index}">
                            <img class="pic-1" src="/resources/images/${product.thumbnail}">
                            <img class="pic-2" src="/resources/images/${product.thumbnail}">
                        </a>
                        <span class="product-new-label">New</span>
                    </div>
                    <div class="product-content">
                        <h3 class="title"><a href="#">${product.name}</a></h3>
                        <div class="price">
                            <span>${product.price}원</span>
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