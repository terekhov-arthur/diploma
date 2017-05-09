<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%@ include file="WEB-INF/jspf/header.jspf"%>
</head>
<body>
    <div class="col-lg-3 center-block">
        <form action="auth/login" method="post">
            <input class="form-control" type="text" name="login" placeholder="Login"><br>
            <input class="form-control" type="password" name="password" placeholder="Password"><br>
            <input class="btn btn-primary" type="submit" value="Sign in">
        </form>
    </div>
</body>
</html>
