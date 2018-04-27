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

function getSummary(searchType){	
	if(searchType == 'onload'){
		var mm = ("0" + (today.getMonth() + 1)).slice(-2);
		  var yyyy = today.getFullYear();
		  yyyy = parseInt(yyyy);
		  var StartDateNumber = "0"+1;
		  var EndDateNumber = 30;
		  if(mm == 02){EndDateNumber = 28;}
		  document.getElementById("Start_Date").value=  yyyy+'-'+mm+'-'+StartDateNumber;
		  document.getElementById("End_Date").value=  yyyy+'-'+mm+'-'+EndDateNumber;
		  var startDate= yyyy+'-'+mm+'-'+StartDateNumber;
		  var endDate= yyyy+'-'+mm+'-'+EndDateNumber;
		  

		  var URL = gloablContextURL+"/Report?Event=GETSUMMARY&fromdate="+startDate+"&enddate="+endDate;
			if(window.XMLHttpRequest){
			requestOBJ = new XMLHttpRequest();
			}else if(window.ActiveXObject){
				requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
			}
			try{
			requestOBJ.onreadystatechange=getSummaryResponseOnload;
			requestOBJ.open("POST",URL,true);
			requestOBJ.setRequestHeader("Content-type", "text/xml");
			requestOBJ.send();
			}catch(e){
			alert("Something went wrong");
			}
		
	}
	else if(searchType = 'manual'){		
		var givenStartDate = document.getElementById("Start_Date").value;
		var givenEndDate = document.getElementById("End_Date").value;
		var URL = gloablContextURL+"/Report?Event=GETSUMMARY&fromdate="+givenStartDate+"&enddate="+givenEndDate;
		if(window.XMLHttpRequest){
		requestOBJ = new XMLHttpRequest();
		}else if(window.ActiveXObject){
			requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
		}
		try{
		requestOBJ.onreadystatechange=getSummaryResponseManual;
		requestOBJ.open("POST",URL,true);
		requestOBJ.setRequestHeader("Content-type", "text/xml");
		requestOBJ.send();
		}catch(e){
		alert("Something went wrong");
		}		
	}
}




function getSummaryResponseOnload(){
	var itemarray;
	if(requestOBJ.readyState==4){
	if(requestOBJ.status==200){
	var responsetext= requestOBJ.responseText;	
	responsetext= responsetext.replace("<xml>","");
	if(responsetext.indexOf("~")>0){
		var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
		itemarray = finalResponse.split("#");
		var data = [];
		for(var i=0; i<itemarray.length;i++){
			data.push(
					{
						CustomerID : itemarray[i].split('~')[0],
						Date : itemarray[i].split('~')[1],
						Name : itemarray[i].split('~')[2],
						Credit : itemarray[i].split('~')[3],
						Debit : itemarray[i].split('~')[4],
						Outstanding : itemarray[i].split('~')[5]
					}		
					
			);		
		}
		data;	
		dataSet = data;
		
		 var rowCount = incomeexpsummary.rows.length;
	        for (var i = rowCount - 1; i > 0; i--) {
	        	incomeexpsummary.deleteRow(i);
	        }
				
		var rows = "";
		$.each(dataSet, function(){
		    rows += "<tr class='item'><td>" + this.CustomerID + "</td><td>" + this.Date + "</td><td>" + this.Name + "</td><td>" + this.Credit + "</td><td>" + this.Debit + "</td><td>" + this.Outstanding + "</td></tr>";
		});		
		$(rows).appendTo( "#incomeexpsummary tbody" );
		
	}
	else
		{
		 var rowCount = incomeexpsummary.rows.length;
	        for (var i = rowCount - 1; i > 0; i--) {
	        	incomeexpsummary.deleteRow(i);
	        }
		}
	}
	}

	/*For Table Highlight and Variable set for Editing*/
	var table = document.getElementById('incomeexpsummary');
	var cells = table.getElementsByTagName('td');
	for (var i = 0; i < cells.length; i++) {
	    // Take each cell
	    var cell = cells[i];
	    // do something on onclick event for cell
	    cell.onclick = function () {
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
	    }
	}
}







function getSummaryResponseManual(){
	var itemarray;
	if(requestOBJ.readyState==4){
	if(requestOBJ.status==200){
	var responsetext= requestOBJ.responseText;	
	responsetext= responsetext.replace("<xml>","");
	if(responsetext.indexOf("~")>0){
		var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
		itemarray = finalResponse.split("#");
		var data = [];
		for(var i=0; i<itemarray.length;i++){
			data.push(
					{
						CustomerID : itemarray[i].split('~')[0],
						Date : itemarray[i].split('~')[1],
						Name : itemarray[i].split('~')[2],
						Credit : itemarray[i].split('~')[3],
						Debit : itemarray[i].split('~')[4],
						Outstanding : itemarray[i].split('~')[5]
					}		
					
			);		
		}
		data;	
		dataSet = data;
				
		 var rowCount = incomeexpsummary.rows.length;
	        for (var i = rowCount - 1; i > 0; i--) {
	        	incomeexpsummary.deleteRow(i);
	        }
				
		var rows = "";
		$.each(dataSet, function(){
		    rows += "<tr class='item'><td>" + this.CustomerID + "</td><td>" + this.Date + "</td><td>" + this.Name + "</td><td>" + this.Credit + "</td><td>" + this.Debit + "</td><td>" + this.Outstanding + "</td></tr>";
		});		
		$(rows).appendTo( "#incomeexpsummary tbody" );
		
	}
	else
		{
		var rowCount = incomeexpsummary.rows.length;
        for (var i = rowCount - 1; i > 0; i--) {
        	incomeexpsummary.deleteRow(i);
        }
		}
	}
	}

	/*For Table Highlight and Variable set for Editing*/
	var table = document.getElementById('incomeexpsummary');
	var cells = table.getElementsByTagName('td');
	for (var i = 0; i < cells.length; i++) {
	    // Take each cell
	    var cell = cells[i];
	    // do something on onclick event for cell
	    cell.onclick = function () {
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
	    }
	}
}