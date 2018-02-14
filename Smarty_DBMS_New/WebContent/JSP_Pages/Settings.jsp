<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String returnFlag_AddNewCustomer= null;
if(request.getParameter("returnFlag_AddNewCustomer")!=null && request.getParameter("returnFlag_AddNewCustomer")!="")
{
	returnFlag_AddNewCustomer = (String)request.getParameter("returnFlag_AddNewCustomer");
	
}
String returnFlag_DeleteCustomer= null;
if(request.getAttribute("returnFlag_DeleteCustomer")!=null && request.getAttribute("returnFlag_DeleteCustomer")!="")
{
	returnFlag_DeleteCustomer = (String)request.getAttribute("returnFlag_DeleteCustomer");
}
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Assets/css/Styling.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
        table.dataTable tbody>tr.selected,
        table.dataTable tbody>tr>.selected {
          background-color: #A2D3F6;
        }
        
        th {
        width : 14.25%;
        }
</style>

<title>Settings</title>
</head>
<body onload="ListCustomers();">
<%@ include file = "Header.jsp" %>
<div class="Maincontainer" style="margin-top: 125px;">
	<nav class="innerNavigation navbar-default">
	 <div id="navbarCollapse" class="collapse navbar-collapse">
           <ul class="nav navbar-nav">
               <li class="active"><a href="<%=request.getContextPath()%>/JSP_Pages/Settings.jsp">Customers</a></li>
               <!--<li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_CustomerTypes.jsp")>Customer Types</a></li> -->
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_ItemTypes.jsp">Items</a></li>
			<li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_Profile.jsp">Profile</a></li>
           </ul>
       </div>
	</nav>
	<div style="margin-top: 115px;">
	<button onclick="OpenModalPopup('addCustomer')" class="button" style="vertical-align:middle"><span>New</span></button> 
	<button onclick="OpenModalPopup('editCustomer');setEditData();" class="button" style="vertical-align:middle"><span>Edit</span></button> 
	<button onclick="OpenModalPopup('deleteCustomer');showdeleteCustomer();" class="button" style="vertical-align:middle"><span>Delete</span></button>
	<input oninput="w3.filterHTML('#customerListTable', '.item', this.value)" placeholder="Search Details" style="margin-left: 343px;width: 22%;">
	</div>
    <table class="table table-hover" id="customerListTable" style="margin-top: 30px;">
    <thead>
      <tr>
        <th>CustomerID</th>
        <th>Name</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Credit Given</th>
        <th>Debited Back</th>
        <th>Outstanding</th>
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>
<div id="addCustomer" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/AddCustomer" method=post ID="ADDNEWCUSTOMER">
   <input type="hidden" name="EVENT" maxlength=25 value="ADDNEWCUSTOMER"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('addCustomer')" class="close" title="Close Modal" >&times;</span>
    </div>

    <div class="container">
      <label><b>Name</b></label><br>
      <input type="text" placeholder="Enter Customer Name" name="cust_name" required><br>

      <label><b>Phone Number</b></label><br>
      <input type="text" placeholder="Enter Phone Number" name="cust_phone" maxlength=10 required><br>
      
      <label><b>Address</b></label><br>
      <input type="text" placeholder="Enter Address" name="cust_Address" required><br>
      
      <label for="Cust_type">Customer Type</label> <br>
	    <select id="Cust_type" name="Cust_type">
	      <option value="HOTEL">Hotel</option>
	      <option value="INDIVIDUAL">Individual</option>
	    </select> <br>     
    </div>
    <div class="container_bottom">
      <button type="submit">Add Customer</button>
      <button type="button" onclick="CloseModalPopup('addCustomer')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="editCustomer" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/EditCustomer" method=post ID="EDITCUSTOMER">
   <input type="hidden" name="EVENT" maxlength=25 value="EDITCUSTOMER"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('editCustomer')" class="close" title="Close Modal" >&times;</span>
    </div>

    <div class="container">
    
      <input type="hidden" id ="edit_cust_id" name="edit_cust_id"></input>
      <label><b>Name</b></label><br>
      <input type="text" placeholder="Enter Customer Name" id ="edit_cust_name" name="edit_cust_name" required><br>

      <label><b>Phone Number</b></label><br>
      <input type="text" placeholder="Enter Phone Number" id ="edit_cust_phone" name="edit_cust_phone" maxlength=10 required><br>
      
      <label><b>Address</b></label><br>
      <input type="text" placeholder="Enter Address" id ="edit_cust_Address" name="edit_cust_Address" required><br>
      
      <label for="edit_Cust_type">Customer Type</label> <br>
	    <select id="edit_Cust_type" name="edit_Cust_type">
	      <option value="Hotel">Hotel</option>
	      <option value="Individual">Individual</option>
	    </select> <br>     
    </div>
    <div class="container_bottom">
      <button type="submit">Update</button>
      <button type="button" onclick="CloseModalPopup('editCustomer')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>


<div id="deleteCustomer" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/DeleteCustomer" method=post ID="DELETECUSTOMER">
   <input type="hidden" name="EVENT" maxlength=25 value="DELETECUSTOMER"></input>
     <input type="hidden" id ="delete_Cust_Id" name="delete_Cust_Id"></input>   
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('deleteCustomer');" class="close" title="Close Modal" >&times;</span>
    </div>

    <div class="container">
      Are you sure want to delete the Customer..?   <span id="DeleteCustomerName"></span> 
    </div>
    <div class="container_bottom">
      <button onclick="deleteSelectedRecord();">Delete</button>
      <button type="button" onclick="CloseModalPopup('deleteCustomer')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>

</body>

<script type="text/javascript" src="<%=request.getContextPath()%>/Assets/Js/w3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS_Files/Settings.js"></script>

<script>
var gloablContextURL = "<%=request.getContextPath()%>";

var AddCustomer_Status = "<%=returnFlag_AddNewCustomer%>";
if(AddCustomer_Status == "Success"){
		alertify.notify('Customer Added Successfully', 'success', 3);
		if(AddCustomer_Status == "Success"){
			window.location.href = gloablContextURL+"/JSP_Pages/Settings.jsp";			
		}
	}
	else if (AddCustomer_Status == "Failed"){
		alertify.notify('Error Occured Please Try Again', 'error', 3);
	}
	
var DeleteCustomer_Status = '<%=returnFlag_DeleteCustomer%>';
if(DeleteCustomer_Status == "Success"){
	alertify.notify('Customer Deleted Successfully', 'success', 3);
	}
	else if (DeleteCustomer_Status == "Failed"){
	alertify.notify('Error Occured Please Try Again', 'error', 3);
	}
</script>

</html>