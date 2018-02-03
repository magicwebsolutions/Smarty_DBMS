<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String returnFlag_AddNewStockOut= null;
String returnFlag_UpdateStockOut=null;
if(request.getAttribute("returnFlag_AddNewStockOut")!=null && request.getAttribute("returnFlag_AddNewStockOut")!="")
{
	returnFlag_AddNewStockOut = (String)request.getAttribute("returnFlag_AddNewStockOut");
}
if(request.getAttribute("returnFlag_UpdateStockOut")!=null && request.getAttribute("returnFlag_UpdateStockOut")!="")
{
	returnFlag_UpdateStockOut = (String)request.getAttribute("returnFlag_UpdateStockOut");
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
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/StockIn.jsp">Stock In</a></li>
               <li class="active"><a href="<%=request.getContextPath()%>/JSP_Pages/StockOut.jsp")>Stock Out</a></li>
           </ul>
       </div>
	</nav>
	<div style="margin-top: 115px;">
	<button id="newstockoutbutton" onclick="OpenModalPopup('addStockSales')" class="button" style="vertical-align:middle"><span>Add Sale Details</span></button> 
	<button id="updatestockoutbutton" onclick="OpenModalPopup('editStockSalesDtls');setEditTransactionData();" class="button" style="vertical-align:middle"><span>Edit Sale Details</span></button> 
	<input oninput="w3.filterHTML('#StockOutDetails', '.item', this.value)" placeholder="Search Details" style="margin-left: 678px;">
	</div>
	
	<div style="margin-top: 10px;">
			<span>Date of Transaction</span> 	
			 <input type="date" id="StockOut_date" name="StockOut_date">
			<button onclick="getCurrentDayStockDtls('searchDate');">Go</button><br>
	</div>
	
	<div style="margin-top: 10px;" id="NoStockMsg">
	<h1> No Stock Added</h1>	
	</div>
	
    <table class="table table-hover" id="StockOutDetails" style="margin-top: 30px;">
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

<div id="addStockSales" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/Stock" method=post ID="ADDNEWSTOCKSALESDTLS">
   <input type="hidden" name="Event" maxlength=50 value="ADDNEWSTOCKSALESDTLS"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('addStockSales')" class="close" title="Close Modal" >&times;</span>
    </div>

   <div class="container">
   
	    <label><b>Transaction Date</b></label><br>
	    <input type="date" id="Add_Stockout_date" name="Add_Stockout_date"><br>
		
		<label><b>Select Item</b></label><br>
		<select id="getItemsDropDown" name="getItemsDropDown"  class="soflow">		 
		</select> 
		<br>
		<label><b>Quantity</b></label><br>
     	<input type="text" placeholder="Enter Quantity" id= "Stockout_Qty" name="Stockout_Qty" maxlength=10 required><br>

     	<label><b>Amount</b></label><br>
     	<input type="text" placeholder="Enter Amount" id= "Stockout_Amt" name="Stockout_Amt" maxlength=10 required><br>
     
     	<label><b>Description</b></label><br>
     	<input type="text" placeholder="Enter Description" id= "Stockout_Description" name="Stockout_Description" required><br>

     
   </div>
    <div class="container_bottom">
      <button type="submit">Add Stock Sales</button>
      <button type="button" onclick="CloseModalPopup('addStockSales')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



<div id="editStockSalesDtls" class="modal">  
  <form class="modal-content animate" action="/Smarty_DBMS_New/Stock" method=post ID="UPDATESTOCKSALESDTLS">
   <input type="hidden" name="Event" maxlength=50 value="UPDATESTOCKSALESDTLS"></input>
    <div class="imgcontainer">
      <span onclick="CloseModalPopup('editStockSalesDtls')" class="close" title="Close Modal" >&times;</span>
    </div>
   <div class="container">   
   		<input type="text" id= "Edit_Trans_stockOutid" name="Edit_Trans_stockOutid" style="display: none;"><br>
	     <label><b>Transaction Date</b></label><br>
	     <input type="date" id="Edit_StockOut_date" name="Edit_StockOut_date"><br>	
		<br>
		<label><b>Select Item</b></label><br>
		<select id="Edit_getItemsDropDown" name="Edit_getItemsDropDown"  class="soflow">		 
		</select> 
		<br>
		 <label><b>Quantity</b></label><br>
     <input type="text" placeholder="Enter Quantity" id= "Edit_StockOut_Qty" name="Edit_StockOut_Qty" maxlength=10 required><br>
     <label><b>Amount</b></label><br>
     <input type="text" placeholder="Enter Amount" id= "Edit_StockOut_Amount" name="Edit_StockOut_Amount" maxlength=10 required><br>
     <label><b>Description</b></label><br>
     <input type="text" placeholder="Enter Description" id= "Edit_StockOut_Description" name="Edit_StockOut_Description" required><br>
   </div>
    <div class="container_bottom">
      <button type="submit">Update</button>
      <button type="button" onclick="CloseModalPopup('editStockSalesDtls')" class="cancelbtn">Cancel</button>
    </div>
  </form>
</div>



</body>


<script type="text/javascript" src="<%=request.getContextPath()%>/Assets/Js/w3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS_Files/StockOut.js"></script>

<script>
var gloablContextURL = "<%=request.getContextPath()%>";

var AddTransaction_Status = '<%=returnFlag_AddNewStockOut%>';
var UpdateTransaction_Status = '<%=returnFlag_UpdateStockOut%>';

if(AddTransaction_Status == "Success"){
		alertify.notify('Stock Detail Added Successfully', 'success', 3);
		setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/StockOut.jsp";}, 500);
	}
	else if (AddTransaction_Status == "Failed"){
		alertify.notify('Error Occured Please Try Again', 'error', 3);
		setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/StockOut.jsp";}, 500);
	}
if(UpdateTransaction_Status == "Success"){
		alertify.notify('Stock Details Updated Successfully', 'success', 1);
		setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/StockOut.jsp";}, 500);
		
}
else if (UpdateTransaction_Status == "Failed"){
	alertify.notify('Error Occured Please Try Again', 'error', 3);
	setTimeout(function(){ window.location.href = gloablContextURL+"/JSP_Pages/StockOut.jsp";}, 500);
}

</script>

</html>