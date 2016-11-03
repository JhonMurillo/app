var ComponentForm = function () {
    return {
        loadMask: function () {
            $('.mask_date').mask('99/99/9999');
            $('.calendar input').mask('99/99/9999');
            $('.mask_phone').mask('(999) 999-9999');
            $('.mask_movil').mask('(9) 999-9999');
            $('.mask_nit').mask('999 999 999-9');
            $('.mask_cedula').mask("#.###", {reverse: true});
//            $('.mask_money').mask2("#.##0", {reverse: true});
//            $('.mask_number').mask2("#0", {reverse: true});
        },
        initDatatable: function () {
            App.datatables();
            $('.datatable').dataTable({
                order: [[0, "desc"]],
                pageLength: 10,
                lengthMenu: [[10, 20, 30, -1], [10, 20, 30, 'Todo']],
                language: {
                    lengthMenu: "Mostrando _MENU_ registros por página",
                    zeroRecords: "No existen registros",
                    info: "Página _PAGE_ de _PAGES_",
                    infoEmpty: "No hay registros válidos",
                    infoFiltered: "(de _MAX_ registros)"
                }
            });
            $('.datatable-oc').dataTable({
                order: [[0, "desc"]],
                pageLength: 5,
                lengthMenu: [[5, 10, 20, -1], [5, 10, 20, 'Todo']],
                language: {
                    lengthMenu: "Mostrando _MENU_ registros por página",
                    zeroRecords: "No existen registros",
                    info: "Página _PAGE_ de _PAGES_",
                    infoEmpty: "No hay registros válidos",
                    infoFiltered: "(de _MAX_ registros)"
                }
            });
            $('.dataTables_filter input').attr('placeholder', 'Buscar');
        },
        reloadDatatable: function () {
            $('table.datatable').siblings('.row').remove();
            $('table.datatable').parent().removeAttr("id").removeAttr("class");
            this.initDatatable();
        },
        reloadDatatableOc: function () {
            $('table.datatable').siblings('.row').remove();
            $('table.datatable').parent().removeAttr("id").removeAttr("class");
            this.initDatatable();
        },
        initLoadingDiv: function (div) {
            $(div).css('position', 'relative');
            marginTop = $(div).css('padding-top');
            marginLeft = $(div).css('padding-left');
            loading = $('<div class="loadingDiv" style="display:none"/>')
                    .css('margin-top', '-' + marginTop)
                    .css('margin-left', '-' + marginLeft)
                    .append('<i class="fa fa-spinner fa-4x fa-spin"/>');
            $(div).prepend(loading);
            $(div + ' .loadingDiv').fadeIn('slow');
        },
        stopLoadingDiv: function (div) {
            $(div + ' .loadingDiv').fadeOut('slow', function () {
                console.log($(this));
                $(this).remove();
            });
        }

    };
}();