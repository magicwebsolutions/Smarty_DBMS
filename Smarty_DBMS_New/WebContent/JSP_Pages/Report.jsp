<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Assets/css/Styling.css">
<title>Report</title>
</head>
<body onload="getSummary('onload');">
<%@ include file = "Header.jsp" %>
<div class="Maincontainer" style="margin-top: 125px;">
	<nav class="innerNavigation navbar-default">
	 <div id="navbarCollapse" class="collapse navbar-collapse">
           <ul class="nav navbar-nav">
               <li class="active"><a href="<%=request.getContextPath()%>/JSP_Pages/Report.jsp">Income/Expense Summary</a></li>
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/ReportCustomerSummary.jsp")>Customer Summary</a></li>
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/ReportStock.jsp")>Stock Summary</a></li>
           </ul>
       </div>
	</nav>
	<div style="margin-top: 10px;">
			<span>From</span> 	
			<input type="date" id="Start_Date" name="Start_Date">			
			
			<span>To</span> 	
			<input type="date" id="End_Date" name="End_Date">
			<button onclick="getSummary('manual');"> Go</button><br>
	</div>
	
    <table class="table table-hover" id="incomeexpsummary" style="margin-top: 30px;">
    <thead>
      <tr>
        <th>Customer ID</th>
        <th>Date</th>
        <th>Customer Name</th>        
        <th>Credit Given</th>
        <th>Credit Paid</th>
        <th>Outstanding</th>          
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>

</body>


<script type="text/javascript" src="<%=request.getContextPath()%>/Assets/Js/w3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS_Files/Report.js"></script>
<script>
var gloablContextURL = "<%=request.getContextPath()%>";
</script>
</html>