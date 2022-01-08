<%@ page import="com.example.pfad1.enums.board.DeleteResult" %>
<%@ page import="com.example.pfad1.enums.cart.CartDeleteResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="cartDeleteVo" type="com.example.pfad1.vos.cart.CartDeleteVo"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>장바구니 삭제</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
        <c:choose>
            <c:when test="${cartDeleteVo.result == CartDeleteResult.NOT_ALLOWED}">
                <script>
                    alert('로그인이 필요한 서비스입니다.');
                    window.location.href='/login';
                </script>
            </c:when>
            <c:when test="${cartDeleteVo.result == CartDeleteResult.CART_NOT_DEFINED}">
                <script>
                    alert('상품이 존재하지 않습니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${cartDeleteVo.result == CartDeleteResult.SUCCESS}">
                <script>
                    alert('상품을 장바구니에서 삭제하였습니다.');
                    window.location.href='/cart';
                </script>
            </c:when>
            <c:otherwise>
                <script>
                    alert('게시글을 삭제하는 도중 오류가 발생하였습니다.\n\n잠시 후 다시 시도해주세요.');
                    window.history.back();
                </script>
            </c:otherwise>
        </c:choose>
</head>
</html>