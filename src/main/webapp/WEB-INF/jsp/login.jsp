<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%@ include file="../jspf/header.jspf"%>
</head>
<body>
    <div class="col-lg-3 center-block">
        <form:form action="/login" method="post">
            <input class="form-control" type="text" name="username" placeholder="Username"><br>
            <input class="form-control" type="password" name="password" placeholder="Password"><br>
            <input class="btn btn-primary" type="submit" value="Sign in">
        </form:form>
    </div>
</body>
</html>
