<%--@elvariable id="modifyVo" type="com.example.pfad1.vos.board.ModifyVo"--%>
<%@ page import="com.example.pfad1.enums.board.WriteResult" %>
<%@ page import="com.example.pfad1.enums.board.ModifyResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="writeVo" type="com.example.pfad1.vos.board.WriteVo"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>게시글 수정</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/user/resources/stylesheets/board/read.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/user/resources/stylesheets/board/write.css">
    <script defer src="${pageContext.request.contextPath}/board/resources/scripts/ckeditor.js"></script>
    <script defer src="${pageContext.request.contextPath}/board/resources/scripts/modify.js"></script>
    <c:if test="${modifyVo.result != ModifyResult.SUCCESS}">
        <c:choose>
            <c:when test="${modifyVo.result == ModifyResult.ARTICLE_NOT_DEFINED}">
                <script>
                    alert('존재하지 않는 게시글입니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${modifyVo.result == ModifyResult.BOARD_NOT_DEFINED}">
                <script>
                    alert('존재하지 않는 게시판입니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${modifyVo.result == ModifyResult.NOT_ALLOWED}">
                <script>
                    alert('게시글을 수정할 권한이 없습니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:otherwise>
                <script>
                    alert('알 수 없는 이유로 게시글을 수정할 수 없습니다. \n\n잠시 후 다시 시도해주세요.');
                    window.history.back();
                </script>
            </c:otherwise>
        </c:choose>
        <% out.close(); %>
    </c:if>
</head>
<%@ include file="../header.jsp" %>
<body class="write">
<h1>게시글 수정</h1>
<form method="post" rel="modify-form">
    <table>
        <thead>
        <tr>
            <td>
                <input id="title-input" type="text" maxlength="100" name="title" autofocus style="width: 100%;" value="${modifyVo.title}">
            </td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="2">
                <textarea name="content" cols="30" rows="10" maxlength="10000" >${modifyVo.content}</textarea>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td>
                <div>
                    <a href="#" onclick="window.history.back();">돌아가기</a>
                    <span></span>
                    <input type="submit" value="작성">
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</form>
</body>
<%@ include file="../footer.jsp" %>
</html>