package com.tekdays

import grails.converters.JSON
import grails.transaction.Transactional
import groovy.json.JsonBuilder
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@RestController
class TekEventController {

    def dataTablesSourceService

    def taskService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", revision: "PUT", appData: "GET"]

    def index() {
        [properties: ["name", "city", "organizer", "venue", "startDate", "endDate", "description", "id", "Get Revision"]]
    }

    def show(Long id) {
        def tekEventInstance
        if (params.nickname) {
            tekEventInstance = TekEvent.findByNickname(params.nickname)
        } else {
            tekEventInstance = TekEvent.get(id)
        }
        if (!tekEventInstance) {
            if (params.nickname) {
                flash.message = "TekEvent not found with nickname ${params.nickname}"
            } else {
                flash.message = "TekEvent not found with id $id"
            }
            redirect(action: "index")
            return
        }
        [tekEventInstance: tekEventInstance]
    }

    def dtList() {} //avtomat stexcume dtList.gsp vor@ irakanum chka)))

    def dataTablesRenderer() {
        def propertiesToRender = ["name", "city", "organizer", "venue", "startDate", "endDate", "description", "id", "id"]
        // petq e nkarenq ays dashter@
        def entityName = TekEvent.class.simpleName //classi anun@
        render dataTablesSourceService.dataTablesSource(propertiesToRender, entityName, params)
        //table@ kstana ir spaseliq Json@ ays hramanic heto

    }


    def create() {
        respond new TekEvent(params)
    }

    @Transactional
    def save(TekEvent tekEventInstance) {
        if (tekEventInstance == null) {
            notFound()
            return
        }

        if (tekEventInstance.hasErrors()) {
            respond tekEventInstance.errors, view: 'create'
            return
        }

        tekEventInstance.save flush: true
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
        if (tekEventInstance?.organizer?.id != session.user.id) {
            redirect(controller: 'tekEvent', action: 'show', id: tekEventInstance.id)
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
            respond tekEventInstance.errors, view: 'edit'
            return
        }

        tekEventInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect tekEventInstance
            }
            '*' { respond tekEventInstance, [status: OK] }
        }
    }

    def appData() {
        def data = TekEvent.get(params.id)
        if (data) {
            def builder = new JsonBuilder()
            def root = builder.event {

                city(data.city)
                name(data.name)
                description(data.description)
            }
            render root as JSON
        } else {
            data = TekEvent.list()
            def builder = new JsonBuilder()
            def list = builder.events {
                event data.collect() {
                    [city       : it.city,
                     name       : it.name,
                     description: it.description,
                     start_date : it.startDate,
                     end_date   : it.endDate,
                     venue      : it.venue,
                     volunteers : it.volunteers.collect {
                         [name: it.fullName]
                     }]
                }
            }
            render list as JSON
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
        if (tekEventInstance?.organizer?.id != session.user.id) {
            redirect(controller: 'tekEvent', action: 'show', id: tekEventInstance.id)
            return
        }
        tekEventInstance.delete flush: true


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def volunteer = {
        def event = TekEvent.get(params.id)
        event.addToVolunteers(session.user)
        event.save(flush: true)
        render "Thank you for Volunteering"
    }

    def revisionSelect() {
        [instance: TekEvent.get(params.id)]
    }

}
