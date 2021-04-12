package com.tekdays

import org.hibernate.SessionFactory
import org.hibernate.envers.AuditReaderFactory
import org.hibernate.envers.query.AuditQuery
import org.springframework.beans.factory.annotation.Autowired

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekEventController {

    SessionFactory sessionFactory

    def taskService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekEvent.list(params), model:[tekEventInstanceCount: TekEvent.count()]
    }

    def show(Long id) {
        def tekEventInstance
        if(params.nickname){
            tekEventInstance = TekEvent.findByNickname(params.nickname)
        }
        else {
            tekEventInstance = TekEvent.get(id)
        }
        if (!tekEventInstance) {
            if(params.nickname){
                flash.message = "TekEvent not found with nickname ${params.nickname}"
            }
            else {
                flash.message = "TekEvent not found with id $id"
            }
            redirect(action: "index")
            return
        }
        [tekEventInstance: tekEventInstance]
    }

    def create() {
        respond new TekEvent(params)
    }

    RevisionService revisionService

    def revisions(){
        def revisionList = revisionService.revisions(TekEvent.class,params.getLong("id"))
        [revisionList: revisionList]
    }

    @Transactional
    def save(TekEvent tekEventInstance) {
        if (tekEventInstance == null) {
            notFound()
            return
        }

        if (tekEventInstance.hasErrors()) {
            respond tekEventInstance.errors, view:'create'
            return
        }

        tekEventInstance.save flush:true
        taskService.addDefaultTasks(tekEventInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect tekEventInstance
            }
            '*' { respond tekEventInstance, [status: CREATED] }
        }
    }

    def edit(TekEvent tekEventInstance) {
        if (tekEventInstance?.organizer?.id != session.user.id){
            redirect(controller: 'tekEvent',action: 'show',id: tekEventInstance.id)
        }
        respond tekEventInstance
    }

    @Transactional
    def update(TekEvent tekEventInstance) {
        if (tekEventInstance == null) {
            notFound()
            return
        }

        if (tekEventInstance.hasErrors()) {
            respond tekEventInstance.errors, view:'edit'
            return
        }

        tekEventInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect tekEventInstance
            }
            '*'{ respond tekEventInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekEvent tekEventInstance) {

        if (tekEventInstance == null) {
            notFound()
            return
        }
        //Comment for merge
        //another comment
        if (tekEventInstance?.organizer?.id != session.user.id){
            redirect(controller: 'tekEvent',action: 'show',id: tekEventInstance.id)
            return
        }
            tekEventInstance.delete flush:true



        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def search = {
        if (params.query){
            def events = TekEvent.search(params.query).results
        }
    }
    def volunteer = {
        def event = TekEvent.get(params.id)
        event.addToVolunteers(session.user)
        event.save(flush: true)
        render "Thank you for Volunteering"
    }

}
