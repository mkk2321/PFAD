<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.pd.cart.enums.CartUpdateResult" %>
<%@ page import="com.example.pd.cart.enums.CartReadResult" %>
<%@ page import="com.example.pd.cart.enums.OrderListResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cart/resources/stylesheets/orderList.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
          integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
    <script defer src="${pageContext.request.contextPath}/cart/resources/scripts/cart.js"></script>
    <title>장바구니</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<main>
    <h2>주문조회</h2>
    <table>
        <c:if test="${orderListVo.result == OrderListResult.ORDER_NOT_DEFINED}">
            <span>주문내역이 없습니다.</span>
        </c:if>
        <c:if test="${orderListVo.result == OrderListResult.SUCCESS}">
        <thead>
        <tr>
            <th>이미지</th>
            <th>상품명</th>
            <th>주문일자</th>
            <th>주문번호</th>
            <th>주문금액(수량)</th>
            <th>주문상태</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="orderList" items="${orderListVo.orderEntities}">
                <tr>
                    <td>
                        <img src="/resources/images/${orderList.thumbnail}" style="width: 3rem;">
                    </td>
                    <td>
                            ${orderList.goodsName}
                    </td>
                    <td>${orderListVo.formatCreatedAt()}</td>
                    <td>${orderList.orderCode}</td>
                    <td>${orderList.stock * orderList.price}(${orderList.stock})</td>
                    <td>입금대기</td>
                    <td>
                        <a href="#" onclick="if(confirm('주문을 취소 하시겠습니까?')) window.location.href='/order-list/delete/${orderList.goodsIndex}/${orderList.orderCode}';">주문 취소</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

</main>
</body>
<%@ include file="../footer.jsp" %>
</html>
