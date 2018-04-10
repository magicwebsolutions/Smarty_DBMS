<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String returnFlag_AddNewCRTransaction= null;
String returnFlag_UpdateCRTransaction=null;
if(request.getAttribute("returnFlag_AddNewCRTransaction")!=null && request.getAttribute("returnFlag_AddNewCRTransaction")!="")
{
	returnFlag_AddNewCRTransaction = (String)request.getAttribute("returnFlag_AddNewCRTransaction");
}
if(request.getAttribute("returnFlag_UpdateCRTransaction")!=null && request.getAttribute("returnFlag_UpdateCRTransaction")!="")
{
	returnFlag_UpdateCRTransaction = (String)request.getAttribute("returnFlag_UpdateCRTransaction");
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/Smarty_DBMS_New/Assets/css/Styling.css">
<title>Transaction</title>
</head>
<body onload="onload();getCurrentDayTransaction('today');">
<%@ include file = "Header.jsp" %>
<div class="Maincontainer" style="margin-top: 125px;">
	<nav class="innerNavigation navbar-default">
	 <div id="navbarCollapse" class="collapse navbar-collapse">
           <ul class="nav navbar-nav">
               <li class="active"><a href="/Smarty_DBMS_New/JSP_Pages/Transaction_CreditTo.jsp">Credit To Customer</a></li>
               <li><a href="/Smarty_DBMS_New/JSP_Pages/Transaction_DebitFrom.jsp")>Debit From Customer</a></li>
           </ul>
       </div>
	</nav>
	<div style="margin-top: 115px;">
	<button id= "btn_NewTrans" onclick="OpenModalPopup('addCustomerTransaction')"  class="button" style="vertical-align:middle"><span>New Transaction</span></button> 
	<button id= "btn_EditTrans" onclick="OpenModalPopup('editCustomerTransaction');setEditTransactionData();"  class="button" style="vertical-align:middle"><span>Edit Transaction</span></button> 
	<input oninput="w3.filterHTML('#TransactionCredit', '.item', this.value)" placeholder="Search Details" style="margin-left: 343px;width: 22%;">
	</div>
	
	<div style="margin-top: 10px;">
			<span>Date of Transaction</span> 	
			 <input type="date" id="Trans_date" name="Trans_date">
			<button onclick="getCurrentDayTransaction('searchDate');">Go</button><br>
	</div>
	
	<div style="margin-top: 10px;" id="NoTransactionMsg">
	<h1> No Transaction on this Day</h1>	
	</div>
	
    <table class="table table-hover" id="TransactionCredit" style="margin-top: 30px;">
    <thead>
      <tr>
        <th>Bill ID</th>
        <th>Date</th>
        <th>Customer Name</th>        
        <th>Amount</th>
        <th>Item</th>
        <th>Type</th>
        <th>Description</th>       
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>

<div id="addCustomerTransaction" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/Transaction" method=post ID="ADDNEWCREDITTOTRANSACTION" novalidate>
  
  <div>
  		<h2 style="margin-left: 15px;">Add Transaction</h2>
      	<span onclick="CloseModalPopup('addCustomerTransaction');showExistingCustomerGrid()" class="close" title="Close Modal" >&times;</span>
  </div>
  
   <input type="hidden" name="Event" maxlength=50 value="ADDNEWCREDITTOTRANSACTION"></input>
   <div class="container">
   
   <table>
   	<tr>
		<td><label><b>Transaction Date</b></label></td>
		<td><label><b>Select Item</b></label></td>
   	</tr>
   	<tr>
   		<td style="width: 50%;"> <input type="date" id="Add_Trans_date" name="Add_Trans_date"></td>
		<td><select id="getItemsDropDown" name="getItemsDropDown"  class="soflow"></select> <br></td>
   	</tr>
   	</table>
		
		<div id="existingcustomeraddiv"> 
		<label><b>Select Customer</b></label><br>
		<select id="getCustomerDropDown" name="getCustomerDropDown" class="soflow">		 
		</select> 
		<img onclick="showAddNewCustomerGrid()" id="addnewIcon" style="height: 28px;width: 30px;" src= "/Smarty_DBMS_New/Assets/Images/customerAdd.png"/>
		</div>
		
		<div id="newcustomeraddiv">
		<div>
		<label style="width: 33%;"><b>Customer Name</b></label>
		<label><b>Customer Type</b></label><br>

		<input type="text" placeholder="Enter Customer Name" id= "AddNewCustomer_Name" name="AddNewCustomer_Name" maxlength=100 required style="width: 30%;">
		<select id="Cust_type" name="Cust_type" style="width: 22.8%;">
	      <option value="HOTEL">Hotel</option>
	      <option value="INDIVIDUAL">Individual</option>
	    </select></select> 
	    <img onclick="showExistingCustomerGrid()" id="backIcon" style="height: 28px; width: 30px;" src= "<%=request.getContextPath()%>/Assets/Images/backButton.png"/><br>
	    <br>
	    </div>
		
   		
			    
		<label><b>Customer Phone</b></label><br>
		<input type="text" placeholder="Enter Customer Phone" id= "AddNewCustomer_Phone" name="AddNewCustomer_Phone" maxlength=100 required style=" width: 53.2%;"><br>
		<label><b>Customer Address</b></label><br>
		<input type="text" placeholder="Enter Customer Address" id= "AddNewCustomer_Address" name="AddNewCustomer_Address" maxlength=100 required  style=" width: 53.2%;"><br>
		</div>
		


     <label><b>Amount</b></label><br>
     <input type="text" placeholder="Enter Amount" id= "Trans_Amount" name="Trans_Amount" maxlength=10 required style=" width: 53.2%;"><br>
     
     <label><b>Description</b></label><br>
     <input type="text" placeholder="Enter Description" id= "Trans_Description" name="Trans_Description" required style=" width: 53.2%;"><br>

     
   </div>
    <div class="container_bottom">
      <button type="submit">Add Transaction</button>
      <button type="button" onclick="CloseModalPopup('addCustomerTransaction')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



