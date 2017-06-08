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
        <div class="panel-group" id="accordion" style="color:black;">
            <c:forEach varStatus="levelStatus" var="level" items="${levels}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse${levelStatus.index}">
                                Level ${level.id}
                            </a>
                        </h4>

                    </div>
                    <%--IN CASE WE NEED TO HAVE FIRST LEVEL OPEN--%>
                    <%--<c:choose>--%>
                        <%--<c:when test="${levelStatus.index == 0}">--%>
                            <%--<c:set var="in" value="in"/>--%>
                        <%--</c:when>--%>
                        <%--<c:otherwise>--%>
                            <%--<c:set var="in" value=""/>--%>
                        <%--</c:otherwise>--%>
                    <%--</c:choose>--%>
                    <div id="collapse${levelStatus.index}" class="panel-collapse collapse ${in}">
                        <div class="panel-body">
                            <div class="panel-group">
                                <c:forEach varStatus="taskStatus" var="task" items="${level.tasks}">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="padding: 0">
                                            <a href="/task/${task.id}" class="btn btn-lg btn-primary right-btn">Go</a>
                                            <h4 class="panel-title" style="display: inline; margin-left: -50px;">
                                            <c:if test="${not empty task.description}"><a data-toggle="collapse" href="#collapse${levelStatus.index}${taskStatus.index}"></c:if>
                                                ${task.name}
                                            <c:if test="${not empty task.description}"></a></c:if>
                                            </h4>
                                        </div>
                                        <div id="collapse${levelStatus.index}${taskStatus.index}" class="panel-collapse collapse">
                                            <div class="panel-body">${task.description}</div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    </div>
</div>
</body>
</html>
