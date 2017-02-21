<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/pages/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />

	<section id="main">
		<div class="container">	
			<div class="alert alert-danger">
				Error 500: An error has occurred!
				<br/>
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

</body>
</html>