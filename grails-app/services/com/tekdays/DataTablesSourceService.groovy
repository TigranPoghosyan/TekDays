package com.tekdays

import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.plugins.support.aware.GrailsApplicationAware

@Transactional(readOnly = true)
class DataTablesSourceService implements GrailsApplicationAware {

    GrailsApplication grailsApplication

    def dataTablesSource(propertiesToRender, entityName, params) { // 1-@ fielderi cucak 2-@ domaini anun ev 3-@ parapetrer
        boolean someFilter = false // identifikatora stuguma filtracia ka te che?
        Class clazz = grailsApplication.domainClasses.find {it.clazz.simpleName == entityName}.clazz // staunm enq domain class@ u
        // gnumenq ira klass@ vercnenq

        def filters = [] //massiv enq stexcum
        propertiesToRender.eachWithIndex { prop, idx -> //qaylumenq indexi vr-ayov inch hertaknautyamb nkarelenq tenca nkarvum...
            // eachWithIndex@ov karanq ev property tanq ev index
            def sSearchField = params["sSearch_${idx}"] //paramsic kardum enq sSearch index@ @st ir indexi gtnum e
            if (sSearchField != '') { //ete datark e uremn chka vochinch
                someFilter = true // ete ban grac e filtr@ durs ga
                filters << "dt.${prop} = '${sSearchField}'" // aystex grum enq sa vortex dt.n domaini annunn e...aysinqn filtr dzezavorvec
            }
            if (params.sSearch) { //heto stugum enq sa ka te che ? sa poiski texn e
                if (params["bSearchable_${idx}"] == 'true') { // ete ka u bSearchable-i id-n true e
                    filters << "dt.${prop} like :filter"
                }
            }
        }
        def filter = filters.join(" OR ") //erp vor dzevavorvum e amen filtri hamar join enq anum

        def dataToRender = [:] //map enq sarqum vor@ ir mej uni dashter vor@ tablei hamapatasxan nkarchutyunn e anum
        dataToRender.sEcho = params.sEcho //sa echo-n e
        dataToRender.aaData = []  // Array of data.

        dataToRender.iTotalRecords = clazz.count() //cuyc e talis mer domaini bolor elemeneri qanak@
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords //qani record e cuyc talis ham@nkumneri qanak@

        def query = new StringBuilder("from ${entityName} as dt where dt.id is not null") //sa sql injectioni hamar e vor apahovenq
        //dt-i aylans sarqecinq ev stugecinq null-i hamar
        def appendToQuery = ""//datark string enq sarqum

        query.append(appendToQuery) //
        if (params.sSearch) { //ete ka
            query.append(" and (${filter})") //append enq anum sharq sarqac eventner@
        } else if (someFilter) { //ete search@ ka mnaca@ ignore enq anum ete chka myusnern enq append anum uxaki filterner@ tarber en
            query.append(" and (${filter})")
        }
        def sortingCols = params?.iSortingCols as int //sorti hamar iSortingCols-um
        def orderBy =  new StringBuilder() //
        (0..sortingCols-1).each {
            if(it>0) {
                orderBy.append(",")//hertov vrov kancnenq u storaketnerov kavelacnenq
            }
            def sortDir = params["sSortDir_${it}"]?.equalsIgnoreCase('asc') ? 'asc' : 'desc' // achman kam nvazman kargov enq dasavorum
            def sortProperty = propertiesToRender[params["iSortCol_${it}"] as int] // propertytoRenderic anun@ stanum e ev ayd element@ dran enq veraagrum

            orderBy.append("dt.${sortProperty} ${sortDir}")
        }
        query.append(" order by ${orderBy}") // sra ardtunqum mer amen inch@ kdzevavorvi

        def records
        if (params.sSearch) { //ete search@ unenq hashvum enq searchi ardyunqum mer count@ ashxatacnelu hnaravorumtyun@
            String sSearch = params.sSearch
            // Revise the number of total display records after applying the filter
            def countQuery = new StringBuilder("select count(*) from ${entityName} as dt where dt.id is not null")
            countQuery.append(appendToQuery).append(" and (${filter})")
            def result = clazz.executeQuery(countQuery.toString(), [filter: "%${sSearch}%"]) //sql injection chlinelu hamar
            if (result) {
                dataToRender.iTotalDisplayRecords = result[0]
            }
            records = clazz.findAll(query.toString(), [filter: "%${sSearch}%"], [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        } else if (someFilter) {//myus depqum
            // Revise the number of total display records after applying the filter
            def countQuery = new StringBuilder("select count(*) from ${entityName} as dt where dt.id is not null")
            countQuery.append(appendToQuery).append(" and (${filter})")
            def result = clazz.executeQuery(countQuery.toString())
            if (result) {
                dataToRender.iTotalDisplayRecords = result[0]
            }
            records = clazz.findAll(query.toString(), [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
            //maximum displaylengthn e veradardzum aysinqn qanak@,ardyunqum kstananq eji qanaki chap tox
        } else {
            // Revise the number of total display records after applying the filter
            def countQuery = new StringBuilder("select count(*) from ${entityName} as dt where dt.id is not null") //
            countQuery.append(appendToQuery)
            def result = clazz.executeQuery(countQuery.toString())
            if (result) {
                dataToRender.iTotalDisplayRecords = result[0]
            }
            records = clazz.findAll(query.toString(), [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        }
//ardyunqum records cucakum ka domaini bolor dashter@
        //ka data bolor recordneri hamar?
        records?.each { r ->
            def data = []
            propertiesToRender.each { f ->

//                    data.add(r["${f}"][0]?.toString())
                    data.add((r["${f}"] instanceof BigDecimal)?r["${f}"]:r["${f}"]?.toString())
            }
            dataToRender.aaData << data //aa datai mej ays datan enq lcnum
        }

        return dataToRender as JSON //verjum menq datatorender@ json enq sarqum ev json@ veradardznuma mez dtlist
    }
}

