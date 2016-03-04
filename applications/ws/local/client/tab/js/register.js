$(document).ready(function() {

    localStorage.setItem("register_car_type", "Select Car Type")
    localStorage.setItem("register_car_name", "Select Car Name")
    $(".registerCar").click(function() {

        if ($("#agencyName").val() == undefined || $("#agencyName").val() == "" ||
            $("#agencyPhoneNumber").val() == undefined || $("#agencyPhoneNumber").val() == "" ||
            $("#carOwnerName").val() == undefined || $("#carOwnerName").val() == "" ||
            $("#driverName").val() == undefined || $("#driverName").val() == "" ||
            $("#driverPhoneNumber").val() == undefined || $("#driverPhoneNumber").val() == "" ||
            $("#driverLicenseNumber").val() == undefined || $("#agencyName").val() == "" ||
            $("#minDistance").val() == undefined || $("#minDistance").val() == "" ||
            $("#price").val() == undefined || $("#price").val() == "" ||
            $("#carLocation").val() == undefined || $("#carLocation").val() == "" ||
            $("#carCapacity").val() == undefined || $("#carCapacity").val() == "" ||
            $("#address").val() == undefined || $("#address").val() == "" ||
            $("#carNumber").val() == undefined || $("#carNumber").val() == "" ||
            $("#carModel").val() == undefined || $("#carModel").val() == "" ||
	    $("#ac").val() == undefined || $("#ac").val() == "") {

            $(".mandatoryText").removeClass("hide");
            return;
        }
        registerCar();

    });


});

var registerCar = function(name, email, phone) {

    var registerCarInput = {};
    registerCarInput.carAgency = $("#agencyName").val();
    registerCarInput.carNumber = $("#carNumber").val();
    registerCarInput.minimunDistancePerDay = $("#minDistance").val();
    registerCarInput.pricePerKilometer = $("#price").val();
    registerCarInput.carRegisteredAt = $("#carLocation").val();
    registerCarInput.carCapacity = $("#carCapacity").val();
    registerCarInput.carModel = $("#carModel").val();
    registerCarInput.carType = localStorage.getItem("register_car_type");
    registerCarInput.carName = localStorage.getItem("register_car_name");
    registerCarInput.ac = $("#ac").val();

    var registerDriverInput = {};
    registerDriverInput.driverName = $("#driverName").val();
    registerDriverInput.carName = localStorage.getItem("register_car_name");
    registerDriverInput.driverPhoneNumber = $("#driverPhoneNumber").val();
    registerDriverInput.driverLicence = $("#driverLicenseNumber").val();
    registerDriverInput.agencyName = $("#agencyName").val();
    registerDriverInput.agencyPhoneNumber = $("#agencyPhoneNumber").val();
    registerDriverInput.location = $("#carLocation").val();
    registerDriverInput.carType = localStorage.getItem("register_car_type");
    registerDriverInput.carNumber = $("#carNumber").val();


    console.log(registerCarInput);
console.log(registerDriverInput);

    HTTPUtil.POST(TAB.ENDPOINT + "/cars/register-car", registerCarInput, function(res, err) {

        if (res === null) {
            console.log("Couldn't able to fetch the answer for the given question")
        } else {
            console.log(res);
            HTTPUtil.POST(TAB.ENDPOINT + "/driver/register-driver", registerDriverInput, function(res, err) {

                if (res === null) {
                    console.log("Couldn't able to fetch the answer for the given question")
                } else {
                    console.log(res);
                    window.location.href = "RegisterCar.html";
                }
            });
        }
    });


}


function changeFunc() {
    var selectBox = document.getElementById("carType");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    if (selectedValue == "Hatchback") {
        $("." + selectedValue).removeClass("hide");
        $(".Sedan").addClass("hide");
        $(".Suv").addClass("hide");
    } else if (selectedValue == "Sedan") {
        $("." + selectedValue).removeClass("hide");
        $(".Suv").addClass("hide");
        $(".HatchBack").addClass("hide");
    } else if (selectedValue == "Suv") {
        $("." + selectedValue).removeClass("hide");
        $(".HatchBack").addClass("hide");
        $(".Sedan").addClass("hide");
    }
    localStorage.setItem("register_car_type", selectedValue)
}

function changeFunc1() {
    var selectBox = document.getElementById("hatchback");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    localStorage.setItem("register_car_name", selectedValue);
}

function changeFunc2() {
    var selectBox = document.getElementById("sedan");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    localStorage.setItem("register_car_name", selectedValue);
}

function changeFunc3() {
    var selectBox = document.getElementById("suv");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    localStorage.setItem("register_car_name", selectedValue);
}