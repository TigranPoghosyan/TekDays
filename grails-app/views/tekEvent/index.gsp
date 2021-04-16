<%@ page import="com.tekdays.TekEvent" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tekEvent.label', default: 'TekEvent')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <g:javascript>
        $(document).ready(function () {
            $('#dt').DataTable({
                sScrollY: "75%",
                sScrollX: "100%",
                bProcessing: true,
                bServerSide: true,
                sAjaxSource: "/TekDays/tekEvent/dataTablesRenderer",
                bJQueryUI: false,
                bAutoWidth: false,
                sPaginationType: "full_numbers",
                aLengthMenu: [[10, 25, 50, 100, 200], [10, 25, 50, 100, 200]],
                iDisplayLength: 10,
                bSortable: true,
                aoColumnDefs: [

                    {
                        bSearchable: false, aTargets: [0]
                    },
                    {bSortable: false},
                    {createdCell: function (td, cellData, rowData, row, col) {
                            $(td).attr('style', 'text-align: center;');
                        },
                        render: function (data, type, full, meta) {
                            if (data) {
                                return '<a href="/TekDays/tekEvent/edit/' + data + '" class="btn">Edit</a>';
                            } else {
                                return "";
                            }
                        },
                        aTargets: [7]},
                    {render: function (data, type, full, meta) {
                            if (data) {
                                return '<a href="/TekDays/revisions/revisionSelect/' + data + '?type=com.tekdays.TekEvent" class="btn">Revisions</a>';
                            } else {
                                return "";
                            }
                        },
                        aTargets: [8]}
                ]
            });
        });
    </g:javascript>
</head>

<body>
<a href="#list-tekEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>

        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>


</div>

<div id="list-tekEvent" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>


    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:jasperTagLib entityName="${entityName}"/>

    <table class="display compact" id="dt">
        <thead>
        <tr>
            <g:each in="${properties}" var="prop">
                <th>${prop}</th>
            </g:each>
        </tr>
        </thead>

        <tbody>

        </tbody>

        <tfoot>
        <tr>
            <g:each in="${properties}" var="prop">
                <th>${prop}</th>
            </g:each>
        </tr>
        </tfoot>
    </table>
</body>
</html>
