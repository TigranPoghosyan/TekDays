<body>
<div id="show-tekUser" class="content scaffold-show" role="main">
    <h1 style="border-bottom: 1px solid #CCCCCC;
    margin: 0.8em 1em 0.3em;
    padding: 0 0.25em;">
        <g:message code="default.show.label" args="['TekUser']"/></h1>

    <ol style="margin: 0.6em 1.25em 0 1.25em;
    padding: 0.3em 1.8em 1.25em;
    position: relative;
    zoom: 1;
    border: none;">

        <g:if test="${tekUserInstance?.fullName}">
            <li class="fieldcontain">
                <span id="fullName-label" class="property-label"><g:message code="tekUser.fullName.label"
                                                                            default="Full Name"/></span>

                <span style="display: block;
                margin-left: 27%;"
                      aria-labelledby="fullName-label"><g:fieldValue bean="${tekUserInstance}"
                                                                     field="fullName"/></span>

            </li>
        </g:if>

        <g:if test="${tekUserInstance?.userName}">
            <li class="fieldcontain">
                <span id="userName-label" class="property-label"><g:message code="tekUser.userName.label"
                                                                            default="User Name"/></span>

                <span style=" display: block;
                margin-left: 27%;"
                      aria-labelledby="userName-label"><g:fieldValue bean="${tekUserInstance}"
                                                                     field="userName"/></span>

            </li>
        </g:if>

        <g:if test="${tekUserInstance?.email}">
            <li class="fieldcontain">
                <span id="email-label" class="property-label"><g:message code="tekUser.email.label"
                                                                         default="Email"/></span>

                <span style=" display: block;
                margin-left: 27%;"
                      aria-labelledby="email-label"><g:fieldValue bean="${tekUserInstance}"
                                                                  field="email"/></span>

            </li>
        </g:if>

        <g:if test="${tekUserInstance?.website}">
            <li class="fieldcontain">
                <span id="website-label" class="property-label"><g:message code="tekUser.website.label"
                                                                           default="Website"/></span>

                <span style=" display: block;
                margin-left: 27%;"
                      aria-labelledby="bio-label"><g:fieldValue bean="${tekUserInstance}"
                                                                                           field="website"/></span>

            </li>
        </g:if>

        <g:if test="${tekUserInstance?.bio}">
            <li class="fieldcontain">
                <span id="bio-label" class="property-label"><g:message code="tekUser.bio.label" default="Bio"/></span>

                <span style=" display: block;
                margin-left: 27%;"
                      aria-labelledby="bio-label"><g:fieldValue bean="${tekUserInstance}"
                                                                field="bio"/></span>

            </li>
        </g:if>

    </ol>

</div>
</body>
