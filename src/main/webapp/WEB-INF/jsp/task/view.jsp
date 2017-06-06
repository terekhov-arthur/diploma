<jsp:useBean id="task" scope="request" type="ua.nure.model.Task"/>
<html>
<head>
    <title>${task.name}</title>
    <%@include file="../../jspf/head.jspf" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <h2>${task.description}</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            <code class="code-box">${task.source}</code>
            <form:form action="/task/${task.id}/check" method="post">
                <img src="<c:url value="/bootstrap/img/mac.png"/>" alt="macbook image">
                <textarea id="source" name="solution" cols="70" rows="10">${task.source}</textarea>
                <input class="btn btn-success run-button" type="submit" value="Run">
            </form:form>
        </div>
        <div class="col-md-2 statistic">
            <jsp:useBean id="statistic" scope="request" type="ua.nure.model.TaskStatistic"/>
            <c:if test="${not empty statistic}">
                <table class="table">
                    <tr>
                        <td>Completed</td>
                        <td>${statistic.completed}</td>
                    </tr>
                    <tr>
                        <td>Attempts failed</td>
                        <td>${statistic.fails}</td>
                    </tr>
                </table>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
