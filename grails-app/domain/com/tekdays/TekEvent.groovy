package com.tekdays

class TekEvent {

    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description

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
    }


    static mapping = {
        tasks cascade: 'all-delete-orphan'
        messages cascade: 'all-delete-orphan'
        sponsorships cascade: 'all-delete-orphan'


    }

    @Override
    public String toString() {
        return "$name,$city"
    }

}
