package com.tekdays

class RevisionsController {

    RevisionService revisionService

    def revisions() {
        def revisionList = revisionService.getRevisionResults(Class.forName(params.type), params.getLong('id'))
        [revisionList: revisionList, showList: params.showList]
    }

    def revisionSelect() {
        if (!params.type) {
                redirect(uri: "/")
        }else {
            def cls = Class.forName(params.type)
            def instance = cls.get(params.id)
            [instance: instance]
        }
    }

    def revisionButton(){
        if (!params.type) {
            redirect(uri: "/")
        }else {
            def cls = Class.forName(params.type)
            def instance = cls.get(params.id)

            def revisionList1 = revisionService.getRevisionResults(Class.forName(params.type), params.getLong('id'))
            [revisionList: revisionList1, showList: params.showList]
        }
    }
}