package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Sponsor)
class SponsorSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test website name"() {
        when:"website starts with https://www"
        def sponsor = new Sponsor(name: 'hello',website: 'https://www.hello.com',description: 'bye',logo: 7)
        then:"website is valid"
        sponsor.validate(['website'])

        when:"website doesn't start with https://www"
        def sponsor1 = new Sponsor(name: 'hello',website: 'https:/www.hello.com',description: 'bye',logo: 7)
        then:"website is not valid"
        !sponsor1.validate(['website'])
    }
}
