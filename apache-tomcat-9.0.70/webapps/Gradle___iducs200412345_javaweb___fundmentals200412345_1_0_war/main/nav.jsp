<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="index.jsp">
            Egyou's Blog
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <%-- JSTL은 EL이나 HTML 태그 사용이 편리하다 --%>
            <c:choose>
                <c:when test="${sessionScope.logined == null}">
                <!-- 로그인 전 -->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-login-form.jsp">Sign in</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-create-form.jsp">Register</a></li>
                </c:when>
                <c:otherwise>
                <!-- 관리자 모드 -->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/members">member list</a></li>
                <!-- 로그인 후 : session -->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-detail?email=${sessionScope.logined}">Detail</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-update-form?email=${login.email}">Update</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-delete-form.jsp">삭제</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/member-logout">Sign out</a></li>
                </c:otherwise>
            </c:choose>
            </ul>
        </div>
    </div>
</nav>