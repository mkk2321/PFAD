<%@ page import="com.example.pfad1.enums.cart.CartReadResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cart/resources/stylesheets/cart.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
          integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
    <script defer src="${pageContext.request.contextPath}/cart/resources/scripts/cart.js"></script>
    <title>장바구니</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<main>
    <h2>장바구니</h2>
    <form method="post" rel="cart-form">
        <table style="border-bottom: 0.0625rem solid #909090; border-top: 0.0625rem solid #909090; border-collapse: separate;">
            <thead>
            <tr>
                <th><input type="checkbox" name="checkAll"></th>
                <th>이미지</th>
                <th>상품명</th>
                <th>금액</th>
                <th>수량</th>
                <th>총 금액</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${empty cartReadVo || cartReadVo == null}">
                <span>장바구니가 비었습니다.</span>
            </c:if>
            <c:if test="${cartReadVo.result == CartReadResult.SUCCESS}">
                <c:forEach var="cartReadVo" items="${cartReadVo.cartReadVos}">
                    <tr style="border-top: 0.0625rem solid #909090;">
                        <td>
                            <input type="checkbox" class="checkBox">
                        </td>
                        <td>
                            <img src="/resources/images/${cartReadVo.thumbnail}" alt="" style="width: 2rem;">
                        </td>
                        <td>${cartReadVo.productName}</td>
                        <td>
                            <input class="price" type="text" style="width: 1rem;"
                                   value="${cartReadVo.price}">
                        </td>
                        <td style="width: 6rem;">
                            <input class="stock" type="number" value="${cartReadVo.stock}" min="1"
                                   style="width: 2.5rem; display: inline;">
                            <a href="/cart/update/${cartReadVo.productIndex}/${cartReadVo.stock}">변경</a>
<%--                            <input type="submit" value="변경">--%>
                        </td>
                        <td>
                            <input type="text" class="sumPrice" style="width: 2rem;">
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart/delete/${cartReadVo.productIndex}">
                                <i class="fas fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <div>
            <h3 style="display: inline;">총 금액 : </h3>
            <span class="totalPrice"></span>
        </div>
        <div>
            <a href="/cart/delete/all">장바구니 비우기</a>
            <a href="/order">주문하기</a>
        </div>

    </form>
</main>
</body>
<%@ include file="../footer.jsp" %>
</html>
