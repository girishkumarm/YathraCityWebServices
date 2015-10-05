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
									var endPoint="http://localhost:8080/ws_yathra_dev/rest/yathra/get-otp-match-response/"+phoneNumber+"/"+otp;
									HTTPUtil.GET(endPoint,"a","b",function(res, err) {
										if (res === null) {
											alert(prettifyJSON(err));
										}
										else {
										console.log(res);
											if (res.status == "200"){
												console.log("success");
												window.location.href =  "page2.html" ;
											}
											else{
												alert("OTP does not match try again");
											}
										}
									});
									
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
					
					$("#send_otp").click(function() {
					
						var phoneNumber= "";
						var otp = "";
						
						phoneNumber = document.getElementById("phoneNumber").value;
						otp = document.getElementById("otp").value;
							if (phoneNumber.trim()) {
									console.log(phoneNumber.length);
								if (phoneNumber.length==10) {
									//console.log(phoneNumber);
									localStorage.setItem("phoneNumber", phoneNumber);
									
									var endPoint="http://localhost:8080/ws_yathra_dev/rest/yathra/get-otp/"+phoneNumber;
									HTTPUtil.GET(endPoint,"a","b",function(res, err) {
										if (res === null) {
											alert(prettifyJSON(err));
										}
										else {
											if (res.status == "200"){
												console.log("success");
											}
										}
									});
									localStorage.setItem("otp", "12345");	
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