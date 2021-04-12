<%@ page import="com.tekdays.Task" %>

<%@ page import="com.tekdays.Sponsor" %>

<f:with bean="taskInstance">
	<f:field property="title"/>

	<f:field property="notes">
		<g:textArea name="notes" cols="40" rows="5" maxlength="5000" value="${taskInstance?.notes}"/>
	</f:field>

	<f:field property="assignedTo"/>

	<f:field property="dueDate">
		<g:datePicker name="dueDate" precision="day" value="${taskInstanse?.dueDate}" default="none" noSelection="['':'']" years="${2050..2021}"/>
	</f:field>
	<f:field property="event"/>
	<f:field property="completed"/>

</f:with>
