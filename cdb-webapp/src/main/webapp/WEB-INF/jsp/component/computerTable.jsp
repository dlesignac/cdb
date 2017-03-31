<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>

<div class="container" style="margin-top: 10px;">
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th class="editMode" style="width: 60px; height: 22px;">
                    <input type="checkbox" id="selectall" />
                    <span style="vertical-align: top;">
                        -
                        <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                            <i class="fa fa-trash-o fa-lg"></i>
                        </a>
                    </span>
                </th>
                <th><spring:message code="dashboard.computer_name"/> <a href="<mylib:link target='dashboard' filter='${page.filter}' sort='computerName' order='ASC' limit='${page.limit}' page='1'/>"><span class="glyphicon glyphicon-sort pull-right" aria-hidden="true"></span></a></th>
                <th><spring:message code="dashboard.introduced"/> <a href="<mylib:link target='dashboard' filter='${page.filter}' sort='introducedDate' order='ASC' limit='${page.limit}' page='1'/>"><span class="glyphicon glyphicon-sort pull-right" aria-hidden="true"></span></a></th>
                <th><spring:message code="dashboard.discontinued"/> <a href="<mylib:link target='dashboard' filter='${page.filter}' sort='discontinuedDate' order='ASC' limit='${page.limit}' page='1'/>"><span class="glyphicon glyphicon-sort pull-right" aria-hidden="true"></span></a></th>
                <th><spring:message code="dashboard.company"/> <a href="<mylib:link target='dashboard' filter='${page.filter}' sort='companyName' order='ASC' limit='${page.limit}' page='1'/>"><span class="glyphicon glyphicon-sort pull-right" aria-hidden="true"></span></a></th>
            </tr>
        </thead>
        <!-- Browse attribute computers -->
        <tbody id="results">
            <c:forEach items="${page.entries}" var="computer">
                <tr>
                    <td class="editMode"><input type="checkbox" name="cb" class="cb" value="${computer.id}"></td>
                    <td><a href="${pageContext.request.contextPath}/computer/${computer.id}"><c:out value="${computer.name}"/></a></td>
                    <td><c:out value="${computer.introduced}"/></td>
                    <td><c:out value="${computer.discontinued}"/></td>
                    <td><c:out value="${computer.manufacturer.name}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>