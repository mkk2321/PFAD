<%@ page import="com.example.pfad1.enums.user.ModifyResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>비밀번호 변경</title>
    <link rel="stylesheet" href="../resources/stylesheets/common.css">
    <link rel="stylesheet" href="/user/resources/stylesheets/user/recover.css">
    <link rel="stylesheet" href="/user/resources/stylesheets/user/modifyPasswordCheck.css" />
    <script src="../resources/scripts/Ajax.js"></script>
    <script defer src="${pageContext.request.contextPath}/user/resources/scripts/recoverPasswordReset.js"></script>

    <script>
        <c:if test="${modifyResult != null}">
        <c:choose>
        <c:when test="${modifyResult != ModifyResult.SUCCESS}">
        alert('비밀번호가 올바르지 않습니다. 다시 시도해주세요.');
        window.history.back();
        </c:when>
        <c:otherwise>
        window.location.href='modify';
        </c:otherwise>
        </c:choose>
        </c:if>
    </script>
</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<h2>비밀번호 인증</h2>
<form method="post" rel="password-recover">
    <table class="visible">
        <tbody>
        <tr>
            <th>
                <label for="password-recover-input">현재 비밀번호</label>
            </th>
            <td>
                <input autofocus type="password" id="password-recover-input" name="password">
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td>
                <input type="submit" value="확인">
            </td>
        </tr>
        </tfoot>
    </table>
</form>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>