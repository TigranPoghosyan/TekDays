<%--
  Created by IntelliJ IDEA.
  User: sofs1
  Date: 3/29/21
  Time: 4:20 PM
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Login</title>
</head>

<body>
<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="validate">
    <table>
        <tr class="prop">
            <td class="name">
                <label for="username"><g:message code="login.username"/></label>
            </td>
            <td class="value">
                <input type="text" id="username" name="username" value="">
            </td>
        </tr>
        <tr class="prop">
            <td class="name">
                <label for="password"><g:message code="login.password"/></label>

        <input type="hidden" name="cName" value="${cName}">
        <input type="hidden" name="aName" value="${aName}">
        <input type="hidden" name="id" value="${id}">

            </td>
            <td class="value">
                <input type="password" id="password" name="password" value="">
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <input type="submit" value="<g:message code="login.loginbutton"/>"/>
            </td>
        </tr>
    </table>
</g:form>
</body>
</html>