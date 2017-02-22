
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>

<footer class="navbar-fixed-bottom">
    <div class="container text-center">

        <mylib:pagination limit="${page.limit}" count="${page.count}" number="${page.number}" />

    </div>
</footer>