<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form name="headerForm" method="post">
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                <c:choose>
                    <c:when test="${sessionScope.loginUser ne null}">
                        <li class="nav-item"><a class="nav-link" id="greetingId" style="font-weight: bold">${sessionScope.loginUser.name}님 안녕하세요 ^^</a></li>
<%--                        <li class="nav-item"><a class="nav-link" id="extendLoginId" style="font-weight: bold; cursor: pointer" >Extend Login Time</a></li>--%>
                        <li class="nav-item"><a class="nav-link" id="logoutId" style="font-weight: bold; cursor: pointer">Log Out</a></li>
                        <li class="nav-item"><a class="nav-link" id="myPageId" style="font-weight: bold; cursor: pointer">MyPage</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link" id="logInId" style="font-weight: bold">LogIn</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
</form>
<script src="/js/header.js"></script>