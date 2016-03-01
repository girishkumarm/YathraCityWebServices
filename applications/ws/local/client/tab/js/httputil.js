var name = contactName;
var contactId = contactEmailId;
var imageUrl = "images/placeholder.png";
var urgentTime = "0M";
var normalTime = "0M";
var contactReplyTime = contactReplyTime = React
		.createClass({
			render : function() {
				return React
						.createElement(
								'div',
								{
									className : ''
								},
								getBackgroundOverlay(),
								React
										.createElement(
												'div',
												{
													className : 'content-wrapper'
												},
												React
														.createElement(
																'div',
																{
																	className : 'bb-card'
																},
																React
																		.createElement(
																				'div',
																				{
																					className : 'bb-card__nav-holder'
																				},
																				React
																						.createElement(
																								'button',
																								{
																									className : 'bb-card__nav-holder__arrow da_nav_icons_arrow flaticon-arrow487'
																								},
																								null),
																				React
																						.createElement(
																								'button',
																								{
																									className : 'bb-card__nav-holder__close da_nav_icons_cross flaticon-cancel30'
																								},
																								null),
																				React
																						.createElement(
																								'div',
																								{
																									className : 'bb-card__nav-holder-info-user'
																								},
																								React
																										.createElement(
																												'div',
																												{
																													className : 'bb-card__nav-holder__info-contact'
																												},
																												React
																														.createElement(
																																'span',
																																{
																																	className : 'bb-card__nav-holder__info-contact__profile-img-holder'
																																},
																																React
																																		.createElement(
																																				'img',
																																				{
																																					className : 'bb-card__nav-holder__info-contact__profile-img',
																																					src : 'images/placeholder.png'
																																				},
																																				null)),
																												React
																														.createElement(
																																'span',
																																{
																																	className : 'bb-card__nav-holder__info-contact__info'
																																},
																																React
																																		.createElement(
																																				'span',
																																				{
																																					className : 'bb-card__nav-holder__info-contact__title'
																																				},
																																				this.props.data.name),
																																React
																																		.createElement(
																																				'span',
																																				{
																																					className : 'bb-card__nav-holder__info-contact__sub-title'
																																				},
																																				this.props.data.contactId))))),
																React
																		.createElement(
																				'div',
																				{
																					className : 'bb-response-time-data'
																				},
																				React
																						.createElement(
																								'div',
																								{
																									className : 'bb-response-time-data-holder'
																								},
																								React
																										.createElement(
																												'div',
																												{
																													className : 'bb-response-time-data-holder__data'
																												},
																												React
																														.createElement(
																																'span',
																																{
																																	className : 'bb-response-time-data-holder__data__secondary-text'
																																},
																																"It takes "
																																		+ this.props.data.name),
																												React
																														.createElement(
																																'span',
																																{
																																	className : 'bb-response-time-data-holder__data__primary-text'
																																},
																																this.props.data.urgentTime),
																												React
																														.createElement(
																																'span',
																																{
																																	className : 'bb-response-time-data-holder__data__secondary-text'
																																},
																																"To respond to your Urgent Emails")),
																								React
																										.createElement(
																												'div',
																												{
																													className : 'bb-response-time-data-holder__data'
																												},
																												React
																														.createElement(
																																'span',
																																{
																																	className : 'bb-response-time-data-holder__data__secondary-text'
																																},
																																"It takes "
																																		+ this.props.data.name),
																												React
																														.createElement(
																																'span',
																																{
																																	className : 'bb-response-time-data-holder__data__primary-text'
																																},
																																this.props.data.normalTime),
																												React
																														.createElement(
																																'span',
																																{
																																	className : 'bb-response-time-data-holder__data__secondary-text'
																																},
																																"To respond to your Normal Emails")))),
																getGotoInboxButton())))
			}
		});

function renderNormalEmailsTime(name, time) {
	if (time == "undefined" || time == 0) {
		return React.createElement('div', {
			className : 'bb-response-time-data-holder__data'
		}, React.createElement('span', {
			className : 'bb-response-time-data-holder__data__secondary-text'
		}, this.props.data.name), React.createElement('span', {
			className : 'bb-response-time-data-holder__data__primary-text'
		}), React.createElement('span', {
			className : 'bb-response-time-data-holder__data__secondary-text'
		}, "Has not responded to any of your Normal Emails"))
	} else {
		return React.createElement('div', {
			className : 'bb-response-time-data-holder__data'
		}, React.createElement('span', {
			className : 'bb-response-time-data-holder__data__secondary-text'
		}, "It takes " + this.props.data.name), React.createElement('span', {
			className : 'bb-response-time-data-holder__data__primary-text'
		}, this.props.data.normalTime), React.createElement('span', {
			className : 'bb-response-time-data-holder__data__secondary-text'
		}, "To respond to your Normal Emails"))
	}
}

