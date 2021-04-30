package com.tekdays

import org.hibernate.envers.Audited
import grails.rest.*

//@Resource(uri = '/tekEvent',formats = ['json','xml'])
@Audited
class TekEvent {

    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description
    String nickname

    static searchable = true
    static hasMany = [volunteers  : TekUser,
                      respondents : String,
                      sponsorships: SponsorShip,
                      tasks       : Task,
                      messages    : TekMessage,
                      sponsors    : Sponsor]


    static constraints = {
        name()
        city blank: false
        description maxSize: 5000
        organizer()
        venue()
        startDate()
        endDate()
        volunteers nullable: true
        sponsorships nullable: true
        tasks nullable: true
        messages nullable: true
        nickname nullable: true, unique: true
        organizer lazy: false
        volunteers lazy: false
    }


    static mapping = {
        tasks cascade: 'all-delete-orphan'
        messages cascade: 'all-delete-orphan'
        sponsorships cascade: 'all-delete-orphan'
        organizer lazy: false
    }

    @Override
    public String toString() {
        return "$name,$city"
    }

}
