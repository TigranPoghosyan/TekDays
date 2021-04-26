<h3>Forum Messages</h3>
<br>
<table>
    <thead>
    <tr>
        <th><g:message code="dashboard.author"/></th>
        <th><g:message code="dashboard.subject"/></th>
        <th><g:message code="dashboard.content"/></th>
    </tr>
    </thead>
    <g:each in="${messages}" var="msg">
        <tr>
            <td>
                <g:link controller="tekUser" action="show" id="${msg.author.id}">
                    ${msg.author}
                </g:link>
            </td>
            <td>
                ${msg.subject}
            </td>
            <td>
                ${msg.content[0..Math.min(msg.content.size() - 1, 24)]}
                ${msg.content.size() > 25 ? '...' : ''}
            </td>
        </tr>
    </g:each>
</table>
<g:link controller="tekMessage" action="index" id="${event.id}">
    View threaded messages for this event.
</g:link>
