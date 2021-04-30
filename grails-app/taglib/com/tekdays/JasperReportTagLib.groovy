package com.tekdays

class JasperReportTagLib {

    def jasperTagLib = { attrs ->
        out << jasperReport(jasper: attrs.entityName, format: "XLS,PDF,HTML,CSV", description: attrs.entityName, name: "${attrs.entityName}List")
    }

    def emailSendType = {
        out << "<p>"
        out << message(code: "default.send")
        out << "</p>"
        out << link(action: "sendJasperToEmail", params: [format: 'PDF'], "PDF ")
        out << link(action: "sendJasperToEmail", params: [format: 'CSV'], "CSV ")
        out << link(action: "sendJasperToEmail", params: [format: 'XLS'], "XLS ")
    }
}
