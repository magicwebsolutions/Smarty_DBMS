var OutstandingData = [];
var totalSales = [];
var item_Sales_Ration =[];

function getDashboardInfo(){
	var URL = gloablContextURL+"/Dashboard?Event=DASHBOARD";
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
	requestOBJ.onreadystatechange=DashboardResponse;
	requestOBJ.open("POST",URL,true);
	requestOBJ.setRequestHeader("Content-type", "text/xml");
	requestOBJ.send();
	}catch(e){
	alert("Something went wrong");
	}
}

function DashboardResponse(){

	var singleGrid;
	var outStandarray;
	var itemRatioarray;
if(requestOBJ.readyState==4){
if(requestOBJ.status==200){
var responsetext= requestOBJ.responseText;
responsetext= responsetext.replace("<xml>","");
debugger;
if(responsetext.indexOf("~")>0){
	debugger;
	var outstandingServerResData =[];
	var itemsRatioServerResData =[];
		if(responsetext.split("|")[0] != null){
			outstandingServerResData =responsetext.split("|")[0];
			}
		if(responsetext.split("|")[1] != null){
			itemsRatioServerResData = responsetext.split("|")[1];
		}
	//var finalResponse = responsetext.substring(0, responsetext.lastIndexOf('#'));
		outStandarray = outstandingServerResData.split("#");
		itemRatioarray =itemsRatioServerResData.split("#");
	for(var i=0; i<outStandarray.length;i++){
		OutstandingData.push([outStandarray[i].split('~')[1],parseInt(outStandarray[i].split('~')[2])]);		
	}
	debugger;
	for(var j=0; j<itemRatioarray.length;j++){
		if(itemRatioarray[j].split('~')[0].trim().length > 0 && itemRatioarray[j].split('~')[1].trim().length > 0){item_Sales_Ration.push({"name" : itemRatioarray[j].split('~')[0],"y":parseInt(itemRatioarray[j].split('~')[1])});}
	}
	debugger;
	console.log("OutstandingData--->"+OutstandingData);
	Loading();
}
}
}	
}






function Loading() {
Highcharts.chart('bar-chart', {
    chart: {
        type: 'column'
    },
	 title: {
        text: 'Outstanding Amount By customers'
    },
    xAxis: {
        type: 'category',
        labels: {
            rotation: -45,
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Outstanding Amount (in Rupees)'
        }
    },
    legend: {
        enabled: false
    },
    tooltip: {
        pointFormat: 'Amount Due: <b>Rs.{point.y:.1f}</b>'
    },
    series: [{
        name: 'Population',
        data : OutstandingData,
        dataLabels: {
            enabled: true,
            rotation: -90,
            color: '#FFFFFF',
            align: 'right',
            format: '{point.y:.1f}', // one decimal
            y: 10, // 10 pixels down from the top
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    }]
    
});


/*Pie Chart*/

Highcharts.chart('pie-chart', {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    },
    title: {
        text: 'Item wise Sales Ratio'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                style: {
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                }
            }
        }
    },
    series: [{
        name: 'Sales',
        colorByPoint: true,
    	data : item_Sales_Ration
    }]
});

/*line-chart*/

Highcharts.chart('line-chart', {

    title: {
        text: 'Total Revenue'
    },
    yAxis: {
        title: {
            text: 'Amount in Rupees'
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
            pointStart: 2010
        }
    },

    series: [{
        name: 'Chicken',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }, {
        name: 'Mutton',
        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
    }, {
        name: 'Fish',
        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
    }, {
        name: 'Egg',
        data: [12562, 15896, 7988, 12169, 15112, 22452, 34400, 34227]
    }],

    responsive: {
        rules: [{
            condition: {
                maxWidth: 500
            },
            chartOptions: {
                legend: {
                    layout: 'horizontal',
                    align: 'center',
                    verticalAlign: 'bottom'
                }
            }
        }]
    }

});
}


function generatebackup(){
	 if(navigator.onLine)
    {
        alert("Browser is online");
        triggerEmail();
    }
    else
    {
        alert("Browser is offline");
    }
}

function triggerEmail(){
	var URL = gloablContextURL+"/Dashboard?Event=MAILTRIGGER";
	if(window.XMLHttpRequest){
	requestOBJ = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		requestOBJ= new ActivXCObject("Microsoft.XMLHTTP");
	}
	try{
	requestOBJ.onreadystatechange=triggerEmailResponse;
	requestOBJ.open("POST",URL,true);
	requestOBJ.setRequestHeader("Content-type", "text/xml");
	requestOBJ.send();
	}catch(e){
	alert("Something went wrong");
	}
}

function triggerEmailResponse(){
	
	
}