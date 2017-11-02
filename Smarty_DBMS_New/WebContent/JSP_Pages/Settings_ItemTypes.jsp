<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String returnFlag_AddNewItemType= null;
String returnFlag_EditItemType= null;
String returnFlag_DeleteItemType= null;
if(request.getAttribute("returnFlag_AddNewItemType")!=null && request.getAttribute("returnFlag_AddNewItemType")!="")
{
	returnFlag_AddNewItemType = (String)request.getAttribute("returnFlag_AddNewItemType");
}

if(request.getAttribute("returnFlag_EditItemType")!=null && request.getAttribute("returnFlag_EditItemType")!="")
{
	returnFlag_EditItemType = (String)request.getAttribute("returnFlag_EditItemType");
}

if(request.getAttribute("returnFlag_DeleteItemType")!=null && request.getAttribute("returnFlag_DeleteItemType")!="")
{
	returnFlag_DeleteItemType = (String)request.getAttribute("returnFlag_DeleteItemType");
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
        table.dataTable tbody>tr.selected,
        table.dataTable tbody>tr>.selected {
          background-color: #A2D3F6;
        }
        
        th {
        width : 20%;
        }
</style>
<title>Settings</title>
</head>
<body onload="ListItemTypes();">
<%@ include file = "Header.jsp" %>
<div class="Maincontainer" style="margin-top: 125px;">
	<nav class="innerNavigation navbar-default">
	 <div id="navbarCollapse" class="collapse navbar-collapse">
           <ul class="nav navbar-nav">
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings.jsp">Customers</a></li>
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_CustomerTypes.jsp">Customer Types</a></li>
               <li  class="active"><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_ItemTypes.jsp">Items</a></li>
			<li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_Profile.jsp">Profile</a></li>
           </ul>
       </div>
	</nav>
	
	<div style="margin-top: 115px;">
	<button onclick="OpenModalPopup('addItemType')" style="width:auto;">New</button> 
	<button onclick="OpenModalPopup('editItemType');setItemTypeEditData();" style="width:auto;">Edit</button> 
	<button onclick="OpenModalPopup('deleteItemType');showdeleteItem();">Delete</button>
	<input oninput="w3.filterHTML('#ItemTypeMain', '.item', this.value)" placeholder="Search Details" style="margin-left: 953px;">
	</div>
	
	<table class="table table-hover" id="ItemTypeMain" style="margin-top: 30px;">
    <thead>
      <tr>
        <th>Type ID</th>
        <th>Item Type</th>
        <th>Description</th>
        <th>Status</th>
        <th>Created Date</th>
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>


<div id="addItemType" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/AddItemType" method=post ID="ADDNEWITEMTYPE">
   <input type="hidden" name="EVENT" maxlength=25 value="ADDNEWITEMTYPE"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('addItemType')" class="close" title="Close Modal" >&times;</span>
    </div>

    <div class="container">
      <label><b>Item Type</b></label><br>
      <input type="text" placeholder="Enter Item Type" name="itemType_name" required><br>

      <label><b>Description</b></label><br>
      <input type="text" placeholder="Enter Description" name="itemType_description" required><br>
      
    </div>
    <div class="container_bottom">
      <button type="submit">Add Item Type</button>
      <button type="button" onclick="CloseModalPopup('addItemType')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="editItemType" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/EditItemType" method=post ID="EDITITEMTYPE">
   <input type="hidden" name="EVENT" maxlength=25 value="EDITITEMTYPE"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('editItemType')" class="close" title="Close Modal" >&times;</span>
    </div>

    <div class="container">
    
      <input type="hidden" id ="edit_ItemType_id" name="edit_ItemType_id"></input>
      <label><b>Item Type</b></label><br>
      <input type="text" placeholder="Enter Item Type" id ="edit_ItemType_name" name="edit_ItemType_name" required><br>

      <label><b>Description</b></label><br>
      <input type="text" placeholder="Enter Description" id ="edit_ItemType_description" name="edit_ItemType_description" required><br>
      
   
    </div>
    <div class="container_bottom">
      <button type="submit">Update</button>
      <button type="button" onclick="CloseModalPopup('editItemType')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="deleteItemType" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/DeleteItemType" method=post ID="DELETEITEMTYPE">
   <input type="hidden" name="EVENT" maxlength=25 value="DELETEITEMTYPE"></input>
     <input type="hidden" id ="delete_ItemType_Id" name="delete_ItemType_Id"></input>   
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('deleteItemType');" class="close" title="Close Modal" >&times;</span>
    </div>

    <div class="container">
      Are you sure want to delete the Item Type..?   <span id="DeleteItemTypeName"></span> 
    </div>
    <div class="container_bottom">
      <button onclick="deleteSelectedRecord();">Delete</button>
      <button type="button" onclick="CloseModalPopup('deleteItemType')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>




</body>


<script>
var gloablContextURL = "<%=request.getContextPath()%>";


var AddItemType_Status = '<%=returnFlag_AddNewItemType%>';
var EditItemType_Status = '<%=returnFlag_EditItemType%>';
var DeleteItemType_Status = '<%=returnFlag_DeleteItemType%>';
if(AddItemType_Status == "Success"){
		alertify.notify('Added Successfully', 'success', 3);
	}
	else if (AddItemType_Status == "Failed"){
		alertify.notify('Error Occured Please Try Again', 'error', 3);
	}
	
if(EditItemType_Status == "Success"){
	alertify.notify('Updated Successfully', 'success', 3);
}
else if (EditItemType_Status == "Failed"){
	alertify.notify('Error Occured Please Try Again', 'error', 3);
}

if(DeleteItemType_Status == "Success"){
	alertify.notify('Deleted Successfully', 'success', 3);
}
else if (DeleteItemType_Status == "Failed"){
	alertify.notify('Error Occured Please Try Again', 'error', 3);
}
	
</script>


<script type="text/javascript" src="<%=request.getContextPath()%>/Assets/Js/w3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS_Files/Settings_ItemTypes.js"></script>
</html>