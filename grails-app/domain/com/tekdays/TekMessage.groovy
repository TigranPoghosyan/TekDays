package com.tekdays

import grails.rest.*
import org.hibernate.envers.Audited

//@Resource(uri = '/tekMessage',formats = ['json','xml'])
@Audited
class TekMessage {

    String subject
    String content
    TekMessage parent
    TekEvent event
    TekUser author
    static constraints = {
        subject blank: false
        content blank: false, maxSize: 2000
        parent nullable: true
        author nullable: false
    }
    static belongsTo = TekEvent
}
