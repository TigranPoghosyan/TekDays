
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <title>Diff Page</title>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="revisionButton">
            <g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div class="table table-active table-bordered">
    <g:showRevisions revisoinList="${revisionList}" dif="true" showList="${showList}"/>
</div>
</body>
</html>