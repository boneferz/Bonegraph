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

	if (dataText.trim().length > 0) {
		$('#send_newMessage_text').val(null); // set

		toTop(); // animation

		$.ajax({
			url: '/api/create',
			type: "POST",
			data: encodeURIComponent(dataText),
			dataType: 'text',
			contentType: 'text/plain'
		}).done(function (data) {
			$('.counter_allposts').after(data);

			// posts counter RESET
			var int = parseInt($('.counter').html()) + 1;
			$('.counter').html(int);

			// symbol counter RESET
			$('.max_symbolsCounter.curentSybols').html(0);
			$('.max_symbolsCounter.curentSybols').removeClass('true');
			$('.max_symbolsCounter.curentSybols').removeClass('max');
		});
	} else {
		$('#send_newMessage_text').val(null);
		// symbol counter RESET
		$('.max_symbolsCounter.curentSybols').html(0);
		$('.max_symbolsCounter.curentSybols').removeClass('true');
		$('.max_symbolsCounter.curentSybols').removeClass('max');
	}
}

// --------------------------------------------------------------------- //
// to TOP
// --------------------------------------------------------------------- //

$('.photoBottom.space_up').click(function() {
	toTop();
});

function toTop() {
	$('.side.content').animate({scrollTop: 0},500);
}

// --------------------------------------------------------------------- //
// EDIT post
// --------------------------------------------------------------------- //
var current_editButton;

// activ
$('.content').on('click', '.editPost', function() {
	// styles - reset
	if (current_editButton != null) {
		current_editButton.removeClass('active');
		current_editButton.parent().siblings('.postText').removeClass('editing');
	}
	current_editButton = $(this); // save

	// style
	$('#send_newMessage').addClass('edit');
	$('#send_newMessage').html('SAVE');
	$(this).addClass('active');
	$(this).parent().siblings('.postText').addClass('editing');
	$('#send_newMessage_text').addClass('editing');
	$('#send_newMessageCancel').show();

	// text
	$('#send_newMessage_text').val($(this).parent().siblings('.postText').html());
});

//disable
$('#send_newMessageCancel').click(function() {
	//stylesreset
	resetEditStyles();
	current_editButton = null;
});

function resetEditStyles() {
	if (current_editButton != null) {
		// style
		$('#send_newMessage').removeClass('edit');
		$('#send_newMessage').html('SUBMIT');
		current_editButton.removeClass('active');
		current_editButton.parent().siblings('.postText').removeClass('editing');
		$('#send_newMessage_text').removeClass('editing');
		$('#send_newMessageCancel').hide();

		// text
		$('#send_newMessage_text').val(null);
	}
}

// send
$('.postWrapper.input').on('click', '.btn.active.edit', function () {
	// data
	var post = $(this).parent().parent().parent('.postWrapper.postType');
	var postIndex = post.index('.postWrapper.postType');

	console.log('edit: ' + postIndex);
	console.log('SEND: ' + postIndex);
});

// --------------------------------------------------------------------- //
// DELETE post
// --------------------------------------------------------------------- //

$('.content').on('click', '.deletePost', function() {
	// reset EDIT
	resetEditStyles();

	var post = $(this).parent().parent().parent('.postWrapper.postType');
	var postIndex = post.index('.postWrapper.postType');
	console.log('postIndex: ' + postIndex);

	$.ajax({
		url: '/api/delete',
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

// --------------------------------------------------------------------- //
// post symbols counter
// --------------------------------------------------------------------- //

var oldVal = "";
$("#send_newMessage_text").on('change keyup paste', function() {
	var newVal = $(this).val();
	var newValSize = $(this).val().length;
	if (oldVal == newVal) return; // nothing
	oldVal = newVal; // save

	// set counter
	$('.max_symbolsCounter.curentSybols').html(newValSize);

	// styles
	if (newValSize == 0) {
		$('.max_symbolsCounter.curentSybols').removeClass('true');
		$('.max_symbolsCounter.curentSybols').removeClass('max');
	} else if (newValSize > 0 && newValSize < 450) {
		$('.max_symbolsCounter.curentSybols').addClass('true');
		$('.max_symbolsCounter.curentSybols').removeClass('max');
	} else if (newValSize == 450) {
		$('.max_symbolsCounter.curentSybols').removeClass('true');
		$('.max_symbolsCounter.curentSybols').addClass('max');
	}
});


