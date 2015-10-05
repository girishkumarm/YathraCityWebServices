$(document).ready(
		function() {
			var endPoint = document.URL.substring(0, document.URL
					.lastIndexOf("/"))
					+ "/rest";
			$("#auth-button").click(function() {
				login(endPoint)
			});

			$("input")
					.bind(
							"keydown",
							function(event) {
								var keycode = (event.keyCode ? event.keyCode
										: (event.which ? event.which
												: event.charCode));
								if (keycode == 13) {
									login(endPoint);
									return false;
								} else {
									return true;
								}
							});

		});

function login(endPoint) {
	var username = "";
	var password = "";
	username = document.getElementById("username").value;
	password = document.getElementById("password").value;
	var creds = {};
	creds.userName = username;
	creds.password = password;
	if (!username.trim() || !password.trim()) {
		$("#error_text").html("provide both username and password");
	} else {
		HTTPUtil.POST(endPoint + "/monitor/authenticate", null, null, creds,
				function(res, err) {
					if (res != null) {
						console.log(res);
						if (res.status == 200) {
							console.log(res);
							var _session = {
								username : username,
								sessionId : res.responseText
							};
							_session = JSON.stringify(_session);
							localStorage.setItem('_Session', _session);
							console.log(_session);
							window.location.replace("../index.html");
						} else {
							window.location.replace("../login.html");
						}
					} else {
						$("#error_text").html("Invalid credentials");
					}
				});
	}
}
