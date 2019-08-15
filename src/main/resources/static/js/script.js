// =======================================
// =======================================

$(document).ready(function () {

    $("#btn").click(function () {

        //1
        $("#btn").before(' <p><b>penic</b></p> ');
        console.log('click');

        //2
        $.ajax({
            url: "http://localhost:8888/greeting", success: function (result) {
                $("#btn").after('<p>' + result.content + '</p>');
                console.log('a j a x : ' + result)
            }
        });
    });

});


// =======================================
// =======================================


$(document).ready(function () {
    console.log("hi man! 9938 heu")
});

function getAjax() {
    $.ajax({
        url: "http://localhost:9999/greeting"
    }).then(function (data) {
        $('.greeting-id').append(data.id);
        $('.greeting-content').append(data.content);
        console.log(data);
        console.log(data.id);
    });
}

function retrieveGuests() {
    var url = '/guests';

    $("#resultsBlock").load(url);
}

function createPost(data) {
    console.log(data);
    console.log('new post!');
}

