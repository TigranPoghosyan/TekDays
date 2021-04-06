package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin}
 * for usage instructions
 */
@TestFor(TekUser)
class TekUserIntegrationSpec extends Specification {

    void setup() {
//        new TekUser(fullName: 'John Doe',
//                userName: 'jdoe',
//                password: 't0ps3cr3t',
//                email: 'jdoe@johnsgroovyshop.com',
//                website: 'blog.johnsgroovyshop.com',
//                bio: 'John has been programming for over 40 years. ...').save()
//        new TekUser(fullName: 'John Deere',
//                userName: 'tractorman',
//                password: 't0ps3cr3t',
//                email: 'john.deere@porkproducers.org',
//                website: 'www.perl.porkproducers.org',
//                bio: 'John is a top notch Perl programmer and a ...').save()
    }

    void "test user"() {
        expect:
        new TekUser(fullName: fullName,
                userName: username,
                password: password,
                email: email,
                website: website,
                bio: bio).validate() == shouldBeValid

        where:
        fullName | username | password | email            | website                 | bio     | shouldBeValid
//        'Tigran' | 'hslel ' | 'itgra.@mail.ru' | 'www.hello.com'         | 'hello' | false
        'Tigran' | 'tik'    | 'hslel ' | 'itgra.@mail.ru' | 'https://www.hello.com' | 'hello' | true

    }


}
//
//void "test my TekUser"() {
//    expect: "Count of users is one"
//    TekUser.count() == 2
//    TekUser.countByFullName('John Doe') == 1
//    TekUser.get(1).fullName == 'John Doe'
//}

