<jsp:useBean id="task" scope="request" type="ua.nure.model.Task"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${task.title}</title>
</head>
<body>
    <h1>${task.title}</h1><br>
    <h3>${task.description}</h3>
    <br><br>
    <h3>${task.methodSignature}</h3>
    <form action="/task/${task.id}/check" method="post">
        <input type="text" name="solution" placeholder="Place your code here"/>
        <br>
        <input type="submit" value="Run">
    </form>
</body>
</html>
