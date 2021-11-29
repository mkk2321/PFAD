<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>상품 등록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/user/resources/stylesheets/product/productRegister.css">
    <script defer src="${pageContext.request.contextPath}/product/resources/scripts/ckeditor.js"></script>
    <script defer src="${pageContext.request.contextPath}/product/resources/scripts/register.js"></script>
    <style>
        .ck-editor__editable_inline {
            width: 40rem;
            min-height: 15rem;
        }
    </style>
</head>
<body class="register">
<%@ include file="../header.jsp"%>
<form method="post" rel="register-form">
    <div>
        <section class="productInfo">
            <div class="category">
                <div>
                    <label for="firstCategory">1차 분류</label>
                    <select name="firstCategory" id="firstCategory">
                        <option value="">전체</option>
                    </select>
                </div>

                <div>
                    <label for="secondCategory">2차 분류</label>
                    <select name="secondCategory" id="secondCategory">
                        <option value="">전체</option>
                    </select>
                </div>
            </div>

            <div>
                <label for="productName">상품명</label>
                <input type="text" id="productName" name="productName">
            </div>

            <div>
                <label for="productPrice">가격</label>
                <input type="number" id="productPrice" name="productPrice">
            </div>

            <div>
                <label for="productStock">상품수량</label>
                <input type="number" id="productStock" max="32767" name="productStock">
            </div>

            <div>
                <label for="productDes">상품소개</label>
                <textarea name="productDes" id="productDes" cols="10" rows="5"></textarea>
            </div>
        </section>

        <section>
            <label>상품 이미지</label>
            <textarea name="image" id="image-input" style="width: 40rem; height: 20rem;"></textarea>
        </section>
    </div>

    <div class="registerButton">
        <input type="submit" value="상품 등록">
    </div>

</form>
<%@ include file="../footer.jsp"%>
</body>
</html>