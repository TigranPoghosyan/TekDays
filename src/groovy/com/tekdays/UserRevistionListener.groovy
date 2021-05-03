package com.tekdays

import org.codehaus.groovy.grails.web.util.WebUtils
import org.hibernate.envers.RevisionListener

class UserRevistionListener implements RevisionListener {
    @Override
    void newRevision(Object entity) {

        UserRevisionEntity revisionEntity = (UserRevisionEntity) entity
        def session = WebUtils.retrieveGrailsWebRequest().session
        TekUser user = session.user ?: TekUser.findByUserName("admin")
        revisionEntity.currentUser = user

    }
}
