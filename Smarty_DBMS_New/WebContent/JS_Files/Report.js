var today = new Date();

function onload(){
	  var mm = ("0" + (today.getMonth() + 1)).slice(-2);
	  var yyyy = today.getFullYear();
	  yyyy = parseInt(yyyy);
	  var StartDateNumber = "0"+1;
	  var EndDateNumber = 30;
	  if(mm == 02){EndDateNumber = 28;}
	  document.getElementById("Start_Date").value=  yyyy+'-'+mm+'-'+StartDateNumber;
	  document.getElementById("End_Date").value=  yyyy+'-'+mm+'-'+EndDateNumber;
	  getSummary(yyyy+'-'+mm+'-'+StartDateNumber,yyyy+'-'+mm+'-'+EndDateNumber);
}

function getSummary(startDate,endDate){	
	var ajaxUrl =  globalContextURL+"/Report?Event=GETSUMMARY&fromdate="+startDate+"&enddate="+endDate;
	alert(ajaxUrl);
	if(window.XMLHttpRequest){
		requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject)
	{
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
		requestOBJ.onreadystatechange=getSummaryResponse;
		requestOBJ.open("POST",URL,true);
		requestOBJ.setRequestHeader("Content-type", "text/xml");
		requestOBJ.send();
		}catch(e){
		alert("Something went wrong");
		}
}

function getSummaryResponse(){
	
	alert("111111111111111111");
}