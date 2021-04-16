package com.tekdays

class JasperReportTagLib {

    def jasperTagLib = { attrs ->
        out << jasperReport(jasper: attrs.entityName, format: "XLS,PDF,HTML,CSV", description: attrs.entityName, name: "${attrs.entityName}List")
    }
}
