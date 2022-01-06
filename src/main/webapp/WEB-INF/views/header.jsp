<%--@elvariable id="cartReadVo" type="com.example.pfad1.vos.cart.CartReadVo"--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="userEntity" type="com.example.pfad1.entities.user.UserEntity"--%>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<header>
    <div>
        <a href="/">
            <img src="${pageContext.request.contextPath}/resources/images/logo.jpg" alt="">
        </a>
        <ul class="menuNav">
            <li>
                <a href="${pageContext.request.contextPath}/home">홈</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/introduce">소개</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/product">상품</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/faq">FAQ</a>
            </li>
            <li class="board">
                <a href="${pageContext.request.contextPath}/board/list">게시판
                    <i class="fas fa-caret-down"></i>
                </a>
                <ul>
                    <li>
                        <a href="${pageContext.request.contextPath}/board/list/notice">공지사항</a>
                        <a href="${pageContext.request.contextPath}/board/list/free">자유 게시판</a>
                        <a href="${pageContext.request.contextPath}/board/list/qna">Q&A</a>
                    </li>
                </ul>

            </li>
        </ul>
        <span></span>
        <c:if test="${userEntity == null}">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/login">로그인</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/register">회원가입</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/recover">계정복구</a>
                </li>
            </ul>
        </c:if>
        <c:if test="${userEntity != null}">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/modify-password-check"><b>${userEntity.id}</b>님 환영합니다</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/order-list">주문조회</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/logout">로그아웃</a>
                </li>
            </ul>
        </c:if>
        <a href="${pageContext.request.contextPath}/cart" style="position: relative;">
            <img class="shop" src="${pageContext.request.contextPath}/resources/images/shop.png" alt="" style="width: 3rem;">
        </a>
    </div>
</header>
