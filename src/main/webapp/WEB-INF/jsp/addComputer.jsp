<!DOCTYPE html>
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<jsp:include page="/WEB-INF/jsp/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"/>

	<mylib:errors success='<spring:message code="addcomputer.computer_added_successfully"/>'/>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="addcomputer.add_computer"/></h1>
                    <form id="computerForm" action="#" method="POST">
                        <fieldset>
                            <div id="computerNameContainer" class="form-group">
                                <label for="name"><spring:message code="addcomputer.computer_name"/></label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="addcomputer.computer_name"/>">
                            </div>
                            <div id="introducedContainer" class="form-group">
                                <label for="introduced"><spring:message code="addcomputer.introduced_date"/></label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="<spring:message code="addcomputer.introduced_date"/>">
                            </div>
                            <div id="discontinuedContainer" class="form-group">
                                <label for="discontinued"><spring:message code="addcomputer.discontinued_date"/></label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="<spring:message code="addcomputer.discontinued_date"/>">
                            </div>
                            <div id="companyIdContainer" class="form-group">
                                <label for="companyId"><spring:message code="addcomputer.company"/></label>
                                <select class="form-control" id="companyId" name="companyId" >
                                    <option value=""></option>
                                    <c:forEach items="${companies}" var="company">
                                        <option value="${company.id}">${company.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>
                        <div class="actions pull-right">
                            <input id="submit" type="submit" value="<spring:message code="addcomputer.add"/>" class="btn btn-primary">
                            or
                            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-default"><spring:message code="addcomputer.cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/formValidation.js"></script>
</body>
</html>