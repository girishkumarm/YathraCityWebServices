 $(document).ready(function() {

     $('#search_car').click(function() {

	localStorage.setItem("pick_up_city", $('#pick_up_city').val());
	localStorage.setItem("visiting_city", $('#visiting_city').val());

	if ($("#from").val() == "" || $("#to").val() == "") {
		if ($("#from").val() == "") {
			$("#from").attr("placeholder", "Please Select Journey Date");
			$("#from").toggleClass("red-placeholder");
		}else{
			localStorage.setItem("from_date", $('#pick_up_city').val());
		}
		if ($("#to").val() == "") {
			$("#to").attr("placeholder", "Please Select Return Date");
			$("#to").toggleClass("red-placeholder");
			return;
		}else{
			localStorage.setItem("to_date", $('#pick_up_city').val());
		}
	}
	
	var from = new Date($("#from").val()).getTime();
	var to = new Date($("#to").val()).getTime();
	
	if(to<from || to<new Date().getTime() || from<new Date().getTime()){
		alert("Please Select Proper Dates");
	}else{
		localStorage.setItem("to_date", $('#to').val());
		localStorage.setItem("from_date", $('#from').val());
		window.location.href = "cars.html";
	}
     });

     var width = $(window).width();
     if (width >= 850) {
         $('.website').removeClass('hide');
     } else {
         $('.mobile').removeClass('hide');
     }

     $('.alert').hide();


 });


//for the calender to choose the dates of pick up and drop
 $(function() {
     $(".to_date").datepicker({
		
         defaultDate: "+1w",
         changeMonth: true,
         numberOfMonths: 1,
         onClose: function(selectedDate) {
             $("#to").datepicker("option", "minDate", selectedDate);
         }
     });

 });
$(function() {
     $(".from_date").datepicker({
         defaultDate: "+1w",
         changeMonth: true,
         numberOfMonths: 1,
         onClose: function(selectedDate) {
             $("#from").datepicker("option", "minDate", selectedDate);
         }
     });

 });