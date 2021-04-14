package com.tekdays

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SponsorShipController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SponsorShip.list(params), model: [sponsorShipInstanceCount: SponsorShip.count()]
    }

    def show(SponsorShip sponsorShipInstance) {
        respond sponsorShipInstance
    }

    def create() {
        respond new SponsorShip(params)
    }

    @Transactional
    def save(SponsorShip sponsorShipInstance) {
        if (sponsorShipInstance == null) {
            notFound()
            return
        }

        if (sponsorShipInstance.hasErrors()) {
            respond sponsorShipInstance.errors, view: 'create'
            return
        }

        sponsorShipInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sponsorShip.label', default: 'SponsorShip'), sponsorShipInstance.id])
                redirect sponsorShipInstance
            }
            '*' { respond sponsorShipInstance, [status: CREATED] }
        }
    }

    def edit(SponsorShip sponsorShipInstance) {
        respond sponsorShipInstance
    }

    @Transactional
    def update(SponsorShip sponsorShipInstance) {
        if (sponsorShipInstance == null) {
            notFound()
            return
        }

        if (sponsorShipInstance.hasErrors()) {
            respond sponsorShipInstance.errors, view: 'edit'
            return
        }

        sponsorShipInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SponsorShip.label', default: 'SponsorShip'), sponsorShipInstance.id])
                redirect sponsorShipInstance
            }
            '*' { respond sponsorShipInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SponsorShip sponsorShipInstance) {

        if (sponsorShipInstance == null) {
            notFound()
            return
        }

        sponsorShipInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SponsorShip.label', default: 'SponsorShip'), sponsorShipInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsorShip.label', default: 'SponsorShip'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
