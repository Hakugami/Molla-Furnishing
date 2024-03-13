$(document).ready(function() {
    console.log('Document is ready---------------------------------');
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
});