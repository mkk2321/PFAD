<%@ page import="com.example.pd.user.enums.LoginResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>PFAD</title>
    <link rel="stylesheet" href="../resources/stylesheets/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/user/resources/stylesheets/login.css">
    <script>
        <c:if test="${loginResult != LoginResult.SUCCESS && loginResult != null}">
            <c:choose>
                <c:when test="${loginResult == LoginResult.EMAIL_VERIFIED_FAILURE}">
                    <c:set var="alert" value="이메일 인증이 완료되지 않은 아이디입니다." />
                </c:when>

                <c:when test="${loginResult == LoginResult.DELETE_FAILURE}">
                    <c:set var="alert" value="해당 아이디는 삭제 요청이 된 아이디입니다." />
                </c:when>

                <c:when test="${loginResult == LoginResult.SUSPENDED_FAILURE}">
                    <c:set var="alert" value="해당 아이디는 관리자에 의해 정지된 아이디입니다." />
                </c:when>

                <c:otherwise>
                    <c:set var="alert" value="존재하지 않는 아이디 혹은 비밀번호 입니다." />
                </c:otherwise>

            </c:choose>
        alert('${alert}');
        window.history.back();
        </c:if>
    </script>
</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp"%>
<main>
    <div class="login">
        <img src="${pageContext.request.contextPath}/resources/images/logo.jpg" alt="logo">
        <form method="post">
            <section class="inputData">
                <table>
                    <tr>
                        <td>
                            <input autofocus type="text" id="id-input" placeholder="ID" name="id">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" id="password-input" placeholder="PW" name="password">
                        </td>
                    </tr>
                </table>
            </section>
            <section class="button">
                <input type="submit" value="로그인">
            </section>
            <section class="question">
                <a href="${pageContext.request.contextPath}/recover">
                    <span>계정을 분실하셨나요 ?</span>
                </a>
                <a href="${pageContext.request.contextPath}/register">
                    <span>아직 계정이 없으신가요 ?</span>
                </a>
            </section>
        </form>
    </div>
</main>
<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>
