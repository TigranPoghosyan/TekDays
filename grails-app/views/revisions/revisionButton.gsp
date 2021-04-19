<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'revisions.label', default: 'Differences')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
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