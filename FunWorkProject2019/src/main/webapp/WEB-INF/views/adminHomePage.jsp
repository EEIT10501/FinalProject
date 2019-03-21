<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>基本資訊</title>
</head>
<style>
.card-text-size {
	font-size: 14px;
}

.footerbackground {
	background: #343a40;
	color: white;
}

.nav-item:hover {
	background-color: gray;
	border-radius: 15px;
}

.asideblock {
	height: 600px;
}
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
			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script>
    var jobType = ${jobTypeJson}; 
	var config = {
	          type: 'pie',
	          data: {
	              datasets: [{
	                  data: [
	                  	jobType[9][1],
	                  	jobType[2][1],
	                  	jobType[7][1],
	                  	jobType[8][1],
	                  	jobType[3][1],
	                  	jobType[0][1],
	                  	jobType[5][1],
	                  	jobType[4][1],
	                  	jobType[6][1],
	                  	jobType[1][1]
	                  ],
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
	                  ]
	              }],
	              labels: [
	                  '餐飲',
	                  '服務',
	                  '銷售',
	                  '辦公',
	                  '活動',
	                  '作業員',
	                  '勞力',
	                  '問券調查',
	                  '補教',
	                  '其他'
	              ]
	          },
	          options: {
	              responsive: true
	          }
	      };
	
		window.onload = function() {
		       var ctx = document.getElementById('chart-area').getContext('2d');
		       window.myPie = new Chart(ctx, config);
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
</body>
</html>