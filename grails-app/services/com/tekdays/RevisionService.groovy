package com.tekdays

import grails.transaction.Transactional
import org.hibernate.SessionFactory
import org.hibernate.envers.AuditReaderFactory
import org.hibernate.envers.query.AuditQuery

@Transactional
class RevisionService {

    SessionFactory sessionFactory

    def serviceMethod() {

    }

    def revisions(className,Long id) {
        def auditQueryCreator = AuditReaderFactory.get(sessionFactory.currentSession).createQuery()
        def revisionList = []
        AuditQuery query = auditQueryCreator.forRevisionsOfEntity(className, false, true)
        query.resultList.each {
            if (it[0].id == id) {
                revisionList.add(it)
            }
        }
        return revisionList
    }
}
