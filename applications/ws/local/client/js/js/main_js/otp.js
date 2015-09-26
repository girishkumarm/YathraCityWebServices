$(document).ready(
		function() {
		
				
					$("#choose_car").click(function() {
					
						var phoneNumber= "";
						var otp = "";
						
						phoneNumber = document.getElementById("phoneNumber").value;
						otp = document.getElementById("otp").value;
							if (phoneNumber.trim()) {
								//console.log(phoneNumber.length);
								if (phoneNumber.length==10) {
								//console.log(phoneNumber);
								localStorage.setItem("phoneNumber", phoneNumber);
								if (otp.trim()) {
									//console.log(otp);
									localStorage.setItem("otp", otp);	
									if(otp=="12345"){
										window.location.href =  "page2.html" ; 
									}
									else{
										alert("OTP does not match try again");
									}
								}
								else{
									alert("Please Enter OTP to continue");
								}
								}
								else{
									alert("Please Enter Valid Phone number");
								}
							}
							else{
								alert("Please enter phone number to get OTP");
							}
					});
		
			});