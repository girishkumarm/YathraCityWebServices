// Morris.js Charts sample data for SB Admin template
var label1 = "Used";
var label2 = "Remaining";
var value1 = "";
var value2 = "";
function keepRunning() {
	hit();
	hit2()
	setInterval(function() {
		hit();
		hit2()
	}, 1000);
}
$(function(){
	keepRunning();
})
function hit() {
	var endPoint = document.URL.substring(0, document.URL.lastIndexOf("/"))
			+ "/rest";
	HTTPUtil
			.GET(
					endPoint + "/monitor/get-registered-modules",
					username_h,
					sessionId_h,
					function(res, err) {
						if (res === null) {
							// alert(prettifyJSON(err));
						} else {
							if (res.status == 400) {
								console.log(res);
								window.location.replace("../login.html");
							}
							if (res.length == 0) {
								alert("no modules");
							} else {
								for (var i = 0; i < 1; i++) {
									var cpuUserUsed = res[i].sysInfo.cpuInfo.userTimeUsedPercent;
									var cpuIdleUsed = res[i].sysInfo.cpuInfo.idleTimeUsedPercent;
									value1=Math.ceil(cpuUserUsed);
									value2=Math.ceil(cpuIdleUsed);
								}

							}
						}
					});
}

$(function() {

	// Donut Chart
	Morris.Donut({
		element : 'morris-donut-chart-2',
		data : [ {
			label : label2,
			value : value2
		}, {
			label : label1,
			value : value1
		}, /*
			 * { label : "Mail-Order Sales", value : 20 }
			 */],
		resize : true
	});

});

$(function() {

	// Donut Chart
	Morris.Donut({
		element : 'morris-donut-chart-3',
		data : [ {
			label : label2,
			value : value2
		}, {
			label : label1,
			value : value1
		}, /*
			 * { label : "Mail-Order Sales", value : 20 }
			 */],
		resize : true
	});

});
function hit2() {

	$("#morris-donut-chart-1").empty();
	// Donut Chart
	Morris.Donut({
		element : 'morris-donut-chart-1',
		data : [ {
			label : label2,
			value : value2
		}, {
			label : label1,
			value : value1
		}, /*
			 * { label : "Mail-Order Sales", value : 20 }
			 */],
		resize : true
	});

}