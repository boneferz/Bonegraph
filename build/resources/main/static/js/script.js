$('#send_newMessage').click(function (params) {
	var dataText = $('#send_newMessage_text').val(); // get
	console.log(dataText);
	$('#send_newMessage_text').val(null); // set

	$.ajax({
		url: 'http://localhost:9999/api/create',
		type: "POST",
		data: encodeURIComponent(dataText),
		dataType: 'text',
		contentType: 'text/plain'
	}).done(function (data) {
		console.log('done! : ' + data);
		$('.counter_allposts').after('<p>' + data + '</p>');
	});
});