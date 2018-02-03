<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String returnFlag_AddNewStock= null;
String returnFlag_UpdateStockIn=null;
if(request.getAttribute("returnFlag_AddNewStockIn")!=null && request.getAttribute("returnFlag_AddNewStockIn")!="")
{
	returnFlag_AddNewStock = (String)request.getAttribute("returnFlag_AddNewStockIn");
}
if(request.getAttribute("returnFlag_UpdateStockIn")!=null && request.getAttribute("returnFlag_UpdateStockIn")!="")
{
	returnFlag_UpdateStockIn = (String)request.getAttribute("returnFlag_UpdateStockIn");
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Assets/css/Styling.css">
<title>Transaction</title>
</head>
<body onload="onload();getCurrentDayStockDtls('today');">
<%@ include file = "Header.jsp" %>
<div class="Maincontainer" style="margin-top: 125px;">
	<nav class="innerNavigation navbar-default">
	 <div id="navbarCollapse" class="collapse navbar-collapse">
           <ul class="nav navbar-nav">
               <li class="active"><a href="<%=request.getContextPath()%>/JSP_Pages/StockIn.jsp">Stock In</a></li>
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/StockOut.jsp")>Stock Out</a></li>
           </ul>
       </div>
	</nav>
	<div style="margin-top: 115px;">
	<button onclick="OpenModalPopup('addStock')" class="button" style="vertical-align:middle"><span>Load Stock Details</span></button> 
	<button onclick="OpenModalPopup('editStockDtsls');setEditTransactionData();" class="button" style="vertical-align:middle"><span>Edit Stock Details</span></button> 
	<input oninput="w3.filterHTML('#StockInDetails', '.item', this.value)" placeholder="Search Details" style="margin-left: 678px;">
	</div>
	
	<div style="margin-top: 10px;">
			<span>Date of Transaction</span> 	
			 <input type="date" id="StockIn_date" name="StockIn_date">
			<button onclick="getCurrentDayStockDtls('searchDate');">Go</button><br>
	</div>
	
	<div style="margin-top: 10px;" id="NoStockMsg">
	<h1> No Stock Added</h1>	
	</div>
	
    <table class="table table-hover" id="StockInDetails" style="margin-top: 30px;">
    <thead>
      <tr>
        <th>Stock ID</th>
        <th>Date</th>
        <th>Item Name</th>        
        <th>Quantity</th>
        <th>Amount</th>
        <th>Type</th>
        <th>Description</th>       
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>

<div id="addStock" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/Stock" method=post ID="ADDNEWSTOCKDTLS">
   <input type="hidden" name="Event" maxlength=50 value="ADDNEWSTOCKDTLS"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('addStock')" class="close" title="Close Modal" >&times;</span>
    </div>

   <div class="container">
   
	    <label><b>Transaction Date</b></label><br>
	    <input type="date" id="Add_Stock_date" name="Add_Stock_date"><br>
		
		<label><b>Select Item</b></label><br>
		<select id="getItemsDropDown" name="getItemsDropDown"  class="soflow">		 
		</select> 
		<br>
		<label><b>Quantity</b></label><br>
     	<input type="text" placeholder="Enter Quantity" id= "Stock_Qty" name="Stock_Qty" maxlength=10 required><br>

     	<label><b>Amount</b></label><br>
     	<input type="text" placeholder="Enter Amount" id= "Stock_Amt" name="Stock_Amt" maxlength=10 required><br>
     
     	<label><b>Description</b></label><br>
     	<input type="text" placeholder="Enter Description" id= "Stock_Description" name="Stock_Description" required><br>

     
   </div>
    <div class="container_bottom">
      <button type="submit">Add Stock</button>
      <button type="button" onclick="CloseModalPopup('addStock')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



<div id="editStockDtsls" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/Stock" method=post ID="UPDATESTOCKDTLS">
   <input type="hidden" name="Event" maxlength=50 value="UPDATESTOCKDTLS"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('editStockDtsls')" class="close" title="Close Modal" >&times;</span>
    </div>
   <div class="container">   
   		<input type="text" id= "Edit_Trans_stockid" name="Edit_Trans_stockid" style="display: none;"><br>
	     <label><b>Transaction Date</b></label><br>
	     <input type="date" id="Edit_Stock_date" name="Edit_Stock_date"><br>	
		<br>
		<label><b>Select Item</b></label><br>
		<select id="Edit_getItemsDropDown" name="Edit_getItemsDropDown"  class="soflow">		 
		</select> 
		<br>
		 <label><b>Quantity</b></label><br>
     <input type="text" placeholder="Enter Quantity" id= "Edit_Stock_Qty" name="Edit_Stock_Qty" maxlength=10 required><br>
     <label><b>Amount</b></label><br>
     <input type="text" placeholder="Enter Amount" id= "Edit_Stock_Amount" name="Edit_Stock_Amount" maxlength=10 required><br>
     <label><b>Description</b></label><br>
     <input type="text" placeholder="Enter Description" id= "Edit_Stock_Description" name="Edit_Stock_Description" required><br>
   </div>
    <div class="container_bottom">
      <button type="submit">Update</button>
      <button type="button" onclick="CloseModalPopup('editStockDtsls')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



</body>


<script type="text/javascript" src="<%=request.getContextPath()%>/Assets/Js/w3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS_Files/StockIn.js"></script>

<script>
var gloablContextURL = "<%=request.getContextPath()%>";

var AddTransaction_Status = '<%=returnFlag_AddNewStock%>';
var UpdateTransaction_Status = '<%=returnFlag_UpdateStockIn%>';

if(AddTransaction_Status == "Success"){
		alertify.notify('Stock Detail Added Successfully', 'success', 3);
		setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/StockIn.jsp";}, 500);
	}
	else if (AddTransaction_Status == "Failed"){
		alertify.notify('Error Occured Please Try Again', 'error', 3);
		setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/StockIn.jsp";}, 500);
	}
if(UpdateTransaction_Status == "Success"){
		alertify.notify('Stock Details Updated Successfully', 'success', 1);
		setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/StockIn.jsp";}, 500);
		
}
else if (UpdateTransaction_Status == "Failed"){
	alertify.notify('Error Occured Please Try Again', 'error', 3);
	setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/StockIn.jsp";}, 500);
}

</script>

</html>