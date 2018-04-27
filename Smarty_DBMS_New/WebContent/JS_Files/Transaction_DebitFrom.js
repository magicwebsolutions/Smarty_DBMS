var data = [];
var dataSet;
var CTransaction_SelectedRowDataSet =[];
var today = new Date();
var Customerarray;	
var WindowType ="";
var formattedDate;

function OpenModalPopup(popupName){document.getElementById(popupName).style.display='block';}
function CloseModalPopup(popupName){document.getElementById(popupName).style.display='none';}

/*For Esc key Modal Close*/
document.addEventListener('keyup', function(e) {
    if (e.keyCode == 27) {
    	CloseModalPopup('addCustomerTransaction_DR');
    	CloseModalPopup('editCustomerTransaction_DR');
    }
});


function onload()
{	
	document.getElementById("btn_EditDebitTrans").className += " button_disable";
	document.getElementById("btn_EditDebitTrans").disabled = true;
  var dd = today.getDate();
  var mm = today.getMonth()+1; 
  var yyyy = today.getFullYear();
  yyyy = parseInt(yyyy);
  today = yyyy+'-'+mm+'-'+dd
  
	mm = ('0'+mm+'').slice(-2);
	dd = ('0'+dd+'').slice(-2);
	formattedDate = yyyy + '-' + mm + '-' + dd;
	document.getElementById("Trans_date").value = formattedDate;
	document.getElementById("Add_Trans_date").value = formattedDate;	
}


function getCurrentDayTransaction(param){
	var datePassed 
	if(param == 'today'){
		datePassed = today;
		}
	else if(param == 'searchDate'){
		datePassed = document.getElementById("Trans_date").value;
	}
	var URL = gloablContextURL+"/Transaction?Event=DEBITFROM_CURRENTDAY&datePassed="+datePassed;
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
						Description : itemarray[i].split('~')[4],
						TransDate : itemarray[i].split('~')[5],
						BillItem : itemarray[i].split('~')[6],
						CustId : itemarray[i].split('~')[7]
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
		    rows += "<tr class='item'><td>" + this.BillID + "</td><td>" + this.TransDate + "</td><td>" + this.Name + "</td><td>" + this.BillAmt + "</td><td>" + this.BillType + "</td><td>" + this.Description +  "</td><td style='display:none'>"+this.CustId+"</td></tr>";
		});
		$( rows ).appendTo( "#TransactionCredit tbody" );
		
		var table = document.getElementById('TransactionCredit');
		var cells = table.getElementsByTagName('td');
		for (var i = 0; i < cells.length; i++) {
		    // Take each cell
		    var cell = cells[i];
		    // do something on onclick event for cell
		    cell.onclick = function () {
		    	var element = document.getElementById("btn_EditDebitTrans");
		    	element.classList.remove("button_disable");
		    	document.getElementById("btn_EditDebitTrans").disabled = false;
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
		        CTransaction_SelectedRowDataSet = {
		        		SelectedRowID : rowId,
		        		BillID : rowSelected.cells[0].innerHTML,
		        		TransDate  : rowSelected.cells[1].innerHTML,
		        		Name  : rowSelected.cells[2].innerHTML,
		        		Amount  : rowSelected.cells[3].innerHTML,
		        		Type  : rowSelected.cells[4].innerHTML,
		        		Description:rowSelected.cells[5].innerHTML,
		        		CustId : rowSelected.cells[6].innerHTML
		        }
		    }
		}
	}
	else
		{
		document.getElementById("btn_EditDebitTrans").className += " button_disable";
		document.getElementById("btn_EditDebitTrans").disabled = true;
			document.getElementById("TransactionCredit").hidden = true;
			document.getElementById("NoTransactionMsg").hidden = false;
		}
	getExistingCustomers('NEW');
	}
	
	}
}


