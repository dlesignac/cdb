<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/jsp/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				Error 500: An error has occurred !
				<br/>
				<!--
                    ${pageContext.exception}
                    <c:forEach var="trace" items="${pageContext.exception.stackTrace}">
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