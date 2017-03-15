<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="success" type="java.lang.String" required="true" %>

<c:if test="${errors ne null}">
    <div class="col-xs-6 col-xs-offset-3 box">
        <c:choose>
            <c:when test="${not empty errors}">
                <c:forEach items="${errors}" var="error">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <strong>Oops !</strong> ${error}.
                    </div>
                </c:forEach>
            </c:when>
            <c:when test="${empty errors}">
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <strong>Yay !</strong> ${success}.
                </div>
            </c:when>
        </c:choose>
    </div>
</c:if>