<div id="editCustomerTransaction" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/Transaction" method=post ID="EDITCREDITTOTRANSACTION">
   <input type="hidden" name="Event" maxlength=50 value="EDITCREDITTOTRANSACTION"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('editCustomerTransaction')" class="close" title="Close Modal" >&times;</span>
    </div>
   <div class="container">   
   		<input type="text" id= "Edit_Trans_BillId" name="Edit_Trans_BillId" style="display: none;"><br>
   		<input type="hidden" name="cust_dropdown_id" id="cust_dropdown_id">
	     <label><b>Transaction Date</b></label><br>
	     <input type="date" id="Edit_Trans_date" name="Edit_Trans_date"><br>
		<label><b>Select Customer</b></label><br>
		<select  id="Edit_getCustomerDropDown" name="Edit_getCustomerDropDown" class="soflow" disabled>		 
		</select> 
		<br>
		<label><b>Select Item</b></label><br>
		<select id="Edit_getItemsDropDown" name="Edit_getItemsDropDown"  class="soflow">		 
		</select> 
		<br>
     <label><b>Amount</b></label><br>
     <input type="text" placeholder="Enter Amount" id= "Edit_Trans_Amount" name="Edit_Trans_Amount" maxlength=10 required><br>
     <label><b>Description</b></label><br>
     <input type="text" placeholder="Enter Description" id= "Edit_Trans_Description" name="Edit_Trans_Description" required><br>
   </div>
    <div class="container_bottom">
      <button type="submit">Update</button>
      <button type="button" onclick="CloseModalPopup('editCustomerTransaction')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



</body>


<script type="text/javascript" src="/Smarty_DBMS_New/Assets/Js/w3.js"></script>
<script type="text/javascript" src="/Smarty_DBMS_New/JS_Files/Transaction_CreditTo.js"></script>

<script>
var gloablContextURL = "<%=request.getContextPath()%>";

var AddTransaction_Status = 'null';
var UpdateTransaction_Status = 'null';

var AddTransaction_Status = '<%=returnFlag_AddNewCRTransaction%>';
var UpdateTransaction_Status = '<%=returnFlag_UpdateCRTransaction%>';

if(AddTransaction_Status == "Success"){
		alertify.notify('Transaction Added Successfully', 'success', 3);
		setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/Transaction_CreditTo.jsp";}, 500);
	}
	else if (AddTransaction_Status == "Failed"){
		alertify.notify('Error Occured Please Try Again', 'error', 3);
		setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/Transaction_CreditTo.jsp";}, 500);
	}
if(UpdateTransaction_Status == "Success"){
		alertify.notify('Transaction Updated Successfully', 'success', 1);
		setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/Transaction_CreditTo.jsp";}, 500);
		
}
else if (UpdateTransaction_Status == "Failed"){
	alertify.notify('Error Occured Please Try Again', 'error', 3);
	setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/Transaction_CreditTo.jsp";}, 500);
}

</script>

</html>