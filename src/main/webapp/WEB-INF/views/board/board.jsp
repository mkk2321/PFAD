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
    <title>${listVo.name == null ? "게시판 오류" : listVo.name}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/user/resources/stylesheets/board/something.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <script defer src="${pageContext.request.contextPath}/user/resources/scripts/board/board.js"></script>
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
<body class="board">
<%@ include file="../header.jsp" %>
<main>
    <div class="boardNav">
        <nav>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/board/list/notice">공지사항</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/board/list/free">자유게시판</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/board/list/qna">Q&A</a>
                </li>
            </ul>
        </nav>
        <span></span>
        <div>
            검색공간
        </div>
    </div>
    <div class="article">
        <table>
            <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성 일시</th>
                <th>조회수</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty listVo.articles || listVo.articles == null}">
                <td colspan="5" style="text-align: center;"><i>작성된 게시글이 없습니다.</i></td>
            </c:if>
            <c:set var="num" value="${listVo.boardPerCount - (listVo.page-1) * listVo.queryLimit}"/>
            <c:forEach var="article" items="${listVo.articles}" varStatus="status">
                <tr>
                    <td class="boardNo">${num}</td>
                    <td><a href="/board/list/read/${article.index}" style="color: #5f71aa;">${article.title}</a></td>
                    <td>${article.id}</td>
                    <td>${article.formatCreatedAt()}</td>
                    <td>${article.view}</td>
                </tr>
                <c:set var="num" value="${num-1}"/>
            </c:forEach>
            </tbody>
            <tfoot>
            <c:if test="${listVo.maxPage > 0}">
                <tr>
                    <td>
                        <div>
                            <c:if test="${listVo.page > 1}">
                                <a href="/board/list/${listVo.code}/1"><<</a>
                                <a href="/board/list/${listVo.code}/${listVo.page - 1}"><</a>
                            </c:if>
                            <c:forEach var="page" begin="${listVo.startPage}" end="${listVo.endPage}">
                                <c:choose>
                                    <c:when test="${listVo.page == page}">
                                        <b>${page}</b>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/board/list/${listVo.code}/${page}">${page}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${listVo.page < listVo.maxPage}">
                                <a href="/board/list/${listVo.code}/${listVo.page + 1}">></a>
                                <a href="/board/list/${listVo.code}/${listVo.maxPage}">>></a>
                            </c:if>
                        </div>
                    </td>
                </tr>
            </c:if>
            </tfoot>
        </table>
    </div>
</main>
<%@ include file="../footer.jsp" %>
</body>
</html>