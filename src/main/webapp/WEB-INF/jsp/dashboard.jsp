<!DOCTYPE html>
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>

<jsp:include page="/WEB-INF/jsp/common/head.jsp"/>

<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>

<mylib:errors successMessage="dashboard.computers_deleted_successfully"
              errorMessage="dashboard.failed_to_delete_computers"/>

<section id="main">
    <jsp:include page="/WEB-INF/jsp/component/navContainer.jsp"/>

    <form id="deleteForm" action="${pageContext.request.contextPath}/computer/delete" method="POST">
        <input type="hidden" name="selection" value="">
    </form>

    <jsp:include page="/WEB-INF/jsp/component/computerTable.jsp"/>
</section>

<jsp:include page="/WEB-INF/jsp/component/pagination.jsp"/>

<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/js/dashboard.js"/>"></script>

</body>
</html>