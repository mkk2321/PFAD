<%@ page import="com.example.pd.cart.enums.CartUpdateResult" %>
<%@ page import="com.example.pd.cart.enums.CartReadResult" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:if test="${cartReadVo.result == CartReadResult.NOT_ALLOWED}">
        <script>
            alert('로그인이 필요한 서비스입니다.');
            window.location.href = '/login';
        </script>
    </c:if>
    <c:if test="${cartUpdateVo.result != null}">
        <c:choose>
            <c:when test="${cartUpdateVo.result == CartUpdateResult.NOT_ALLOWED}">
                <script>
                    alert('로그인이 필요한 서비스입니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${cartUpdateVo.result == CartUpdateResult.SUCCESS}">
                <script>
                    alert('장바구니의 상품을 수정하였습니다.');
                    window.location.href = '/cart';
                </script>
            </c:when>
            <c:otherwise>
                <script>
                    alert('장바구니에 접속하지 못하였습니다.\n\n잠시 후 다시 시도해주세요.');
                    window.history.back();
                </script>
            </c:otherwise>
        </c:choose>
    </c:if>
    <title>장바구니</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<main>
    <h2>장바구니</h2>
    <form method="post" rel="cart-form">

            <c:if test="${cartReadVo.result == CartReadResult.CART_NOT_DEFINED}">
                <span style="width: 100%; text-align: center;">장바구니가 비었습니다.</span>
            </c:if>

            <c:if test="${cartReadVo.result == CartReadResult.SUCCESS}">
        <table style="border-bottom: 0.0625rem solid #909090; border-top: 0.0625rem solid #909090; border-collapse: separate;">
            <thead>
            <tr>
                    <%--                <th><input type="checkbox" name="checkAll"></th>--%>
                <th>이미지</th>
                <th>상품명</th>
                <th>금액</th>
                <th>수량</th>
                <th>총 금액</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
                <c:forEach var="cartReadVo" items="${cartReadVo.cartReadVos}">
                    <tr style="border-top: 0.0625rem solid #909090;">
                            <%--                        <td>--%>
                            <%--                            <input type="checkbox" class="checkBox">--%>
                            <%--                        </td>--%>
                        <input type="hidden" name="productsIndex" value="${cartReadVo.productIndex}">
                        <td>
                            <img src="/resources/images/${cartReadVo.thumbnail}" alt="" style="width: 2rem;">
                        </td>
                        <td>${cartReadVo.productName}</td>
                        <td>
                            <input class="price" type="text" value="${cartReadVo.price}" readonly>
                        </td>
                        <td>
                            <input class="stock" type="number" name="stocks" value="${cartReadVo.stock}" min="1" max="20" style="width: 2.5rem; display: inline;">
                                <%--                            <a href="/cart/update/${cartReadVo.productIndex}">변경</a>--%>
                        </td>

                        <td>
                            <input type="text" class="sumPrice" readonly>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart/delete/${cartReadVo.productIndex}">
                                <i class="fas fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <div class="totalPriceWrap">
            <h3 style="display: inline;">총 금액 : </h3>
            <input type="text" name="totalPrice" class="totalPrice" style="width: 5.5%;" readonly>
            <span>원</span>
        </div>
        <div class="cartButton">
            <a href="/cart/delete/all"
               onclick="if(confirm('정말 장바구니를 비우시겠습니까?')) window.location.href='/cart/delete/all';">장바구니 비우기</a>
            <input type="submit" value="장바구니 수정" style="display: inline; cursor: pointer;">

            <a href="/order">주문하기</a>
            <%--            <input type="submit" value="주문하기">--%>
        </div>
            </c:if>
    </form>

</main>
</body>
<%@ include file="../footer.jsp" %>
</html>
