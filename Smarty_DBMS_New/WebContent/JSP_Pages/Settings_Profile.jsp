<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String returnFlag_ProfileUpdateStatus= null;
if(request.getAttribute("returnFlag_EditProfileStatus")!=null && request.getAttribute("returnFlag_EditProfileStatus")!="")
{
	returnFlag_ProfileUpdateStatus = (String)request.getAttribute("returnFlag_EditProfileStatus");
}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Settings</title>
</head>
<body>
<%@ include file = "Header.jsp" %>

<div class="Maincontainer" style="margin-top: 125px;">
	<nav class="innerNavigation navbar-default">
	 <div id="navbarCollapse" class="collapse navbar-collapse">
           <ul class="nav navbar-nav">
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings.jsp">Customers</a></li>
               <!--<li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_CustomerTypes.jsp")>Customer Types</a></li> -->
               <li><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_ItemTypes.jsp">Items</a></li>
			<li  class="active"><a href="<%=request.getContextPath()%>/JSP_Pages/Settings_Profile.jsp">Profile</a></li>
           </ul>
       </div>
	</nav>
	<div style="margin-top: 115px;" class="row">
	<div class="container">
  <h2>Change Password</h2>
	 <form action="/Smarty_DBMS_New/ChangeProfileSettings" method=post ID="CHANGEPROFILES">		
	 	<input type="hidden" name="EVENT" maxlength=25 value="CHANGEPROFILES"></input>
	 	<table>
	 	<tr>
		  <td style="width:20%"><label for="curr_pwd">Enter Current Password:</label></td>
		  <td><input type="password" class="form-control" id="curr_pwd" name= "curr_pwd" placeholder="Enter current password"></td>
		</tr> 
		<tr>
		  <td style="width:20%"><label for="new_pwd">Enter New Password:</label></td>
		  <td><input type="password" class="form-control" id="new_pwd" name= "new_pwd" placeholder="Enter new password"></td>
		</tr>
		<tr>
		  <td style="width:20%"><label for="renew_pwd">Re-Enter New Password:</label></td>
		  <td><input type="password" class="form-control" id="renew_pwd" name= "renew_pwd" placeholder="Re-Enter new password"></td>
		 </tr>
		</table>  
		   <button onclick="UpdatePassword();" class="btn btn-success">Submit</button>
	 </form>
</div>
	</div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/Assets/Js/w3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS_Files/Settings.js"></script>

<script>
var gloablContextURL = "<%=request.getContextPath()%>";

var ProfileUpdate_Status = '<%=returnFlag_ProfileUpdateStatus%>';
if(ProfileUpdate_Status == "Success"){
		alertify.notify('Password Changed Successfully', 'success', 3);
	}
	else if (ProfileUpdate_Status == "Failed"){
		alertify.notify('Error Occured Please Try Again', 'error', 3);
	}
</script>
</body>
</html>