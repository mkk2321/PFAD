<%@ page import="com.example.pd.user.enums.IdRecoverResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="idRecoverVo" type="com.example.pd.vos.user.IdRecoverVo"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>계정 찾기</title>
    <script>
        <c:if test="${idRecoverVo.result != null}">
        <c:choose>
        <c:when test="${idRecoverVo.result == IdRecoverResult.SUCCESS}">
        const email = '${idRecoverVo.id}';
        const href = `login?re=\${email}`;
        if(confirm(`이메일 찾기 결과 <\${email}> 입니다. \n\n확인을 클릭하면 이메일을 복사한 뒤 로그인 페이지로 이동합니다.`)) {
            setTimeout(() => {
                window.navigator.clipboard.writeText(email);
                window.location.href = href;
            }, 10);
        }else {
            window.location.href = href;
        }
        </c:when>
        <c:otherwise>
        alert('일치하는 회원 정보를 찾을 수 없습니다.');
        window.history.back();
        </c:otherwise>
        </c:choose>
        </c:if>
    </script>
</head>
<body>

</body>
</html>