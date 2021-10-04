<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>상품 등록</title>
    <link rel="stylesheet" href="../../../resources/stylesheets/common.css">
    <link rel="stylesheet" href="../user/resources/stylesheets/product/productRegister.css">
    <script src="user/resources/scripts/product/productRegister.js"></script>
</head>
<body>
<%@ include file="../header.jsp"%>
<form method="post">
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
            <label for="upImgFile">상품 이미지</label>
            <input type="file" id="upImgFile" name="file" accept="image/*" onchange="uploadImgPreview();">
            <div class="productImagePreview">
                <img id="thumbnailImg" src=""/>
            </div>
        </section>
    </div>

    <div class="registerButton">
        <input type="submit" value="상품 등록">
    </div>

</form>
<%@ include file="../footer.jsp"%>
</body>
</html>