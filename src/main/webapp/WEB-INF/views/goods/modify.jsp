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
          href="${pageContext.request.contextPath}/product/resources/stylesheets/productRegister.css">
    <script defer src="${pageContext.request.contextPath}/product/resources/scripts/register.js"></script>
    <style>
        .ck-editor__editable_inline {
            width: 40rem;
            min-height: 15rem;
        }
    </style>
    
       <c:if test="${productModifyVo.result != ProductModifyResult.SUCCESS}">
           <c:choose>
               <c:when test="${productModifyVo.result == ProductModifyResult.NOT_ALLOWED}">
                   <script>
                       alert('상품을 등록할 권한이 없습니다.');
                       window.history.back();
                   </script>
               </c:when>
               <c:when test="${productModifyVo.result == ProductModifyResult.NOT_PRODUCT_DEFINED}">
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
        <section class="productInfo">

            <div>
                <label for="productName">상품명</label>
                <input type="text" id="productName" name="name" value="${productModifyVo.name}">
            </div>

            <div>
                <label for="productPrice">가격</label>
                <input type="number" id="productPrice" name="price" value="${productModifyVo.price}">
            </div>

            <div>
                <label for="productStock">상품수량</label>
                <input type="number" id="productStock" max="32767" name="stock" value="${productModifyVo.stock}">
            </div>

            <div>
                <label for="productDes">상품소개
                    <%--                    <input type="text" value="${imageDownloadVo.fileName}">--%>
                </label>
                <textarea name="description" id="productDes" cols="10" rows="5">${productModifyVo.description}</textarea>
            </div>
        </section>

        <section>
            <label>상품 이미지</label>
            <input type="file" name="thumbnail" id="input-image" style="display: block;">
            <img src="${pageContext.request.contextPath}/resources/images/${productModifyVo.thumbnail}" alt="" id="preview-image">
        </section>
    </div>

    <div class="registerButton">
        <input type="submit" value="상품 등록">
    </div>
</form>
<%@ include file="../footer.jsp" %>
</body>
</html>
