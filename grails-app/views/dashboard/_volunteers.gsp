<h3>Volunteers</h3>
<br>
<table>
    <thead>
    <tr>
        <th><g:message code="dashboard.name1"/></th>
        <th><g:message code="dashboard.email"/></th>
        <th><g:message code="dashboard.website1"/></th>
    </tr>
    </thead>
    <g:each in="${volunteers}" var="volunteer">
        <tr>
            <td>
                <g:link controller="tekUser" action="show" id="${volunteer.id}">
                    ${volunteer.fullName}
                </g:link>
            </td>
            <td>
                <a href="mailto:${volunteer.email}">
                    ${volunteer.email}
                </a>
            </td>
            <td>
                <a href="http://${volunteer.website}">
                    ${volunteer.website}
                </a>
            </td>
        </tr>
    </g:each>
</table>