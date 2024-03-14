$(document).ready(function () {
    let product = sessionStorage.getItem('product');
    product = JSON.parse(product);

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

    $("#img-1-wrap").attr("data-src", product.images[0]);
    $("#img-1").attr("src", product.images[0]).attr("data-zoom-image", product.images[0]);
    $('#thumb-1').attr("src", product.images[0]);

    $("#img-2-wrap").attr("data-src", product.images[1]);
    $("#img-2").attr("src", product.images[1]).attr("data-zoom-image", product.images[1]);
    $('#thumb-2').attr("src", product.images[1]);

    $('.pd-detail__name').text(product.name);
    $('.pd-detail__price').text('$' + product.price);
    $('.product-m__category a').text(product.category);
    $('.pd-tab__desc p').text(product.description);
    $('.pd-detail__stock').text(product.quantity + ' in stock');
    $('.pd-detail__discount').text('(69% OFF)');
    $('.pd-detail__del').text('$' + (product.price * 2));
    $('.pd-detail__rating').html(generateRatingStars(product.rating));
    $('.pd-detail__review').text('(' + product.ratings.length + ')');
    $('#product-material').text(product.productDetails.material);
    $('#product-color').text(product.productDetails.color);
    $('#product-dimensions').text(product.productDetails.dimensions);
    $('#product-weight').text(product.productDetails.weight);
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