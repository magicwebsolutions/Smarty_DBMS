/*For CustomerType*/
var CustType_data =[];
var CustType_dataSet =[];
var CustType_SelectedRowDataSet =[];

var AddCustType_data =[];
var AddCustType_dataSet =[];


/*Comman Functionalities*/


var modal = document.getElementById('addCustomerType');
var modal1 = document.getElementById('editCustomerType');
var modal2= document.getElementById('deleteCustomerType');

//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
if (event.target == modal) {
   modal.style.display = "none";
}
if (event.target == modal1) {
	modal1.style.display = "none";
	}
if (event.target == modal2) {
	modal2.style.display = "none";
	}
}



/*Modal Popup Open/Close Universal Fuction*/
function OpenModalPopup(popupName){document.getElementById(popupName).style.display='block';}
function CloseModalPopup(popupName){document.getElementById(popupName).style.display='none';}
/*For Esc key Modal Close*/
document.addEventListener('keyup', function(e) {
    if (e.keyCode == 27) {
    	CloseModalPopup('addCustomerType');
    	CloseModalPopup('editCustomerType');
    	CloseModalPopup('deleteCustomerType');
    }
});


function ListCustomerType(){	
	var CustType_URL = gloablContextURL+"/ListCustomerType?Event=LISTCUSTOMERTYPE";
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
	requestOBJ.onreadystatechange=ListCustomerTypeResponse;
	requestOBJ.open("POST",CustType_URL,true);
	requestOBJ.setRequestHeader("Content-type", "text/xml");
	requestOBJ.send();
	}catch(e){
	alert("Something went wrong");
	}	
}

function ListCustomerTypeResponse(){
var itemarray;
if(requestOBJ.readyState==4){
if(requestOBJ.status==200){
var responsetext= requestOBJ.responseText;
responsetext= responsetext.replace("<xml>","");
if(responsetext.indexOf("~")>0){
	var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
	itemarray = finalResponse.split("#");
	for(var i=0; i<itemarray.length;i++){
		CustType_data.push(
				{
					TypeID : itemarray[i].split('~')[0],
					Type : itemarray[i].split('~')[1],
					Description : itemarray[i].split('~')[2],
					Status : itemarray[i].split('~')[3],
					Date : itemarray[i].split('~')[4]
				}		
				
		);		
	}
	CustType_data;	
	CustType_dataSet = CustType_data;	
	
	var rows = "";
	$.each(CustType_dataSet, function(){
	    rows += "<tr class='item'><td>" + this.TypeID + "</td><td>" + this.Type + "</td><td>" + this.Description + "</td><td>"+ this.Status + "</td><td>" + this.Date + "</td></tr>";
	});
	$( rows ).appendTo( "#CustomerTypeMain tbody" );
	
}
}
}

/*For Table Highlight and Variable set for Editing*/
var table = document.getElementById('CustomerTypeMain');
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
        CustType_SelectedRowDataSet = {
        		SelectedRowID : rowId,
        		TypeID : rowSelected.cells[0].innerHTML,
        		Type : rowSelected.cells[1].innerHTML,
        		Description : rowSelected.cells[2].innerHTML,
        		Status : rowSelected.cells[3].innerHTML,
        		Date : rowSelected.cells[4].innerHTML
        }
    }
}
}



function addNewCustomerType(){
	
	var AddCustType_URL = gloablContextURL+"/AddCustomerType?Event=ADDCUSTOMERTYPE";
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
	requestOBJ.onreadystatechange=AddCustomerTypeResponse;
	requestOBJ.open("POST",AddCustType_URL,true);
	requestOBJ.setRequestHeader("Content-type", "text/xml");
	requestOBJ.send();
	}catch(e){
	alert("Something went wrong");
	}	
	
}


function AddCustomerTypeResponse(){
	var addCustType_itemarray;
if(requestOBJ.readyState==4){
if(requestOBJ.status==200){
var responsetext= requestOBJ.responseText;
responsetext= responsetext.replace("<xml>","");
/*if(responsetext.indexOf("~")>0){
	var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
	addCustType_itemarray = finalResponse.split("#");
	for(var i=0; i<addCustType_itemarray.length;i++){
		AddCustType_data.push(
				{
					CustomerID : addCustType_itemarray[i].split('~')[0],
					Name : addCustType_itemarray[i].split('~')[1],
					Phone : addCustType_itemarray[i].split('~')[2],
					Address : addCustType_itemarray[i].split('~')[3],
					Credit : addCustType_itemarray[i].split('~')[4],
					Debit : addCustType_itemarray[i].split('~')[5],
					Outstanding : addCustType_itemarray[i].split('~')[6]
				}		
				
		);		
	}
	AddCustType_data;	
	AddCustType_dataSet = AddCustType_data;
	
	
	var rows = "";
	$.each(AddCustType_dataSet, function(){
	    rows += "<tr><td>" + this.CustomerID + "</td><td>" + this.Name + "</td><td>" + this.Phone + "</td><td>" + this.Address + "</td><td>" + this.Credit + "</td><td>" + this.Debit + "</td><td>" + this.Outstanding + "</td></tr>";
	});
	$( rows ).appendTo( "#customerListTable tbody" );
	
}*/
}
}
}

function setCustTypeEditData(){
	document.getElementById("edit_custType_id").value = CustType_SelectedRowDataSet.TypeID;
	document.getElementById("edit_custType_name").value = CustType_SelectedRowDataSet.Type;
	document.getElementById("edit_custType_description").value = CustType_SelectedRowDataSet.Description;
	
}


function showdeleteCustomer(){
	document.getElementById("DeleteCustomerTypeName").innerHTML = CustType_SelectedRowDataSet.Type;
	document.getElementById("delete_CustType_Id").value = CustType_SelectedRowDataSet.TypeID;
}

function deleteSelectedRecord(){	
	document.getElementById("DELETECUSTOMERTYPE").submit();
	document.getElementById("CustomerTypeMain").deleteRow(CustType_SelectedRowDataSet.SelectedRowID);
	
}


