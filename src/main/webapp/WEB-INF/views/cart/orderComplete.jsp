<%--@elvariable id="userEntity" type="com.example.pfad1.entities.user.UserEntity"--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cart/resources/stylesheets/orderComplete.css">
    <title>주문완료 페이지</title>
</head>
<%@ include file="../header.jsp"%>
<body>
<div>
    <div class="orderInfo">
        <table>
            <tbody>
            <tr>
                <th>주문번호</th>
                <td>${orderCompleteVo.orderCode}</td>
            </tr>
            <tr>
                <th>수령인</th>
                <td>${orderCompleteVo.name}</td>
            </tr>
            <tr>
                <th>우편번호</th>
                <td>${userEntity.addressPostal}</td>
            </tr>
            <tr>
                <th>기본 주소</th>
                <td>${userEntity.addressPrimary}</td>
            </tr>
            <tr>
                <th>상세 주소</th>
                <td>${userEntity.addressSecondary}</td>
            </tr>
            <tr>
                <th>연락처</th>
                <td>${orderCompleteVo.contactFirst} - ${orderCompleteVo.contactSecond} - ${orderCompleteVo.contactThird}</td>
            </tr>
            <tr>
                <th>주문일시</th>
                <td>${orderCompleteVo.formatCreatedAt()}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="orderCheckButton">
        <a href="${pageContext.request.contextPath}/home">완료</a>
    </div>
</div>
</body>
<%@ include file="../footer.jsp"%>
</html>