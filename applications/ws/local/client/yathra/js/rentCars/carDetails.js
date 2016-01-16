$(document).ready(function() {
   var width = $(window).width();
      if (width >= 850) {
          $('.websiteView').show();
      } else {
          $('.websiteView').hide();
      } 

   $('.car-list-item').click(function() {
      	window.location.href = "orderDetails.html";
   });

   var carImage=localStorage.getItem("car_image");
	
    if($(window).width()>=850){
	carImage=carImage.replace("mobile", "webisite");
    }else{
	carImage=carImage.replace("website", "mobile");
    }
    $('.carimage').attr("src",carImage);
    $('.carname').html(localStorage.getItem("car_name"));

 $('.glyphicon-arrow-left').click(function(){
        parent.history.back();

    });
});
