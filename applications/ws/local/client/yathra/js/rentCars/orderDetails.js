$(document).ready(function() {
var width = $(window).width();
     if (width >= 850) {
         $('.website').removeClass('hide');
     } else {
         $('.mobile').removeClass('hide');
     }

console.log(localStorage.getItem("car_name"));
    var carImage=localStorage.getItem("car_image");
    carImage=carImage.replace("mobile", "website");
    $('.carimage').attr("src",carImage);
    $('.carname').html(localStorage.getItem("car_name"));
 
$('.glyphicon-arrow-left').click(function(){
        parent.history.back();

    });

});
