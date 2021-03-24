package tekdays

class TekEvent {

    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description

    static hasMany = [volunteers  : TekUser,
                      respondents : String,
                      sponsorships: SponsorShip,
                      tasks        : Task,
                      messages    : TekMessage,
                      sponsors    : Sponsor]


    static constraints = {
        name()
        city()
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

    @Override
    public String toString() {
        return "$name,$city"
    }

}
