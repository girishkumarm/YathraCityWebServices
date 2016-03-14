$(document).ready(function() {

	$('#agency').html(localStorage.getItem("agency"));
	$('#fuel').html(localStorage.getItem("fuel"));
	$('#minimum_distance').html(localStorage.getItem("minimum_distance"));
	$('#minimum_fare').html(localStorage.getItem("minimum_fare"));
	$('#driver_bata').html(localStorage.getItem("driver_bata"));
	$('#pick_up_city').html(localStorage.getItem("pick_up_city"));
	$('#visiting_city').html(localStorage.getItem("visiting_city"));


	var width = $(window).width();
	     if (width >= 850) {
		 $('.website').removeClass('hide');
	     } else {
		 $('.mobile').removeClass('hide');
	     }

	     if (width >= 850) {
		 document.location.replace("http://tabcars.in/website/rentCars/orderDetails.html");
		 $('.websiteView').show();
	     } else {
		 $('.websiteView').hide();
	     }
	$(document).ready(function() {
		$('.bookCarButton').click(function() {
			 getCarAvailability();
			window.location.href = "confirmBooking.html";
		});
	 });

	    var carImage=localStorage.getItem("car_image");
	    carImage=carImage.replace("mobile", "website");
	    $('.carimage').attr("src",carImage);
	    $('.carname').html(localStorage.getItem("car_name"));
	 
	$('.glyphicon-arrow-left').click(function(){
		parent.history.back();

	    });

});


var getCarAvailability = function() {

    var inputObject = {};
    inputObject.registeredAt = localStorage.getItem("pick_up_city");
    inputObject.carName=localStorage.getItem("car_name");
    inputObject.fromDate = localStorage.getItem("from_date");
    inputObject.toDate = localStorage.getItem("to_date");
    inputObject.carType=localStorage.getItem("car_type");
    inputObject.carNumber=localStorage.getItem("car_number");

    HTTPUtil.POST(TAB.ENDPOINT+"/cars/get-car-details", inputObject, function(res, err) {
console.log(inputObject);
        if (res === null) {
            console.log("Couldn't able to fetch the answer for the given question");
		window.location.href = "carDetails.html";
        } else {
		window.location.href = "confirmBooking.html";
        }
    });

}