var HTTPUtil = {
	GET : function(endpoint, username, sessionId, callback) {
		$.ajax({
			type : "GET",
			headers : {
				'username' : username,
				'sessionId' : sessionId
			},
			url : endpoint,
			dataType : "json",
			async : false
		}).done(function(msg) {
			callback(msg, undefined);
		}).error(function(msg) {
			callback(msg, undefined);
		});
	},
	POST : function(endpoint, username, sessionId, dataBody, callback) {
		$.ajax({
			type : "POST",
			headers : {
				'username' : username,
				'sessionId' : sessionId
			},
			url : endpoint,
			dataType : "json",
			data : JSON.stringify(dataBody),
			contentType : "application/json; charset=utf-8"
		}).done(function(msg) {
			callback(msg, undefined);
		}).error(function(msg) {
			callback(msg, undefined);
		});

	},
	POSTFORM : function(endpoint, dataBody, callback) {
		$.ajax({
			type : "POST",
			url : endpoint,
			dataType : "json",
			data : {
				"data" : JSON.stringify(dataBody)
			},
			contentType : "application/json; charset=utf-8"
		}).success(function(msg) {
			callback(msg, undefined);
		}).error(function(msg) {
			callback(null, msg);
		});
	}
};
