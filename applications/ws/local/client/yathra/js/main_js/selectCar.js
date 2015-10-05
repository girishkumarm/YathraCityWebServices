$(document).ready(
		function() {
		
					getCars();
		
			});
			
			
function getCars() {
	var city= "";
	var capacity = "";
	
	city = localStorage.getItem("pickUpPoint");	
	capacity = localStorage.getItem("number0fPeoples");
						
	var endPoint="http://localhost:8080/ws_yathra_dev/rest/yathra/get-car-details/"+city+"/"+capacity;
		HTTPUtil.GET(endPoint,"a","b",function(res, err) {
			if (res === null) {
				alert(prettifyJSON(err));
			}
			else {
				console.log(endPoint);
				var length = res.car.length;
				var body = "";
				if(length>0){
					for (var idx = 0; idx < length; idx++) {
						var data1 = res.car[idx].carOwner;
						var data2 = res.car[idx].carName;
						var data3 = res.car[idx].pricePerKilometer;
						var data4 = res.car[idx].minimunDistancePerDay;
						var data5 = "8/10";
						body = body
								+ "<tr><td>"
								+ data1
								+ "</td><td>"
								+ data2
								+ "</td><td>"
								+ data3
								+ "</td><td>"
								+ data4
								+ "</td><td>"
								+ data5
								+ "</td><td></tr>";
					}
				}
				else{
					body=body + "<tr><td colspan=\"4\">No Cars Found....... Search Again</td><tr>";
				}
				$("#carsAvailable > tbody").html(body);
					if (res.status == "200"){
						console.log("success");
					}
			}
		});
}