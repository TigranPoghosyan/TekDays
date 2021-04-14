<%--
  Created by IntelliJ IDEA.
  User: sofs1
  Date: 4/14/21
  Time: 3:45 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
     <meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<g:javascript>
    $(document).ready(function() {
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
            bSortable:true,
            aoColumnDefs: [
                {

                    bSearchable:false,
                    aTargets:[0]

                },
                {

                    bSortable:false,
                    aTargets:[1]},

                {
                createdCell: function ( td, cellData, rowData, row, col) {
                    $(td).attr('style','text-align: center;');
                },
                render: function ( data, type, full, meta ) {
                    if (data) {
                        return '<a href="/TekDays/tekEvent/edit/' + data + '" class="btn">Edit</a>';
                    } else {
                        return "";
                    }
                },
                aTargets: [2]
                        }]
                        });
                        });
</g:javascript>
  </head>
<body>
<table class="display compact" id="dt">
    <thead>
    <tr>
        <th>name</th>
        <th>city</th>
        <th>action</th>
    </tr>
    </thead>
    <tbody></tbody>
    <tfoot>
    <tr>
        <th>name</th>
        <th>city</th>
        <th>action</th>
    </tr>
    </tfoot>
</table>
</body>
</html>
