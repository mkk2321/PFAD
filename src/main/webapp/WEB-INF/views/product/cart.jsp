<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/resources/stylesheets/common.css">
    <title>장바구니</title>
</head>
<body>
<%@ include file="../header.jsp"%>
<main>
    <h2>장바구니</h2>
    <table>
        <tr>
            <th>이미지</th>
            <th>상품명</th>
            <th>금액</th>
            <th>수량</th>
            <th>총 금액</th>
        </tr>
        <tr>
            <td>${cartEntity.userId}</td>
            <td>${cartEntity.productIndex}</td>
            <td>${cartEntity.stock}</td>
        </tr>
    </table>
</main>
</body>
<%@ include file="../footer.jsp"%>
</html>