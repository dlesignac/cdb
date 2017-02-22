
<%@ attribute name="limit" type="java.lang.Integer" required="true" %>
<%@ attribute name="count" type="java.lang.Integer" required="true" %>
<%@ attribute name="number" type="java.lang.Integer" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${count <= 5}">
        <c:set var="begin" value="1"/>
        <c:set var="end" value="${count}"/>
    </c:when>
    <c:when test="${number <= 3}">
        <c:set var="begin" value="1"/>
        <c:set var="end" value="5"/>
    </c:when>
    <c:when test="${number >= count - 2}">
        <c:set var="begin" value="${count - 4}"/>
        <c:set var="end" value="${count}"/>
    </c:when>
    <c:otherwise>
        <c:set var="begin" value="${number - 2}"/>
        <c:set var="end" value="${number + 2}"/>
    </c:otherwise>
</c:choose>

<ul class="pagination">
    <c:if test="${number != 1}">
        <li><a href="?limit=${limit}&page=${number - 1}" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
        </a></li>
    </c:if>
    <c:forEach var="i" begin="${begin}" end="${end}">
        <li><a href="?limit=${limit}&page=${i}">${i}</a></li>
    </c:forEach>
    <c:if test="${number != count}">
        <li><a href="?limit=${limit}&page=${number + 1}" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
        </a></li>
    </c:if>
</ul>

<div class="btn-group btn-group-sm pull-right" role="group">
    <a href="?limit=10&page=1" class="btn btn-default" role="button">10</a>
    <a href="?limit=50&page=1" class="btn btn-default" role="button">50</a>
    <a href="?limit=100&page=1" class="btn btn-default" role="button">100</a>
</div>