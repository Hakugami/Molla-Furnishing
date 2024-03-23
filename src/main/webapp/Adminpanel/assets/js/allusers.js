$(document).ready(function() {

    var currentPage = 1;

    $('#show-more-btn').on('click', function (event) {
            event.preventDefault();

            currentPage++;
            loadUsers();
    });

    function loadUsers() {
        $.ajax({
            url: '/molla/view/admin/allusers',
            type: 'POST',
            dataType: 'json',
            data: { currentPage: currentPage },
            success: function (users) {
                users.forEach(function (user) {
                    displayUser(user);
                });
            },
            error: function () {
                console.log('Error retrieving users.');
            }
        });
    }

    function displayUser(user) {
        var genderBadge = createGenderBadge(user.gender);

        var row = '<tr>' +
            '<td><h6 class="fw-semibold mb-0">' + user.id + '</h6></td>' +
            '<td><h6 class="fw-semibold mb-0">' + user.name + '</h6></td>' +
            '<td><h6 class="fw-semibold mb-0">' + user.birthday + '</h6></td>' +
            genderBadge +
            '<td><h6 class="fw-semibold mb-0">' + user.email + '</h6></td>' +
            '<td><h6 class="fw-semibold mb-0">' + user.job + '</h6></td>' +
            '<td><h6 class="fw-semibold mb-0">' + user.interest + '</h6></td>' +
            '<td><h6 class="fw-semibold mb-0">' + user.creditLimit + '</h6></td>' +
            '<td><a href="viewuser?id=' + user.id + '"><button type="button" class="btn btn-outline-secondary m-1">View</button></a></td>' +
            '</tr>';

        $('#usersTable tbody').append(row);
    }

    function createGenderBadge(gender) {
        var badgeClass = gender === 'Female' ? 'bg-danger' : 'bg-primary';
        return '<td><div class="d-flex align-items-center gap-2"><span class="badge ' + badgeClass + ' rounded-3 fw-semibold">' + gender + '</span></div></td>';
    }

});
