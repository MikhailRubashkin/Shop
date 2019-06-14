$(document).ready(function () {
    $('.addScooterBtn').click(function () {
        addScooter($(this));
    });
});

function addScooter(element) {
    var scooter_id = $(element).attr('id');
    var json = JSON.stringify(scooter_id);
    console.log(json);
    $.ajax({
        type: 'get',
        // data:{productId:productId, command:"addProduct"},
        url: contextUrl + '/frontController?command=addScooter&scooter_id=' + scooter_id
    }).done(function (data) {
        var scooter_id = JSON.parse(data);
        $('#count'+scooter_id).text(scooter_id);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}
