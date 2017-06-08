<html>
<head>
    <title>Free Mode</title>
    <%@include file="../../jspf/head.jspf" %>
</head>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <div class="row">
            <input id="search" type="text" class="form-control" placeholder="Comma-separated labels">
        </div>
        <br><br>
        <div class="row" style="color:black">
            <div id="task-panel" class="panel-group">
                <c:forEach varStatus="taskStatus" var="task" items="${tasks}">
                    <div class="panel panel-default ${completeMap[task.id] ? 'panel-success' : 'panel-warning'}">
                    <div class="panel-heading" style="padding: 0">
                        <a href="/task/${task.id}" class="btn btn-md btn-success" style="position: relative; right: -525px;">Go</a>
                        <h4 class="panel-title" style="display: inline; margin-left: -20px;">
                            <c:if test="${not empty task.description}"><a data-toggle="collapse"
                                                                          href="#collapse${taskStatus.index}"></c:if>
                                ${task.name} <c:if test="${completeMap[task.id]}"><span class="glyphicon glyphicon-ok" style="color:yellowgreen"></span></c:if>
                            <c:if test="${not empty task.description}"></a></c:if>
                        </h4>
                    </div>
                    <div id="collapse${taskStatus.index}"
                         class="panel-collapse collapse">
                        <div class="panel-body"><pre>${task.description}</pre></div>
                        <div class="panel-footer">
                            <c:forEach var="label" items="${task.labels}">
                                <span class="label label-primary" style="font-size: 10pt; margin-right: 5px">${label.value}</span>
                            </c:forEach>
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