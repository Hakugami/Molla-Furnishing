    // Function to enable or disable all fieldsets
    function toggleFieldsets(disabled) {
        var fieldsets = document.querySelectorAll('fieldset');
        fieldsets.forEach(function(fieldset) {
            fieldset.disabled = disabled;
        });
    }

    // Function to reset form data
    function resetFormData() {
        // Reset user name
        document.getElementById('nameInput').value = "Hamada";

        // Reset credit limit
        document.getElementById('creditInput').value = "1000";

        // Reset birthday
        document.getElementById('date').value = "";

        // Reset job
        document.getElementById('jobInput').value = "HamadaJob";
    }

    // Function to handle edit user button click
    function editUser() {
        // Enable all fieldsets
        toggleFieldsets(false);

        // Disable the edit user button
        document.getElementById('editUserButton').disabled = true;
    }

    // Function to handle reset button click
    function resetForm() {
        // Reset form data
        resetFormData();

        // Disable all fieldsets
        toggleFieldsets(true);

        // Enable the edit user button
        document.getElementById('editUserButton').disabled = false;
    }

    // Attach event listeners to edit user and reset buttons
    document.getElementById('editUserButton').addEventListener('click', editUser);
    document.getElementById('resetButton').addEventListener('click', resetForm);



    // JavaScript code to handle adding and removing rows from the address table

// Initialize a counter for the row numbering
let rowCount = 1;

// Function to add a new row to the address table
function addAddressRow() {
    // Get a reference to the address table body
    let addressTableBody = document.querySelector("#addressTableBody");

    // Create a new row
    let newRow = document.createElement("tr");

    // Populate the new row with cells
    newRow.innerHTML = `
        <td><h6 class="fw-semibold mb-1">${rowCount}</h6></td>
        <td><input type="text" class="form-control form-control-sm" value="" name="street[]"></td>
        <td><input type="text" class="form-control form-control-sm" value="" name="city[]"></td>
        <td><input type="text" class="form-control form-control-sm" value="" name="state[]"></td>
        <td><input type="text" class="form-control form-control-sm" value="" name="country[]"></td>
        <td><input type="text" class="form-control form-control-sm" value="" name="zipcode[]"></td>
    `;

    // Increment the row count for the next row
    rowCount++;

    // Append the new row to the address table body
    addressTableBody.appendChild(newRow);
}

// Function to update the row numbers in the address table
function updateRowNumbers() {
    // Get all rows in the address table body
    let rows = document.querySelectorAll("#addressTableBody tr");

    // Loop through each row and update the row number
    rows.forEach((row, index) => {
        // Update the text content of the first cell in the row
        let rowNumberCell = row.querySelector(".row-number");
        rowNumberCell.textContent = index + 1;
    });
}

// Function to remove the last row from the address table
function removeAddressRow() {
    // Get a reference to the address table body
    let addressTableBody = document.querySelector("#addressTableBody");

    // Check if there are any rows to remove
    if (addressTableBody.children.length > 1) {
        // Remove the last row from the address table
        addressTableBody.removeChild(addressTableBody.lastElementChild);

        // Update row numbers after removal
        updateRowNumbers();
    } else {
        alert("Cannot remove the last row.");
    }
}

// Event listener for the "Add Address" button
document.getElementById("addAddressBtn").addEventListener("click", addAddressRow);
    // Event listener for the "Remove Address" button
document.getElementById("removeAddressBtn").addEventListener("click", removeAddressRow);
