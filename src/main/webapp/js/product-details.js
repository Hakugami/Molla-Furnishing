$(document).ready(function () {
    // function updateMainImage(selectedIndex) {
    //     const image = product.images[selectedIndex];
    //     // $('#pd-o-initiate .slick-slide').remove();
    //     const mainImage = $('<div class="main-image pd-o-img-wrap" data-src="' + image + '"><img class="sec-image u-img-fluid" src="' + image + '" data-zoom-image="' + image + '" alt=""></div>');
    //     mainImage.find('img').elevateZoom({
    //         zoomType: "lens",
    //         lensShape: "round",
    //         lensSize: 200
    //     });
    //     $('#pd-o-initiate .slick-list').append(mainImage);
    // }
    //
    // updateMainImage(0);
    //
    // $('.thumb-image').click(function () {
    //     const selectedIndex = $(this).parent().index();
    //     updateMainImage(selectedIndex);
    // });
    //
    // const observer = new MutationObserver(() => {
    //     $('#pd-o-initiate .slick-track').css('width', '2225px');
    // });
    // observer.observe($('#pd-o-initiate').parent()[0], { childList: true });
    //
    // $('.pd-o-thumbnail').slick({
    //     slidesToShow: 4,
    //     slidesToScroll: 1,
    //     arrows: true,
    //     asNavFor: '#pd-o-initiate',
    //     dots: false,
    //     focusOnSelect: true,
    //     appendArrows: $('#pd-o-initiate'),
    //     responsive: [
    //         {
    //             breakpoint: 768,
    //             settings: {
    //                 slidesToShow: 3
    //             }
    //         },
    //     ],
    //     swipe: false,
    //     variableWidth: true
    // });

    // if (product.images) {
    //     product.images.forEach(function (imagePath) {
    //         const thumbnail = $('<div class="slick-slide"><img class="thumb-image u-img-fluid" src="' + imagePath + '" alt=""></div>');
    //         $('#pd-o-thumbnail').append(thumbnail);
    //     });
    // }


    let params = new URLSearchParams(window.location.search);
    let name = params.get('name'); // replace 'name' with your actual parameter name

    $.ajax({
        url: 'RetrieveProducts/',
        type: 'GET',
        dataType: 'json',
        data: { name: name },
        success: function (data) {
            let product = data[0];
            console.log(product);
            // Iterate over product images
            for (let i = 0; i < Math.min(product.images.length, 5); i++) {
                // Update main image
                $(`#img-${i + 1}-wrap`).attr("data-src", product.images[i]);
                $(`#img-${i + 1}`).attr("src", product.images[i]).attr("data-zoom-image", product.images[i]);

                // Update thumbnail image
                $(`#thumb-${i + 1}`).attr("src", product.images[i]);
            }

            // Update category anchor tag
            $('#productCategory').attr('href', 'product?category=' + product.categoryName).text(product.categoryName);

            // Update subcategory anchor tag
            $('#productSubcategory').attr('href', 'product?subcategory=' + product.subCategoryName).text(product.subCategoryName);

            // Update product name anchor tag
            $('#productName').text(product.name);
            $('.pd-detail__name').text(product.name);
            $('.pd-detail__price').text('$' + product.price);
            $('.product-m__category a').text(product.categoryName);
            $('.pd-tab__desc p').text(product.description);
            $('.pd-detail__preview-desc').text(product.description);
            $('.pd-detail__stock').text(product.quantity + ' in stock');
            $('.pd-detail__discount').text('(69% OFF)');
            $('.pd-detail__del').text('$' + (product.price * 2));
            $('.pd-detail__rating').html(generateRatingStars(product.rating));
            $('.pd-detail__review').text('(' + product.ratings.length + ')');
            $('#product-material').text(product.productDetails.material);
            $('#product-color').text(product.productDetails.color);
            $('#product-dimensions').text(product.productDetails.dimensions);
            $('#product-weight').text(product.productDetails.weight);
        },
        error: function () {
            console.log('Error retrieving product.');
        }
    });


});

//NOFALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
$(document).on('click', '#addtoCart', function (event) {
    event.preventDefault();
    //let productIndex = $(this).closest('.col-lg-3').index();
    let product = sessionStorage.getItem('product');
    let productAlreadyInCart = sessionStorage.getItem('shoppingData').some(item => item.name === product.name);
    let productId = product.productId;
    if (product.quantity === 0) {
        alert("Product is out of stock");
        return;
    }
    if (!productAlreadyInCart) {
        shopping_products.push(product);
    }

    productCounts[product.name] = (productCounts[product.name] || 0) + 1;


    // Store updated product counts and shopping products in sessionStorage
    updateShoppingData({ products: shopping_products, productCounts: productCounts });

    // sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
    console.log('Shopping Products:', shopping_products);
    console.log('Product added to cart:', product);
    console.log('Product Counts:', productCounts);
    updateTotalSumProMax();
    $.ajax({
        url: 'cart',
        type: 'POST',
        data: {
            action: 'addProduct',
            productId: productId,
            quantity: 1
        },
        success: function (response) {
            if (response === 'true') {
                alert('Product quantity incremented successfully!');
            } else {
                alert('Failed to increment product quantity.');
            }
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
});





function generateRatingStars(rating) {
    let stars = '';
    for (let i = 0; i < 5; i++) {
        if (i < rating) {
            stars += '<i class="fas fa-star"></i>';
        } else {
            stars += '<i class="far fa-star"></i>';
        }
    }
    return stars;
}