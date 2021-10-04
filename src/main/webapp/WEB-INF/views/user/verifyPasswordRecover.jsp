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
            alert('인증이 완료되었습니다. 비밀번호 변경 사이트로 이동합니다.');
            window.location.href='login';
        </c:when>
        <c:otherwise>
            alert('인증에 실패하였습니다. 잠시 후 다시 시도하거나 관리자에게 문의해주세요.');
            window.history.back();
        </c:otherwise>
        </c:choose>
    </script>
</head>
<body>

</body>
</html>