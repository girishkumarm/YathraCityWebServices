var pree = [];
var v = 0;
var reqTypeSaved = 0;
var username_h, sessionId_h;
var endPoint;
$(document)
		.ready(
				function() {
					var _session = localStorage.getItem('_Session');
					if (_session)
						_session = JSON.parse(_session);
					if (_session) {
						username_h = _session.username;
						sessionId_h = _session.sessionId;
					}

					$('#day-id')
							.selectric()
							.on(
									'change',
									function() {
										endPoint = document.URL.substring(0,
												document.URL.lastIndexOf("/"))
												+ "/rest/info-graphics-config/get-info-graphics-config-by-day/"
												+ $(
														"select[name='select-day'] option:selected")
														.val();
										fetchInfoGraphicsConfigByDay(endPoint);
									});

					endPoint = document.URL.substring(0, document.URL
							.lastIndexOf("/"))
							+ "/rest/info-graphics-config/get-all-info-graphics-config";
					fetchInfoGraphicsConfig(endPoint);

					HTTPUtil
							.GET(
									document.URL.substring(0, document.URL
											.lastIndexOf("/"))
											+ "/rest/monitor/get-all-info-graphics-templates",
									username_h,
									sessionId_h,
									function(res, err) {
										if (res == null) {
											alert(prettifyJSON(err));
										} else {
											if (res.status == 400) {
												console.log(res);
												window.location
														.replace("../login.html");
											}
											selectData = "<table class=\"table-responsive\" ><tr><td colspan=\"2\"><label><font size=\"4\" color=\"black\">Add Info-Graphics list :</font></label</td></tr><tr>";
											if (res.length == 0)
												selectData += "<td><label><font size=\"4\" color=\"black\"> Info Graphics Templates not found! </label></td><td></td><td></td><td></td></tr>";
											for (var i = 0; i < res.length; i++) {
												var obj = res[i];
												selectData += "<td colspan=\"2\"><input id=\""
														+ obj.methodName
														+ "\"type=\"checkbox\"class=\"custom-blue_checkbox\" value="
														+ obj.methodName
														+ ">  "
														+ obj.methodDisplayName
														+ " </td></tr><tr>";
											}
											selectData += "</table>";
											console.log(selectData);
											$("#info-graphics-method-names")
													.html(selectData);

										}
									});
				});

$(document)
		.ready(
				function() {
					$("#add-edit-infographic")
							.click(
									function() {
										var checkedValues = $(
												"#info-graphics-method-names input:checkbox:checked")
												.map(function() {
													return this.value;
												}).get();

										endPoint = document.URL.substring(0,
												document.URL.lastIndexOf("/"))
												+ "/rest/info-graphics-config/save-info-graphics-config";

										if (validateFields($(
												"select[name='select-day'] option:selected")
												.val())
												|| checkedValues == null
												|| checkedValues.length == 0) {
											alert('Please enter all the mandatory fields for adding a info graphics config');
											return;
										}

										var info_graphics_input = {};
										info_graphics_input.day = $(
												"select[name='select-day'] option:selected")
												.val();
										info_graphics_input.active = true;
										info_graphics_input.created_at = new Date()
												.getTime();
										info_graphics_input.info_graphics_methods = checkedValues;

										addInfoGraphicsConfig(endPoint,
												info_graphics_input);
									})
				});

function validateFields(val) {
	if (val == null || val == "")
		return true;
	return false
}

function fetchInfoGraphicsConfig(endPoint) {
	HTTPUtil
			.GET(
					endPoint,
					username_h,
					sessionId_h,
					function(res, err) {
						if (res == null) {
							alert(prettifyJSON(err));
						} else {
							if (res == null || res.length == 0) {
								var body = "";
								body = body
										+ "<tr><td colspan=\"4\">No Entries found, Search Again later</td><tr>";
								$("#info-graphics > tbody").html(body);
							} else {
								if (res.status == 400) {
									console.log(res);
									window.location.replace("../login.html");
								}
								$("#info-graphics > tbody").html("");
								var body = "";

								for (var idx = 0; idx < res.length; idx++) {

									var day = res[idx].day;
									var created_at = res[idx].created_at;
									var info_graphics_methods = "";
									info_graphics_methods = "<select>";
									for (var i = 0; i < res[idx].info_graphics_methods.length; i++) {
										var obj = res[idx].info_graphics_methods[i];
										info_graphics_methods += "<option value="
												+ i + ">" + obj + "</option>";
									}
									info_graphics_methods += "</select>";

									body = body
											+ "<tr><td>"
											+ day
											+ "</td><td>"
											+ new Date(created_at)
											+ "</td><td>"
											+ info_graphics_methods
											+ "</td><td><button id=\""
											+ day
											+ "\" type=\"button\" onclick=deleteConfig('"
											+ day
											+ "') class=\"btn btn-primary active\">delete</button></td></tr>";
								}

								$("#info-graphics > tbody").html(body);
							}

						}
					});
}

