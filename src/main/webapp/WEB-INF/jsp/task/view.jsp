<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${task.title}</title>
</head>
<body>
    <form action="/task/${task.id}/check" method="post">
        <input type="text" name="solution" placeholder="Place your code here"/>
        <br>
        <input type="submit" value="Run">
    </form>
</body>
</html>
