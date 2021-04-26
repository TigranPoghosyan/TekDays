<%--
  Created by IntelliJ IDEA.
  User: sofs1
  Date: 4/1/21
  Time: 12:39 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>TekDays - Dashboard</title>
    <meta name="layout" content="main"/>
</head>

<body>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home"href="${createLink(uri: '/')}"><g:message code="dashboard.home1"/></a></li>
        <li><g:link class="create" controller="task" action="create"> <g:message code="dashboard.task"/></g:link></li>
        <li><g:link class="create" controller="sponsorship" action="create"> <g:message code="dashboard.sponsor"/></g:link></li>
        <li><g:link class="list" controller="sponsor" action="index"> <g:message code="dashboard.allsponsors"/></g:link></li>
    </ul>
</div>


<div id="event" class="dashboard">
    <g:render template="event" model="${['event': event]}"/>
</div>

<div id="tasks" class="dashboard">
    <g:render template="tasks" model="${['tasks': tasks]}"/>
</div>

<div id="volunteers" class="dashboard">
    <g:render template="volunteers" model="${['volunteers': volunteers]}"/>
</div>

<div id="messages" class="dashboard">
    <g:render template="messages" model="${[messages: messages]}"/>
</div>

<div id="sponsors" class="dashboard">
    <g:render template="sponsors" model="${[sponsorships: sponsorships]}"/>
</div>

</body>
</html>