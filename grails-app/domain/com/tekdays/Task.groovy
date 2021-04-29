package com.tekdays

import grails.rest.*
import org.hibernate.envers.Audited

@Audited
@Resource(uri = '/task',formats = ['json','xml'])
class Task {

    String title
    String notes
    TekUser assignedTo
    Date dueDate
    TekEvent event
    Boolean completed

    static constraints = {
        title blank: false
        notes blank: true, nullable: true, maxSize: 5000
        assignedTo nullable: true
        dueDate nullable: true
        completed nullable: true
    }
    static belongsTo = TekEvent

    static mapping = {
        event lazy: false
        assignedTo lazy: false
    }

}
