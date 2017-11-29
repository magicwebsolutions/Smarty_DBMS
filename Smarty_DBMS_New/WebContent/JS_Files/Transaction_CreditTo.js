var data = [];
var dataSet;
var SelectedRowDataSet =[];
var today = new Date();

function OpenModalPopup(popupName){document.getElementById(popupName).style.display='block';}
function CloseModalPopup(popupName){document.getElementById(popupName).style.display='none';}


function onload()
{	//alert("Hekljelkrjekl");
 debugger;
  var dd = today.getDate();
  var mm = today.getMonth()+1; 
  var yyyy = today.getFullYear();
  //yyyy = parseInt(yyyy) + 1;
  yyyy = parseInt(yyyy);
  today = yyyy+'-'+mm+'-'+dd
  debugger;
  //document.getElementById("Trans_date").value = today; 
}


function getCurrentDayTransaction(param){
	var datePassed 
	debugger;
	if(param == 'today'){
		datePassed = today
		document.getElementById("Trans_date").value = today;
		}
	else if(param == 'searchDate'){
		datePassed = document.getElementById("Trans_date").value;
	}
	debugger;
	var URL = gloablContextURL+"/Transaction?Event=CREDITTO_CURRENTDAY&datePassed="+datePassed;
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
	requestOBJ.onreadystatechange=CurrentDayTransactionResponse;
	requestOBJ.open("POST",URL,true);
	requestOBJ.setRequestHeader("Content-type", "text/xml");
	requestOBJ.send();
	}catch(e){
	alert("Something went wrong");
	}
}

function CurrentDayTransactionResponse(){
	var itemarray;
	if(requestOBJ.readyState==4){
	if(requestOBJ.status==200){
	var responsetext= requestOBJ.responseText;
	responsetext= responsetext.replace("<xml>","");
	if(responsetext.indexOf("~")>0){
		var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
		itemarray = finalResponse.split("#");
		data.length = 0;
		for(var i=0; i<itemarray.length;i++){
			data.push(
					{
						BillID : itemarray[i].split('~')[0],
						Name : itemarray[i].split('~')[1],
						BillAmt : itemarray[i].split('~')[2],
						BillType : itemarray[i].split('~')[3],
						Description : itemarray[i].split('~')[4]
					}		
					
			);		
		}
		data;	
		dataSet = data;
		
		document.getElementById("TransactionCredit").hidden = false;
		document.getElementById("NoTransactionMsg").hidden = true;
		
		var rows = "";
		$('#TransactionCredit').find('tbody').empty();
		$.each(dataSet, function(){
		    rows += "<tr class='item'><td>" + this.BillID + "</td><td>" + this.Name + "</td><td>" + this.BillAmt + "</td><td>" + this.BillType + "</td><td>" + this.Description + "</td></tr>";
		});
		$( rows ).appendTo( "#TransactionCredit tbody" );
		
		var table = document.getElementById('TransactionCredit');
		console.log("table-->"+table);
		var cells = table.getElementsByTagName('td');
		for (var i = 0; i < cells.length; i++) {
		    // Take each cell
		    var cell = cells[i];
		    // do something on onclick event for cell
		    cell.onclick = function () {
		        // Get the row id where the cell exists
		        var rowId = this.parentNode.rowIndex;
		        console.log("RowID--->"+rowId);
		        var rowsNotSelected = table.getElementsByTagName('tr');
		        for (var row = 0; row < rowsNotSelected.length; row++) {
		            rowsNotSelected[row].style.backgroundColor = "";	
		            rowsNotSelected[row].classList.remove('selected');
		        }
		        console.log("rowsNotSelected--->"+rowsNotSelected);
		        var rowSelected = table.getElementsByTagName('tr')[rowId];
		        console.log("rowSelected--->"+rowSelected);
		        rowSelected.style.backgroundColor = "#d0e4f1";
		        rowSelected.className += " selected";
		        SelectedRowDataSet = {
		        		SelectedRowID : rowId,
		        		CustomerID : rowSelected.cells[0].innerHTML,
		        		Name : rowSelected.cells[1].innerHTML,
		        		Phone : rowSelected.cells[2].innerHTML,
		        		Address : rowSelected.cells[3].innerHTML
		        }
		    }
		}
	}
	else
		{
			document.getElementById("TransactionCredit").hidden = true;
			document.getElementById("NoTransactionMsg").hidden = false;
		}
	}
	getExistingCustomers();
	}
}


/*For Fetching Customer to map in dropdown*/
function getExistingCustomers(){	
	var getCustomerURL = gloablContextURL+"/Transaction?Event=GET_CUSTOMERS";
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
	requestOBJ.onreadystatechange=ExistingCustomersnResponse;
	requestOBJ.open("POST",getCustomerURL,true);
	requestOBJ.setRequestHeader("Content-type", "text/xml");
	requestOBJ.send();
	}catch(e){
	alert("Something went wrong");
	}
}


function ExistingCustomersnResponse(){
	debugger;
	var Customerarray;	
	
	var select = document.getElementById("getCustomerDropDown"), option = null;	
	if(requestOBJ.readyState==4){
	if(requestOBJ.status==200){
	var responsetext= requestOBJ.responseText;
	responsetext= responsetext.replace("<xml>","");
	debugger;
	if(responsetext.indexOf("~")>0){
		debugger;
		var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
		Customerarray = finalResponse.split("#");
		for(var i=0; i<Customerarray.length;i++){
			
			 	option = document.createElement("option");
		        option.value = Customerarray[i].split('~')[0];
		        option.innerHTML = Customerarray[i].split('~')[1];
		        select.appendChild(option);			
		}
		debugger;
		data;	
		dataSet = data;
	}
	}
	getExistingItems();
	}
}




/*For Fetching Items to map in dropdown*/
function getExistingItems(){	
	var getItemsURL = gloablContextURL+"/Transaction?Event=GET_ITEMS";
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
	requestOBJ.onreadystatechange=ExistingItemsResponse;
	requestOBJ.open("POST",getItemsURL,true);
	requestOBJ.setRequestHeader("Content-type", "text/xml");
	requestOBJ.send();
	}catch(e){
	alert("Something went wrong");
	}
}


function ExistingItemsResponse(){
	debugger;
	var Itemsrarray;	
	
	var select = document.getElementById("getItemsDropDown"), option = null;
	
	if(requestOBJ.readyState==4){
	if(requestOBJ.status==200){
	var responsetext= requestOBJ.responseText;
	responsetext= responsetext.replace("<xml>","");
	debugger;
	if(responsetext.indexOf("~")>0){
		debugger;
		var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
		Itemsrarray = finalResponse.split("#");
		for(var i=0; i<Itemsrarray.length;i++){			
			 	option = document.createElement("option");
		        option.value = Itemsrarray[i].split('~')[0];
		        option.innerHTML = Itemsrarray[i].split('~')[1];
		        select.appendChild(option);
		}
		debugger;
		data;	
		dataSet = data;
	}
	}
	}
}
