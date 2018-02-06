var dataSet;
var today = new Date();

function onload(){
	  var mm = ("0" + (today.getMonth() + 1)).slice(-2);
	  var yyyy = today.getFullYear();
	  yyyy = parseInt(yyyy);
	  var StartDateNumber = "0"+1;
	  var EndDateNumber = 30;
	  if(mm == 02){EndDateNumber = 28;}
	  document.getElementById("Start_Date").value=  yyyy+'-'+mm+'-'+StartDateNumber;
	  document.getElementById("End_Date").value=  yyyy+'-'+mm+'-'+EndDateNumber;
	  getSummary(yyyy+'-'+mm+'-'+StartDateNumber,yyyy+'-'+mm+'-'+EndDateNumber);
}

function getExistingCustomers(){
	debugger;
	document.getElementById("customerDtlsReport").style.visibility = "hidden";
	
	var getCustomerURL = gloablContextURL+"/Transaction?Event=GET_CUSTOMERS";
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
		debugger;
		requestOBJ.onreadystatechange=ExistingCustomersnResponse;
		requestOBJ.open("POST",getCustomerURL,true);
		requestOBJ.setRequestHeader("Content-type", "text/xml");
		requestOBJ.send();
	debugger;
	}catch(e){
	alert("Something went wrong");
	}
}


function ExistingCustomersnResponse(){
	debugger;
		var select = document.getElementById("getCustomerDropDown"); 
		var option = null;	
		debugger;
		if(requestOBJ.readyState==4){
			debugger;
		if(requestOBJ.status==200){
			debugger;
		var responsetext= requestOBJ.responseText;
		responsetext= responsetext.replace("<xml>","");
		debugger;
		if(responsetext.indexOf("~")>0){
			debugger;
			var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
			Customerarray = finalResponse.split("#");
			var data = [];
			option = document.createElement("option");
			option.value = "CLEAR";
			option.innerHTML = "Select Customer";		        
	        select.appendChild(option);
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
		}
	
}


function getCustomerDetailedReport(customerid){
	debugger;
	document.getElementById("customerDtlsReport").style.visibility = "visible";
	if(customerid == "CLEAR"){
		document.getElementById("customerDtlsReport").style.visibility = "hidden";
		document.getElementById('custPhone').innerHTML = "";
		document.getElementById('custAddress').innerHTML = "";
		document.getElementById('custOutstanding').innerHTML ="";
		
	}else {
		var getCustomerDtlsURL = gloablContextURL+"/Report?Event=GET_CUSTOMER_REPORT&customerId="+customerid;
		if(window.XMLHttpRequest){
		requestOBJ = new XMLHttpRequest();
		}else if(window.ActiveXObject){
			requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
		}
		try{
			debugger;
			requestOBJ.onreadystatechange=getDetailedReportOfCustomer;
			requestOBJ.open("POST",getCustomerDtlsURL,true);
			requestOBJ.setRequestHeader("Content-type", "text/xml");
			requestOBJ.send();
		debugger;
		}catch(e){
		alert("Something went wrong");
		}
	}
		
}
function getDetailedReportOfCustomer(){
	var customerarray;
	var customerInfo;
	if(requestOBJ.readyState==4){
	if(requestOBJ.status==200){
	var responsetext= requestOBJ.responseText;	
	responsetext= responsetext.replace("<xml>","");
	console.log("responsetext in ONLOAD---->"+responsetext);
	debugger;
	if(responsetext.indexOf("~")>0){
		debugger;
		var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
		debugger;
		customerarray = finalResponse.split("#");
		if(customerarray == ""){customerarray =[]};
		debugger;
		var data = [];
		var CustPhone = "Not Available";
		var Address = "Not Available";
		var Outstanding = "0";
		for(var i=0; i<customerarray.length;i++){
			data.push(
					{
						BillID : customerarray[i].split('~')[0],
						BillDate : customerarray[i].split('~')[1],
						Name : customerarray[i].split('~')[2],
						BillType : customerarray[i].split('~')[3],
						BillAmount : customerarray[i].split('~')[4],
						Description : customerarray[i].split('~')[5]
					}		
					
			);		
		}
		var custInfo = responsetext.split("|")[1];
		customerInfo = custInfo.split("~");
		
			CustPhone = customerInfo[0];
			Address = customerInfo[1];
			Outstanding = customerInfo[2];
			
			document.getElementById('custPhone').innerHTML = CustPhone;
			document.getElementById('custAddress').innerHTML = Address;
			document.getElementById('custOutstanding').innerHTML =Outstanding;
					
		debugger;
		data;	
		dataSet = data;
		
console.log("zzzzzzzzzzzzzz--->"+dataSet);
		
		 var rowCount = customerDtlsReport.rows.length;
	        for (var i = rowCount - 1; i > 0; i--) {
	        	customerDtlsReport.deleteRow(i);
	        }
       debugger;
        if(dataSet.length > 0){
        	var rows = "";
    		$.each(dataSet, function(){
    		    rows += "<tr class='item'><td>" + this.BillID + "</td><td>" + this.BillDate + "</td><td>" + this.Name + "</td><td>" + this.BillType + "</td><td>" + this.BillAmount + "</td><td>" + this.Description + "</td></tr>";
    		});		
    		$(rows).appendTo( "#customerDtlsReport tbody" );
        } else {
        	document.getElementById("customerDtlsReport").style.visibility = "hidden";
        }
		
		
	}
	else
		{
		 var rowCount = customerDtlsReport.rows.length;
	        for (var i = rowCount - 1; i > 0; i--) {
	        	customerDtlsReport.deleteRow(i);
	        }
		}
	}
	}

	/*For Table Highlight and Variable set for Editing*/
	var table = document.getElementById('customerDtlsReport');
	debugger;
	console.log("table-->"+table);
	debugger;
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
	        debugger;
	        var rowSelected = table.getElementsByTagName('tr')[rowId];
	        console.log("rowSelected--->"+rowSelected);
	        rowSelected.style.backgroundColor = "#d0e4f1";
	        rowSelected.className += " selected";
	        debugger;
	    }
	}
}