package com.tekdays

import org.hibernate.envers.Audited
import grails.rest.*


@Audited
@Resource(uri = '/tekUser',formats = ['json','xml'])
class TekUser {

    String fullName
    String userName
    String password
    String email
    String website
    String bio


    String toString() { fullName }

    static constraints = {
        fullName nullable: false, blank: false
        userName nullable: false, blank: false
        email nullable: false, blank: false
        website nullable: false, blank: false
        bio nullable: false, blank: false, maxSize: 5000
        password nullable: false, blank: false
    }

    static mapping = {
        fullName lazy: false
    }

}
