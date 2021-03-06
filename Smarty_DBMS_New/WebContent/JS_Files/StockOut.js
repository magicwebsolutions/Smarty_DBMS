var data = [];
var dataSet;
var StockIn_SelectedRowDataSet = [];
var today = new Date();
var Customerarray;
var WindowType = "";
var formattedDate;

function OpenModalPopup(popupName) {
	document.getElementById(popupName).style.display = 'block';
}
function CloseModalPopup(popupName) {
	document.getElementById(popupName).style.display = 'none';
}

/* For Esc key Modal Close */
document.addEventListener('keyup', function(e) {
	if (e.keyCode == 27) {
		CloseModalPopup('addCustomerTransaction');
		CloseModalPopup('editCustomerTransaction');
	}
});

function onload() {
	document.getElementById("updatestockoutbutton").className += " button_disable";
	document.getElementById("updatestockoutbutton").disabled = true;
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear();
	yyyy = parseInt(yyyy);
	today = yyyy + '-' + mm + '-' + dd;
	mm = ('0' + mm + '').slice(-2);
	dd = ('0' + dd + '').slice(-2);
	formattedDate = yyyy + '-' + mm + '-' + dd;
	document.getElementById("StockOut_date").value = formattedDate;
	document.getElementById("Add_Stockout_date").value = formattedDate;
	debugger;
}

function getCurrentDayStockDtls(param) {
	debugger;
	var datePassed;
	var ScreenType = 'STOCKOUT';
	if (param == 'today') {
		datePassed = today
		document.getElementById("StockOut_date").value = formattedDate;
	} else if (param == 'searchDate') {
		datePassed = document.getElementById("StockOut_date").value;
	}
	var URL = gloablContextURL + "/Stock?Event=STOCK_CURRENTDAY&datePassed="
			+ datePassed + "&screentype=" + ScreenType;
	if (window.XMLHttpRequest) {
		requestOBJ = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		requestOBJ = new ActivXCObject("Microsoft.XMLHTTP");
	}
	try {
		requestOBJ.onreadystatechange = CurrentDayStockResponse;
		requestOBJ.open("POST", URL, true);
		requestOBJ.setRequestHeader("Content-type", "text/xml");
		requestOBJ.send();
	} catch (e) {
		alert("Something went wrong");
	}
}

function CurrentDayStockResponse() {
	var itemarray;
	if (requestOBJ.readyState == 4) {
		if (requestOBJ.status == 200) {
			var responsetext = requestOBJ.responseText;
			responsetext = responsetext.replace("<xml>", "");
			if (responsetext.indexOf("~") > 0) {
				var finalResponse = responsetext.substring(0, responsetext
						.lastIndexOf('#'));
				itemarray = finalResponse.split("#");
				data.length = 0;
				debugger;
				for (var i = 0; i < itemarray.length; i++) {
					data.push({
						StockID : itemarray[i].split('~')[0],
						StockInDate : itemarray[i].split('~')[1],
						ItemName : itemarray[i].split('~')[2],
						StockQty : itemarray[i].split('~')[3],
						StockAmt : itemarray[i].split('~')[4],
						StockType : itemarray[i].split('~')[5],
						Description : itemarray[i].split('~')[6]
					}

					);
				}
				data;
				dataSet = data;

				document.getElementById("StockOutDetails").hidden = false;
				document.getElementById("NoStockMsg").hidden = true;

				var rows = "";
				$('#StockOutDetails').find('tbody').empty();
				$.each(dataSet, function() {
					rows += "<tr class='item'><td>" + this.StockID
							+ "</td><td>" + this.StockInDate + "</td><td>"
							+ this.ItemName + "</td><td>" + this.StockQty
							+ "</td><td>" + this.StockAmt + "</td><td>"
							+ this.StockType + "</td><td>" + this.Description
							+ "</td></tr>";
				});
				$(rows).appendTo("#StockOutDetails tbody");

				var table = document.getElementById('StockOutDetails');
				var cells = table.getElementsByTagName('td');
				for (var i = 0; i < cells.length; i++) {
					// Take each cell
					var cell = cells[i];
					// do something on onclick event for cell
					cell.onclick = function() {

						var element = document
								.getElementById("updatestockoutbutton");
						element.classList.remove("button_disable");
						document.getElementById("updatestockoutbutton").disabled = false;

						// Get the row id where the cell exists
						var rowId = this.parentNode.rowIndex;
						var rowsNotSelected = table.getElementsByTagName('tr');
						for (var row = 0; row < rowsNotSelected.length; row++) {
							rowsNotSelected[row].style.backgroundColor = "";
							rowsNotSelected[row].classList.remove('selected');
						}
						var rowSelected = table.getElementsByTagName('tr')[rowId];
						rowSelected.style.backgroundColor = "#d0e4f1";
						rowSelected.className += " selected";
						debugger;
						StockIn_SelectedRowDataSet = {
							SelectedRowID : rowId,
							StockID : rowSelected.cells[0].innerHTML,
							StockInDate : rowSelected.cells[1].innerHTML,
							ItemName : rowSelected.cells[2].innerHTML,
							StockQty : rowSelected.cells[3].innerHTML,
							StockAmt : rowSelected.cells[4].innerHTML,
							StockType : rowSelected.cells[5].innerHTML,
							Description : rowSelected.cells[6].innerHTML
						}
					}
				}
			} else {
				document.getElementById("updatestockoutbutton").className += " button_disable";
				document.getElementById("updatestockoutbutton").disabled = true;
				document.getElementById("StockOutDetails").hidden = true;
				document.getElementById("NoStockMsg").hidden = false;
			}
			getExistingItems('NEW');
		}

	}
}

