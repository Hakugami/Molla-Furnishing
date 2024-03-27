$(document).ready(function () {
    let userData = null;
    $.ajax({
        url: 'loadProfile', // replace with your API endpoint
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            userData = data;
            console.log(userData);
            renderOrders(userData);
            renderProfile(userData);
        },
        error: function (error) {
            console.log('Error fetching profile data:', error);
        }
    });
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


function renderOrders(userData) {
    if (userData && userData.orders) {
        var tbody = $('.dash__table > tbody');
        tbody.empty(); // clear the existing rows

        userData.orders.forEach(function (order) {
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
