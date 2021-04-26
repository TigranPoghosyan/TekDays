<%@ page import="grails.util.Environment" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" charset="UTF-8"/>
    <title>TekDays - The Community is the Conference!</title>
</head>

<body>

<div id="homeSearch">
    <g:form controller="tekEvent" action="search">
        <label>Search:</label>
        <label for="query"></label><input id="query" type="text" name="query"/>
        <input type=submit value="Go"/>
    </g:form>
</div>

<br>

<div class="welcome">
    <br/>

    <h3 style="text-align: center"><g:message code="default.welcome.code"/></h3>
    <br>

    <p><g:message code="default.welcoming.message"/></p>
    <br>

</div>

<g:organizerEvents/>
<g:volunteerEvents/>

<div class="homeCell">
    <h3><g:message code="default.find.code"/></h3>
    <br>

    <p><g:message code="default.foundevent.code"/></p>
    <br>
    <span class="buttons">
        <g:link controller="tekEvent" class="findEventButton" action="index"><g:message
                code="default.findtekevent"/></g:link>
    </span>

</div>

<div class="welcome">
    <br>

    <h3 style="text-align: center"><g:message code="default.organize.code"/></h3>
    <br>

    <p><g:message code="default.eventtext.code"/></p>
    <br>
    <span class="buttons">
        <g:link controller="tekEvent" action="create"><g:message code="default.organizeevent.code"/></g:link>
    </span>
</div>

<div class="homeCell">
    <br>

    <h3 style="text-align: center"><g:message code="default.sponsor"/></h3>
    <br>

    <p><g:message code="default.sponsortext"/></p>
    <br>
    <span class="buttons">
        <g:link controller="sponsor" action="create"><g:message code="default.sponsorbutton"/></g:link>
    </span>

</div>
</body>
</html>