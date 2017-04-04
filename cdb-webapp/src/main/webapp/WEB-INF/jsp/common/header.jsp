<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>

<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">
            <spring:message code="header.application"/> - Computer Database
        </a>
        <mylib:login/>
    </div>
</header>