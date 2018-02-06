<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Assets/css/Styling.css">
<title>Report</title>
</head>
<body onload="getExistingCustomers();">
<%@ include file = "Header.jsp" %>
<div class="Maincontainer" style="margin-top: 125px;">
	<nav class="innerNavigation navbar-default">
	 <div id="navbarCollapse" class="collapse navbar-collapse">
           <ul class="nav navbar-nav">
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/Report.jsp">Income/Expense Summary</a></li>
               <li  class="active"><a href="<%=request.getContextPath()%>/JSP_Pages/ReportCustomerSummary.jsp")>Customer Summary</a></li>
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/ReportStock.jsp")>Stock Summary</a></li>
           </ul>
       </div>
	</nav>
	<div style="margin-top: 10px;">
	
	<table style="width:100%">
	<tr>
		<td><label><b>Select Customer</b></label><br></td>
		<td><label><b>Customer Phone</b></label><br></td>
		<td><label><b>Customer Address</b></label><br></td>
		<td><label><b>Total Outstanding</b></label><br></td>
		
	</tr>
	<tr>
		<td><select id="getCustomerDropDown" name="getCustomerDropDown" class="soflow" onchange="getCustomerDetailedReport(this.value)">		 
		</select></td>	
		<td><label id="custPhone"></label><br></td>
		<td><label id="custAddress"></label><br></td>
		<td><label id="custOutstanding"></label><br></td>

	</tr>
	</table>
		
		
		
		
	</div>
	
    <table class="table table-hover" id="customerDtlsReport" style="margin-top: 30px;">
    <thead>
      <tr>
        <th>Bill ID</th>
        <th>Bill Date</th>
        <th>Customer Name</th>        
        <th>Bill Type</th>
        <th>Bill Amount</th>
        <th>Description</th>
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>

</body>


<script type="text/javascript" src="<%=request.getContextPath()%>/Assets/Js/w3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS_Files/ReportCustomerSummary.js"></script>

<script>
var gloablContextURL = "<%=request.getContextPath()%>";
</script>

</html>