<%@ page import="com.example.pfad1.enums.board.ReadResult" %>
<%@ page import="com.example.pfad1.enums.board.CommentWriteResult" %>
<%@ page import="com.example.pfad1.vos.board.CommentDeleteVo" %>
<%@ page import="com.example.pfad1.enums.board.CommentDeleteResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="readVo" type="com.example.pfad1.vos.board.ReadVo"--%>
<%--@elvariable id="userEntity" type="com.example.pfad1.entities.user.UserEntity"--%>
<%--@elvariable id="commentWriteResult" type="com.example.pfad1.enums.board.CommentWriteResult"--%>
<%--@elvariable id="commentDeleteResult" type="com.example.pfad1.enums.board.CommentDeleteResult"--%>
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
    <c:if test="${commentWriteResult != null}">
        <c:choose>
            <c:when test="${commentWriteResult == CommentWriteResult.ARTICLE_NOT_DEFINED}">
                <script>
                    alert('존재하지 않는 게시글입니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${commentWriteResult == CommentWriteResult.BOARD_NOT_DEFINED}">
                <script>
                    alert('존재하지 않는 게시판입니다sad.');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${commentWriteResult == CommentWriteResult.NOT_ALLOWED}">
                <script>
                    alert('댓글을 작성할 권한이 없습니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:otherwise>
                <script>
                    alert('알 수 없는 이유로 댓글을 작성하지 못하였습니다.');
                    window.history.back();
                </script>
            </c:otherwise>
        </c:choose>
        <%out.close();%>
    </c:if>
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
            <c:if test="${userEntity.id.equals(readVo.id) || userEntity.admin }">
                <a href="${pageContext.request.contextPath}/board/${readVo.boardCode}/modify/${readVo.index}">수정</a>
                <a href="#" onclick="if (confirm('이 게시글이 삭제됩니다.'))
                        window.location.href='${pageContext.request.contextPath}/board/delete/${readVo.index}';">삭제</a>
            </c:if>
        </div>
        <h3>${readVo.title}</h3>
        <div class="boardInfo">
            <ul>
                <li>작성자 : ${readVo.id}</li>
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
    <section class="boardCommentWrap">
        <form method="post">
            <table>
                <tbody>
                <tr>
                    <td colspan="8">
                        <input type="hidden" name="boardCode" value="${readVo.boardCode}">
                        <label>
                            <span hidden>댓글</span>
                            <input type="text" maxlength="100" name="content" placeholder="댓글">
                        </label>
                        <input type="submit" value="작성">
                    </td>
                </tr>
                <tr>
                    <td colspan="8">
                        <div>
                            <table>
                                <tbody class="comment">
                                <c:if test="${empty readVo.comments}">
                                    <tr>
                                        <td>작성된 게시글이 없습니다.</td>
                                    </tr>
                                </c:if>
                                <c:forEach var="comment" items="${readVo.comments}">
                                    <tr>
                                        <td>
                                            <div>
                                                <span style="background-color: ${comment.userId.equals(userEntity.id) ? '#1e90ff44' : '#99999955'};">
                                                    <a>${comment.userId}</a>
                                                    <span></span>
                                                    <c:if test="${userEntity.admin || userEntity.id.equals(comment.userId)}">
                                                        <a href="#" class="commentDelete" onclick="if(confirm('정말로 댓글을 삭제할까요?')){window.location.href='/board/${readVo.boardCode}/delete/${comment.articleIndex}';}">삭제</a>
                                                    </c:if>
                                                    <a>${comment.formatCreatedAt()}</a>
                                                </span>
                                                <span>${comment.content}</span>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </section>


</main>
<%@ include file="../footer.jsp" %>
</body>
</html>