<%--@elvariable id="verifyEmailResult" type="com.example.pd.enums.user.EmailVerificationResult"--%>
<%--@elvariable id="emailVerificationResult" type="com.example.pd.enums.use.EmailVerificationResult"--%>
<%@ page import="com.example.pd.user.enums.EmailVerificationResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원가입 인증</title>
    <script>
            <c:if test="${emailVerificationResult != null}">
                <c:choose>
                    <c:when test="${emailVerificationResult == EmailVerificationResult.SUCCESS}">
                        <c:set var="alert" value="회원 인증에 성공하였습니다. 로그인 페이지로 이동합니다." />
                    </c:when>
                    <c:when test="${emailVerificationResult == EmailVerificationResult.NORMALIZATION_FAILURE}">
                        <c:set var="alert" value="회원 인증에 실패하였습니다. 회원가입을 다시 진행해주세요." />
                    </c:when>
                    <c:otherwise>
                        <c:set var="alert" value="알 수 없는 이유로 회원 인증에 실패하였습니다." />
                    </c:otherwise>
                </c:choose>
            alert('${alert}');
                <c:choose>
                    <c:when test="${emailVerificationResult == EmailVerificationResult.SUCCESS}">
                        window.location.href='login';
                    </c:when>
                    <c:otherwise>
                        window.history.back();
                    </c:otherwise>
                </c:choose>
            </c:if>
    </script>
</head>
</html>