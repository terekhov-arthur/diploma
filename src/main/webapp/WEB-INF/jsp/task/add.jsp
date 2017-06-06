<html>
<head>
    <title>Add task</title>
    <%@include file="../../jspf/head.jspf"%>
</head>
<body>
    <%@include file="../../jspf/header.jspf"%>
    <div class="container">
        <div class="row">
            <form:form action="/task" method="post" enctype="multipart/form-data" onsubmit="return validate()">
                <div class="col-md-4 col-md-offset-4">
                    <input class="form-control"  type="text" name="name" placeholder="task name" required><br>
                    <textarea class="form-control" type="text" name="description" placeholder="brief description">
                    </textarea><br>
                    <select class="form-control" name="level">
                        <c:forEach var="level" items="${levels}">
                            <option value="${level.id}">Level ${level.id}</option>
                        </c:forEach>
                    </select><br>
                    <input class="labels" type="text" name="labelSet" placeholder="Labels"><br>
                </div>
                <div class="col-md-8 col-md-offset-2" style="text-align: center;">
                    <label class="control-label">Select source and test files</label>
                    <input id="sourceData" type="file" multiple name="sourceData[]" placeholder="source"/><br>
                    <input class="btn btn-success" type="submit" value="send">
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>
