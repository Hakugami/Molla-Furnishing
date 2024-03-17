$(document).ready(function () {
    $.ajax({
        url: 'loadProfile',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            displayProfile(data);
        },
        error: function () {
            alert('Error loading profile');
        }

    })

});

function displayProfile(profile) {
    $('#p-fname').text(profile.name);
    $('#p-email').text(profile.email);
    $('#p-phone').text(profile.phone);
    console.log('hello '+profile.birthday);
    $('#p-bdate').text(profile.birthday);
    $('#p-gender').text(profile.gender);
}