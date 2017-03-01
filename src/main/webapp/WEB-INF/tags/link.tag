<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="target" type="java.lang.String" required="true" %>
<%@ attribute name="search" type="java.lang.String" required="true" %>
<%@ attribute name="orderBy" type="java.lang.String" required="true" %>
<%@ attribute name="order" type="java.lang.String" required="true" %>
<%@ attribute name="limit" type="java.lang.Integer" required="true" %>
<%@ attribute name="page" type="java.lang.Integer" required="true" %>

<c:url value="/${target}" var="url">
   <c:param name="search" value="${search}"/>
   <c:param name="orderBy" value="${orderBy}"/>
   <c:param name="order" value="${order}"/>
   <c:param name="limit" value="${limit}"/>
   <c:param name="page" value="${page}"/>
</c:url>

${url}