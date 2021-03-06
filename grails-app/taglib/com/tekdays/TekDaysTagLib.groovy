package com.tekdays

class TekDaysTagLib {
//static encodeAsForTags = [tagName: 'raw']
    def messageThread = { attrs ->
        def messages = attrs.messages.findAll { msg -> !msg.parent }
        processMessages(messages, 0)
    }

    void processMessages(messages, indent) {
        messages.each { msg ->
            def body = "${msg?.author} - ${msg?.subject}"
            out << "<p style='height:35; margin-left:${indent * 20}px;'>"
            out << "${remoteLink(action: 'showDetail', id: msg.id, update: 'details', body)}"
            out << "</p>"
            def children = TekMessage.findAllByParent(msg)
            if (children) {
                processMessages(children, indent + 1)
            }
        }
    }
    def loginToggle = {
        out << "<div style='margin: 15px 0 40px;'>"
        if (request.getSession(false) && session.user) {
            out << "<span style='float:left; margin-left: 15px'>"
            out << "Welcome ${session.user}."
            out << "</span><span style='float:right;margin-right:15px'>"
            out << "<a href='${createLink(controller: 'tekUser', action: 'logout')}'>"
            out << "Logout </a></span>"
        } else {
            out << "<span style='float:right;margin-right:10px'>"
            out << "<a href='${createLink(controller: 'tekUser', action: 'login')}'>"
            out << "Login </a></span>"
        }
        out << "</div><br/>"
    }

    def organizerEvents = {
        if (request.getSession(false) && session.user) {
            def events = TekEvent.findAllByOrganizer(session.user)
            if (events) {
                out << "<div class='homeCell'"
                out << "<h3> ${message code: 'default.eventorganizing'}</h3>"
                out << "<ol>"
                events.each {
                    out << "<li><a href='"
                    out << "${createLink(controller: 'tekEvent', action: 'show', id: it.id)}'>"
                    out << "${it}</a></li>"
                }
                out << "</ol>"
                out << "</div>"
            }
        }
    }

    def volunteerEvents = {
        if (request.getSession(false) && session.user) {
            def events = TekEvent.createCriteria().list {
                volunteers {
                    eq('id', session.user?.id)
                }
            }
            if (events) {
                out << "<div class='homeCell'>"
                out << "<h3> ${message code: 'default.eventsvolunteered'}</h3>"
                out << "<ol>"
                events.each {
                    out << "<li><a href='"
                    out << "${createLink(controller: 'tekEvent', action: 'show', id: it.id)}'>"
                    out << "${it}</a></li>"
                }
                out << "</ol>"
                out << "</div>"
            }
        }
    }

    def volunteerButton = { attrs ->
        if (request.getSession(false) && session.user) {
            def user = session.user.merge()
            def event = TekEvent.get(attrs.eventId as Serializable)
            if (event && !event.volunteers.contains(user)) {
                out << "<span id='volunteerSpan' class='menuButton'>"
                out << "<button id='volunteerButton' type='button'>"
                out << "Volunteer For This Event"
                out << "</button>"
                out << "</span>"
            }
        }
    }

    def showRevisions = { attrs ->
        def revisionList = attrs.revisoinList
        List keySet = new ArrayList(revisionList[0][0].properties.keySet())
        if (revisionList) {
            out << """
        <table>
        <thead>
            <tr>
                <th>Checkbox</th>
                <th>RevId</th>
                <th>RevType</th>
            """
            keySet.each { k ->
                if (k in attrs.showList) {
                    out << "<th>${k}</th>"
                }
            }
            out << """
                <th>ChangedDate</th>
                <th>User</th>
            </tr>
        </thead>
        """
            out << "<tbody>"
            revisionList.each {

                out << "<tr>"
                out << "<td>"
                out << checkBox(name: "myCheckbox", id: "myCheckbox")
                out << "</td>"
                out << "<td>${it[1]?.id}</td>"
                out << "<td>${it[2]}</td>"

                keySet.each { k ->
                    if (k in attrs.showList) {
                        out << "<td>${it[0].properties."${k}"}</td>"
                    }
                }
                out << """
                        <td>${UserRevisionEntity.read(it[1]?.id)?.revisionDate?.format('yyyy-MM-dd HH:mm')}</td>
                        <td>${UserRevisionEntity.read(it[1]?.id)?.currentUser}</td>

                    </tr>
            """
            }
            out << """ </tbody>
                    </table>"""
        } else {
            out << "<h1> No Revisions for this entity!!! </h1>"
        }
    }
}