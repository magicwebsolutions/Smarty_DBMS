<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String returnFlag_AddNewCustomerType= null;
String returnFlag_EditCustomerType= null;
String returnFlag_DeleteCustomerType= null;
if(request.getAttribute("returnFlag_AddNewCustomerType")!=null && request.getAttribute("returnFlag_AddNewCustomerType")!="")
{
	returnFlag_AddNewCustomerType = (String)request.getAttribute("returnFlag_AddNewCustomerType");
}

if(request.getAttribute("returnFlag_EditCustomerType")!=null && request.getAttribute("returnFlag_EditCustomerType")!="")
{
	returnFlag_EditCustomerType = (String)request.getAttribute("returnFlag_EditCustomerType");
}

if(request.getAttribute("returnFlag_DeleteCustomerType")!=null && request.getAttribute("returnFlag_DeleteCustomerType")!="")
{
	returnFlag_DeleteCustomerType = (String)request.getAttribute("returnFlag_DeleteCustomerType");
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Assets/css/Styling.css">
</head>
<body onload="ListCustomerType();">
<%@ include file = "Header.jsp" %>
<div class="Maincontainer" style="margin-top: 125px;">
	<nav class="innerNavigation navbar-default">
	 <div id="navbarCollapse" class="collapse navbar-collapse">
           <ul class="nav navbar-nav">
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings.jsp">Customers</a></li>
               <li class="active"><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_CustomerTypes.jsp">Customer Types</a></li>
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_ItemTypes.jsp">Items</a></li>
			<li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_Profile.jsp">Profile</a></li>
           </ul>
       </div>
	</nav>
	
	<div style="margin-top: 115px;">
	<button onclick="OpenModalPopup('addCustomerType')" class="button" style="vertical-align:middle"><span>New</span></button> 
	<button onclick="OpenModalPopup('editCustomerType');setCustTypeEditData();"  class="button" style="vertical-align:middle"><span>Edit</span></button> 
	<button onclick="OpenModalPopup('deleteCustomerType');showdeleteCustomer();" class="button" style="vertical-align:middle"><span>Delete</span></button>
	<input oninput="w3.filterHTML('#CustomerTypeMain', '.item', this.value)" placeholder="Search Details" style="margin-left: 953px;">
	</div>
	
	<table class="table table-hover" id="CustomerTypeMain" style="margin-top: 30px;">
    <thead>
      <tr>
        <th>Type ID</th>
        <th>Customer Type</th>
        <th>Description</th>
        <th>Status</th>
        <th>Created Date</th>
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>


<div id="addCustomerType" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/AddCustomerType" method=post ID="ADDNEWCUSTOMERTYPE">
   <input type="hidden" name="EVENT" maxlength=25 value="ADDNEWCUSTOMERTYPE"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('addCustomerType')" class="close" title="Close Modal" >&times;</span>
    </div>

    <div class="container">
      <label><b>Customer Type</b></label><br>
      <input type="text" placeholder="Enter Customer Type" name="custType_name" required><br>

      <label><b>Description</b></label><br>
      <input type="text" placeholder="Enter Description" name="custType_description" required><br>
      
    </div>
    <div class="container_bottom">
      <button type="submit">Add Customer Type</button>
      <button type="button" onclick="CloseModalPopup('addCustomerType')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="editCustomerType" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/EditCustomerType" method=post ID="EDITCUSTOMERTYPE">
   <input type="hidden" name="EVENT" maxlength=25 value="EDITCUSTOMERTYPE"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('editCustomerType')" class="close" title="Close Modal" >&times;</span>
    </div>

    <div class="container">
    
      <input type="hidden" id ="edit_custType_id" name="edit_custType_id"></input>
      <label><b>Customer Type</b></label><br>
      <input type="text" placeholder="Enter Customer Type" id ="edit_custType_name" name="edit_custType_name" required><br>

      <label><b>Description</b></label><br>
      <input type="text" placeholder="Enter Description" id ="edit_custType_description" name="edit_custType_description" required><br>
      
   
    </div>
    <div class="container_bottom">
      <button type="submit">Update</button>
      <button type="button" onclick="CloseModalPopup('editCustomerType')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="deleteCustomerType" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/DeleteCustomerType" method=post ID="DELETECUSTOMERTYPE">
   <input type="hidden" name="EVENT" maxlength=25 value="DELETECUSTOMERTYPE"></input>
     <input type="hidden" id ="delete_CustType_Id" name="delete_CustType_Id"></input>   
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('deleteCustomerType');" class="close" title="Close Modal" >&times;</span>
    </div>

    <div class="container">
      Are you sure want to delete the Customer Type..?   <span id="DeleteCustomerTypeName"></span> 
    </div>
    <div class="container_bottom">
      <button onclick="deleteSelectedRecord();">Delete</button>
      <button type="button" onclick="CloseModalPopup('deleteCustomerType')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


</body>
<script>
var gloablContextURL = "<%=request.getContextPath()%>";

var AddCustomerType_Status = '<%=returnFlag_AddNewCustomerType%>';
var EditCustomerType_Status = '<%=returnFlag_EditCustomerType%>';
var DeleteCustomerType_Status = '<%=returnFlag_DeleteCustomerType%>';
if(AddCustomerType_Status == "Success"){
		alertify.notify('Added Successfully', 'success', 3);
	}
	else if (AddCustomerType_Status == "Failed"){
		alertify.notify('Error Occured Please Try Again', 'error', 3);
	}
	
if(EditCustomerType_Status == "Success"){
	alertify.notify('Updated Successfully', 'success', 3);
}
else if (EditCustomerType_Status == "Failed"){
	alertify.notify('Error Occured Please Try Again', 'error', 3);
}

if(DeleteCustomerType_Status == "Success"){
	alertify.notify('Deleted Successfully', 'success', 3);
}
else if (DeleteCustomerType_Status == "Failed"){
	alertify.notify('Error Occured Please Try Again', 'error', 3);
}
	
</script>


<script type="text/javascript" src="<%=request.getContextPath()%>/Assets/Js/w3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS_Files/Settings_CustomerTypes.js"></script>
</html>