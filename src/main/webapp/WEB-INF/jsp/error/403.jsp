<!DOCTYPE html>
<html>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/WEB-INF/jsp/common/head.jsp"/>

<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>

<section id="main">
    <div class="container">
        <div class="alert alert-danger">
            <spring:message code="error403.error_403"/>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

</body>
</html>