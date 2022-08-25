<%@ page import="com.example.pd.cart.enums.CartAddResult" %>
<%@ page import="com.example.pd.goods.enums.GoodsReadResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="userEntity" type="com.example.pd.entities.user.UserEntity"--%>
<%--@elvariable id="cartAddVo" type="com.example.pd.vos.cart.CartAddVo"--%>
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
    <script defer src="/goods/resources/scripts/read.js/"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/goods/resources/stylesheets/read.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <c:if test="${goodsReadVo.result != goodsReadResult.SUCCESS}">
        <c:choose>
            <c:when test="${goodsReadVo.result == goodsReadResult.GOODS_NOT_DEFINED}">
                <script>
                    alert('해당 상품은 존재하지 않습니다.');
                    window.history.back();
                </script>
            </c:when>
        </c:choose>
    </c:if>
    <c:if test="${cartAddVo.result != null}">
        <c:choose>
            <c:when test="${cartAddVo.result == CartAddResult.NOT_ALLOWED}">
                <script>
                    alert('로그인이 필요한 서비스입니다.');
                    window.location.href='/login';
                </script>
            </c:when>
            <c:when test="${cartAddVo.result == CartAddResult.SUCCESS}">
                <script>
                    if(confirm('장바구니에 상품을 담았습니다. \n장바구니로 이동 하시겠습니까?')) {
                        window.location.href='/cart';
                    } else {
                        window.history.back();
                    }
                </script>
            </c:when>
            <c:otherwise>
                <script>
                    alert('알 수 없는 이유로 실패하였습니다.\n\n잠시 후 다시 시도해주세요.');
                    window.history.back();
                </script>
            </c:otherwise>
        </c:choose>
    </c:if>
    <title>${goodsReadVo.name}</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<main>
    <section>
        <c:if test="${userEntity.admin}">
            <div class="adminButton button">
                <a href="/goods/modify/${goodsReadVo.index}">상품 수정</a>
                <a href="/goods/delete/${goodsReadVo.index}">상품 삭제</a>
            </div>
        </c:if>
        <div>
            <section class="goodsImage">
                <img src="/resources/images/${goodsReadVo.thumbnail}" alt="">
            </section>
            <section class="goodsInfo">
                <h3>${goodsReadVo.name}</h3>
                <h4>가격 : <span style="font-weight: normal;">${goodsReadVo.price}원</span></h4>
                <h4>남은 재고 수 : <span style="font-weight: normal;">${goodsReadVo.stock}</span></h4>
                <form method="post">
                    <label>
                        <span style="margin-right: 0.33rem; font-weight: bold">수량</span>
                        <input type="number" name="stock" style="width: 2.5rem;" min="1" value="1">
                    </label>
                        <input type="submit" value="add to cart" style="width: 8rem; background-color: #008800aa">
                </form>
                <span style=" margin: 2rem 0; width: 20rem;">${goodsReadVo.description}</span>
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

