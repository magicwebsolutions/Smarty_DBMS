var data = [];
var dataSet;
var SelectedRowDataSet =[];


function ListCustomers()
{	
document.getElementById("setting_edit_btn").className += " button_disable";
document.getElementById("setting_edit_btn").disabled = true;

document.getElementById("setting_delete_btn").className += " button_disable";
document.getElementById("setting_delete_btn").disabled = true;
	
var URL = gloablContextURL+"/ListCustomer?Event=LISTCUSTOMER";
if(window.XMLHttpRequest){
requestOBJ = new XMLHttpRequest();
}else if(window.ActiveXObject){
	requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ.onreadystatechange=ListCustomerResponse;
requestOBJ.open("POST",URL,true);
requestOBJ.setRequestHeader("Content-type", "text/xml");
requestOBJ.send();
}catch(e){
alert("Something went wrong");
}
}
function ListCustomerResponse(){
	var itemarray;
if(requestOBJ.readyState==4){
if(requestOBJ.status==200){
var responsetext= requestOBJ.responseText;
responsetext= responsetext.replace("<xml>","");
if(responsetext.indexOf("~")>0){
	var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
	itemarray = finalResponse.split("#");
	for(var i=0; i<itemarray.length;i++){
		data.push(
				{
					CustomerID : itemarray[i].split('~')[0],
					Name : itemarray[i].split('~')[1],
					Phone : itemarray[i].split('~')[2],
					Address : itemarray[i].split('~')[3],
					Credit : itemarray[i].split('~')[4],
					Debit : itemarray[i].split('~')[5],
					Outstanding : itemarray[i].split('~')[6]
				}		
				
		);		
	}
	data;	
	dataSet = data;
	
	
	var rows = "";
	$.each(dataSet, function(){
	    rows += "<tr class='item'><td>" + this.CustomerID + "</td><td>" + this.Name + "</td><td>" + this.Phone + "</td><td>" + this.Address + "</td><td>" + this.Credit + "</td><td>" + this.Debit + "</td><td>" + this.Outstanding + "</td></tr>";
	});
	$( rows ).appendTo( "#customerListTable tbody" );
	
}else{
	document.getElementById("setting_edit_btn").className += " button_disable";
	document.getElementById("setting_edit_btn").disabled = true;

	document.getElementById("setting_delete_btn").className += " button_disable";
	document.getElementById("setting_delete_btn").disabled = true;
}
}
}

/*For Table Highlight and Variable set for Editing*/
var table = document.getElementById('customerListTable');
var cells = table.getElementsByTagName('td');
for (var i = 0; i < cells.length; i++) {
    // Take each cell
    var cell = cells[i];
    // do something on onclick event for cell
    cell.onclick = function () {
    	

    	var element1 = document.getElementById("setting_edit_btn");
    	element1.classList.remove("button_disable");
    	document.getElementById("setting_edit_btn").disabled = false;

    	var element2 = document.getElementById("setting_delete_btn");
    	element2.classList.remove("button_disable");
    	document.getElementById("setting_delete_btn").disabled = false;
        // Get the row id where the cell exists
    	document.getElementById("setting_edit_btn").disabled = false;
    	document.getElementById("setting_delete_btn").disabled = false;
        var rowId = this.parentNode.rowIndex;
        var rowsNotSelected = table.getElementsByTagName('tr');
        for (var row = 0; row < rowsNotSelected.length; row++) {
            rowsNotSelected[row].style.backgroundColor = "";
            rowsNotSelected[row].classList.remove('selected');
        }
        var rowSelected = table.getElementsByTagName('tr')[rowId];
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


/* Adding Customer */

function addNewCustomer(){
	
	var URL = gloablContextURL+"/AddCustomer?Event=ADDCUSTOMER";
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
	requestOBJ.onreadystatechange=AddCustomerResponse;
	requestOBJ.open("POST",URL,true);
	requestOBJ.setRequestHeader("Content-type", "text/xml");
	requestOBJ.send();
	}catch(e){
	alert("Something went wrong");
	}	
	
}


function AddCustomerResponse(){
	var itemarray;
if(requestOBJ.readyState==4){
if(requestOBJ.status==200){
var responsetext= requestOBJ.responseText;
responsetext= responsetext.replace("<xml>","");
if(responsetext.indexOf("~")>0){
	var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
	itemarray = finalResponse.split("#");
	for(var i=0; i<itemarray.length;i++){
		data.push(
				{
					CustomerID : itemarray[i].split('~')[0],
					Name : itemarray[i].split('~')[1],
					Phone : itemarray[i].split('~')[2],
					Address : itemarray[i].split('~')[3],
					Credit : itemarray[i].split('~')[4],
					Debit : itemarray[i].split('~')[5],
					Outstanding : itemarray[i].split('~')[6]
				}		
				
		);		
	}
	data;	
	dataSet = data;
	
	
	var rows = "";
	$.each(dataSet, function(){
	    rows += "<tr><td>" + this.CustomerID + "</td><td>" + this.Name + "</td><td>" + this.Phone + "</td><td>" + this.Address + "</td><td>" + this.Credit + "</td><td>" + this.Debit + "</td><td>" + this.Outstanding + "</td></tr>";
	});
	$( rows ).appendTo( "#customerListTable tbody" );
	
}
}
}
}

/*Adding Customer Ends*/

var modal = document.getElementById('addCustomer');

//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
if (event.target == modal) {
   modal.style.display = "none";
}
}

/*Modal Popup Open/Close Universal Fuction*/
function OpenModalPopup(popupName){document.getElementById(popupName).style.display='block';}
function CloseModalPopup(popupName){document.getElementById(popupName).style.display='none';}
/*For Esc key Modal Close*/
document.addEventListener('keyup', function(e) {
    if (e.keyCode == 27) {
    	CloseModalPopup('addCustomer');
    	CloseModalPopup('editCustomer');
    	CloseModalPopup('deleteCustomer');
    }
});


/*For setting Selected row data in Edit Popup*/
function setEditData(){
	document.getElementById("edit_cust_id").value = SelectedRowDataSet.CustomerID;
	document.getElementById("edit_cust_name").value = SelectedRowDataSet.Name;
	document.getElementById("edit_cust_phone").value = SelectedRowDataSet.Phone;
	document.getElementById("edit_cust_Address").value = SelectedRowDataSet.Address;
}


function showdeleteCustomer(){
	//alert("SelectedRowID-->"+SelectedRowDataSet.SelectedRowID);
	//alert("Selected Row-->"+SelectedRowDataSet.Name);
	
	document.getElementById("DeleteCustomerName").innerHTML = SelectedRowDataSet.Name;
	document.getElementById("delete_Cust_Id").value = SelectedRowDataSet.CustomerID;
}

function deleteSelectedRecord(){	
	document.getElementById("DELETECUSTOMER").submit();
	document.getElementById("customerListTable").deleteRow(SelectedRowDataSet.SelectedRowID);
	
}
