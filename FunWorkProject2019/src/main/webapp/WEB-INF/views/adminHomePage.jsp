<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
<title>基本資訊</title>
</head>
<style>

</style>
<body>
	<%@ include file="/WEB-INF/views/includes/adminnavbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
                <jsp:include page="/WEB-INF/views/includes/adminSideBar.jsp"></jsp:include>
            </div>
			<div class="col-sm-8">
                <h1 id="jobCount"></h1>
                <div id="canvas-holder">
                    <canvas id="chart-area"></canvas>
                </div>
                <hr>
                <h1>會員加值統計</h1>
                <p id="test"></p>
                <div class="row">
                <div class="col-sm-6">
                <div id="canvas-holder">
                    <canvas id="chart-area2"></canvas>
                </div>
                </div>
                <div class="col-sm-6">
                <div id="canvas-holder">
                    <canvas id="chart-area3"></canvas>
                </div>
                </div>
                </div>
			</div>      
			<div class="col-sm-2">/div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script>
    var jobType = ${jobTypeJson}; 
    
    var jobCountArray = [];
    for(var i = 0;i < jobType.length;i++){
    	jobCountArray[i] = jobType[i][1];
    }
    
    var jobTypeArray = [];
    for(var i = 0;i < jobType.length;i++){
    	jobTypeArray[i] = jobType[i][0];
    }
    
    var orderByMouth = ${orderByMouthJson};
    var dateArray = [];
    for(var i = 0;i < orderByMouth.length;i++){
    	dateArray[i] = orderByMouth[i][0] + "年" + orderByMouth[i][1] + "月";
    }
    
    var orderArray = [];
    for(var i = 0;i < orderByMouth.length;i++){ 
    	orderArray[i] = orderByMouth[i][2];       
    }
    
    var priceArray = [];
    for(var i = 0;i < orderByMouth.length;i++){    
    	priceArray[i] = orderByMouth[i][3];
    }
       
	var config = {
	          type: 'pie',
	          data: {
	              datasets: [{
	                  data: jobCountArray,
	                  backgroundColor: [
	                      "red",
	                      "orange",
	                      "yellow",
	                      "green",
	                      "blue",
	                      "YellowGreen",
	                      "Violet",
	                      "Sienna",
	                      "Magenta",
	                      "SkyBlue",
	                  ],
	              }],
	              labels: jobTypeArray
	          },
	          options: {
	              responsive: true,
	          }
	      };
	
	var barChartData  = {
            labels: dateArray,
            datasets: [{
                label: '加值會員數',
                backgroundColor: "red",
                borderColor: "red",
                borderWidth: 1,
                data: orderArray
            }]
        };
	
	var barChartData2  = {
            labels: dateArray,
            datasets: [{
                label: '總收益',
                backgroundColor: "blue",
                borderColor: "blue",
                borderWidth: 1,
                data: priceArray
            }]
        };
	
		window.onload = function() {
		       var ctx = document.getElementById('chart-area').getContext('2d');
		       var ctx2 = document.getElementById('chart-area2').getContext('2d');
		       var ctx3 = document.getElementById('chart-area3').getContext('2d');
		       window.myPie = new Chart(ctx, config);
		       window.myBar  = new Chart(ctx2, {
		    	    type: 'bar',
	                data: barChartData ,
	                options: {
	                	responsive: true,
	                    legend: {
	                        position: 'top',
	                    },
	                    scales: {
	                        yAxes: [{
	                                display: true,
	                                ticks: {
	                                    beginAtZero: true
	                                }
	                            }]
	                    },
	                }
		       });
		       window.myBar  = new Chart(ctx3, {
                   type: 'bar',
                   data: barChartData2 ,
                   options: {
                       responsive: true,
                       legend: {
                           position: 'top',
                       }           
                   }
              });
		   };
		   
		$(function() {
			$.ajax({
		        url : "${pageContext.request.contextPath}/getAllJobPostingCount",
		        type : "GET",
		        success : function(data) {
		            $("#jobCount").html("目前發布中的職缺總數：" + data + "件");
		        }
			});    
		});
	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>