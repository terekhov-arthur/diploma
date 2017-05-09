<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add task</title>
</head>
<body>
    <form:form action="/task" method="post" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="task name"><br>
        <input type="text" name="description" placeholder="brief description"><br>
        <input type="file" name="sourceData" placeholder="source"/><br>
        <input type="file" name="testData" placeholder="test"/><br>
        <input type="submit" value="send">
    </form:form>
</body>
</html>
