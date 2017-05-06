<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add task</title>
</head>
<body>
<form action="/task" method="post" enctype="multipart/form-data">
    <input type="text" name="taskName" placeholder="task name"><br>
    <input type="file" name="source" placeholder="source"/><br>
    <input type="file" name="test" placeholder="test"/><br>
    <input type="submit" value="send">
</form>
</body>
</html>
