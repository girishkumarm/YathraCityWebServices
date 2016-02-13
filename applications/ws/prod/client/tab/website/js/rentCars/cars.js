 var width = $(window).width();
     if (width >= 850) {
         $('.website').removeClass('hide');
     } else {
         $('.mobile').removeClass('hide');
     }
 $(document).ready(function() {

      $('.glyphicon-arrow-left').click(function(){
        parent.history.back();
    });


     $('.hatchbackCar').click(function() {
	localStorage.setItem("car_type", "Hatchback");
	localStorage.setItem("car_image", $(this).attr("src"));
	localStorage.setItem("car_name", $(this).attr("alt"));
        window.location.href = "carDetails.html";
     });
     $('.suvCar').click(function() {
	localStorage.setItem("car_type", "Suv");
	localStorage.setItem("car_image", $(this).attr("src"));
	localStorage.setItem("car_name", $(this).attr("alt"));
        window.location.href = "carDetails.html";
     });
     $('.sedanCar').click(function() {
	localStorage.setItem("car_type", "Sedan");
	localStorage.setItem("car_image", $(this).attr("src"));
	localStorage.setItem("car_name", $(this).attr("alt"));
        window.location.href = "carDetails.html";
     });


     $('.hatchback_button').click(function() {
         $('.suv').addClass('hide');
	$('.sedan').addClass('hide');
	$('.hatchback').removeClass('hide');
	$('.hatchback_button').addClass('active');
	$('.suv_button').removeClass('active');
	$('.sedan_button').removeClass('active');
     });
     $('.suv_button').click(function() {
        $('.suv').removeClass('hide');
	$('.sedan').addClass('hide');
	$('.hatchback').addClass('hide');
	$('.hatchback_button').removeClass('active');
	$('.suv_button').addClass('active');
	$('.sedan_button').removeClass('active');
     });
     $('.sedan_button').click(function() {
         $('.suv').addClass('hide');
	$('.sedan').removeClass('hide');
	$('.hatchback').addClass('hide');
	$('.hatchback_button').removeClass('active');
	$('.suv_button').removeClass('active');
	$('.sedan_button').addClass('active');
     });

 });
