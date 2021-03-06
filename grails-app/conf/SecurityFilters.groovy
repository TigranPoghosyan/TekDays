class SecurityFilters {
    def filters = {
        doLogin(controller: '*', action: '*') {
            before = {
                if (!controllerName)
                    return true
                def allowedActions = ['show', 'index', 'login',
                                      'validate','search','dataTablesRenderer','appData']
                if (!session.user && !allowedActions.contains(actionName)) {
                    redirect(controller: 'tekUser', action: 'login',
                            params: ['cName': controllerName,
                                     'aName': actionName,
                                     'id'   : params.id])
                    return false
                }
            }
        }
    }
}
