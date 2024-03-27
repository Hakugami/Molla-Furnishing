$(document).ready(function () {
    let userData = null;
    let ordersData = null;
    $.ajax({
        url: 'getUserOrders', // replace with your API endpoint
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            ordersData = data;
            console.log(data);
            renderOrders(ordersData);

        },
        error: function (error) {
            console.log('Error fetching profile data:', error);
        }
    });
});

$.ajax({
    url: 'loadProfile'
    , type: 'GET'
    , dataType: 'json'
    , success: function (data) {
        userData = data;
        console.log(data);
        renderProfile(userData);
    },
    error: function (error) {
        console.log('Error fetching profile data:', error);
    }

});


function renderProfile(userData) {
    if (userData) {
       $('#dash-name').text(userData.name);
        $('#dash-email').text(userData.email);
        $('#default-address').text(userData.addresses[0].street + ', ' + userData.addresses[0].city + ', ' + userData.addresses[0].state + ', ' + userData.addresses[0].zipCode);
        $('#default-phone').text(userData.phone);

    } else {
        console.log('User data not found.');
    }
}


function renderOrders(ordersData) {
    if (ordersData && ordersData.length > 0) {
        var tbody = $('.dash__table > tbody');
        tbody.empty(); // clear the existing rows

        ordersData.forEach(function (order) {
            var tr = $('<tr>');
            tr.append('<td> # ' + order.id + '</td>');
            tr.append('<td>' + order.orderDate + '</td>');
            tr.append('<td><div class="dash__table-img-wrap"><img class="u-img-fluid" src="' + order.orderItems[0].product.images[0] + '" alt=""></div></td>');
            tr.append('<td><div class="dash__table-total"><span>$' + order.totalAmount + '</span><div class="dash__link dash__link--brand"><a href="dash-manage-order.html">MANAGE</a></div></div></td>');
            tbody.append(tr);
        });
    } else {
        console.log('No orders found in user data.');
    }
}
