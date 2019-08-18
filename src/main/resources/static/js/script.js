// --------------------------------------------------------------------- //
// new message
// --------------------------------------------------------------------- //
var enterPress = false;

// click send btn
$('#send_newMessage').click(function () {
	sendMessage();
});
// 'ENTER' key - cleanup
$('#send_newMessage_text').keyup(function (e) {
	if(e.which == 13 && !e.ctrlKey) {
		if (enterPress) {
			enterPress = false;
			$('#send_newMessage_text').val(null); // set
		}
	}
});
// 'ENTER' key - SEND
$('#send_newMessage_text').keydown(function (e) {
	if(e.which == 13 && !e.ctrlKey) {
		if (!enterPress) {
			enterPress = true;
			sendMessage();
		}
		e.preventDefault();
	}
});
// 'ENTER' key - NEW LINE
$('#send_newMessage_text').keydown(function (e) {
	if(e.which == 13 && e.ctrlKey) {
		$(this).val( $(this).val() + '\n');
	}
});

function sendMessage() {
	var dataText = $('#send_newMessage_text').val(); // get
	console.log(dataText);

	if (dataText.length != 0 && dataText != '') {
		$('#send_newMessage_text').val(null); // set

		$.ajax({
			url: '/api/create',
			type: "POST",
			data: encodeURIComponent(dataText),
			dataType: 'text',
			contentType: 'text/plain'
		}).done(function (data) {
			$('.counter_allposts').after(data);
			var int = parseInt($('.counter').html()) + 1;
			$('.counter').html(int);
		});
	}
}

// --------------------------------------------------------------------- //
// to TOP
// --------------------------------------------------------------------- //

$('.photoBottom.space_up').click(function() {
	$('.side.content').animate({scrollTop: 0},500);
});

// --------------------------------------------------------------------- //
// EDIT post
// --------------------------------------------------------------------- //

$('.content').on('click', '.editPost', function() {
	$(this).parent().parent('.postWrapper.postType').css('background', 'orange');
	var post = $(this).parent().parent('.postWrapper.postType');
	var postIndex = post.index('.postWrapper.postType');
	console.log('edit: ' + postIndex);
});

// --------------------------------------------------------------------- //
// DELETE post
// --------------------------------------------------------------------- //

$('.content').on('click', '.deletePost', function() {
	$(this).parent().parent('.postWrapper.postType').css('background', 'red');
	var post = $(this).parent().parent('.postWrapper.postType');
	var postIndex = post.index('.postWrapper.postType');
	console.log('postIndex: ' + postIndex);

	$.ajax({
		url: 'http://localhost:9999/api/delete',
		type: "POST",
		data: encodeURIComponent(postIndex),
		dataType: 'text',
		contentType: 'text/plain'
	}).done(function (data) {
		console.log('deleted! : ' + data);
		post.remove();
		$('.counter').html(data);
	});
});









