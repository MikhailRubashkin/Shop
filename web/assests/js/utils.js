$(document).ready(function () {
    $('.addScooterBtn').click(function () {
        addScooter($(this));
    });
    $('.reduceScooterBtn').click(function () {
        reduceScooter($(this));
    });
    $('.addOrderBtn').click(function () {
        addOrder();
    });
    $('.paymentBtn').click(function () {
        payment();
    });
});

function addScooter(element) {
    var scooter_id = $(element).attr('id');
    var json = JSON.stringify(scooter_id);
    console.log(json);
    $.ajax({
        type: 'get',
        url: contextUrl + '/frontController?command=addScooter&scooter_id=' + scooter_id
    }).done(function (data) {
        $('#count'+scooter_id).text(data);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function reduceScooter(element) {
    var scooter_id = $(element).attr('id');
    $.ajax({
        type: 'post',
        url: contextUrl + '/frontController?command=removeScooter&scooter_id=' + scooter_id
    }).done(function (data) {
        $('#count'+scooter_id).text(data);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function addOrder(element) {
    var order_id = $(element).attr('id');
    $.ajax({
        type: 'post',
        url: contextUrl + '/frontController?command=addOrder&order_id=' +order_id
    }).done(function (data) {
        alert("Item successfully added to cart")
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}
function payment() {
    $.ajax({
        type: 'post',
        url: contextUrl + '/frontController?command=payment'
    }).done(function (data) {
        alert("Payment made successfully !!")
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}