<%@ page import="com.tekdays.UserRevisionEntity" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tekEvent.label', default: 'TekEvent')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<html>
<head>
    <title>Revision</title>
</head>

<body>
<g:showRevisions revisionList="${revisionList}" showList="${showList}"/>
</body>

</html>

