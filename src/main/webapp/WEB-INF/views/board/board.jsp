<%@ page import="com.example.pfad1.enums.board.ListResult" %>
<%@ page import="com.example.pfad1.vos.board.ListVo" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="listVo" type="com.example.pfad1.vos.board.ListVo"--%>
<%--@elvariable id="userEntity" type="com.example.pfad1.entities.user.UserEntity"--%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${listVo.name == null ? "게시판 오류" : listVo.name}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/board/resources/stylesheets/something.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <script defer src="${pageContext.request.contextPath}/board/resources/scripts/board.js"></script>
<%--    <c:if test="${listVo.result != ListResult.SUCCESS}">
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
    </c:if>--%>
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
        <div class="searchContent">
            <form method="get">
<%--                <input type="hidden" name="code" value="${listVo.code}">--%>
                <select name="criteria">
                    <option value="title" ${listVo.searching && listVo.criteria.equals("title") ? "selected" : ""}>제목</option>
                    <option value="content" ${listVo.searching && listVo.criteria.equals("content") ? "selected" : ""}>내용</option>
                    <option value="writer" ${listVo.searching && listVo.criteria.equals("writer") ? "selected" : ""}>작성자</option>
                </select>
                <input type="text" name="keyword" placeholder="검색" value="${listVo.searching ? listVo.keyword : ""}">
                <input type="submit" value="검색">
            </form>
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
            <c:forEach var="article" items="${listVo.articles}">
                <tr>
                    <td class="boardNo">${num}</td>
                    <td>
                        <a href="/board/${article.boardCode}/read/${article.index}" style="color: #5f71aa;">${article.title}</a>
                        <b style="color: #808284; margin-left: 0.2rem;">[${article.commentCount}]</b>
                    </td>
                    <td>${article.id}</td>
                    <td>${article.formatCreatedAt()}</td>
                    <td>${article.view}</td>
                </tr>
                <c:set var="num" value="${num-1}"/>
            </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <c:if test="${userEntity != null && (userEntity.admin || !listVo.writeForbidden)}">
                        <td>
                            <a class="writeButton" href="/board/write/${listVo.code}">글쓰기</a>
                        </td>
                    </c:if>
            <c:if test="${listVo.maxPage > 0}">
                    <td>
                        <div>
<%--                            <%
                                String searchUrl;
                                if(listVo.getCriteria != null && )
                            %>--%>
                            <%--<c:if test="${listVo.criteria != null && listVo.keyword != null}">
                                <c:choose>
                                    <c:set var="searchUrl" value="/&criteria=${listVo.criteria}&keyword=${listVo.keyword}" />
                                </c:choose>
                                <c:otherwise>
                                    <c:set var="searchUrl" value="" />
                                </c:otherwise>--%>
                    <%--        </c:if>--%>

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
                                        <a href="/board/list/${listVo.code}/${page}${listVo.searchUrl}">${page}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${listVo.page < listVo.maxPage}">
                                <a href="/board/list/${listVo.code}/${listVo.page + 1}">></a>
                                <a href="/board/list/${listVo.code}/${listVo.maxPage}">>></a>
                            </c:if>
                        </div>
                    </td>
            </c:if>
                </tr>
            </tfoot>
        </table>
    </div>
</main>
<%@ include file="../footer.jsp" %>
</body>
</html>