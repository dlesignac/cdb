<!DOCTYPE html>
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>

<jsp:include page="/WEB-INF/jsp/common/head.jsp"/>

<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>

<mylib:errors successMessage="editcomputer.computer_edited_successfully"
              errorMessage="editcomputer.could_not_edit_computer"/>

<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <div class="label label-default pull-right">
                    id: ${computer.id}
                </div>
                <h1><spring:message code="editcomputer.edit_computer"/></h1>

                <form id="computerForm" action="#" method="POST">
                    <input type="hidden" value="${computer.id}" name="id" id="id"/>
                    <fieldset>
                        <div class="form-group">
                            <label for="name"><spring:message code="editcomputer.computer_name"/></label>
                            <input type="text" class="form-control" name="name" id="name"
                                   placeholder="<spring:message code="editcomputer.computer_name"/>"
                                   value="<c:out value="${computer.name}"/>">
                        </div>
                        <div class="form-group">
                            <label for="introduced"><spring:message code="editcomputer.introduced_date"/></label>
                            <input type="date" class="form-control" name="introduced" id="introduced"
                                   placeholder="<spring:message code="editcomputer.introduced_date"/>"
                                   value="<c:out value="${computer.introduced}"/>">
                        </div>
                        <div class="form-group">
                            <label for="discontinued"><spring:message code="editcomputer.discontinued_date"/></label>
                            <input type="date" class="form-control" name="discontinued" id="discontinued"
                                   placeholder="<spring:message code="editcomputer.discontinued_date"/>"
                                   value="<c:out value="${computer.discontinued}"/>">
                        </div>
                        <div class="form-group">
                            <label for="companyId"><spring:message code="editcomputer.company"/></label>
                            <select class="form-control" name="companyId" id="companyId">
                                <option value=""></option>
                                <c:forEach items="${companies}" var="company">
                                    <option value="${company.id}">${company.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="<spring:message code="editcomputer.edit"/>" class="btn btn-primary">
                        <spring:message code="editcomputer.or"/>
                        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-default"><spring:message
                                code="editcomputer.cancel"/></a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/js/formValidation.js"/>"></script>
</body>
</html>