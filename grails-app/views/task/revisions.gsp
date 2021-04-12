<%--
  Created by IntelliJ IDEA.
  User: sofs1
  Date: 4/8/21
  Time: 11:24 AM
--%>

<%@ page import="com.tekdays.UserRevisionEntity" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<html>
<head>
    <title>Revision</title>
</head>



<body>
<g:showRevisions revisionList="${revisionList}"/>
</body>
</html>