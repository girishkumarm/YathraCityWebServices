$(document).ready(
		function() {
		
					$("#search_car").click(function() {
					
						var pickUpPoint = "";
						var dateOfTravelling = "";
						var number0fPeoples = "";
						pickUpPoint = document.getElementById("pick_up_point").value;
						dateOfTravelling = document.getElementById("from_date").value;
						number0fPeoples = document.getElementById("count_peoples").value;
							if (pickUpPoint.trim()) {
								//console.log(pickUpPoint);
								localStorage.setItem("pickUpPoint", pickUpPoint);
								if (dateOfTravelling.trim()) {
									//console.log(dateOfTravelling);
									localStorage.setItem("dateOfTravelling", dateOfTravelling);
									if (number0fPeoples.trim()) {
										//console.log(number0fPeoples);
										localStorage.setItem("number0fPeoples", number0fPeoples);
										window.location.href =  "page1.html" ; 
									}
									else{
										alert("Please select Number of peoples");
									}
								}
								else{
									alert("Please select date of travelling");
								}
							}
							else{
								alert("Please select the pick up point");
							}
					});
		
			});