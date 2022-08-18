<%@ page import="com.example.pd.user.enums.IdRecoverResult" %>
<%@ page import="com.example.pd.user.enums.PasswordResetRecoverResult" %>
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
    <link rel="stylesheet" href="/user/resources/stylesheets/recover.css">
    <script src="../resources/scripts/Ajax.js"></script>
    <script defer src="${pageContext.request.contextPath}/user/resources/scripts/recoverPasswordReset.js"></script>

    <script>
        <c:if test="${passwordResetRecoverVo.result != null}">
        <c:choose>
        <c:when test="${passwordResetRecoverVo.result == PasswordResetRecoverResult.SUCCESS}">
        alert('비밀번호 변경이 완료 되었습니다. 로그인 페이지로 이동합니다.');
        window.location.href='login';
        </c:when>
        <c:otherwise>
        alert('비밀번호가 올바르지 않습니다. 다시 시도해주세요.');
        window.history.back();
        </c:otherwise>
        </c:choose>
        </c:if>
    </script>

</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<h2>비밀번호 변경</h2>
<form method="post" rel="password-recover">
    <table class="visible">
        <tbody>
        <tr>
            <th>
                <label for="id-recover-input">아이디</label>
            </th>
            <td>
                <input readonly type="text" id="id-recover-input" name="id" value="${passwordResetRecoverVo.id}">
            </td>
        </tr>
        <tr>
            <th>
                <label for="password-recover-input">비밀번호 입력</label>
            </th>
            <td>
                <input autofocus type="password" id="password-recover-input" name="password">
                <div rel="password-message" class="input-message"></div>
            </td>
        </tr>

        <tr>
            <th>
                <label for="checkPassword-recover-input">비밀번호 재입력</label>
            </th>
            <td>
                <input type="password" id="checkPassword-recover-input" name="checkPassword">
                <div rel="checkPassword-message" class="input-message"></div>
            </td>
        </tr>

        </tbody>
        <tfoot>
        <tr>
            <td>
                <input type="submit" value="비밀번호 변경">
            </td>
        </tr>
        </tfoot>
    </table>
</form>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>