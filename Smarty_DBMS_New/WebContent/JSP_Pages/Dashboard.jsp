<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS_Files/Dashboard.js"></script>
<script src="<%=request.getContextPath()%>/Assets/Js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/Assets/Js/exporting.js"></script>
</head>

<body onload="getDashboardInfo();">
<%@ include file = "Header.jsp" %>

<div class="Maincontainer" style="background-color: red;margin-top: 51px;">
	<div class="row" style="padding: 10px;">
		<div class="col-md-12"> 
			<div id="bar-chart" style="height: 350px;"></div> 
		</div>
	 </div>
	 
	 <div class="row" style="padding: 10px;">
		<div class="col-md-5"> 
			<div id="pie-chart" style="height: 300px;"></div> 
		</div>
		
		<div class="col-md-7"> 
			<div id="line-chart" style="height: 300px;"></div> 
		</div>		
	 </div>
</div>
</body>

<script>
var gloablContextURL = "<%=request.getContextPath()%>";
</script>

</html>