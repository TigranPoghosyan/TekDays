<h3>Things to do</h3>
<br>
<table>
    <thead>
    <tr>
        <th><g:message code="dashboard.tasktitle"/></th>
        <th><g:message code="dashboard.duedate"/></th>
        <th><g:message code="dashboard.assignedto"/></th>
    </tr>
    </thead>
    <g:each in="${tasks}" var="task">
        <tr>
            <td>${task.title}</td>
            <td>
                <g:formatDate format="MM/dd/yyyy" date="${task.dueDate}"/>
            </td>
            <td>
                ${task.assignedTo}
            </td>
        </tr>
    </g:each>
</table>

<g:if test="${event.tasks.size()>0}">
    <g:link controller="task" action="index" id="${event.id}">
        View all ${event.tasks.size()} tasks for this event.
    </g:link>
    </g:if>

<g:else>
    No tasks for this event
</g:else>


