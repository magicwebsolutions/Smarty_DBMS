var data = [];
var dataSet;
var SelectedRowDataSet =[];

/*function ListCustomers()
{	
var URL = gloablContextURL+"/ListCustomer?Event=LISTCUSTOMER";
if(window.XMLHttpRequest){
requestOBJ = new XMLHttpRequest();
}else if(window.ActiveXObject){
	requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
}
try{
requestOBJ.onreadystatechange=CustomerResponse;
requestOBJ.open("POST",URL,true);
requestOBJ.setRequestHeader("Content-type", "text/xml");
requestOBJ.send();
}catch(e){
alert("Something went wrong");
}

}
function CustomerResponse(){
	var singleGrid;
	var itemarray;
if(requestOBJ.readyState==4){
if(requestOBJ.status==200){
var responsetext= requestOBJ.responseText;
responsetext= responsetext.replace("<xml>","");
if(responsetext.indexOf("~")>0){
	var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
	itemarray = finalResponse.split("#");
	for(var i=0; i<itemarray.length;i++){
		data.push(itemarray[i].split('~'));		
	}
	data;	
	dataSet = data;
	
	
	
	$(document).ready(function() {	
		var columnDefs = [{
	          title: "Customer ID",
	          name: "name"
	        }, {
	          title: "Name"
	        }, {
	          title: "Phone"
	        }, {
	          title: "Address"
	        }];
		
		var myTable;		

        myTable = $('#example').DataTable({
          "sPaginationType": "full_numbers",
          data: dataSet,
          columns: columnDefs,
          dom: 'Bfrtip',        // Needs button container
          select: 'single',
          responsive: true,
          altEditor: true,     // Enable altEditor
          buttons: [{
            text: 'Add',
            name: 'add'// do not change name
          },
          {
            extend: 'selected', // Bind to Selected row
            text: 'Edit',
            name: 'edit'        // do not change name
          },
          {
            extend: 'selected', // Bind to Selected row
            text: 'Delete',
            name: 'delete'      // do not change name
         }]
        
        

        });	
        
        myTable.on( 'buttons-action', function ( e, buttonApi, dataTable, node, config ) {
            console.log( 'Button '+buttonApi.text()+' was activated' );
        } );
        
	} );
	
}
}
}
}*/


function ListCustomers()
{	
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
	var singleGrid;
	var itemarray;
if(requestOBJ.readyState==4){
if(requestOBJ.status==200){
var responsetext= requestOBJ.responseText;
responsetext= responsetext.replace("<xml>","");
debugger;
if(responsetext.indexOf("~")>0){
	debugger;
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
	debugger;
	data;	
	dataSet = data;
	
	
	var rows = "";
	$.each(dataSet, function(){
	    rows += "<tr class='item'><td>" + this.CustomerID + "</td><td>" + this.Name + "</td><td>" + this.Phone + "</td><td>" + this.Address + "</td><td>" + this.Credit + "</td><td>" + this.Debit + "</td><td>" + this.Outstanding + "</td></tr>";
	});
	$( rows ).appendTo( "#customerListTable tbody" );
	
}
}
}

/*For Table Highlight and Variable set for Editing*/
var table = document.getElementById('customerListTable');
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
        SelectedRowDataSet = {
        		SelectedRowID : rowId,
        		CustomerID : rowSelected.cells[0].innerHTML,
        		Name : rowSelected.cells[1].innerHTML,
        		Phone : rowSelected.cells[2].innerHTML,
        		Address : rowSelected.cells[3].innerHTML
        }
        debugger;
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
	var singleGrid;
	var itemarray;
if(requestOBJ.readyState==4){
if(requestOBJ.status==200){
var responsetext= requestOBJ.responseText;
responsetext= responsetext.replace("<xml>","");
debugger;
if(responsetext.indexOf("~")>0){
	debugger;
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
	debugger;
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
    }
});


/*For setting Selected row data in Popup*/
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







