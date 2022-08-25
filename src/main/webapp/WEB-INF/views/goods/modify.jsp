<%@ page import="com.example.pd.goods.enums.GoodsRegisterResult" %>
<%@ page import="com.example.pd.goods.enums.GoodsModifyResult" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>상품 수정</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/goods/resources/stylesheets/goodsRegister.css">
    <script defer src="${pageContext.request.contextPath}/goods/resources/scripts/register.js"></script>
    <style>
        .ck-editor__editable_inline {
            width: 40rem;
            min-height: 15rem;
        }
    </style>
    
       <c:if test="${goodsModifyVo.result != GoodsModifyResult.SUCCESS}">
           <c:choose>
               <c:when test="${goodsModifyVo.result == GoodsModifyResult.NOT_ALLOWED}">
                   <script>
                       alert('상품을 등록할 권한이 없습니다.');
                       window.history.back();
                   </script>
               </c:when>
               <c:when test="${goodsModifyVo.result == GoodsModifyResult.NOT_GOODS_DEFINED}">
                   <script>
                       alert('상품을 찾을 수 없습니다.');
                       window.history.back();
                   </script>
               </c:when>
               <c:otherwise>
                   <script>
                       alert('알 수 없는 이유로 접근에 실패하였습니다.');
                       window.history.back();
                   </script>
               </c:otherwise>
           </c:choose>
       </c:if>

</head>
<body class="register">
<%@ include file="../header.jsp" %>
<form method="post" rel="register-form">
    <div>
        <section class="goodsInfo">

            <div>
                <label for="goodsName">상품명</label>
                <input type="text" id="goodsName" name="name" value="${goodsModifyVo.name}">
            </div>

            <div>
                <label for="goodsPrice">가격</label>
                <input type="number" id="goodsPrice" name="price" value="${goodsModifyVo.price}">
            </div>

            <div>
                <label for="goodsStock">상품수량</label>
                <input type="number" id="goodsStock" max="32767" name="stock" value="${goodsModifyVo.stock}">
            </div>

            <div>
                <label for="goodsDes">상품소개
                    <%--                    <input type="text" value="${imageDownloadVo.fileName}">--%>
                </label>
                <textarea name="description" id="goodsDes" cols="10" rows="5">${goodsModifyVo.description}</textarea>
            </div>
        </section>

        <section>
            <label>상품 이미지</label>
            <input type="file" name="thumbnail" id="input-image" style="display: block;">
            <img src="${pageContext.request.contextPath}/resources/images/${goodsModifyVo.thumbnail}" alt="" id="preview-image">
        </section>
    </div>

    <div class="registerButton">
        <input type="submit" value="상품 등록">
    </div>
</form>
<%@ include file="../footer.jsp" %>
</body>
</html>
