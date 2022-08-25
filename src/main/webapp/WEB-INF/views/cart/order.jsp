<%@ page
        import="com.example.pd.cart.enums.OrderByCartResult" %>
<%@ page import="com.example.pd.cart.enums.CartReadResult" %><%--@elvariable id="userEntity" type="com.example.pd.entities.user.UserEntity"--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cart/resources/stylesheets/cart.css">
    <link rel="stylesheet" href="../resources/stylesheets/common.css">
    <script defer src="${pageContext.request.contextPath}/cart/resources/scripts/order.js"></script>
    <c:if test="${cartReadVo.result != CartReadResult.SUCCESS}">
        <c:choose>
            <c:when test="${cartReadVo.result == CartReadResult.NOT_ALLOWED}">
                <script>
                    alert('로그인이 필요한 서비스입니다.');
                    window.location.href = '/login';
                </script>
            </c:when>
            <c:when test="${cartReadVo.result == CartReadResult.CART_NOT_DEFINED}">
                <script>
                    alert('장바구니에 상품이 없습니다.');
                    window.history.back();
                </script>
            </c:when>
        </c:choose>
    </c:if> <c:if test="${orderByCartVo.result != null}">
    <c:choose>
        <c:when test="${orderByCartVo.result == OrderByCartResult.SUCCESS}">
            <script>
                alert('주문이 완료 되었습니다.');

                window.location.href = '/orderComplete/${orderByCartVo.orderCode}';
            </script>
        </c:when>
        <c:when test="${orderByCartVo.result == OrderByCartResult.CART_NOT_DEFINED}">
            <script>
                alert('장바구니에 오류가 발생하였습니다. \n처음부터 다시 시도해주세요.');
                window.location.href = '/cart';
            </script>
        </c:when>
        <c:otherwise>
            <script>

                alert('알 수 없는 이유로 주문 실패하였습니다. \n처음부터 다시 시도해주세요.');
                window.location.href = '/home';
            </script>
        </c:otherwise>
    </c:choose>
</c:if>
    <c:if test="${orderByCartVo.result != null}">
        <c:choose>
            <c:when test="${orderByCartVo.result == OrderByCartResult.SUCCESS}">
                <script>
                    alert('주문이 완료 되었습니다.');
                    window.location.href = '/orderComplete/${orderByCartVo.orderCode}';
                </script>
            </c:when>
            <c:when test="${orderByCartVo.result == OrderByCartResult.CART_NOT_DEFINED}">
                <script>
                    alert('장바구니에 오류가 발생하였습니다. \n처음부터 다시 시도해주세요.');
                    window.location.href = '/cart';
                </script>
            </c:when>
            <c:otherwise>
                <script>
                    alert('알 수 없는 이유로 주문 실패하였습니다. \n처음부터 다시 시도해주세요.');
                    window.location.href = '/home';
                </script>
            </c:otherwise>
        </c:choose>
    </c:if>
    <title>주문하기</title>

</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<main>
    <h2>주문하기</h2>
    <form method="post" rel="cart-form">
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
            <%--            <c:if test="${orderByCartVo.result == CartReadResult.SUCCESS}">--%>
            <c:forEach var="cartReadVo" items="${cartReadVo.cartReadVos}">
                <tr style="border-top: 0.0625rem solid #909090;">
                    <input type="hidden" name="goodsIndex" value="${cartReadVo.goodsIndex}">
                    <td>
                        <img src="/resources/images/${cartReadVo.thumbnail}" alt="" style="width: 2rem;">
                    </td>
                    <td>${cartReadVo.goodsName}</td>
                    <td>
                        <span>${cartReadVo.price}</span>
                    </td>
                    <td>
                        <span>${cartReadVo.stock}</span>
                    </td>

                    <td>
                        <span class="sumPrice">${cartReadVo.price * cartReadVo.stock}</span>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/cart/delete/${cartReadVo.goodsIndex}">
                            <i class="fas fa-trash"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <%--            </c:if>--%>
            </tbody>
        </table>
    </form>
    <form method="post" rel="order-form">
        <div class="orderWrap">
            <div class="orderTotalPriceWrap">
                <h3 style="display: inline;">총 금액 : </h3>
                <span class="totalPrice"></span>
            </div>
            <div class="orderTableWrap">
                <table class="orderTable">
                    <tr>
                        <th>수령인</th>
                        <td>
                            <span>${userEntity.name}</span>
                        </td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td>
                            <div>우편번호 : ${userEntity.addressPostal}</div>
                            <div>기본 주소 : ${userEntity.addressPrimary}</div>
                            <div>상세 주소 : ${userEntity.addressSecondary}</div>
                        </td>
                    </tr>
                    <tr>
                        <th>연락처</th>
                        <td>
                            <span>${userEntity.contactFirst}</span>
                            <span>${userEntity.contactSecond}</span>
                            <span>${userEntity.contactThird}</span>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="orderSubmit">
            <input type="submit" value="주문하기">
        </div>
    </form>
</main>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>