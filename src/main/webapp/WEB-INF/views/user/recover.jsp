<%@ page import="com.example.pfad1.enums.user.IdRecoverResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>계정 찾기</title>
    <link rel="stylesheet" href="../resources/stylesheets/common.css">
    <link rel="stylesheet" href="/user/resources/stylesheets/user/recover.css">
    <script defer src="${pageContext.request.contextPath}/user/resources/scripts/recover.js"></script>

</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<h2>ID/PW 찾기</h2>
<section>
    <span class="click">ID 찾기</span>
    <span>비밀번호 찾기</span>
</section>
<form method="post" rel="id-recover" action="recover-id">
    <table class="visible">
        <tbody>
        <tr>
            <th>
                <label for="email-recover-input">이메일</label>
            </th>
            <td>
                <input autofocus type="email" id="email-recover-input" placeholder="이메일" name="email">
            </td>
        </tr>
        <tr>
            <th>
                <label for="name-recover-input">이름</label>
            </th>
            <td>
                <input autofocus type="text" id="name-recover-input" placeholder="이름" name="name">
            </td>
        </tr>
        <tr>
            <th>
                <label for="birth-input">생년월일</label>
            </th>
            <td>
                <input type="text" name="birth" id="birth-input" placeholder="생년월일">
                <span>-</span>
                <input type="text" name="gender">
                <span>●●●●●●</span>
            </td>
        </tr>
        <tr>
            <th>
                <label for="contact-id-recover-input">연락처</label>
            </th>
            <td>
                <select id="contact-id-recover-input" name="contactCompany">
                    <option value="skt">SKT</option>
                    <option value="kt">KT</option>
                    <option value="lgu">LGU+</option>
                    <option value="etc">기타</option>
                </select>
                <select name="contactFirst">
                    <option name="010">010</option>
                    <option name="011">011</option>
                    <option name="012">012</option>
                    <option name="013">013</option>
                    <option name="014">014</option>
                </select>
                <span>-</span>
                <input type="text" name="contactSecond" placeholder="0000">
                <span>-</span>
                <input type="text" name="contactThird" placeholder="0000">
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td>
                <input type="submit" value="아이디 찾기">
            </td>
        </tr>
        </tfoot>
    </table>
</form>

<form method="post" rel="password-recover" action="recover-password">
    <table>
        <tbody>
        <tr>
            <th>
                <label for="id-password-recover-input">아이디</label>
            </th>
            <td>
                <input autofocus type="text" id="id-password-recover-input" placeholder="아이디" name="id">
            </td>
        </tr>
        <tr>
            <th>
                <label for="email-password-recover-input">이메일</label>
            </th>
            <td>
                <input type="email" id="email-password-recover-input" placeholder="이메일" name="email">
            </td>
        </tr>
        <tr>
            <th>
                <label for="name-password-recover-input">이름</label>
            </th>
            <td>
                <input autofocus type="text" id="name-password-recover-input" placeholder="이름" name="name">
            </td>
        </tr>
        <tr>
            <th>
                <label for="birth-password-recover-input">생년월일</label>
            </th>
            <td>
                <input type="text" name="birth" id="birth-password-recover-input" placeholder="생년월일">
                <span>-</span>
                <input type="text" name="gender">
                <span>●●●●●●</span>
            </td>
        </tr>
        <tr>
            <th>
                <label for="contact-password-recover-input">연락처</label>
            </th>
            <td>
                <select id="contact-password-recover-input" name="contactCompany">
                    <option value="skt">SKT</option>
                    <option value="kt">KT</option>
                    <option value="lgu">LGU+</option>
                    <option value="etc">기타</option>
                </select>
                <select name="contactFirst">
                    <option name="010">010</option>
                    <option name="011">011</option>
                    <option name="012">012</option>
                    <option name="013">013</option>
                    <option name="014">014</option>
                </select>
                <span>-</span>
                <input type="text" name="contactSecond" placeholder="0000">
                <span>-</span>
                <input type="text" name="contactThird" placeholder="0000">
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td>
                <input type="submit" value="비밀번호 찾기">
            </td>
        </tr>
        </tfoot>
    </table>
</form>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>