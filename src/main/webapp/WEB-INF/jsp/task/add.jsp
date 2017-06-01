<html>
<head>
    <title>Add task</title>
    <%@include file="../../jspf/head.jspf"%>
</head>
<body>
    <%@include file="../../jspf/header.jspf"%>
    <form:form action="/task" method="post" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="task name"><br>
        <input type="text" name="description" placeholder="brief description"><br>
        <select name="level">
            <c:forEach var="level" items="${levels}">
                <option value="${level.id}">Level ${level.id}</option>
            </c:forEach>
        </select><br>
        <input type="file" name="sourceData" placeholder="source"/><br>
        <input type="file" name="testData" placeholder="test"/><br>
        <input type="submit" value="send">
    </form:form>
</body>
</html>
