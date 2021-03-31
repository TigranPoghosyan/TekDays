package com.tekdays

import groovy.util.logging.Slf4j
import jline.internal.Log
import org.apache.log4j.LogManager

import java.util.logging.Logger

class Sponsor {


    String name
    String website
    String description
    byte[] logo

    static hasMany = [events: TekEvent, sponsorships: SponsorShip]
    static belongsTo = TekEvent

    String toString() { name }


    static constraints = {
        name blank: false
        website blank: false, url: true
        description nullable: true, maxSize: 5000
        logo nullable: true, maxSize: 1000000
        sponsorships nullable: true
    }

    static mapping = {
        sponsorships cascade: 'all-delete-orphan'
    }

}
