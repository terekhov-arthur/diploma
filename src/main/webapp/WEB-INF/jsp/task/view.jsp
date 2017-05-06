<jsp:useBean id="task" scope="request" type="ua.nure.model.Task"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${task.name}</title>
</head>
<body>
    <h2>${task.description}</h2>
    <form action="/task/${task.id}/check" method="post">
        <textarea name="solution" cols="70" rows="10">${source}</textarea>
        <br>
        <input type="submit" value="Run">
    </form>
</body>
</html>
