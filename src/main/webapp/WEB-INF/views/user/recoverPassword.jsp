<%@ page import="com.example.pfad1.enums.user.PasswordRecoverResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="passwordRecoverResult" type="com.example.pfad1.enums.user.PasswordRecoverResult"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>계정 찾기</title>
    <script>
        <c:choose>
        <c:when test="${passwordRecoverResult == PasswordRecoverResult.SUCCESS}">
            <c:set var="alert" value="이메일로 인증코드를 보냈으니 1시간내에 확인해주세요. \n\n 로그인 페이지로 이동합니다." />
            window.location.href='login';
        </c:when>
        <c:otherwise>
        alert('일치하는 회원 정보를 찾을 수 없습니다.');
        window.history.back();
        </c:otherwise>
        </c:choose>
        alert('${alert}');
    </script>
</head>
<body>

</body>
</html>