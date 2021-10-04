<%@ page import="com.example.pfad1.enums.board.ListResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="listVo" type="com.example.pfad1.vos.board.ListVo"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${listVo.name == null ? "게시판 없음" : listVo.name}</title>
    <link rel="stylesheet" href="../../../resources/stylesheets/common.css">
    <link rel="stylesheet" href="/user/resources/stylesheets/board/something.css">
    <c:if test="${listVo.result != ListResult.SUCCESS}">
        <c:choose>
            <c:when test="${listVo.result == ListResult.BOARD_NOT_DEFINED}">
                <script>
                    alert('존재하지 않는 게시판입니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:otherwise>
                <script>
                    alert('게시판을 확인하는 도중 오류가 발생하였습니다. \n\n잠시 후 다시 시도해주세요.');
                    window.history.back();
                </script>
            </c:otherwise>
        </c:choose>
        <% out.close(); %>
    </c:if>
</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<main>
    <h1>${listVo.name}</h1>
    <div class="something">
        <table>
            <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${empty listVo.articles}">
                <tr>
                    <td colspan="5">작성된 게시글이 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="article" items="${listVo.articles}">
                <tr>
                    <td>${article.index}</td>
                    <td>
                        <a href="/board/read/${article.index}">${article.title}</a>
                    </td>
                    <td>${article.id}</td>
                    <td>${article.formatCreatedAt()}</td>
                    <td>${article.view}</td>
                </tr>
            </c:forEach>
            </tbody>

            <tfoot>
            <tr style="text-align: center">
                <td colspan="5">
                    <c:if test="${listVo.page > 1}">
                        <a href="${pageContext.request.contextPath}/board/list/${listVo.code}/1"
                           style="margin-right: 0.5rem; padding: 0.33rem 0.5rem;">&lt;&lt;</a>
                        <a href="${pageContext.request.contextPath}/board/list/${listVo.code}/${listVo.page-1}"
                           style="margin-right: 0.5rem; padding: 0.33rem 0.5rem;">&lt;</a>
                    </c:if>
                    <c:forEach var="page" begin="${listVo.startPage}" end="${listVo.endPage}">
                        <c:choose>
                            <c:when test="${listVo.page == page}">
                                <b style="margin-right: 0.5rem; padding: 0.33rem 0.5rem;">${page}</b>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/board/list/${listVo.code}/${page}"
                                   style="margin-right: 0.5rem; padding: 0.33rem 0.5rem;">${page}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${listVo.page < listVo.maxPage}">
                        <a href="${pageContext.request.contextPath}/board/list/${listVo.code}/${listVo.page+1}"
                           style="margin-right: 0.5rem; padding: 0.33rem 0.5rem;">&gt;</a>
                        <a href="${pageContext.request.contextPath}/board/list/${listVo.code}/${listVo.maxPage}"
                           style="margin-right: 0.5rem; padding: 0.33rem 0.5rem;">&gt;&gt;</a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="5">
                    <form method="get">
                        <select name="criteria">
                            <option value="title">제목</option>
                            <option value="content">제목 + 내용</option>
                            <option value="nickname">작성자</option>
                        </select>
                        <input type="text" name="keyword" placeholder="검색">
                        <input type="submit" value="검색">
                    </form>
                </td>
            </tr>
    </div>
    </tfoot>
    </table>
</main>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>