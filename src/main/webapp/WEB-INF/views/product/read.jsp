<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%--    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
    <script defer src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script defer src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script defer src="/user/resources/scripts/product/read.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product/resources/stylesheets/read.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <title>${productReadVo.name}</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<main>
    <section>
        <div class="adminButton button">
            <a href="/product/modify/${productReadVo.index}">상품 수정</a>
            <a href="/product/delete/${productReadVo.index}">상품 삭제</a>
        </div>
        <div>
            <section class="productImage">
                <img src="/resources/images/${productReadVo.thumbnail}" alt="">
            </section>
            <section class="productInfo">
                <h3>${productReadVo.name}</h3>
                <h4>가격 : $${productReadVo.price}</h4>
                <h4>남은 재고 수 : ${productReadVo.stock}</h4>
                <form method="post">
                    <label>
                        <h4>수량</h4>
                        <input type="number" name="stock" style="width: 2.5rem;">
                    </label>
                    <input type="submit" value="add to cart" style="width: 8rem; background-color: #008800aa">
                </form>
                <h4>${productReadVo.description}</h4>
            </section>
        </div>
        <%--<div class="button">
            <a href="#">장바구니</a>
            <a href="#">주문하기</a>
        </div>--%>
    </section>
</main>
<%@ include file="../footer.jsp" %>
</body>
</html>

