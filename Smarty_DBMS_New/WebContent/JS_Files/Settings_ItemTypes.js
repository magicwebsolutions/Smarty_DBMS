/*For ItemomerType*/
var ItemType_data =[];
var ItemType_dataSet =[];
var ItemType_SelectedRowDataSet =[];

var AddItemType_data =[];
var AddItemType_dataSet =[];



/*Comman Functionalities*/


var modal = document.getElementById('addItemomerType');
var modal1 = document.getElementById('editItemomerType');
var modal2= document.getElementById('deleteItemomerType');

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
    	CloseModalPopup('addItemType');
    	CloseModalPopup('editItemType');
    	CloseModalPopup('deleteItemType');
    }
});


function ListItemTypes(){	
	var ItemType_URL = gloablContextURL+"/ListItemType?Event=LISTITEMTYPE";
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
	requestOBJ.onreadystatechange=ListItemTypeResponse;
	requestOBJ.open("POST",ItemType_URL,true);
	requestOBJ.setRequestHeader("Content-type", "text/xml");
	requestOBJ.send();
	}catch(e){
	alert("Something went wrong");
	}	
}

function ListItemTypeResponse(){
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
		ItemType_data.push(
				{
					TypeID : itemarray[i].split('~')[0],
					Type : itemarray[i].split('~')[1],
					Description : itemarray[i].split('~')[2],
					Status : itemarray[i].split('~')[3],
					Date : itemarray[i].split('~')[4]
				}		
				
		);		
	}
	debugger;
	ItemType_data;	
	ItemType_dataSet = ItemType_data;	
	
	var rows = "";
	$.each(ItemType_dataSet, function(){
	    rows += "<tr class='item'><td>" + this.TypeID + "</td><td>" + this.Type + "</td><td>" + this.Description + "</td><td>"+ this.Status + "</td><td>" + this.Date + "</td></tr>";
	});
	$( rows ).appendTo( "#ItemTypeMain tbody" );
	
}
}
}

/*For Table Highlight and Variable set for Editing*/
var table = document.getElementById('ItemTypeMain');
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
        ItemType_SelectedRowDataSet = {
        		SelectedRowID : rowId,
        		TypeID : rowSelected.cells[0].innerHTML,
        		Type : rowSelected.cells[1].innerHTML,
        		Description : rowSelected.cells[2].innerHTML,
        		Status : rowSelected.cells[3].innerHTML,
        		Date : rowSelected.cells[4].innerHTML
        }
        debugger;
    }
}
}



function addNewItemomerType(){
	
	var AddItemType_URL = gloablContextURL+"/AddItemType?Event=ADDNEWITEMTYPE";
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
	requestOBJ.onreadystatechange=AddItemTypeResponse;
	requestOBJ.open("POST",AddItemType_URL,true);
	requestOBJ.setRequestHeader("Content-type", "text/xml");
	requestOBJ.send();
	}catch(e){
	alert("Something went wrong");
	}	
	
}


function AddItemTypeResponse(){
	var addItemType_itemarray;
if(requestOBJ.readyState==4){
if(requestOBJ.status==200){
var responsetext= requestOBJ.responseText;
responsetext= responsetext.replace("<xml>","");
debugger;
if(responsetext.indexOf("~")>0){
	debugger;
	var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
	addItemType_itemarray = finalResponse.split("#");
	for(var i=0; i<addItemType_itemarray.length;i++){
		AddItemType_data.push(
				{
					ItemomerID : addItemType_itemarray[i].split('~')[0],
					Name : addItemType_itemarray[i].split('~')[1],
					Phone : addItemType_itemarray[i].split('~')[2],
					Address : addItemType_itemarray[i].split('~')[3],
					Credit : addItemType_itemarray[i].split('~')[4],
					Debit : addItemType_itemarray[i].split('~')[5],
					Outstanding : addItemType_itemarray[i].split('~')[6]
				}		
				
		);		
	}
	debugger;
	AddItemType_data;	
	AddItemType_dataSet = AddItemType_data;
	
	
	var rows = "";
	$.each(AddItemType_dataSet, function(){
	    rows += "<tr><td>" + this.ItemomerID + "</td><td>" + this.Name + "</td><td>" + this.Phone + "</td><td>" + this.Address + "</td><td>" + this.Credit + "</td><td>" + this.Debit + "</td><td>" + this.Outstanding + "</td></tr>";
	});
	$( rows ).appendTo( "#ItemomerListTable tbody" );
	
}
}
}
}

function setItemTypeEditData(){
	debugger;
	document.getElementById("edit_ItemType_id").value = ItemType_SelectedRowDataSet.TypeID;
	document.getElementById("edit_ItemType_name").value = ItemType_SelectedRowDataSet.Type;
	document.getElementById("edit_ItemType_description").value = ItemType_SelectedRowDataSet.Description;	
}


function showdeleteItem(){
	document.getElementById("DeleteItemTypeName").innerHTML = ItemType_SelectedRowDataSet.Type;
	document.getElementById("delete_ItemType_Id").value = ItemType_SelectedRowDataSet.TypeID;
}

function deleteSelectedRecord(){	
	document.getElementById("DELETEITEMTYPE").submit();
	document.getElementById("ItemTypeMain").deleteRow(ItemType_SelectedRowDataSet.SelectedRowID);
	debugger;
}


