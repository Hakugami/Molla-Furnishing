$(document).ready(function() {
    // Generate date options for the select boxes
    console.log('Document ready!');
    generateDateOptions();

    // Handle the form submission
    $(document).on('submit', '#signup-form', function(event) {
        event.preventDefault();
        console.log('Form submitted!');

        // Create a UserDto object from the form data
        var userDto = {
            // firstName: $('#reg-fname').val(),
            // lastName: $('#reg-lname').val(),
            name: $('#reg-fname').val()+" "+$('#reg-lname').val(),
            email: $('#reg-email').val(),
            password: $('#reg-password').val(),
            job: $('#reg-job').val(),
            interest: $('#reg-interests').val(),
            creditLimit: $('#reg-credit-limit').val(),
            gender: $('input[name="gender"]:checked').val(),
            birthday: $('#year').val() + '-' + $('#month').val() + '-' + $('#day').val()
        };

        // Send a POST request to the server
        $.ajax({
            url: 'register',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(userDto),
            success: function() {
                alert('Registration successful!');
            },
            error: function() {
                alert('Registration failed. Please try again.');
            }
        });
    });
});

function generateDateOptions() {
    console.log('Generating date options...');
    // Select the select boxes
    var monthSelect = document.querySelector('#month');
    var daySelect = document.querySelector('#day');
    var yearSelect = document.querySelector('#year');

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
    months.forEach(function(month) {
        monthSelect.appendChild(createOption(month));
    });

    // Append the options to the day select box
    days.forEach(function(day) {
        daySelect.appendChild(createOption(day));
    });

    // Append the options to the year select box
    years.forEach(function(year) {
        yearSelect.appendChild(createOption(year));
    });
}