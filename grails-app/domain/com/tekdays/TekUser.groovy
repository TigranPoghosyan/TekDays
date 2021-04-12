package com.tekdays

import org.hibernate.envers.Audited

@Audited
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

}
