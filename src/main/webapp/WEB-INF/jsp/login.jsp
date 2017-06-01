<html>
<head>
    <title>Sign In</title>
    <%@ include file="../jspf/head.jspf"%>
</head>
<body>
    <%@ include file="../jspf/head.jspf"%>
    <div class="login-form center-block">
        <form:form action="/login" method="post">
            <input class="form-control" type="text" name="username" placeholder="Username"><br>
            <input class="form-control" type="password" name="password" placeholder="Password"><br>
            <input class="btn btn-success" type="submit" value="Sign In">
            <a href="<c:url value="/sign-up"/>" class="btn btn-primary">Sign Up</a>
        </form:form>
    </div>
</body>
</html>
