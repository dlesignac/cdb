<%@ page isErrorPage="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/jsp/common/head.jsp"/>

<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>

<section id="main">
    <div class="container">
        <div class="alert alert-danger">
            <spring:message code="error500.error_500"/>
            <br/>
            <!--
				    At ${url}
                    ${exception}
                    <c:forEach var="trace" items="${exception.stackTrace}">
                        ${trace}
                    </c:forEach>
				-->
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

</body>
</html>