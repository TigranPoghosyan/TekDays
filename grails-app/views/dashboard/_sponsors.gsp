<h3>Sponsors</h3>
<br>
<table>
    <thead>
    <tr>
        <th><g:message code="dashboard.name"/></th>
        <th><g:message code="dashboard.website"/></th>
        <th><g:message code="dashboard.contribution"/></th>
    </tr>
    </thead>
    <g:each in="${sponsorships}" var="s">
        <tr>
            <td>
                <g:link controller="sponsor" action="show" id="${s.sponsor.id}">
                    ${s.sponsor.name}
                </g:link>
            </td>
            <td>
                <a href="${s.sponsor.website}">
                    ${s.sponsor.website}
                </a>
            </td>
            <td>
                ${s.contributionType}
            </td>
        </tr>
    </g:each>
</table>