$(document).ready(function() {

var width = $(window).width();
      if (width >= 850) {
          $('.websiteView').show();
      } else {
	  document.location.replace("http://tabcars.in/mobile/rentCars/confirmBooking.html");
          $('.websiteView').hide();
      }
     $('.alert').hide();

 $('.glyphicon-arrow-left').click(function(){
        parent.history.back();

    });

$('#name').click(function(){
        $('.alert').attr('style','display: none');

    });

$('.bookCar').click(function(){
	if($('#name').val()=="" || $('#phone').val()=="" || $('#email').val()==""){

		if($('#name').val()==""){
			$('.name-alert').attr('style','display: block');
		}else{
			$('.name-alert').attr('style','display: none');
		}
		if($('#phone').val()==""){
			$('.num-alert').attr('style','display: block');
		}else{
			$('.name-alert').attr('style','display: none');
		}
		if($('#email').val()==""){
			$('.email-alert').attr('style','display: block');
		}else{
			$('.name-alert').attr('style','display: none');
		}

	}else{
		bookCar($('#name').val(),$('#email').val(),$('#phone').val());
	}
    });

});


var bookCar = function(name,email,phone) {

    var inputObject = {};
    inputObject.fromDate = localStorage.getItem("from_date");
    inputObject.toDate=localStorage.getItem("to_date");
    inputObject.carNumber = localStorage.getItem("car_number");;
    inputObject.travelCity = localStorage.getItem("visiting_city");
    inputObject.carAgency = localStorage.getItem("agency");
    inputObject.customerName=name;
    inputObject.customerNumber = phone;
    inputObject.pickUpCity = localStorage.getItem("pick_up_city");
    inputObject.carLocation=localStorage.getItem("registered_at");
    inputObject.carType = localStorage.getItem("car_type");
    inputObject.customerEmail = email;

    console.log(inputObject);

    HTTPUtil.POST(TAB.ENDPOINT+"/booking/booking-car", inputObject, function(res, err) {

        if (res === null) {
            console.log("Couldn't able to fetch the answer for the given question")
        } else {
            console.log(res);
		window.location.href = "confirmMessage.html";
        }
    });
    

}