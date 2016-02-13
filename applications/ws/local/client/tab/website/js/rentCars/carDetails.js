var carDetails=[];

$(document).ready(function() {

    getCarDetails();

    var carImage = localStorage.getItem("car_image");

    if ($(window).width() >= 850) {
        carImage = carImage.replace("mobile", "webisite");
    } else {
        carImage = carImage.replace("website", "mobile");
    }
    $('.carimage').attr("src", carImage);
    $('.carname').html(localStorage.getItem("car_name"));

    $('.glyphicon-arrow-left').click(function() {
        parent.history.back();

    });
});


var getCarDetails = function() {

    var inputObject = {};
    inputObject.registeredAt = localStorage.getItem("pick_up_city");
    inputObject.carName=localStorage.getItem("car_name");
    //inputObject.carName = "Indica";
    inputObject.fromDate = localStorage.getItem("from_date");
    inputObject.toDate = localStorage.getItem("to_date");
    inputObject.carType=localStorage.getItem("car_type");
    //inputObject.carType = "Hatchback";

    HTTPUtil.POST(TAB.ENDPOINT+"/cars/get-car-details", inputObject, function(res, err) {
console.log(inputObject);
        if (res === null) {
            console.log("Couldn't able to fetch the answer for the given question")
        } else {
            carDetails = res.car;
            console.log(res);
	    if(carDetails.length>0){
		$('.carDetailsDiv').html("");
	    }else{
		var carImage=localStorage.getItem("car_image");
	        carImage=carImage.replace("mobile", "website");
	        $('.carimage').attr("src",carImage);
	    }
	 if(carDetails.length>0){
            for (var index = 0; index < carDetails.length; index++) {
                var carDetailsData = "";
                carDetailsData = "<div class=\"car-list-item row container\" id=" + index + "><hr/><div class=\"list-group col-sm-12 col-lg-12\"><div class=\"col-sm-6 col-lg-6\"><h5 class=\"text-success\">" + carDetails[index].carAgency + "</h5><div>" + carDetails[index].carCapacity + "+1 Seats</div><div>" + carDetails[index].carModel + "</div></div><div class=\"col-sm-6 col-lg-6\"><h5 class=\"text-info\"><i class=\"fa fa-inr\" style=\"font-size:15px\"></i>" + carDetails[index].pricePerKilometer + "/km</h5><div>" + carDetails[index].minimunDistancePerDay + "km/day</div></div>";

                $('.carDetailsDiv').prepend(carDetailsData);
            }}else{

			var noCarsData="<hr/><div class=\"car-list-item row\" id=\"Arun Travels\"><div class=\"list-group text-center \" ><div class=\"col-sm-12 col-lg-12\"><h5 class=\"text-success\">Sorry!!! No cars Available for this date...</h5></div><div class=\"col-sm-12 col-lg-12\"><h5 class=\"text-info\">Please try changing your dates or Car!!!</h5></div></div></div><hr/>";
			
			$('.carDetailsDiv').html(noCarsData);		

		}
        }


        $('.car-list-item').click(function() {
            console.log("clicked");
	    var car=carDetails[$(this).attr('id')];
	    console.log(car);
            localStorage.setItem("agency", car.carAgency);
	    localStorage.setItem("registered_at", car.carRegisteredAt);
	    localStorage.setItem("car_number", car.carNumber);
            localStorage.setItem("fuel", "Diesel");
            localStorage.setItem("minimum_distance", car.minimunDistancePerDay);
            localStorage.setItem("driver_bata", "500");
            localStorage.setItem("minimum_fare", car.minimunDistancePerDay * car.pricePerKilometer);
            window.location.href = "orderDetails.html";
        });

    });

}
