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
    <h3 style="text-align: center">Welcome to TekDays.com &#128509;</h3>
    <br>

    <p>TekDays.com is a site dedicated to assisting individuals and
    communities to organize technology conferences. To bring great
    minds with common interests and passions together for the good
    of greater geekdom!</p>
    <br>

</div>

<g:organizerEvents/>
<g:volunteerEvents/>

<div class="homeCell">
    <h3>Find a Tek Event &#129395;</h3>
    <br>

    <p>See if there's a technical event in the works that strikes your
    fancy. If there is, you can volunteer to help or just let the
    organizers know that you'd be interested in attending.
    Everybody has a role to play.</p>
    <br>
    <span class="buttons">
        <g:link controller="tekEvent" class="findEventButton" action="index">Find a Tek Event</g:link>
    </span>

</div>

<div class="welcome">
    <br>

    <h3 style="text-align: center">Organize a Tek Event &#128293;</h3>
    <br>

    <p>If you don't see anything that suits your interest and location,
    then why not get the ball rolling. It's easy to get started and
    there may be others out there ready to get behind you to make it
    happen.</p>
    <br>
    <span class="buttons">
        <g:link controller="tekEvent" action="create">Organize a Tek Event</g:link>
    </span>
</div>

<div class="homeCell">
    <br>

    <h3 style="text-align: center">Sponsor a Tek Event &#128176;</h3>
    <br>

    <p>If you are part of a business or organization that is involved in
    technology then sponsoring a tek event would be a great way to
    let the community know that you're there and you're involved.</p>
    <span class="buttons" id="">
        <br>
        <g:link controller="sponsor" action="create">Sponsor a Tek Event</g:link>
    </span>
</div>
</body>
</html>