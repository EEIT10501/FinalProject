<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>

<!-- 以下是fullcalendar使用 -->

<link href="<c:url value='/css/fullcalendar.min.css'/>" rel="stylesheet" />
<link href="<c:url value='/css/fullcalendar.print.css'/>"
	rel="stylesheet" media='print' />


<title>班表</title>
<script>
var delCount = [];
$(document).ready(function() {	

		$("#DateSearch").click(function(){
			var time1 = $("#time1").val();
			var time2 = $("#time2").val();
	 		if(time1!="" && time2!=""){
			document.location.href="<c:url value='/UserSchedule/Date/'/>"+time1+"/"+time2;
	 		}
	 		else{
	 			alert("請選擇起訖日期！")
	 		}
		});
	
// 	$("#collapse5").addClass("show"); //讓排班管理載入此頁面時便展開
	$("#saveEvent").click(function(){
		saveEvent();
	});

	$('#calendar').fullCalendar({
		defaultView : 'month',
		header : {
			left : 'prev,next',
			center : 'title,addEventButton',
			right : 'month,agendaWeek,agendaDay'
		},
		eventLimit: true, // when too many events in a day, show the popover
		events : <c:out value="${json}" escapeXml="false">${json}</c:out>,
		timeFormat: "HH:mm",      // 所有事件24小時制
		businessHours: true,      //顯示左側時間
		slotLabelFormat:"HH:mm",  //左側時間24小時制 
	});

});

</script>
<style>
.card-text-size {
	font-size: 14px;
}

.footerbackground {
	background: #343a40;
	color: white;
}

.asideblock {
	height: 600px;
}

#external-events {
	float: left;
	width: 100px;
	padding: 0 10px;
	border: 1px solid #ccc;
	background: #eee;
}

#external-events .fc-event {
	margin: 1em 0;
	cursor: move;
}

#calendar-container {	
 	z-index: 1; 
}

#calendar {
	margin: 10px;
}

.btn {
	margin-right: 5px;
}
</style>
</head>

<body>

	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>

	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2">
			<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp" %>
			</div>

			<div class="col-sm-10 align-self-center">
				<div class="row m-3 justify-content-around">
					<div class="col-sm-2">
					</div>
					<div class="col-sm-10">				
						
						<div id='calendar-container'>
						輸入欲查詢的區間：<input type="Date" required="required" id="time1">~<input type="Date" required="required" id="time2">
						<input type="button" id="DateSearch" value="查詢">
						<h3>${interviewList[0].application.job.title}</h3>
							<div id='calendar'></div>
							
							
						</div>
						
					</div>
				</div>

			</div>

		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>

	<script type="text/javascript" src="<c:url value='/js/moment.min.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value='/js/fullcalendar.min.js'/>"></script>
	<script src='https://code.jquery.com/ui/1.11.3/jquery-ui.min.js'></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>