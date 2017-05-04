<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add task</title>
</head>
<body>
<form action="/task" method="post" enctype="multipart/form-data">
    <input type="file" name="template"/>
    <input type="submit" value="send">
</form>
</body>
</html>
