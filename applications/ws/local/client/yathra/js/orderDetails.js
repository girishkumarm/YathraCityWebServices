$(document).ready(function() {

console.log(localStorage.getItem("car_name"));
    var carImage=localStorage.getItem("car_image");
    carImage=carImage.replace("mobile", "website");
    $('.carimage').attr("src",carImage);
    $('.carname').html(localStorage.getItem("car_name"));


});
