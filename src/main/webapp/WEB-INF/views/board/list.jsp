<%@ page import="com.example.pd.board.enums.ListResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="listVo" type="com.example.pd.vos.board.ListVo"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>게시판</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/board/resources/stylesheets/list.css">
</head>
<body> 
<%@ include file="/WEB-INF/views/header.jsp" %>
<main>
    <div>
        <div>
            <h2>
                <a href="${pageContext.request.contextPath}../board/list/notice">공지사항</a>
            </h2>
        </div>
        <div>
            <h2>
                <a href="../board/list/free">자유 게시판</a>
            </h2>
        </div>
        <div>
            <h2>
                <a href="../board/list/qna">Q&A</a>
            </h2>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>