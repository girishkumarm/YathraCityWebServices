$(document).ready(function() {

console.log(localStorage.getItem("car_image"));
    $('.carimage').attr("src",localStorage.getItem("car_image"));


});
