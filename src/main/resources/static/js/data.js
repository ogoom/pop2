$(document).ready( function () {
	 var table = $('#report').DataTable({
			"processing": true,
            "search": {
                return: true
            },
            "serverSide": true,
            "ajax": {
                "url":"/report",
                "dataType":"jsonp"
                }
//			columns: [
//			    { "data": "id"},
//		        { "data": "name" },
//				{ "data": "lastName" },
//				{ "data": "email" },
//				{ "data": "phone" },
//				{ "data": "active" }
//				{ "data": "active" }
//				{ "data": "active" }
//				{ "data": "active" }
//				{ "data": "active" }
//				{ "data": "active" }
//				{ "data": "active" }
//				{ "data": "active" }
//				{ "data": "active" }
//				{ "data": "active" }
//				{ "data": "active" }
//			]
	 });
});

$(document).ready(function() {
    var table = $('#example').DataTable();

    $('#example tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        alert( 'You clicked on '+data[0]+'\'s row' );
    } );
} );