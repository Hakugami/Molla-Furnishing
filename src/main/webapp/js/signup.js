$(document).ready(function() {
    // Generate date options for the select boxes
    console.log('Document ready!');
    generateDateOptions();

    $('#reg-password, #reg-confirm-password').on('keyup', function() {
        if ($('#reg-password').val() !== $('#reg-confirm-password').val()) {
            // Passwords do not match
            // $('#signup-form button[type="submit"]').prop('disabled', false);
            $('#confirm-password-error').text('Passwords do not match');
        } else {
            // Passwords match
            // $('#signup-form button[type="submit"]').prop('disabled', true);
            $('#confirm-password-error').text('');
        }
    });

    $('#reg-email').on('blur', function() {
        var email = $('#reg-email').val();
        $.post('emailValidation', { email: email }, function(response) {
            var message = response.message;
            if (message === "Email is valid") {
                // If the email is valid, enable the submit button
                $('#email-error').text('');
            } else {
                // If the email is not valid, disable the submit button and show an error message
                $('#email-error').text(message);
            }
            checkValidationStatus();
        });
    });

    $('#reg-password').on('blur', function() {
        var password = $('#reg-password').val();
        $.post('passwordValidation', { password: password }, function(response) {
            var message = response.message;
            if (message === "Password is valid") {
                // If the password is valid, clear the error message
                $('#password-error').text('');
            } else {
                // If the password is not valid, show an error message
                $('#password-error').text(message);
            }
            checkValidationStatus();
        });
    });

    $('#reg-phone').on('blur', function() {
        var phoneNumber = $('#reg-phone').val();
        $.post('phoneValidation', { phoneNumber: phoneNumber }, function(response) {
            var message = response.message;
            if (message === "Phone number is valid") {
                // If the phone number is valid, clear the error message
                $('#phone-error').text('');
            } else {
                // If the phone number is not valid, show an error message
                $('#phone-error').text(message);
            }
            checkValidationStatus();
        });
    });

    // Handle the form submission
    $(document).on('submit', '#signup-form', function(event) {
        event.preventDefault();
        console.log('Form submitted!');

        // Validate the form
        if (!validateForm()) {
            return;
        }

        var password = $('#reg-password').val();
        var passwordError = validatePassword(password);
        if (passwordError) {
            $('#password-error').text(passwordError);
            return;
        }

        // Create a UserDto object from the form data
        var userDto = {
            name: $('#reg-fname').val(),
            email: $('#reg-email').val(),
            password: $('#reg-password').val(),
            phone: $('#reg-phone').val(),
            job: $('#reg-job').val(),
            interest: $('#reg-interests').val(),
            creditLimit: $('#reg-credit-limit').val(),
            gender: $('#gender').val(),
            birthday: $('#year').val() + '-' + $('#month').val() + '-' + $('#day').val()
        };

        // Send a POST request to the server
        $.ajax({
            url: 'register',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(userDto),
            success: function() {
                sendSessionDataToServer();
                window.location.href = 'login';
            },
            error: function() {
                alert('Registration failed. Please try again.');
            }
        });
    });
});

function sendSessionDataToServer() {
    console.log('Sending session data to server...');
    // Retrieve data from session storage
    let shoppingData = sessionStorage.getItem('shoppingData');

    // Make sure shoppingData exists and is not empty
    if (shoppingData) {
        console.log('Session data:', shoppingData);

        $.ajax({
            url: 'cart', // Replace with your server endpoint
            type: 'POST',
            data: {
                action: 'addProductsToCart', // Added action parameter to distinguish from 'addProduct' and 'removeProduct
                shoppingData: shoppingData
            },
            success: function (response) {
                console.log('Session data sent to server successfully.');
            },
            error: function (xhr, status, error) {
                console.error('Failed to send session data to server:', error);
            }
        });
    }
}
function validatePassword(password) {
    // Check the length of the password
    if (password.length < 8) {
        return 'Password must be at least 8 characters long';
    }

    // Check for uppercase letters
    if (!/[A-Z]/.test(password)) {
        return 'Password must contain at least one uppercase letter';
    }

    // Check for lowercase letters
    if (!/[a-z]/.test(password)) {
        return 'Password must contain at least one lowercase letter';
    }

    // Check for numbers
    if (!/[0-9]/.test(password)) {
        return 'Password must contain at least one number';
    }

    // Check for special characters
    if (!/[!@#$%^&*]/.test(password)) {
        return 'Password must contain at least one special character (!@#$%^&*)';
    }

    // If all checks pass, return null
    return null;
}

function checkValidationStatus() {
    var emailError = $('#email-error').text();
    var passwordError = $('#password-error').text();
    var phoneError = $('#phone-error').text();

    if (emailError === '' && passwordError === '' && phoneError === '') {
        // If there are no error messages, enable the submit button
        $('#signup-form button[type="submit"]').prop('disabled', false);
    } else {
        // If there are error messages, disable the submit button
        $('#signup-form button[type="submit"]').prop('disabled', true);
    }
}
function validateForm() {
    var fname = $('#reg-fname').val();
    var email = $('#reg-email').val();
    var password = $('#reg-password').val();
    var phone = $('#reg-phone').val();
    var job = $('#reg-job').val();
    var interest = $('#reg-interests').val();
    var creditLimit = $('#reg-credit-limit').val();
    var gender = $('#gender').val()
    var year = $('#year').val();
    var month = $('#month').val();
    var day = $('#day').val();


    if (fname === undefined || fname === null || fname === "" ||
        email === undefined || email === null || email === "" ||
        password === undefined || password === null || password === "" ||
        job === undefined || job === null || job === "" ||
        interest === undefined || interest === null || interest === "" ||
        creditLimit === undefined || creditLimit === null || creditLimit === "" ||
        gender === undefined || gender === null || gender === "" ||
        year === undefined || year === null || year === "" ||
        month === undefined || month === null || month === "" ||
        phone === undefined || phone === null || phone === "" ||
        day === undefined || day === null || day === "") {
        alert('All fields are required.');
        return false;
    }
    if(gender === 'select'){
        alert('Please fill the gender box ');
        return false;
    }
    if(creditLimit < 0){
        alert('Please fill the credit limit box ');
        return false;
    }
    if(phone.length < 11){
        alert('Please fill the phone box ');
        return false;
    }
    if(month ==='Month' || day ==='Day' || year ==='Year'){
        alert('Please fill the birthday box ');
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