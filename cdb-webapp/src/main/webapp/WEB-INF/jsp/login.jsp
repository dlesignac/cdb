<!DOCTYPE html>
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<jsp:include page="/WEB-INF/jsp/common/head.jsp" />

<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>

<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <h1><spring:message code="login.sign_in"/></h1>
                <form id="loginForm" action="#" method="POST">
                    <fieldset>
                        <div class="form-group">
                            <label for="username"><spring:message code="login.username"/></label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="<spring:message code="login.username"/>">
                        </div>
                        <div class="form-group">
                            <label for="password"><spring:message code="login.password"/></label>
                            <input type="date" class="form-control" id="password" name="password" placeholder="<spring:message code="login.password"/>">
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input id="submit" type="submit" value="<spring:message code="login.sign_in"/>" class="btn btn-primary">
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