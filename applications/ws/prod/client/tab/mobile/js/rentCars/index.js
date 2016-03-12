 $(document).ready(function() {

     $('#search_car').click(function() {

	//validating the pick up city and the visiting city
	var validation=true;
        if ($("#pick_up_city").val() == "" || $("#visiting_city").val() == "") {
             if ($("#pick_up_city").val() == "") {
                 $("#pick_up_city").toggleClass("red-placeholder");
		 validation=false;
             }
             if ($("#visiting_city").val() == "") {
                 $("#visiting_city").toggleClass("red-placeholder");
                 validation=false;
             }
         }else{
		localStorage.setItem("pick_up_city", $('#pick_up_city').val());
		localStorage.setItem("visiting_city", $('#visiting_city').val());
	}


         if ($("#from").val() == "" || $("#to").val() == "") {
             if ($("#from").val() == "") {
                 $("#from").toggleClass("red-placeholder");
		 validation=false;
             } 
             if ($("#to").val() == "") {
                 $("#to").toggleClass("red-placeholder");
                 validation=false;
             } 
         }
	
	if(!validation){
		return;
	}

         var from = new Date($("#from").val()).getTime();
         var to = new Date($("#to").val()).getTime();
	console.log(to + " ---- " + from + " --- " + new Date().getTime());
         if (to < from) {
             alert("Please Select Proper Dates");
         } else {
             localStorage.setItem("to_date", $('#to').val());
             localStorage.setItem("from_date", $('#from').val());
             window.location.href = "cars.html";
         }
     });

     var width = $(window).width();
     if (width >= 850) {
         $('body').addClass('websitePadding');
         $('.websiteView').show();
         $('.websiteView').removeClass('hide');
     } else {
         $('body').addClass('mobilePadding');
         $('.mobileView').show();
         $('.mobileView').removeClass('hide');
     }

     $('.alert').hide();

   

    $("#pick_up_city").keyup(function() {

         if (!this.value) {
             $("#pickuplabel").html("");
         }

     });
     $('#pick_up_city').on('input', function() {
         $("#pickuplabel").html("Pickup City");
     });

     $("#visiting_city").keyup(function() {

         if (!this.value) {
             $("#visitinglabel").html("");
         }

     });
     $('#visiting_city').on('input', function() {
         $("#visitinglabel").html("Visiting City");
     });



     $("#to").keyup(function() {
         if (!this.value) {
             $("#returnDate").html("");
         }

     });
     $("#from").keyup(function() {

         if (!this.value) {
             $("#journeyDate").html("");
         }

     });
     $('#from').click('input', function() {
         $("#journeyDate").html("Journey Date");
     });


 });

 $('.from_date').click('input', function() {
         $('input').blur();
     });
 $('.to_date').click('input', function() {
         $('input').blur();
	if ($("#from").val() == "") {
		$("#returnDate").html("");
                 $("#from").toggleClass("red-placeholder");
		 $(".ui-datepicker").hide();
             }else{
			$("#returnDate").html("Return Date");
		}
     });
 //for the calender to choose the dates of pick up and drop
 $(function() {
     $(".to_date").datepicker({
         defaultDate: "+1w",
         changeMonth: true,
         numberOfMonths: 1,
	minDate: 0
     });

 });

$(document).click(function(e){

    if( $(e.target).closest(".navbar-toggle").length > 0 ) {
        return false;
    }
	var div1 = document.getElementById("bs-example-navbar-collapse-1");
	var clas = div1.getAttribute("class");
	if(clas=="navbar-collapse collapse in")
	$(".navbar-toggle").click();
});


 $(function() {
     $(".from_date").datepicker({
         defaultDate: "+1w",
         changeMonth: true,
         numberOfMonths: 1,
	minDate: 0
     });

 });

$("#visiting_city").on('input', function () {
    var val = this.value;
    if($('#visiting option').filter(function(){
        return this.value === val;        
    }).length) {
        //send ajax request
         $('input').blur();
    }
});

$("#pick_up_city").on('input', function () {
    var val = this.value;
    if($('#pickup option').filter(function(){
        return this.value === val;        
    }).length) {
        //send ajax request
         $('input').blur();
    }
});
