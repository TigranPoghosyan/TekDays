<%@ page import="com.tekdays.TekUser; com.tekdays.TekEvent" %>



<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="tekEvent.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${tekEventInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="tekEvent.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" required="" value="${tekEventInstance?.city}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="tekEvent.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="5000" required="" value="${tekEventInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'organizer', 'error')} required">
	<label for="organizer">

		<g:message code="tekEvent.organizer.label" default="Organizer" />
		<span class="required-indicator">*</span>
	</label>
	<g:if test="${session.user?.id != tekEventInstance?.organizer?.id}">
		<g:select id="organizer" name="organizer.id" from="${session.user}" optionKey="id" required="" value="${tekEventInstance?.organizer?.id}" class="many-to-one"/>
	</g:if>
	<g:else>
		<g:select id="organizer" name="organizer.id" from="${TekUser.list()}" optionKey="id" required="" value="${tekEventInstance?.organizer?.id}" class="many-to-one"/>
	</g:else>

</div>

<div>

</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'venue', 'error')} required">
	<label for="venue">
		<g:message code="tekEvent.venue.label" default="Venue" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="venue" required="" value="${tekEventInstance?.venue}"/>
</div>
<g:if test="">

</g:if>



<div class="fieldcontain ${hasErrors(bean: tekEventInstance,
		field: 'startDate',
		'error')} required">
	<label for="startDate">
		<g:message code="tekEvent.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>

	<g:datePicker name="startDate" precision="day"

				  value="${tekEventInstance?.startDate}" years="${2008..2015}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="tekEvent.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${tekEventInstance?.endDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'volunteers', 'error')} ">
	<label for="volunteers">
		<g:message code="tekEvent.volunteers.label" default="Volunteers" />
		
	</label>
	<g:select name="volunteers" from="${com.tekdays.TekUser.list()}" multiple="multiple" optionKey="id" size="5" value="${tekEventInstance?.volunteers*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'sponsorships', 'error')} ">
	%{--@declare id="sponsorships"--}%<label for="sponsorships">
		<g:message code="tekEvent.sponsorships.label" default="Sponsorships" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tekEventInstance?.sponsorships?}" var="s">
    <li><g:link controller="sponsorShip" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="sponsorShip" action="create" params="['event.id': tekEventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'sponsorShip.label', default: 'SponsorShip')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'task', 'error')} ">
	%{--@declare id="task"--}%<label for="task">
		<g:message code="tekEvent.task.label" default="Task" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tekEventInstance?.tasks?}" var="t">
    <li><g:link controller="task" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="task" action="create" params="['tekEvent.id': tekEventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'task.label', default: 'Task')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'messages', 'error')} ">
	%{--@declare id="messages"--}%<label for="messages">
		<g:message code="tekEvent.messages.label" default="Messages" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tekEventInstance?.messages?}" var="m">
    <li><g:link controller="tekMessage" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tekMessage" action="create" params="['event.id': tekEventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tekMessage.label', default: 'TekMessage')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'respondents', 'error')} ">
	%{--@declare id="respondents"--}%<label for="respondents">
		<g:message code="tekEvent.respondents.label" default="Respondents" />
		
	</label>
	

</div>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'sponsors', 'error')} ">
	<label for="sponsors">
		<g:message code="tekEvent.sponsors.label" default="Sponsors" />
		
	</label>
	<g:select name="sponsors" from="${com.tekdays.Sponsor.list()}" multiple="multiple" optionKey="id" size="5" value="${tekEventInstance?.sponsors*.id}" class="many-to-many"/>

</div>

