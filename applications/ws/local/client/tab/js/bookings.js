var getBookings = function() {

	HTTPUtil
			.GET(
					TAB.ENDPOINT + "/canceling/fetch-all-bookings",
					function(res, err) {
						if (res === null) {
							console
									.log("Couldn't able to fetch the booking details from server")
						} else {

							console.log(res);
							var mytbody = document.getElementById("mytbody");

							for (var idx = 0; idx < res.bookingList.length; idx++) {

								if (res.bookingList[idx].bookingId == "WAITING") {
									var button1 = document
											.createElement('button');
									var btext1 = document
											.createTextNode("Confirm");
									button1.appendChild(btext1);
									button1.setAttribute('class',
											'btn btn-sm btn-success');

									var button2 = document
											.createElement('button');
									var btext2 = document
											.createTextNode("Cancel");
									button2.appendChild(btext2);
									button2.setAttribute('class',
											'btn btn-sm btn-danger');

									var row = document.createElement('tr');

									var cell1 = document.createElement('td');
									var cell2 = document.createElement('td');
									var cell3 = document.createElement('td');
									var cell4 = document.createElement('td');
									var cell5 = document.createElement('td');
									var cell6 = document.createElement('td');
									var cell7 = document.createElement('td');
									var cell8 = document.createElement('td');
									cell8.setAttribute('id', 'confirm');
									cell8.setAttribute('fromDate',
											res.bookingList[idx].fromDate);
									cell8.setAttribute('carNumber',
											res.bookingList[idx].carNumber);
									cell8
											.setAttribute(
													'customerNumber',
													res.bookingList[idx].customerNumber);
									var cell9 = document.createElement('td');
									cell9.setAttribute('id', 'cancel');
									cell9.setAttribute('fromDate',
											res.bookingList[idx].fromDate);
									cell9.setAttribute('carNumber',
											res.bookingList[idx].carNumber);
									cell9
											.setAttribute(
													'customerNumber',
													res.bookingList[idx].customerNumber);

									var data1 = res.bookingList[idx].carNumber;
									var data2 = res.bookingList[idx].driverName;
									var data3 = res.bookingList[idx].drivePhoneNumber;
									var data4 = res.bookingList[idx].customerName;
									var data5 = res.bookingList[idx].customerNumber;
									var data6 = res.bookingList[idx].pickUpCity;
									var data7 = res.bookingList[idx].travelCity;

									var text1 = document.createTextNode(data1);
									var text2 = document.createTextNode(data2);
									var text3 = document.createTextNode(data3);
									var text4 = document.createTextNode(data4);
									var text5 = document.createTextNode(data5);
									var text6 = document.createTextNode(data6);
									var text7 = document.createTextNode(data7);

									cell1.appendChild(text1);
									cell2.appendChild(text2);
									cell3.appendChild(text3);
									cell4.appendChild(text4);
									cell5.appendChild(text5);
									cell6.appendChild(text6);
									cell7.appendChild(text7);
									cell8.appendChild(button1);
									cell9.appendChild(button2);

									row.appendChild(cell1);
									row.appendChild(cell2);
									row.appendChild(cell3);
									row.appendChild(cell4);
									row.appendChild(cell5);
									row.appendChild(cell6);
									row.appendChild(cell7);
									row.appendChild(cell8);
									row.appendChild(cell9);

									mytbody.appendChild(row);
								}
							}

						}

					});

}
