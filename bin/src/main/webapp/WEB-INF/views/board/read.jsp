<%@ page import="com.example.pfad1.enums.board.ReadResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="readVo" type="com.example.pfad1.vos.board.ReadVo"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${readVo.title == null ? "게시글 오류" : readVo.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/user/resources/stylesheets/board/read.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
<%--    <script defer src="${pageContext.request.contextPath}/user/resources/scripts/board/board.js"></script>--%>
    <c:if test="${readVo.result != ReadResult.SUCCESS}">
        <c:choose>
            <c:when test="${readVo.result == ReadResult.ARTICLE_NOT_DEFINED}">
                <script>
                    alert('존재하지 않는 게시글입니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${readVo.result == ReadResult.READ_NOT_ALLOWED}">
                <script>
                    alert('게시글을 읽을 권한이 없습니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:otherwise>
                <script>
                    alert('게시글을 확인하는 도중 오류가 발생하였습니다. \n\n잠시 후 다시 시도해주세요.');
                    window.history.back();
                </script>
            </c:otherwise>
        </c:choose>
        <% out.close(); %>
    </c:if>
</head>
<body class="read">
<%@ include file="../header.jsp" %>
<main>
    <section class="boardHeaderWrap">
        <div>
            <h1>${readVo.name}</h1>
            <span></span>
            <a href="/board/list/${readVo.boardCode}/${readVo.boardPage}">목록</a>
            <a href="#">수정</a>
            <a href="#">삭제</a>
        </div>
        <h3>${readVo.title}</h3>
        <div class="boardInfo">
            <ul>
                <li>아이디 : ${readVo.id}</li>
                <li>작성일 : ${readVo.formatCreatedAt()}</li>
                <li>수정일 : ${readVo.formatUpdatedAt()}</li>
                <span></span>
                <li>조회수 : ${readVo.view}</li>
                <%--            <li>${ProductReadVo.comment}</li>--%>
            </ul>
        </div>
    </section>
    <section class="boardContentWrap">
        ${readVo.content}
    </section>


</main>
<%@ include file="../footer.jsp" %>
</body>
</html>