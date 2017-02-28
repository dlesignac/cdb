<!DOCTYPE html>
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/pages/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${computersCount} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="${pageContext.request.contextPath}/add-computer">Add Computer</a>
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->
						<th class="editMode" style="width: 60px; height: 22px;">
						    <input type="checkbox" id="selectall" />
						    <span style="vertical-align: top;">
						        -
						        <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
						            <i class="fa fa-trash-o fa-lg"></i>
							    </a>
						    </span>
						</th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
				    <c:forEach items="${page.entries}" var="computer">
				        <tr>
                    		<td class="editMode"><input type="checkbox" name="cb" class="cb" value="${computer.id}"></td>
                    		<td><a href="${pageContext.request.contextPath}/edit-computer?id=${computer.id}" onclick="">${computer.name}</a></td>
                            <td>${computer.introduced}</td>
                            <td>${computer.discontinued}</td>
                            <td>${computer.manufacturer.name}</td>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<jsp:include page="/WEB-INF/pages/footer.jsp" />

	<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dashboard.js"></script>

</body>
</html>