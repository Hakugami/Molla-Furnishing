$(document).ready(function() {
    // Function to populate the table with product data
    function populateTable(page, size) {
        $.ajax({
            url: '/molla/view/RetrieveProducts', // URL of the servlet
            type: 'GET',
            dataType: 'json',
            data: {
                page: page,
                limit: size
            },
            success: function(data) {
                // Clear existing table rows
                $('#productTable tbody').empty();

                // Loop through each product in the data and append to the table
                $.each(data, function(index, product) {
                    var row = '<tr>' +
                        '<td><img alt="" class="rounded-circle" height="55" src="' + product.imageUrl + '"></td>' +
                        '<td><h6 class="fw-semibold mb-0">' + product.id + '</h6></td>' +
                        '<td><h6 class="fw-semibold mb-1">' + product.name + '</h6></td>' +
                        '<td><div class="d-flex align-items-center gap-2"><span class="badge bg-primary rounded-3 fw-semibold">' + product.category + '</span></div></td>' +
                        '<td><h6 class="fw-semibold mb-1">' + product.price + '</h6></td>' +
                        '<td><h6 class="fw-semibold mb-1">' + product.stock + '</h6></td>' +
                        '<td><h6 class="fw-semibold mb-1">' + product.dateAdded + '</h6></td>' +
                        '<td><h6 class="fw-semibold mb-1">' + product.rating + '</h6></td>' +
                        '<td><button type="button" class="btn btn-outline-secondary m-1">View</button><button type="button" class="btn btn-outline-success m-1">Edit</button></td>' +
                        '</tr>';
                    $('#productTable tbody').append(row);
                });

                // Update pagination controls
                updatePaginationControls(page, size);
            },
            error: function(xhr, status, error) {
                console.error('Error fetching product data:', error);
            }
        });
    }

    // Function to update pagination controls
    function updatePaginationControls(page, size) {
        $.ajax({
            url: 'CountProductsServlet', // URL of the servlet to count products
            type: 'GET',
            dataType: 'json',
            success: function(totalProducts) {
                // Calculate total number of pages
                var totalPages = Math.ceil(totalProducts / size);

                // Clear existing pagination controls
                $('#pagination').empty();

                // Add previous button
                if (page > 1) {
                    $('#pagination').append('<button class="btn btn-sm btn-primary mr-2" id="prevPage">Previous</button>');
                }

                // Add page numbers
                for (var i = 1; i <= totalPages; i++) {
                    $('#pagination').append('<button class="btn btn-sm btn-outline-primary mr-2 pageBtn">' + i + '</button>');
                }

                // Add next button
                if (page < totalPages) {
                    $('#pagination').append('<button class="btn btn-sm btn-primary" id="nextPage">Next</button>');
                }
            },
            error: function(xhr, status, error) {
                console.error('Error counting products:', error);
            }
        });
    }

    // Initial page and size
    var currentPage = 1;
    var pageSize = 10;

    // Populate table initially
    populateTable(currentPage, pageSize);

    // Pagination button click event
    $(document).on('click', '.pageBtn', function() {
        currentPage = parseInt($(this).text()); // Get the page number from the clicked button
        populateTable(currentPage, pageSize);
    });

    // Previous page button click event
    $(document).on('click', '#prevPage', function() {
        if (currentPage > 1) {
            currentPage--;
            populateTable(currentPage, pageSize);
        }
    });

    // Next page button click event
    $(document).on('click', '#nextPage', function() {
        currentPage++;
        populateTable(currentPage, pageSize);
    });
});