var HANDLE_ANSWERS = {
	renderAnswerForTheQuestion : function(response) {
		var analysedData = JSON.parse(response.analysedData);
		if (analysedData != null && analysedData.count != undefined
				&& analysedData.count != 0
				&& analysedData.responseTime != undefined
				&& analysedData.responseTime != 0) {

			var normalEmails = {};
			var urjentEmails = {};
			if (analysedData.priorityWiseResponseTimeMap != undefined
					&& analysedData.priorityWiseResponseTimeMap != "") {

				if (analysedData.priorityWiseResponseTimeMap.NORMAL != undefined
						&& analysedData.priorityWiseResponseTimeMap.NORMAL != "") {
					normalEmails = analysedData.priorityWiseResponseTimeMap.NORMAL;
				}

				if (analysedData.priorityWiseResponseTimeMap.URJENT != undefined
						&& analysedData.priorityWiseResponseTimeMap.URJENT != "") {
					urjentEmails = analysedData.priorityWiseResponseTimeMap.URJENT;
				}

			}
			if (normalEmails != undefined && normalEmails != "") {
				normalTime = timeago(normalEmails.responseTime)
			}
			if (urjentEmails != undefined && urjentEmails != "") {
				if (urjentEmails.responseTime != undefined
						&& urjentEmails.responseTime != "")
					urgentTime = timeago(urjentEmails.responseTime)
			}
			getContactRespondTime();
		} else {
			var noResForContactsCount = React
					.createClass({
						render : function() {
							return React
									.createElement(
											'div',
											null,
											getBackgroundOverlay(''),
											React
													.createElement(
															'div',
															{
																className : 'content-wrapper'
															},
															getStaticAnswersBBCardWithArrow(this.props.data)))
						}
					});
			$('.bb-card__nav-holder,.bb-card__contact-list').addClass(
					'fade-out');
			setTimeout(
					function() {
						ReactDOM
								.render(
										React
												.createElement(
														noResForContactsCount,
														{
															data : "You have not sent any Emails to the Contact"
														}),
										document
												.getElementById('body_background_id'));
						$('.bb-card').removeClass('slide-down');
						$(
								'.bb-card__nav-holder,.bb-card__contact-list,.bb-interrupted-text-holder')
								.addClass('fade-out');
						setTimeout(
								function() {
									$(
											'.bb-card__nav-holder,.bb-card__contact-list,.bb-interrupted-text-holder')
											.removeClass('fade-out');
								}, 200);
					}, 200);
		}
	}
}

function getContactRespondTime() {
	var contData = {
		'name' : name,
		'contactId' : contactId,
		'imageUrl' : imageUrl,
		'urgentTime' : urgentTime,
		'normalTime' : normalTime
	};
	$('.bb-card__nav-holder,.bb-card__contact-list').addClass('fade-out');
	setTimeout(
			function() {
				ReactDOM.render(React.createElement(contactReplyTime, {
					data : contData
				}), document.getElementById('body_background_id'));
				$('.bb-card').removeClass('slide-down');
				$(
						'.bb-card__nav-holder,.bb-card__contact-list,.bb-interrupted-text-holder')
						.addClass('fade-out');
				setTimeout(
						function() {
							$(
									'.bb-card__nav-holder,.bb-card__contact-list,.bb-interrupted-text-holder')
									.removeClass('fade-out');
						}, 200);
			}, 200);

}

function timeago(mins) {
	var hours, days, months, years;
	if (mins >= 60) {
		hours = toHours(mins);
		console.log("convert to hours" + hours);
		if (hours > 0 && hours < 24) {
			if (hours == 1) {
				finalVal = "1 hour";
			} else {
				finalVal = "" + hours + " Hours";
			}
		}

		if (hours >= 24) {
			days = toDays(hours);
			console.log("convert to days" + days);
			if (days == 1) {
				finalVal = "1 day";
			} else {
				finalVal = "" + days + " Days";
			}
		}
		if (days >= 365) {
			years = toYears(days);
			if (years == 1) {
				finalVal = "1 year"
			} else {
				finalVal = "" + years + " Years";
			}
		}
	} else {
		if (mins == 1) {
			finalVal = "a minute";
		} else {
			finalVal = " " + mins + " Minutes";
		}
	}
	return finalVal;
}

function toHours(minutes) {
	return Math.floor(minutes * 0.01666666667);
}

function toDays(hours) {
	return Math.floor(hours / 24);
}

function toYears(days) {
	return Math.floor(days / 365);
}