/* For Fetching Items to map in dropdown */
function getExistingItems(type) {
	WindowType = type;
	var getItemsURL = gloablContextURL + "/Transaction?Event=GET_ITEMS";
	if (window.XMLHttpRequest) {
		requestOBJ = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		requestOBJ = new ActivXCObject("Microsoft.XMLHTTP");
	}
	try {
		requestOBJ.onreadystatechange = ExistingItemsResponse;
		requestOBJ.open("POST", getItemsURL, true);
		requestOBJ.setRequestHeader("Content-type", "text/xml");
		requestOBJ.send();
	} catch (e) {
		alert("Something went wrong");
	}
}

function ExistingItemsResponse() {
	var Itemsrarray;
	if (WindowType == 'NEW') {
		var select = document.getElementById("getItemsDropDown"), option = null;
		if (requestOBJ.readyState == 4) {
			if (requestOBJ.status == 200) {
				var responsetext = requestOBJ.responseText;
				responsetext = responsetext.replace("<xml>", "");
				if (responsetext.indexOf("~") > 0) {
					debugger;
					var finalResponse = responsetext.substring(0, responsetext
							.lastIndexOf('#'));
					Itemsrarray = finalResponse.split("#");
					for (var i = 0; i < Itemsrarray.length; i++) {
						option = document.createElement("option");
						option.value = Itemsrarray[i].split('~')[0];
						option.innerHTML = Itemsrarray[i].split('~')[1];
						select.appendChild(option);
					}
					data;
					dataSet = data;
				}
			}
		}
	}

	else if (WindowType == 'EDIT') {

		var select = document.getElementById("Edit_getItemsDropDown"), option = null;

		if (requestOBJ.readyState == 4) {
			if (requestOBJ.status == 200) {
				var responsetext = requestOBJ.responseText;
				responsetext = responsetext.replace("<xml>", "");
				if (responsetext.indexOf("~") > 0) {
					var finalResponse = responsetext.substring(0, responsetext
							.lastIndexOf('#'));
					Itemsrarray = finalResponse.split("#");
					for (var i = 0; i < Itemsrarray.length; i++) {
						option = document.createElement("option");
						option.value = Itemsrarray[i].split('~')[0];
						option.innerHTML = Itemsrarray[i].split('~')[1];
						if (Itemsrarray[i].split('~')[1].trim() == StockIn_SelectedRowDataSet.ItemName
								.trim()) {
							option.selected = true;
						}
						select.appendChild(option);
					}
					data;
					dataSet = data;
				}
			}
		}
	}
}

function setEditTransactionData() {
	getExistingItems('EDIT');
	document.getElementById("Edit_StockOut_date").value = StockIn_SelectedRowDataSet.StockInDate;
	document.getElementById("Edit_getItemsDropDown").value = StockIn_SelectedRowDataSet.ItemName;
	document.getElementById("Edit_StockOut_Amount").value = StockIn_SelectedRowDataSet.StockAmt;
	document.getElementById("Edit_StockOut_Description").value = StockIn_SelectedRowDataSet.Description;
	document.getElementById("Edit_Trans_stockOutid").value = StockIn_SelectedRowDataSet.StockID;
	document.getElementById("Edit_StockOut_Qty").value = StockIn_SelectedRowDataSet.StockQty;

}
