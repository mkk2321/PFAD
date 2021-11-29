<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>상품 등록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/stylesheets/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/user/resources/stylesheets/product/product.css">
    <c:if test="${writeVo.result != WriteResult.SUCCESS}">
        <c:choose>
            <c:when test="${writeVo.result == WriteResult.BOARD_NOT_DEFINED}">
                <script>
                    alert('존재하지 않는 게시판입니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:when test="${writeVo.result == WriteResult.WRITE_NOT_ALLOWED}">
                <script>
                    alert('게시글을 작성할 권한이 없습니다.');
                    window.history.back();
                </script>
            </c:when>
            <c:otherwise>
                <script>
                    alert('알 수 없는 이유로 게시글을 작성할 수 없습니다. \n\n잠시 후 다시 시도해주세요.');
                    window.history.back();
                </script>
            </c:otherwise>
        </c:choose>
        <% out.close(); %>
    </c:if>
</head>
<body>
<%@ include file="../header.jsp"%>
<section>
    <div>
        <ul>
            <li>
                <a href="#">
                    <div class="imgContent">
                        <img src="${pageContext.request.contextPath}/resources/images/carrot_juice.png" alt="carrot_juice">
                    </div>
                    <div>
                        <h5>$5</h5>
                        <p>carrot juice</p>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/product/register">상품 등록</a>
    </div>
</section>
<%@ include file="../footer.jsp"%>
</body>
</html>