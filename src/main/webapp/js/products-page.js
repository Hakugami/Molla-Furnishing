$(document).ready(function () {
    let pageNumber = 1;
    let limit = 8;
    let minPrice = 0;
    let maxPrice = 1000;
    let category = '';
    let rating = 0;
    let date = new Date();


    let filter = {
        page: pageNumber,
        limit: limit,
        minPrice: minPrice,
        maxPrice: maxPrice,
        category: category,
        rating: rating,
        date: date
    };

    function updateCurrentPage(pageNo) {
        filter.page = pageNo;
        loadProducts();
    }

    function updateLimit(limit) {
        filter.limit = limit;
        loadProducts();
    }

    $('#price-filter').on('submit', function (event) {
        // Prevent the form from submitting normally
        event.preventDefault();

        // Get the values of the min and max price inputs
        let minPrice = $('#price-min').val();
        let maxPrice = $('#price-max').val();

        // Update the filter object with the new min and max prices
        filter.minPrice = minPrice;
        filter.maxPrice = maxPrice;

        // Refresh the products list
        loadProducts();
    });

    // Add an event listener to the select box
    $('#sort-strategy').on('change', function () {
        // Get the selected option
        const selectedOption = $(this).val();

        // Determine the sortBy and sortOrder based on the selected option
        switch (selectedOption) {
            case 'Sort By: Newest Items':
                filter.sortBy = 'dateAdded';
                filter.sortOrder = 'desc';
                break;
            case 'Sort By: Latest Items':
                filter.sortBy = 'dateAdded';
                filter.sortOrder = 'asc';
                break;
            case 'Sort By: Best Selling':
                filter.sortBy = 'sales';
                filter.sortOrder = 'desc';
                break;
            case 'Sort By: Best Rating':
                filter.sortBy = 'rating';
                filter.sortOrder = 'desc';
                break;
            case 'Sort By: Lowest Price':
                filter.sortBy = 'price';
                filter.sortOrder = 'asc';
                break;
            case 'Sort By: Highest Price':
                filter.sortBy = 'price';
                filter.sortOrder = 'desc';
                break;
        }

        // Reload the products with the new sorting order
        loadProducts();
    });


    // Attach click event handlers to the page links
    $('.shop-p__pagination a').on('click', function (event) {
        event.preventDefault();

        // Get the page number from the link text
        const pageNumber = $(this).text();

        // Update the current page
        updateCurrentPage(pageNumber);

        // Remove the 'is-active' class from all page links
        $('.shop-p__pagination li').removeClass('is-active');

        // Add the 'is-active' class to the clicked page link
        $(this).parent().addClass('is-active');
    });

    $('#products-amount').on('change', function () {
        // Get the selected number
        var number = $(this).val().split(': ')[1];

        // Update the number of products per page
        updateLimit(number);
    });


    function loadProducts() {
        $.ajax({
            url: 'RetrieveProducts', // URL of your servlet
            type: 'GET',
            dataType: 'json',
            data: filter,
            success: function (data) {
                // Clear the existing products
                $('.product-m').remove();
                const container = document.querySelector('.shop-p__collection .row');
                container.innerHTML = '';

                // Loop through each product in the data
                $.each(data, function (i, product) {
                    // Create the product element
                    var productElement = '<div class="col-lg-3 col-md-4 col-sm-6">' +
                        '<div class="product-m">' +
                        '<div class="product-m__thumb">' +
                        '<a class="aspect aspect--bg-grey aspect--square u-d-block" href="product-detail.html">' +
                        '<img class="aspect__img" src="' + product.image + '" alt=""></a>' +
                        '<div class="product-m__quick-look">' +
                        '<a class="fas fa-search" data-modal="modal" data-modal-id="#quick-look" data-tooltip="tooltip" data-placement="top" title="Quick Look"></a></div>' +
                        '<div class="product-m__add-cart">' +
                        '<a class="btn--e-brand" data-modal="modal" data-modal-id="#add-to-cart">Add to Cart</a></div>' +
                        '</div>' +
                        '<div class="product-m__content">' +
                        '<div class="product-m__category">' +
                        '<a href="shop-side-version-2.html">' + product.category + '</a></div>' +
                        '<div class="product-m__name">' +
                        '<a href="product-detail.html">' + product.name + '</a></div>' +
                        '<div class="product-m__rating gl-rating-style">' + product.rating +
                        '<span class="product-m__review">(23)</span></div>' +
                        '<div class="product-m__price">$' + product.price + '</div>' +
                        '<div class="product-m__hover">' +
                        '<div class="product-m__preview-description">' +
                        '<span>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</span></div>' +
                        '<div class="product-m__wishlist">' +
                        '<a class="far fa-heart" href="#" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist"></a></div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';

                    // Append the product element to the products container
                    $('.shop-p__collection .row').append(productElement);
                });


            },
            error: function () {
                console.log('Error retrieving products.');
            }
        });
    }

    // Call the function to load the products
    loadProducts();
});