function fetchInfoGraphicsConfigByDay(endPoint) {
	HTTPUtil
			.GET(
					endPoint,
					username_h,
					sessionId_h,
					function(res, err) {
						if (res == null) {
							$(
									"#info-graphics-method-names input:checkbox:checked")
									.map(
											function() {
												$("#" + this.value)
														.parent()
														.removeClass(
																"icheckbox_square-blue checked");
												$("#" + this.value)
														.parent()
														.addClass(
																"icheckbox_square-blue");
												$("#" + this.value).parent()
														.iCheck('uncheck');
												return this.value;
											}).get();
							return;
						} else {
							if (res == null || res.length == 0) {
								return;
							} else {
								if (res.status == 400) {
									console.log(res);
									window.location.replace("../login.html");
								}
								for ( var idx = 0; idx < res.info_graphics_methods.length; idx++) {
									$(
											"#info-graphics-method-names input:checkbox:checked")
											.map(
													function() {
														if (this.value != res.info_graphics_methods[idx]) {
															$("#" + this.value)
																	.parent()
																	.removeClass(
																			"icheckbox_square-blue checked");
															$("#" + this.value)
																	.parent()
																	.addClass(
																			"icheckbox_square-blue");
															$("#" + this.value)
																	.parent()
																	.iCheck(
																			'uncheck');
														}
														return this.value;
													}).get();
								}
								for ( var idx = 0; idx < res.info_graphics_methods.length; idx++) {
									$(
											"#info-graphics-method-names input:checkbox:not(:checked)")
											.map(
													function() {
														if (this.value == res.info_graphics_methods[idx]) {
															$("#" + this.value)
																	.parent()
																	.removeClass(
																			"icheckbox_square-blue");
															$("#" + this.value)
																	.parent()
																	.addClass(
																			"icheckbox_square-blue checked");
															$("#" + this.value)
																	.parent()
																	.iCheck(
																			'check');
														}
														return this.value;
													}).get();
								}

							}

						}
					});
}

function addInfoGraphicsConfig(endPoint, job) {
	console.log(endPoint);
	HTTPUtil
			.POST(
					endPoint,
					username_h,
					sessionId_h,
					job,
					function(res, err) {
						if (res == null) {
							alert(prettifyJSON(err));
						} else {
							if (res == null || res.length == 0) {
								alert("Couldn't add the info graphics config, please check the input and try again!'");
							} else {
								if (res.status == 400) {
									console.log(res);
									window.location.replace("../login.html");
								}
								endPoint = document.URL.substring(0,
										document.URL.lastIndexOf("/"))
										+ "/rest/info-graphics-config/get-all-info-graphics-config";
								fetchInfoGraphicsConfig(endPoint);
							}
						}
					});
}

function deleteConfig(id) {
	var result = confirm("Confirm to delete");
	if (!result) {
		return;
	}
	endPoint = document.URL.substring(0, document.URL.lastIndexOf("/"))
			+ "/rest/info-graphics-config/delete-info-graphics-by-day/" + id;
	HTTPUtil
			.GET(
					endPoint,
					username_h,
					sessionId_h,
					function(res, err) {
						if (res == null) {
							alert(prettifyJSON(err));
						} else {
							if (res == null || res.length == 0) {
								var body = "";
								body = body
										+ "<tr><td colspan=\"6\">No Entries found, Search Again later</td><tr>";
								alert("Couldn't delete configuration'");
							} else {
								if (res.status == 400) {
									console.log(res);
									window.location.replace("../login.html");
								}
								endPoint = document.URL.substring(0,
										document.URL.lastIndexOf("/"))
										+ "/rest/info-graphics-config/get-all-info-graphics-config";
								fetchInfoGraphicsConfig(endPoint);
							}
						}
					});
}
