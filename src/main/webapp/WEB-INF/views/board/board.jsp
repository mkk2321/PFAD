<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>게시판</title>
    <link rel="stylesheet" href="../../user/resources/stylesheets/board/something.css">
    <link rel="stylesheet" href="../resources/stylesheets/common.css">
</head>
<body>
<%@ include file="../header.jsp" %>
<main>

    <div class="boardNav">
        <nav>
            <ul>
                <li>
                    <a href="#">공지사항</a>
                </li>
                <li>
                    <a href="#">자유게시판</a>
                </li>
                <li>
                    <a href="#">Q&A</a>
                </li>
            </ul>
        </nav>
    </div>
    <div class="arcicle">
        <table style="width: 40rem;">
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

            </tbody>
            <tfoot>

            </tfoot>
        </table>
    </div>
</main>
<%@ include file="../footer.jsp" %>
</body>
</html>