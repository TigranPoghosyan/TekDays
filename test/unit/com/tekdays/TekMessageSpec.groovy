package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TekMessage)
class TekMessageSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test author"() {
        when:"there is no author"
        def tekMessage = new TekMessage(author: null,subject: "hello",content: "jhd")
        then:"Message should not save"
        !tekMessage.validate(['author'])
        !tekMessage.save()
    }
}
