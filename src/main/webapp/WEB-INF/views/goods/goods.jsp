<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="userEntity" type="com.example.pd.entities.user.UserEntity"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/goods/resources/stylesheets/goods.css">

    <%--template link--%>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <script defer src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script defer src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
    <%--template link--%>

    <title>PFAD</title>

</head>
<body>
<%@ include file="../header.jsp"%>
<div class="container">
    <c:if test="${userEntity.admin}">
        <div>
            <a href="${pageContext.request.contextPath}/goods/register">상품 등록</a>
        </div>
    </c:if>
    <div class="row">
        <c:forEach var="goods" items="${goodsVo.goodsEntities}">
            <%-- <div class="col-md-3 col-sm-6">
                <div class="goods-grid3">
                    <div class="goods-image3">
                        <a href="/goods/read/${goods.index}">
                            <img class="pic-1" src="/resources/images/${goods.thumbnail}">
                            <img class="pic-2" src="/resources/images/${goods.thumbnail}">
                        </a>
                        <span class="goods-new-label">New</span>
                    </div>
                    <div class="goods-content">
                        <h3 class="title"><a href="#">${goods.name}</a></h3>
                        <div class="price">
                            <span>${goods.price}원</span>
                        </div>
                    </div>
                </div>
            </div> --%>
            
            <div class="card" style="width:400px">
			  <img class="card-img-top" src="/resources/images/${goods.thumbnail}" alt="Card image">
			  <div class="card-body">
			    <h4 class="card-title">${goods.name }</h4>
			    <p class="card-text">Some example text.</p>
			    <a href="/goods/read/${goods.index}" class="btn btn-primary">See Profile</a>
			  </div>
			</div>
        </c:forEach>
    </div>
</div>
<%@ include file="../footer.jsp"%>
</body>
</html>