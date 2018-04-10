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

<body onload="getDashboardInfo();" style="background-color: #e3e7ec">
<%@ include file = "Header.jsp" %>
<div class="Maincontainer" style="margin-top: 51px;">
	<div class="row" style="padding: 17px 12px 16px 7px">
	<div class="col-md-4"> 
			<div id="pie-chart" style="height: 300px;"></div> 
	</div>
		
	<div class="col-md-8"> 
			<div id="bar-chart" style="height: 300px;"></div> 
	</div>
</div>
</div>
</body>

<script>
var gloablContextURL = "<%=request.getContextPath()%>";
</script>

</html>