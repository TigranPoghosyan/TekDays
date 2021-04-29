package com.tekdays

import grails.rest.*

@Resource(uri = '/books')
class Book {

    String name
    int pages
    String genre
    String description


    static constraints = {
        genre nullable: false, blank: false
        pages nullable:false,blank:false
        name blank: false
        description nullable: true, maxSize: 5000
    }
}
