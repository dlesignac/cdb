<!DOCTYPE html>
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>

<jsp:include page="/WEB-INF/jsp/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<mylib:errors success='<spring:message code="dashboard.computers_deleted_successfully"/>'/>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${page.count} <spring:message code="dashboard.computers_found"/></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET" class="form-inline">
					    <input type="search" id="searchbox" name="filter" class="form-control" placeholder="<spring:message code="dashboard.search_name"/>" value="${page.filter}"/>
                        <input type="hidden" name="limit" value="${page.limit}"/>
                        <input type="hidden" name="page" value="1"/>
						<input type="submit" id="searchsubmit" value="<spring:message code="dashboard.filter_by_name"/>" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="${pageContext.request.contextPath}/computer/add"><spring:message code="dashboard.add_computer"/></a>
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="dashboard.edit"/></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="${pageContext.request.contextPath}/delete-computers" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th class="editMode" style="width: 60px; height: 22px;">
						    <input type="checkbox" id="selectall" />
						    <span style="vertical-align: top;">
						        -
						        <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
						            <i class="fa fa-trash-o fa-lg"></i>
							    </a>
						    </span>
						</th>
						<th><spring:message code="dashboard.computer_name"/> <a href="<mylib:link target='dashboard' filter='${page.filter}' sort='computerName' order='ASC' limit='${page.limit}' page='1'/>"><span class="glyphicon glyphicon-sort pull-right" aria-hidden="true"></span></a></th>
						<th><spring:message code="dashboard.introduced"/> <a href="<mylib:link target='dashboard' filter='${page.filter}' sort='introducedDate' order='ASC' limit='${page.limit}' page='1'/>"><span class="glyphicon glyphicon-sort pull-right" aria-hidden="true"></span></a></th>
						<th><spring:message code="dashboard.discontinued"/> <a href="<mylib:link target='dashboard' filter='${page.filter}' sort='discontinuedDate' order='ASC' limit='${page.limit}' page='1'/>"><span class="glyphicon glyphicon-sort pull-right" aria-hidden="true"></span></a></th>
						<th><spring:message code="dashboard.company"/> <a href="<mylib:link target='dashboard' filter='${page.filter}' sort='companyName' order='ASC' limit='${page.limit}' page='1'/>"><span class="glyphicon glyphicon-sort pull-right" aria-hidden="true"></span></a></th>
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
				    <c:forEach items="${page.entries}" var="computer">
				        <tr>
                    		<td class="editMode"><input type="checkbox" name="cb" class="cb" value="${computer.id}"></td>
                    		<td><a href="${pageContext.request.contextPath}/computer/${computer.id}"><c:out value="${computer.name}"/></a></td>
                            <td><c:out value="${computer.introduced}"/></td>
                            <td><c:out value="${computer.discontinued}"/></td>
                            <td><c:out value="${computer.manufacturer.name}"/></td>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />

	<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dashboard.js"></script>

</body>
</html>