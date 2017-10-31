<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 
 <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/css/bootstrap.minv3.3.7.css"> 
 <!--<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>-->
 <script type="text/javascript" src="<%=request.getContextPath()%>/Assets/Js/jquery-1.12.4.js"></script>
 

 
 <script src="<%=request.getContextPath()%>/Assets/Js/bootstrap.min.js"></script>	
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Assets/css/Styling.css">
 <!--ALERTIFY-->
 <script src="<%=request.getContextPath()%>/Assets/Alertify/alertify.min.js"></script>	
 <script src="<%=request.getContextPath()%>/Assets/Alertify/default.min.css"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Assets/Alertify/alertify.min.css">
 </head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><img class = "logoStyle" src ="<%=request.getContextPath()%>/Assets/Images/Smarty_DBMS_Logo.png"/></a>
    </div>
    <form class="navbar-form navbar-left">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search">
      </div>
      <!--<button type="submit" class="btn btn-default">Submit</button>-->
    </form>
  </div>
</nav>

<div>
  <div class="navbar-fixed-left">
	  <ul class="nav navbar-nav">
	   <li onclick="navigation('DASHBOARD');"><center><img class = "sideMenuIcons" src="<%=request.getContextPath()%>/Assets/Images/MenuIcons/Dashboard.png"/></center><div>Dashboard</div></li>
	   <li><center><img class = "sideMenuIcons" src="<%=request.getContextPath()%>/Assets/Images/MenuIcons/Inventory.png"/></center><div>Stock In/Out</div></li>
	   <li><center><img class = "sideMenuIcons" src="<%=request.getContextPath()%>/Assets/Images/MenuIcons/Billing.png"/></center><div>Credit/Debit</div></li>
	   <li onclick="navigation('SETTINGS');"><center><img class = "sideMenuIcons" src="<%=request.getContextPath()%>/Assets/Images/MenuIcons/settings.png"/></center><div>Settings</div></li>
	   <li><center><img class = "sideMenuIcons" src="<%=request.getContextPath()%>/Assets/Images/MenuIcons/Dashboard.png"/></center><div>Dashboard</div></li>
	   <li><center><img class = "sideMenuIcons" src="<%=request.getContextPath()%>/Assets/Images/MenuIcons/Inventory.png"/></center><div>Stock In/Out</div></li>
	   <li><center><img class = "sideMenuIcons" src="<%=request.getContextPath()%>/Assets/Images/MenuIcons/Billing.png"/></center><div>Credit/Debit</div></li>
	   <li><center><img class = "sideMenuIcons" src="<%=request.getContextPath()%>/Assets/Images/MenuIcons/settings.png"/></center><div>Settings</div></li>
	  </ul>
</div>
</div>
</body>

<script>
function navigation(screenType){
	
	if(screenType == 'SETTINGS') {window.open("<%=request.getContextPath()%>/JSP_Pages/Settings.jsp","_self")};
	if(screenType == 'DASHBOARD') {window.open("<%=request.getContextPath()%>/JSP_Pages/Dashboard.jsp","_self")};

}
</script>
<style>

.logoStyle {
height: 42px;
width: 102px;
margin-top: -13px;
}

.navbar-form .form-control {
    display: inline-block;
    width: 500px;
    vertical-align: middle;
    margin-left: 295px;
}

.navbar {
    border-radius: 0px;
	overflow: hidden;
    //background-color: #333;
    position: fixed; /* Set the navbar to fixed position */
    top: 0; /* Position the navbar at the top of the page */
    width: 100%; /* Full width */
}


.navbar-fixed-left {
  width: 56px;
  position: fixed;
  height: 100%;
  top: 51px;
  background-color: #94b0c3;
  border-color: #94b0c3;
  z-index: 1;
}

.navbar-fixed-left .navbar-nav > li {
  width: 55px;
  margin-bottom: 34px;
  top: 20px;
}


.navbar-fixed-left .navbar-nav li div {
    background-color: #94b0c3;    
    position: absolute;
	margin-left: 55px;
    margin-top: -33px;
    border-radius: 0px 5px 5px 0px;
    color: white;
    height: 38px;
    width: 100px;
    padding: 8px;
	display: none;
}

.navbar-fixed-left .navbar-nav li:hover div{
	 display: block;
}

.sideMenuIcons {
    height: 30px;
    width: 27px;
}

</style>



</html>
