$(document).ready(function() {

if(localStorage.getItem("user_name")!="" || localStorage.getItem("user_name")!= undefined )
{
	$("#name").val(localStorage.getItem("user_name"));
	$(".name").addClass("floating-label-form-group-with-value");
}
if(localStorage.getItem("user_email_id")!="" || localStorage.getItem("user_email_id")!= undefined )
{
	$("#email").val(localStorage.getItem("user_email_id"));
	$(".email").addClass("floating-label-form-group-with-value");
}
$(".phone").addClass(" floating-label-form-group-with-focus");
var width = $(window).width();
      if (width >= 850) {
          $('.websiteView').show();
      } else {
          $('.websiteView').hide();
      }
     $('.alert').hide();

 $('.glyphicon-arrow-left').click(function(){
        parent.history.back();

    });

$('.bookCar').click(function(){
	if($('#name').val()=="" && $('#phone').val()=="" && $('#email').val()==""){
		$('.alert').attr('style','display: block');

	}else{
		bookCar($('#name').val(),$('#email').val(),$('#phone').val());
	}
    });

});



var bookCar = function(name,email,phone) {

    $(".loader").removeClass("hide");
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
		$(".loader").addClass("hide");
		window.location.href = "confirmMessage.html";
        }
    });
    

}