/*For Fetching Customer to map in dropdown*/
//function getExistingCustomers(type){
	function getExistingCustomers(type){
	WindowType = type;
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
	if(WindowType == 'NEW'){
		var select = document.getElementById("getCustomerDropDown"); 
		var option = null;	
		if(requestOBJ.readyState==4){
		if(requestOBJ.status==200){
		var responsetext= requestOBJ.responseText;
		responsetext= responsetext.replace("<xml>","");
		if(responsetext.indexOf("~")>0){
			var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
			Customerarray = finalResponse.split("#");
			for(var i=0; i<Customerarray.length;i++){			
				 	option = document.createElement("option");
			        option.value = Customerarray[i].split('~')[0];
			        option.innerHTML = Customerarray[i].split('~')[1];		        
			        select.appendChild(option);			
			}
			data;	
			dataSet = data;
		}
		}
		getExistingItems('NEW');
		}
	}
	else if(WindowType == 'EDIT'){
		var select = document.getElementById("Edit_getCustomerDropDown"), option = null;	
		if(requestOBJ.readyState==4){
		if(requestOBJ.status==200){
		var responsetext= requestOBJ.responseText;
		responsetext= responsetext.replace("<xml>","");
		if(responsetext.indexOf("~")>0){
			var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
			Customerarray = finalResponse.split("#");
			for(var i=0; i<Customerarray.length;i++){			
				 	option = document.createElement("option");
			        option.value = Customerarray[i].split('~')[0];
			        option.innerHTML = Customerarray[i].split('~')[1];
			        if(Customerarray[i].split('~')[1].trim() == CTransaction_SelectedRowDataSet.Name.trim()){
			        	option.selected = true
			        }
			        select.appendChild(option);			
			}
			data;	
			dataSet = data;
		}
		}
		getExistingItems('EDIT');
		}
	}
	
	
	
}




/*For Fetching Items to map in dropdown*/
function getExistingItems(type){	
	WindowType = type;
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
	var Itemsrarray;	
	if(WindowType == 'NEW'){
	var select = document.getElementById("getItemsDropDown"), option = null;	
	if(requestOBJ.readyState==4){
	if(requestOBJ.status==200){
	var responsetext= requestOBJ.responseText;
	responsetext= responsetext.replace("<xml>","");
	if(responsetext.indexOf("~")>0){
		var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
		Itemsrarray = finalResponse.split("#");
		for(var i=0; i<Itemsrarray.length;i++){			
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
	
	else if(WindowType == 'EDIT'){
	
	
var select = document.getElementById("Edit_getItemsDropDown"), option = null;
	
	if(requestOBJ.readyState==4){
	if(requestOBJ.status==200){
	var responsetext= requestOBJ.responseText;
	responsetext= responsetext.replace("<xml>","");
	if(responsetext.indexOf("~")>0){
		var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
		Itemsrarray = finalResponse.split("#");
		for(var i=0; i<Itemsrarray.length;i++){			
			 	option = document.createElement("option");
		        option.value = Itemsrarray[i].split('~')[0];
		        option.innerHTML = Itemsrarray[i].split('~')[1];
		        if(Itemsrarray[i].split('~')[1].trim() == CTransaction_SelectedRowDataSet.Item.trim() ){
		        	option.selected =true;
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


function setEditTransactionData(){
	getExistingCustomers('EDIT');
	
	  //var dd = today.getDate();
	  //var mm = today.getMonth()+1; 
	  //var yyyy = today.getFullYear();
	  //yyyy = parseInt(yyyy) + 1;
	 // yyyy = parseInt(yyyy);
	  //today = yyyy+'-'+mm+'-'+dd
	  //document.getElementById("Trans_date").value = today; 
	  Customerarray;
	
	document.getElementById("Edit_Trans_date").value = CTransaction_SelectedRowDataSet.TransDate;
	document.getElementById("Edit_getCustomerDropDown").value = CTransaction_SelectedRowDataSet.Name;
	document.getElementById("Edit_Trans_Amount").value = CTransaction_SelectedRowDataSet.Amount;
	document.getElementById("Edit_Trans_Description").value = CTransaction_SelectedRowDataSet.Description;
	document.getElementById("Edit_Trans_BillId").value = CTransaction_SelectedRowDataSet.BillID;
	document.getElementById("cust_dropdown_id").value = CTransaction_SelectedRowDataSet.CustId;
	
}