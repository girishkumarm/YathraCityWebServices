$(document).ready(function() {
    $('.carimage').attr("src",localStorage.getItem("car_image"));
    $('.carname').html(localStorage.getItem("car_name"));
});
