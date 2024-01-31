<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Chick</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />


    <link href="/css/sidebars.css" rel="stylesheet">
</head>
<body>
<div class="d-flex" id="wrapper">
    <!-- Sidebar-->

    <div class="b-example-divider b-example-vr"></div>

    <div class="flex-shrink-0 p-3" style="width: 280px;">
        <a href="/main" class="d-flex align-items-center pb-3 mb-3 link-body-emphasis text-decoration-none border-bottom">
            <svg class="bi pe-none me-2" width="30" height="24"><use xlink:href="#bootstrap"/></svg>
            <span class="fs-5 fw-semibold">Practice</span>
        </a>
        <ul class="list-unstyled ps-0">

            <span id="menuArea"></span>

            <c:forEach items="${menuDepth1}" var="lv1">
                <li class="mb-1" id="liId${lv1.menuId}" >
                    <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
                            data-bs-toggle="collapse"
                            aria-expanded="true"
                            data-bs-target="#menuId${lv1.menuId}">
                            ${lv1.menuName}
                    </button>
                    <div class="collapse" id="menuId${lv1.menuId}">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                            <c:forEach items="${menuDepth2}" var="lv2">
                                <c:if test="${lv1.menuId eq lv2.parentMenuId}">
                                    <li>
                                        <a href="#"
                                           class="link-body-emphasis d-inline-flex text-decoration-none rounded"
                                            onclick="getPosts('${lv2.menuUrl}');">
                                                ${lv2.menuName}
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div id="page-content-wrapper">

        <jsp:include page="header.jsp"></jsp:include>

        <div class="container-fluid">
            <h1 class="mt-4">test</h1>
            <br>
            <iframe id="frameId" name="id_name" width="800" height="400" scrolling="no" frameBorder="0" ></iframe>
        </div>
    </div>
</div>

<script>

    const getPosts = function(menuUrl) {
        let boardFrame = document.getElementById("frameId");
        if (menuUrl) {
            boardFrame.src = menuUrl;
        }
    }

</script>

</body>
</html>
