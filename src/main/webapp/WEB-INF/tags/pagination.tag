
<%@ attribute name="page" type="fr.ebiz.cdb.model.Page" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>

<c:choose>
    <c:when test="${page.maxNumber <= 5}">
        <c:set var="begin" value="1"/>
        <c:set var="end" value="${page.maxNumber}"/>
    </c:when>
    <c:when test="${page.number <= 3}">
        <c:set var="begin" value="1"/>
        <c:set var="end" value="5"/>
    </c:when>
    <c:when test="${page.number >= page.maxNumber - 2}">
        <c:set var="begin" value="${page.maxNumber - 4}"/>
        <c:set var="end" value="${page.maxNumber}"/>
    </c:when>
    <c:otherwise>
        <c:set var="begin" value="${page.number - 2}"/>
        <c:set var="end" value="${page.number + 2}"/>
    </c:otherwise>
</c:choose>

<ul class="pagination">
    <c:if test="${page.number != 1}">
        <li><a href="<mylib:link target='' search='${page.search}' orderBy='${page.orderBy}' order='ASC' limit='${page.limit}' page='${page.number - 1}'/>" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
        </a></li>
    </c:if>
    <c:forEach var="i" begin="${begin}" end="${end}">
        <li<c:if test="${i == page.number}"> class="active"</c:if>>
            <a href="<mylib:link target='' search='${page.search}' orderBy='${page.orderBy}' order='ASC' limit='${page.limit}' page='${i}'/>">${i}</a>
        </li>
    </c:forEach>
    <c:if test="${page.number != page.maxNumber}">
        <li><a href="<mylib:link target='' search='${page.search}' orderBy='${page.orderBy}' order='ASC' limit='${page.limit}' page='${page.number + 1}'/>" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
        </a></li>
    </c:if>
</ul>

<div class="btn-group btn-group-sm pull-right" role="group">
    <a href="<mylib:link target='' search='${page.search}' orderBy='${page.orderBy}' order='ASC' limit='10' page='1'/>" class="btn btn-default" role="button">10</a>
    <a href="<mylib:link target='' search='${page.search}' orderBy='${page.orderBy}' order='ASC' limit='50' page='1'/>" class="btn btn-default" role="button">50</a>
    <a href="<mylib:link target='' search='${page.search}' orderBy='${page.orderBy}' order='ASC' limit='100' page='1'/>" class="btn btn-default" role="button">100</a>
</div>