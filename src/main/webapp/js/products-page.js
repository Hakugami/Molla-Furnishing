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

    function updateLimit(newLimit) {
        filter.limit = newLimit;
        loadProducts();
    }

    $('#price-filter').on('submit', function (event) {
        event.preventDefault();

        let minPrice = $('#price-min').val();
        let maxPrice = $('#price-max').val();

        filter.minPrice = minPrice;
        filter.maxPrice = maxPrice;

        loadProducts();
    });

    $('#sort-strategy').on('change', function () {
        const selectedOption = $(this).val();

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

        loadProducts();
    });




    $('#show-more-btn').on('click', function (event) {
        event.preventDefault();

        // Increment the page number for "Show More"
        filter.page++;
        loadProducts(true);
    });

    function loadProducts(showMore=false) {
        $.ajax({
            url: 'RetrieveProducts',
            type: 'GET',
            dataType: 'json',
            data: filter,
            success: function (data) {
                if (showMore) {
                    $.each(data, function (i, product) {
                        displayProduct(i, product);
                    });
                }
                else {
                    const container = document.querySelector('.shop-p__collection .row');
                    container.innerHTML = '';
                    $.each(data, function (i, product) {
                        displayProduct(i, product);

                    });
                }
            },
            error: function () {
                console.log('Error retrieving products.');
            }
        });
    }

    function displayProduct(i, product) {
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
        $('.shop-p__collection .row').append(productElement);
    }

    loadProducts();
});
