$(document).ready(function() {
    // Generate date options for the select boxes
    console.log('Document ready!');
    generateDateOptions();

    $('#reg-password, #reg-confirm-password').on('keyup', function() {
        if ($('#reg-password').val() !== $('#reg-confirm-password').val()) {
            // Passwords do not match
            $('#password-error').text('Passwords do not match');
        } else {
            // Passwords match
            $('#password-error').text('');
        }
    });

    // Handle the form submission
    $(document).on('submit', '#signup-form', function(event) {
        event.preventDefault();
        console.log('Form submitted!');

        // Validate the form
        if (!validateForm()) {
            return;
        }

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
                window.location.href = 'home';
            },
            error: function() {
                alert('Registration failed. Please try again.');
            }
        });
    });
});

function validateForm() {
    var fname = $('#reg-fname').val();
    var lname = $('#reg-lname').val();
    var email = $('#reg-email').val();
    var password = $('#reg-password').val();
    var job = $('#reg-job').val();
    var interest = $('#reg-interests').val();
    var creditLimit = $('#reg-credit-limit').val();
    var gender = $('input[name="gender"]:checked').val();
    var year = $('#year').val();
    var month = $('#month').val();
    var day = $('#day').val();

    if (!fname || !lname || !email || !password || !job || !interest || !creditLimit || !gender || !year || !month || !day) {
        alert('All fields are required.');
        return false;
    }

    // Add more specific validation for each field as needed
    // For example, you can check if the email is in the correct format, if the password meets certain criteria, etc.

    return true;
}


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