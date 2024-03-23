$(document).ready(function () {
    let pageNumber = 1;
    let limit = 8;
    let minPrice = 0;
    let maxPrice = 1000;
    let category = '';
    let rating = 0;
    let date = '';
    let products = [];
    let shopping_products = [];
    let productCounts = JSON.parse(sessionStorage.getItem('productCounts')) || {};

    let filter = {
        page: pageNumber,
        limit: limit,
        minPrice: minPrice,
        maxPrice: maxPrice,
        category: category,
        rating: rating,
        date: date
    };

    let params = new URLSearchParams(window.location.search);
    let category1 = params.get('category');

    if(category1 != null){
        filter.category = category1;
    }

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
        filter.page = 1;

        let minPrice = $('#price-min').val();
        let maxPrice = $('#price-max').val();

        filter.minPrice = minPrice;
        filter.maxPrice = maxPrice;

        loadProducts();
    });

    $('#sort-strategy').on('change', function () {
        const selectedOption = $(this).val();
        filter.page = 1;

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

    $('#products-amount').on('change', function () {
        const newLimit = $(this).val();
        const parsedLimit = parseInt(newLimit.split(' ')[1]);
        updateLimit(parsedLimit);
    });

    $('#show-more-btn').on('click', function (event) {
        event.preventDefault();

        filter.page++;
        loadProducts(true);
    });

    function loadProducts(showMore = false) {
        $.ajax({
            url: 'RetrieveProducts',
            type: 'GET',
            dataType: 'json',
            data: filter,
            success: function (data) {
                if (showMore) {
                    $.each(data, function (i, product) {
                        products.push(product);
                        displayProduct(i, product);
                    });
                } else {
                    products = data;
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

    $('.main-form').on('submit', function (event) {
        event.preventDefault();
        filter.name = $('#main-search').val();
        loadProducts();
    });

    function displayProduct(i, product) {
        const productElement = '<div class="col-lg-3 col-md-4 col-sm-6">' +
            '<div class="product-m">' +
            '<div class="product-m__thumb">' +
            '<a class="product-link aspect aspect--bg-grey aspect--square u-d-block" href="ProductPage/' + product.name + '">' +
            '<img class="aspect__img" src="' + product.images[0] + '" alt=""></a>' +
            '<div class="product-m__quick-look">' +
            '<a class="fas fa-search" data-modal="modal" data-modal-id="#quick-look" data-tooltip="tooltip" data-placement="top" title="Quick Look"></a></div>' +
            '<div class="product-m__add-cart">' +
            '<a class="btn--e-brand add-to-cart-btn" data-modal="modal" data-modal-id="#add-to-cart">Add to Cart</a></div>' +
            '</div>' +
            '<div class="product-m__content">' +
            '<div class="product-m__category">' +
            '<a href="product?category=' + product.categoryName +'">' + product.categoryName + '</a></div>' +
            '<div class="product-m__name">' +
            '<a href="ProductPage/' + product.name + '">' + product.name + '</a></div>' +
            '<div class="product-m__rating gl-rating-style">' + product.rating +
            '<span class="product-m__review">(23)</span></div>' +
            '<div class="product-m__price">$' + product.price + '</div>' +
            '<div class="product-m__hover">' +
            '<div class="product-m__preview-description">' +
            '<span>' + product.description + '</span></div>' +
            '<div class="product-m__wishlist">' +
            '<a class="far fa-heart" href="#" data-tooltip="tooltip" data-placement="top" title="Add to Wishlist"></a></div>' +
            '</div>' +
            '</div>' +
            '</div>';
        $('.shop-p__collection .row').append(productElement);

    }

    $(document).on('click', '.add-to-cart-btn', function (event) {
        event.preventDefault();
        let productIndex = $(this).closest('.col-lg-3').index();
        let product = products[productIndex];
    
        let productAlreadyInCart = shopping_products.some(item => item.name === product.name);

        let productId = product.productId;
    
        if (!productAlreadyInCart) {
            shopping_products.push(product);
        }
    
        productCounts[product.name] = (productCounts[product.name] || 0) + 1;
    
        // Store updated product counts and shopping products in sessionStorage
        let shoppingData = {
            products: shopping_products,
            productCounts: productCounts
        };
    
        sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
        console.log('Shopping Products:', shopping_products);
        console.log('Product added to cart:', product);
        console.log('Product Counts:', productCounts);
        $.ajax({
            url: 'cart',
            type: 'POST',
            data: {
                action: 'addProduct',
                productId: productId,
                quantity: 1
            },
            success: function(response) {
                if (response === 'true') {
                    alert('Product quantity incremented successfully!');
                } else {
                    alert('Failed to increment product quantity.');
                }
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    });
    
    

    $(document).on('click', '.product-m__thumb .product-link', function (event) {
        event.preventDefault();
        let productIndex = $(this).closest('.col-lg-3').index();
        let product = products[productIndex];
        sessionStorage.setItem('product', JSON.stringify(product));
        window.location.href = 'ProductPage?name=' + product.name;
    });

    loadProducts();
});
