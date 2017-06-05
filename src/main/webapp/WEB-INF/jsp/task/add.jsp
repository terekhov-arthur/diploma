<html>
<head>
    <title>Add task</title>
    <%@include file="../../jspf/head.jspf"%>
</head>
<body>
    <%@include file="../../jspf/header.jspf"%>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <form:form action="/task" method="post" enctype="multipart/form-data">
                    <input class="form-control"  type="text" name="name" placeholder="task name"><br>
                    <textarea class="form-control" type="text" name="description" placeholder="brief description">
                    </textarea><br>
                    <select class="form-control" name="level">
                        <c:forEach var="level" items="${levels}">
                            <option value="${level.id}">Level ${level.id}</option>
                        </c:forEach>
                    </select><br>
                    <input class="file" minFileCount=2 type="file" name="sourceData" placeholder="source"/><br>
                    <input class="file" type="file" name="testData" placeholder="test"/><br>
                    <input class="btn btn-success" type="submit" value="send">
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
