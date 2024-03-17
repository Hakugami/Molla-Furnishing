$(document).ready(function () {

    // Get all countries
    let countries= co

    // Populate the country dropdown
    $.each(countries, function (index, country) {
        $('#address-country').append($('<option>', {
            value: country.isoCode,
            text: country.name
        }));
    });

    // Update the state dropdown when a country is selected
    $('#address-country').change(function () {
        // Clear the state dropdown
        $('#address-state').empty();

        // Get states of the selected country
        let states = window.csc.getStatesOfCountry(this.value);

        // Populate the state dropdown
        $.each(states, function (index, state) {
            $('#address-state').append($('<option>', {
                value: state.isoCode,
                text: state.name
            }));
        });
    });

});