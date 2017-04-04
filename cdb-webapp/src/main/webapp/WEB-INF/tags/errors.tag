<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="successMessage" type="java.lang.String" required="true" %>
<%@ attribute name="errorMessage" type="java.lang.String" required="true" %>

<c:if test="${success ne null}">
    <div class="container">
        <c:choose>
            <c:when test="${not success}">
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <spring:message code="${errorMessage}"/>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <strong>Yay !</strong> <spring:message code="${successMessage}"/>.
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>