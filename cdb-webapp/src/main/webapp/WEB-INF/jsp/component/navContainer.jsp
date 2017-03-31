<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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