$(function() {

    // $('.dropdown-item').on('click', function() {
    //     let selectedCategory = $(this).text();
    //     $('#categoryBtn').text(selectedCategory);
    // });

    let welcomeName = $('#welcome-name').text();

    function updateNavbarAlignment() {


        if ($(window).width() <= 991) {
            $('.navbar-brand').addClass('text-center');
            $('.offcanvas-title').text(welcomeName);
            $('#welcome-name').hide();
        } else {
            $('.navbar-brand').removeClass('text-center');
            $('#welcome-name').show();
        }
    }
    

    updateNavbarAlignment();

    $(window).resize(function(){
        updateNavbarAlignment();
    });

    /* jquery로 수정필요*/
    // const itemImageInput = document.getElementById('item-image');
    // const itemImagePreview = document.querySelector('.add-item-image');

    // itemImageInput.addEventListener('change', function() {
    //     const selectedImage = itemImageInput.files[0];
    //     if (selectedImage) {
    //         itemImagePreview.src = URL.createObjectURL(selectedImage);
    //     }
    // });

    $('#item-image').on('change', function(){
        let selectedImage = $(this)[0].files[0];
        if (selectedImage) {
            $('.add-item-image').attr('src', URL.createObjectURL(selectedImage));
        }

    });

    /* item.html */
    /* item count */
    
    function change() {

        let stat = $('#count').val();
        let cnt = parseInt(stat,10);

        let stat2 = $('#itemPrice').text();
        let itemPrice = parseInt(stat2,10);
        console.log(itemPrice);

        let totalPrice = (cnt*itemPrice);
        console.log('totaPrice = '+ totalPrice);

        $('.totalPrice').val(totalPrice + '원');
        $('.totalPrice').text(totalPrice + '원');
        
    };


    $('#plusBtn').click(function(e) {
        e.preventDefault();
        let stat = $('#count').val();
        /*parsing 값, 진수(10) */
        let num = parseInt(stat, 10); 
        num++;
        if (10 < num) {
            alert('최대 개수는 10개 입니다.')
            num=10;
        }
        $('#count').val(num);
        change();
    });

    $('#minBtn').click(function(e){
        e.preventDefault();
        let stat = $('#count').val();
        let num = parseInt(stat,10);
        num--;
        if (num <= 0) {
            alert('최소 개수는 1개 입니다.')
            num=1;
        }
        $('#count').val(num);
        change();
    });

});

