<%@ page import="com.example.pd.board.enums.DeleteResult" %>
<%@ page import="com.example.pd.goods.enums.GoodsDeleteResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="deleteVo" type="com.example.pd.vos.board.DeleteVo"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>상품 삭제</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
        <c:choose>
            <c:when test="${goodsDeleteVo.result == GoodsDeleteResult.NORMALIZATION_FAILURE}">
                <script>
                    alert('존재하지 않는 게시글입니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${goodsDeleteVo.result == GoodsDeleteResult.NOT_ALLOWED}">
                <script>
                    alert('게시글을 삭제 할 권한이 없습니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${goodsDeleteVo.result == GoodsDeleteResult.SUCCESS}">
                <script>
                    alert('게시글을 삭제하였습니다.');
                    window.location.href='/goods';
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