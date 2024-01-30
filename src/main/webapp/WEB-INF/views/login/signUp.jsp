<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, 그리고 Bootstrap 기여자들">
    <meta name="generator" content="Hugo 0.104.2">
    <title>sign up</title>

    <link rel="canonical" href="https://getbootstrap.kr/docs/5.2/examples/sign-in/">

    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Favicons -->
    <link rel="apple-touch-icon" href="/images/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/images/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/images/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/images/manifest.json">

    <link rel="mask-icon" href="/images/safari-pinned-tab.svg" color="#712cf9">
    <link rel="icon" href="/images/favicon.ico">
    <meta name="theme-color" content="#712cf9">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .b-example-divider {
            height: 3rem;
            background-color: rgba(0, 0, 0, .1);
            border: solid rgba(0, 0, 0, .15);
            border-width: 1px 0;
            box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
        }

        .b-example-vr {
            flex-shrink: 0;
            width: 1.5rem;
            height: 100vh;
        }

        .bi {
            vertical-align: -.125em;
            fill: currentColor;
        }

        .nav-scroller {
            position: relative;
            z-index: 2;
            height: 2.75rem;
            overflow-y: hidden;
        }

        .nav-scroller .nav {
            display: flex;
            flex-wrap: nowrap;
            padding-bottom: 1rem;
            margin-top: -1px;
            overflow-x: auto;
            text-align: center;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch;
        }
    </style>

    <!-- Custom styles for this template -->
    <link href="/css/signin.css" rel="stylesheet">

    <script>

        let bloodTypes = [
            <c:forEach items="${bloodTypes}" var="bloodType" varStatus="loop">
                "<c:out value="${bloodType.description}" />"
                <c:if test="${!loop.last}">,</c:if>
            </c:forEach>
        ];

        bloodTypes.forEach(function(value) {
            console.log(value);
        });


        console.log(`${bindingResult}`);



    </script>

</head>
<body class="text-center">

<main class="form-signin w-100 m-auto">
    <form action="/signUp" name="signUpForm" method="post">

        <h1 class="h3 mb-3 fw-normal">Sign Up</h1>

        <div class="form-floating">
            <input type="text" class="form-control" name="id" id="inputId" placeholder="Id" value="${user.id}">
            <label for="inputId">Id</label>
        </div>
        <div style="color:red;">
            <spring:hasBindErrors name="user">
                <c:if test="${errors.hasFieldErrors('id')}">
                    ${errors.getFieldError('id').defaultMessage}
                </c:if>
            </spring:hasBindErrors>
        </div>

        <div class="form-floating">
            <input type="password" class="form-control" name="password" id="inputPassword" value="${user.password}" placeholder="Password">
            <label for="inputPassword">Password</label>
        </div>
        <div style="color:red;">
            <spring:hasBindErrors name="user">
                <c:if test="${errors.hasFieldErrors('password')}">
                    ${errors.getFieldError('password').defaultMessage}
                </c:if>
            </spring:hasBindErrors>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" name="name" id="inputName" placeholder="Name" value="${user.name}">
            <label for="inputName">Name</label>
        </div>
        <div style="color:red;">
            <spring:hasBindErrors name="user">
                <c:if test="${errors.hasFieldErrors('name')}">
                    ${errors.getFieldError('name').defaultMessage}
                </c:if>
            </spring:hasBindErrors>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" name="age" id="inputAge" placeholder="Age" value="${user.age}">
            <label for="inputAge">Age</label>
        </div>
        <div style="color:red;">
            <spring:hasBindErrors name="user">
                <c:if test="${errors.hasFieldErrors('age')}">
                    ${errors.getFieldError('age').defaultMessage}
                </c:if>
            </spring:hasBindErrors>
        </div>

        <div class="form-floating">
            <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email" value="${user.email}">
            <label for="inputEmail">Email</label>
        </div>
        <div style="color:red;">
            <spring:hasBindErrors name="user">
                <c:if test="${errors.hasFieldErrors('email')}">
                    ${errors.getFieldError('email').defaultMessage}
                </c:if>
            </spring:hasBindErrors>
        </div>

        <%-- ENUM 활용 --%>
        <div class="form-floating" >
            <select id="bloodTypeId" name="bloodType" style="padding: 10px;border-radius: 15px;margin-top: 15px;">
                <option value="">Choose BloodType</option>
                <c:forEach items="${bloodTypes}" var="bloodType">
                    <option value="${bloodType}">${bloodType.description}</option>
                </c:forEach>
            </select>
        </div>
        <div style="color:red;">
            <spring:hasBindErrors name="user">
                <c:if test="${errors.hasFieldErrors('bloodType')}">
                    ${errors.getFieldError('bloodType').defaultMessage}
                </c:if>
            </spring:hasBindErrors>
        </div>

        <%-- Map 활용 --%>
        <div class="form-check" style="padding: 20px; font-size: 17px;">
            <c:forEach items="${sex}" var="entry">
                <label>
                    <input type="radio" name="sex" value="${entry.key}"> ${entry.value}
                </label>
            </c:forEach>
        </div>
        <div style="color:red;">
            <spring:hasBindErrors name="user">
                <c:if test="${errors.hasFieldErrors('sex')}">
                    ${errors.getFieldError('sex').defaultMessage}
                </c:if>
            </spring:hasBindErrors>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign Up</button>

        <input class="w-100 btn btn-lg btn-primary" id="goBackHomeButton" type="button" style="margin-top: 10px" value="Go Back Home">
    </form>

</main>

<script src="/js/login.js"></script>

<script>

    document.getElementById("bloodTypeId").value = "${user.bloodType}";

    document.getElementsByName("sex").forEach(v => {
        if (v.value === "${user.sex}") {
            v.checked = true;
        }
    })

    document.getElementById("goBackHomeButton").addEventListener("click", () => {
        location.href = "/login";
    })

</script>

</body>
</html>
