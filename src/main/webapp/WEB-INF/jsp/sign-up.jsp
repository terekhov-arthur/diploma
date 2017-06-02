<html>
<head>
    <title>Sign Up</title>
    <%@ include file="../jspf/head.jspf"%>
</head>
<body>
<div class="login-form center-block">
    <c:set var="cl" value="form-control"/>
    <c:if test="${not empty error}">
        <c:set var="cl" value="form-control has-error"/>
    </c:if>
    <form:form action="/sign-up" method="post">
        <input class="form-control" type="text" name="username" placeholder="Username"><br>
        <input class="${cl}" type="password" name="password" placeholder="Password"><br>
        <input class="${cl}" type="password" name="re-password" placeholder="Repeat password"><br>
        <span>${error}</span><br>
        <input class="btn btn-success" type="submit" value="Sign Up">
    </form:form>
</div>
</body>
</html>
