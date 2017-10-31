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
        data: [
            ['Mohan', 500],
            ['Mani', 150],
            ['Imad', 680],
            ['Goutham', 50],
            ['Manoj', 1200],
            ['Jackie', 120],
            ['Vikram', 850],
            ['Ajith', 350],
            ['Vijay', 268],
            ['Raghu', 690],
            ['Bagya', 890],
            ['Prabhu', 780],
            ['Boj', 650],
            ['Krishnan',750],
            ['Raja', 960],
            ['Nirmal',1150],
            ['Muthu', 1300],
            ['Violet', 320],
            ['Venkat', 450],
            ['Sabari', 692]
        ],
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
        name: 'Brands',
        colorByPoint: true,
        data: [{
            name: 'Mutton',
            y: 56.33
        }, {
            name: 'Chicken',
            y: 24.03,
            sliced: true,
            selected: true
        }, {
            name: 'Fish',
            y: 10.38
        }]
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