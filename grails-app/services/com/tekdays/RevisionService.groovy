package com.tekdays

import grails.transaction.Transactional
import org.hibernate.SessionFactory
import org.hibernate.envers.AuditReaderFactory

@Transactional
class RevisionService {
SessionFactory sessionFactory
    def serviceMethod() {}

    def getRevisionResults(Class<?> className, Long id) {
        def revisionList = []
        AuditReaderFactory
                .get(sessionFactory.currentSession)
                .createQuery()
                .forRevisionsOfEntity(className, false, true)
                .resultList
                .each {
                    if (it[0].id == id) {
                        revisionList << it
                    }
                }
        return revisionList
    }

//    def getRevisionResults(Class<?> className, Long id, List<Long> revId) {
//        def revisionList = []
//        AuditReaderFactory
//                .get(sessionFactory.currentSession)
//                .createQuery()
//                .forRevisionsOfEntity(className, false, true)
//                .resultList
//                .each {
//                    if (it[0].id == id && it[1].id in revId) {
//                        revisionList << it
//                    }
//                }
//        return revisionList
//    }

}
