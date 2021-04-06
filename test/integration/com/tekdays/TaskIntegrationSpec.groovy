package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Task)
class TaskIntegrationSpec extends Specification{


    void setup(){
        def task1 = new Task(title: 'Hello',notes: 'bye',assignedTo: new TekUser(),dueDate: new Date(),event: new TekEvent(),completed: true).save()

        def task2 = new Task(title: 'Hello everybade',notes: 'anonymous',assignedTo:new TekUser(),dueDate: new Date(),event: new TekEvent(),completed: false ).save()
    }
    void "test my Task.save()"(){
        expect:"I look how good work my project"
        Task.count() == 2
        Task.countByCompleted(false) == 1

    }

}
