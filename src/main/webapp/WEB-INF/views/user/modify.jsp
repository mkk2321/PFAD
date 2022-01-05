<%--@elvariable id="userEntity" type="com.example.pfad1.entities.user.UserEntity"--%>
<%@ page import="com.example.pfad1.enums.user.ModifyResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원가입</title>
    <link rel="stylesheet" href="../resources/stylesheets/common.css">
    <link rel="stylesheet" href="/user/resources/stylesheets/register.css">
    <script src="../resources/scripts/Ajax.js"></script>
    <script defer src="/user/resources/scripts/modify.js"></script>
    <script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        <c:if test="${modifyResult != null}">
            <c:choose>
                <c:when test="${modifyResult == ModifyResult.SUCCESS}">
                    <c:set var="alert" value="회원 정보 수정에 성공하였습니다. 다시 로그인 해주세요." />
                </c:when>
                <c:otherwise>
                    <c:set var="alert" value="회원 정보 수정에 실패하였습니다."/>
                </c:otherwise>
            </c:choose>
        alert('${alert}');
            <c:choose>
                <c:when test="${modifyResult == ModifyResult.SUCCESS}">
                    window.location.href='logout';
                </c:when>
                <c:otherwise>
                    window.history.back();
                </c:otherwise>
            </c:choose>
        </c:if>

    </script>
</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<main>
    <h1>정보 수정</h1>
    <form method="post" rel="modify-form">
        <table>
            <tr>
                <th>
                    <label  for="id-input">아이디</label>
                </th>
                <td>
                    <input type="text" name="id" id="id-input" placeholder="아이디" value="${userEntity.id}" readonly>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="password-input">비밀번호</label>
                </th>
                <td>
                    <input autofocus type="password" name="password" id="password-input" placeholder="비밀번호">
                    <span class="input-message" rel="password-message"></span>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="password-check-input">비밀번호 재입력</label>
                </th>
                <td>
                    <input type="password" name="checkPassword" id="password-check-input" placeholder="비밀번호 재입력">
                <span class="input-message" rel="password-check-message"></span>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="name-input">이름</label>
                </th>
                <td>
                    <input type="text" name="name" id="name-input" value="${userEntity.name}" readonly>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="birth-input">생년월일</label>
                </th>
                <td>
                    <input type="text" name="birth" id="birth-input" value="${userEntity.birth}" readonly>
                    <span>-</span>
                    <input type="text" name="gender" value="${userEntity.gender}" readonly>
                    <span>●●●●●●</span>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="contact-input">연락처</label>
                </th>
                <td>
                    <select id="contact-input" name="contactCompany" >
                        <option value="skt" ${userEntity.contactCompany.equals("skt") ? 'selected' : ''}>SKT</option>
                        <option value="kt" ${userEntity.contactCompany.equals("kt") ? 'selected' : ''}>KT</option>
                        <option value="lgu" ${userEntity.contactCompany.equals("lgu") ? 'selected' : ''}>LGU+</option>
                        <option value="etc" ${userEntity.contactCompany.equals('etc') ? 'selected' : ''}>기타</option>
                    </select>
                    <select name="contactFirst">
                        <option value="010" ${userEntity.contactFirst.equals("010") ? 'selected' : ""}>010</option>
                        <option value="011" ${userEntity.contactFirst.equals("011") ? 'selected' : ""}>011</option>
                        <option value="012" ${userEntity.contactFirst.equals("012") ? 'selected' : ""}>012</option>
                        <option value="013" ${userEntity.contactFirst.equals("013") ? 'selected' : ""}>013</option>
                        <option value="014" ${userEntity.contactFirst.equals("014") ? 'selected' : ""}>014</option>
                    </select>
                    <span>-</span>
                    <input type="text" name="contactSecond" value="${userEntity.contactSecond}">
                    <span>-</span>
                    <input type="text" name="contactThird" value="${userEntity.contactThird}">
                </td>
            </tr>
            <tr>
                <th>
                    <label for="address-input">주소</label>
                </th>
                <td class="addressTd">
                    <input readonly type="text" name="addressPostal" value="${userEntity.addressPostal}">
                    <input type="button" name="addressButton" value="주소 찾기" id="address-input">
                    <br>
                    <input readonly type="text" name="addressPrimary" value="${userEntity.addressPrimary}">
                    <br>
                    <input type="text" name="addressSecondary" placeholder="상세 주소" value="${userEntity.addressSecondary}">
                </td>
            </tr>
            <tr>
                <th>
                    <label for="email-input">이메일</label>
                </th>
                <td>
                    <input id="email-input" type="email" name="email" value="${userEntity.email}" readonly>
                </td>
            </tr>
        </table>

        <section class="button">
            <input type="reset" value="다시 작성">
            <span></span>
            <input type="submit" value="정보 수정">
        </section>
    </form>
</main>
<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>
