package com.tekdays

import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TekUserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(TekEventController.class);

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekUser.list(params), model: [tekUserInstanceCount: TekUser.count()]
    }


    def show(Long id) {
        def tekUserInstance
        if (params.userName) {
            tekUserInstance = TekUser.findByUserName(params.userName)
        } else {
            tekUserInstance = TekUser.get(id)
        }
        if (!tekUserInstance) {
            if (params.userName) {
                flash.message = "TekUser not found with userName ${params.userName}"
            } else {
                flash.message = "TekUser not found with id $id"
            }
            redirect(action: "index")
            return
        }
        [tekUserInstance: tekUserInstance]
    }

    def create() {
        respond new TekUser(params)
    }

    @Transactional
    def save(TekUser tekUserInstance) {
        if (tekUserInstance == null) {
            notFound()
            return
        }

        if (tekUserInstance.hasErrors()) {
            respond tekUserInstance.errors, view: 'create'
            return
        }

        tekUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect tekUserInstance
            }
            '*' { respond tekUserInstance, [status: CREATED] }
        }
    }

    def edit(TekUser tekUserInstance) {
        respond tekUserInstance
    }

    @Transactional
    def update(TekUser tekUserInstance) {
        if (tekUserInstance == null) {
            notFound()
            return
        }

        if (tekUserInstance.hasErrors()) {
            respond tekUserInstance.errors, view: 'edit'
            return
        }

        tekUserInstance.save flush: true

        LOGGER.info('The tekUser updated userName: {}' + tekUserInstance?.userName)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect tekUserInstance
            }
            '*' { respond tekUserInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekUser tekUserInstance) {

        if (tekUserInstance == null) {
            notFound()
            return
        }

        tekUserInstance.delete flush: true
        LOGGER.info("The tekUser deleted userName()" + tekUserInstance.userName)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekUser.label', default: 'TekUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
    def mailService

    def validate() {
        def user = TekUser.findByUserName(params.username)
        if (user && user.password == params.password) {
            session.user = user
            LOGGER.info('Successfully validated user {}', session.user)

            mailService.sendMail {
                to "${user.email}"
                subject message(code:"email.user.login.subject",args: [user.userName])
                html g.render(template: "mail", model: [tekUserInstance: user])
                LOGGER.info("The email to ${session.user} sent")
            }

            if (params.cName) {
                if (params.aName == 'delete') {
                    redirect(controller: params.cName, action: 'show', id: params.id)
                    return
                }
                redirect controller: params.cName, action: params.aName, id: params.id
            } else {
                redirect(uri: '/')
                //redirect controller: 'tekEvent', action: 'index'
            }
        } else {
            flash.message = "Invalid username and password."
            render view: 'login'
        }
    }

    def login() {
        if (params.cName)
        return [cName: params.cName, aName: params.aName, id: params.id]
    }

    def logout = {
        session.user = null
        redirect(uri: '/')
    }

}
