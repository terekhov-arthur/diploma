<jsp:useBean id="task" scope="request" type="ua.nure.model.Task"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="src/main/webapp/bootstrap/css/global.css"/>
    <title>${task.name}</title>
    <%@include file="../../jspf/header.jspf" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <h2>${task.description}</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            <form:form action="/task/${task.id}/check" method="post">
                <img src="<c:url value="/bootstrap/img/mac.png"/>" alt="macbook image">
                <textarea id="source" name="solution" cols="70" rows="10">${task.source}</textarea>
                <input class="btn btn-success run-button" type="submit" value="Run">
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
