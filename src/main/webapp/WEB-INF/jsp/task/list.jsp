<html>
<head>
    <title>Task List</title>
    <%@include file="../../jspf/head.jspf" %>
</head>
<body>
<div class="container offset-top-100">
    <div class="row">
        <div class="col-md-2 col-md-offset-5"><h2>Tasks</h2></div>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="col-md-1 col-md-offset-1"><a href="<c:url value="/task"/>" class="btn btn-primary">New Task</a></div>
        </sec:authorize>
    </div>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <table id="task-list" class="table">
                <c:forEach var="task" varStatus="i" items="${tasks}">
                    <tr>
                        <td class="task-data col-md-3">
                            ${task.name}
                            <c:if test="${not empty task.description}">
                                <br>
                                <span style="padding: 10px 0 0 20px; display: none;">${task.description}</span>
                            </c:if>
                        </td>
                        <td class="col-md-1">${task.level.id}</td>
                        <td class="col-md-1"><a href="/task/${task.id}" class="btn btn-sm btn-success">Try</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
