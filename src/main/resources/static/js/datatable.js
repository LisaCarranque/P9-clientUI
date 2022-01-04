    $(document).ready(function () {
        $(document).ready(function() {
            $('#sort').dataTable( {
                "ordering": false,
                "info":     false,
                "searching": true,
                "bLengthChange": false,
                "displayLength": 10,
                  "language": {
                    "paginate": {
                      "previous": "<<",
                      "next": ">>"
                    }
                  }
            } );
        } );
        $('#selectid').prop('disabled', true);
        $('#selectid').prop('selected', true);
        $.fn.DataTable.ext.pager.numbers_length = 5;
    });

/*
    $(document).ready(function () {
        $(document).ready(function() {
            $('#sort').dataTable( {
                "ordering": false,
                "info":     false,
                "searching": true,
                "bLengthChange": false,
                "displayLength": 10,
                  "language": {
                    "paginate": {
                      "previous": "<<",
                      "next": ">>"
                    }
                  }
            } );
        } );
        $('#selectid').prop('disabled', true);
        $('#selectid').prop('selected', true);
        $.fn.DataTable.ext.pager.numbers_length = 5;
    });*/

