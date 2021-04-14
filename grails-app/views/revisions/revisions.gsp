<%@ page import="com.tekdays.RevisionsController; com.tekdays.TekEvent" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'revisions.label', default: 'Revisions')}"/>
    <title>Revisions</title>

    <g:javascript>
       function countOfSelectedRevisions() {
        var _count = $("input[name='myCheckbox']:checked").length;
        if(_count>1) {
            var _confirm = confirm("Count of selected revisions is " + _count + "\n\n" +
             "${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}");
           return _confirm;
        } else {
            alert('Count of selected revisions should be more than 1!');
            return false;
        }
    }
</g:javascript>

</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index">
            <g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</div>


<div class="content scaffold-list" role="main">
    <g:showRevisions revisoinList="${revisionList}" showList="${showList}"/>

    <g:form url="[resource: revisions, action: 'revisionButton']" method="PUT">

        <g:hiddenField name="id" value="${revisionList[0][0].id}"/>
        <g:hiddenField name="type" value="${revisionList[0][0].class.name}"/>

        <div>
        </div>
        <div>
       <button type="submit"
               class="save"
               name="btn1"
               value="btn2"
               onclick="return countOfSelectedRevisions();">
           <i class="icon-list-alt"></i>
           Show difference
       </button>

    </g:form>
</div>
</body>
</html>
