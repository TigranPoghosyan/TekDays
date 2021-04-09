package com.tekdays

import groovy.util.logging.Slf4j
import org.hibernate.SessionFactory
import org.hibernate.envers.AuditReaderFactory
import org.hibernate.envers.query.AuditQuery
import org.slf4j.Logger
import org.slf4j.LoggerFactory


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SponsorController {

    SessionFactory sessionFactory

    public static final Logger LOGGER = LoggerFactory.getLogger(SponsorController.class)

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sponsor.list(params), model:[sponsorInstanceCount: Sponsor.count()]
    }

    def show(Sponsor sponsorInstance) {
        LOGGER.info('Trying showing instance with id: {}',sponsorInstance?.id)
        respond sponsorInstance
    }

    def create() {
        respond new Sponsor(params)
    }

//    def revisions(){
//        def auditQueryCreator = AuditReaderFactory.get(sessionFactory.currentSession).createQuery()
//        def revisionList = []
//        AuditQuery query = auditQueryCreator.forRevisionsOfEntity(Sponsor.class, false, true)
//        query.resultList.each {
//            if(it[0].id==params.getLong('id')) {
//                revisionList.add(it)
//            }
//        }
//        [revisionList: revisionList]
//    }
//
//
    @Transactional
    def save(Sponsor sponsorInstance) {
        if (sponsorInstance == null) {
            notFound()
            return
        }

        if (sponsorInstance.hasErrors()) {
            respond sponsorInstance.errors, view:'create'
            return
        }

        sponsorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sponsor.label', default: 'Sponsor'), sponsorInstance.id])
                redirect sponsorInstance
            }
            '*' { respond sponsorInstance, [status: CREATED] }
        }
    }

    def edit(Sponsor sponsorInstance) {
        respond sponsorInstance
    }

    @Transactional
    def update(Sponsor sponsorInstance) {
        if (sponsorInstance == null) {
            notFound()
            return
        }

        if (sponsorInstance.hasErrors()) {
            respond sponsorInstance.errors, view:'edit'
            return
        }

        sponsorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Sponsor.label', default: 'Sponsor'), sponsorInstance.id])
                redirect sponsorInstance
            }
            '*'{ respond sponsorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Sponsor sponsorInstance) {

        if (sponsorInstance == null) {
            notFound()
            return
        }

        sponsorInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Sponsor.label', default: 'Sponsor'), sponsorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsor.label', default: 'Sponsor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
