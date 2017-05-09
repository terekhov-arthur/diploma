<jsp:useBean id="task" scope="request" type="ua.nure.model.Task"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${task.name}</title>
    <%@include file="../../jspf/header.jspf"%>
</head>
<body>
    <h2>${task.description}</h2>
    <form:form action="/task/${task.id}/check" method="post">
        <textarea name="solution" cols="70" rows="10">${task.source}</textarea>
        <br>
        <input type="submit" value="Run">
    </form:form>
</body>
</html>
