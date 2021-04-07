package com.tekdays

class TekUser {

    String fullName
    String userName
    String password
    String email
    String website
    String bio


    String toString(){ fullName }

    static constraints = {
        fullName()
        userName nullable: false,unique: true
        email()
        website()
        bio maxSize:5000
    }

}
