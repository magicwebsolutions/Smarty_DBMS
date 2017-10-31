function submitLogin(){
	document.getElementById("loginForm").submit();
}

function LoginOnLoad(){
	document.getElementById("alertUserName").hidden = true;
	document.getElementById("alertPassword").hidden = true;
	
}

function DashboardRedirect(){	
	window.open("JSP_Pages/Dashboard.jsp","_self");
}