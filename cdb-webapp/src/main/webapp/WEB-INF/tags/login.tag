<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ul class="nav navbar-nav navbar-right">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
           aria-expanded="false">
            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
            <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
            <li>
                <sec:authorize access="isAuthenticated()">
                    <a href="/logout"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Log out</a>
                </sec:authorize>
                <sec:authorize access="not isAuthenticated()">
                    <a href="/login"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> Log in</a>
                </sec:authorize>
            </li>
        </ul>
    </li>
</ul>