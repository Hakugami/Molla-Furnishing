$(document).ready(function () {
    // Generate date options for the select boxes
    console.log('Document ready!');
    generateDateOptions();


    $('#update-profile-btn').click(function (event) {
        event.preventDefault();
        let UserDto = {
            name: $('#Ureg-fname').val(),
            interest: $('#Ureg-interest').val(),
            DOB: $('#Uyear').val() + '-' + $('#Umonth').val() + '-' + $('#Uday').val(),
            gender: $('#Ugender').val(),
        }
        console.log('Update profile button clicked');
        $.ajax({
            type: 'POST',
            url: 'editProfile',
            data: UserDto,
            success: function (data) {
                window.location.href = 'profile';
            },
            error: function () {
                console.log('Error creating user');
            }
        });
    });

});

function generateDateOptions() {
    console.log('Generating date options...');
    // Select the select boxes
    var monthSelect = document.querySelector('#Umonth');
    var daySelect = document.querySelector('#Uday');
    var yearSelect = document.querySelector('#Uyear');

    // Create arrays for the months, days, and years
    var months = moment.months();
    var days = Array.from({length: 31}, (_, i) => i + 1);
    var currentYear = moment().year();
    var years = Array.from({length: 100}, (_, i) => currentYear - i);

    // Function to create an option element
    function createOption(value) {
        var option = document.createElement('option');
        option.value = value;
        option.text = value;
        return option;
    }

    // Append the options to the month select box
    months.forEach(function (month) {
        monthSelect.appendChild(createOption(month));
    });

    // Append the options to the day select box
    days.forEach(function (day) {
        daySelect.appendChild(createOption(day));
    });

    // Append the options to the year select box
    years.forEach(function (year) {
        yearSelect.appendChild(createOption(year));
    });
}