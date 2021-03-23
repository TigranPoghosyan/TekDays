<%@ page import="tekdays.SponsorShip" %>



<div class="fieldcontain ${hasErrors(bean: sponsorShipInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="sponsorShip.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${tekdays.TekEvent.list()}" optionKey="id" required="" value="${sponsorShipInstance?.event?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: sponsorShipInstance, field: 'sponsor', 'error')} required">
	<label for="sponsor">
		<g:message code="sponsorShip.sponsor.label" default="Sponsor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="sponsor" name="sponsor.id" from="${tekdays.Sponsor.list()}" optionKey="id" required="" value="${sponsorShipInstance?.sponsor?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: sponsorShipInstance, field: 'contributionType', 'error')} required">
	<label for="contributionType">
		<g:message code="sponsorShip.contributionType.label" default="Contribution Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="contributionType" from="${sponsorShipInstance.constraints.contributionType.inList}" required="" value="${sponsorShipInstance?.contributionType}" valueMessagePrefix="sponsorShip.contributionType"/>

</div>

<div class="fieldcontain ${hasErrors(bean: sponsorShipInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="sponsorShip.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${sponsorShipInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: sponsorShipInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="sponsorShip.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" cols="40" rows="5" maxlength="5000" value="${sponsorShipInstance?.notes}"/>

</div>

