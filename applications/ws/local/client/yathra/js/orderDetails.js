$(document).ready(function() {

console.log(localStorage.getItem("car_name"));
    $('.carimage').attr("src",localStorage.getItem("car_image"));
    $('.carname').html(localStorage.getItem("car_name"));


});