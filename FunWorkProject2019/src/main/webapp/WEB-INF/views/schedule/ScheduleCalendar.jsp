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
<link href="<c:url value='/css/fullcalendar.print.css'/>" rel="stylesheet" media='print' />
<link rel="stylesheet" type="text/css" href="<c:url value='/DataTables/datatables.min.css/'></c:url>">


<title>排班</title>
<script>
var delCount = [];
$(document).ready(function() {
	$("#DateSearch").click(function(){
		var time1 = $("#time1").val();
		var time2 = $("#time2").val();
 		if(time1!="" && time2!=""){
		document.location.href="<c:url value='/ScheduleCalendar/'/>${interviewList[0].application.job.jobId}/"+time1+"/"+time2;
 		}
 		else{
 			alert("請選擇起訖日期！")
 		}
	});
	
	$("#collapse5").addClass("show"); //讓排班管理載入此頁面時便展開
	$("#jobtable").DataTable();       //DataTable
	$("#saveEvent").click(function(){
		saveEvent();
	});
	
	<c:if test="${change!=null}">
	$('#external-events .fc-event').each(function() {
	    // store data so the calendar knows to render an event upon drop
	    $(this).data('event', {
	      title: $.trim($(this).text()), // use the element's text as the event title
	      stick: true , // maintain when user navigates (see docs on the renderEvent method)
	      start:"08:00",
	      end:"17:00",
	      allDay: false
	    });

	    // make the event draggable using jQuery UI
	    $(this).draggable({
	      zIndex: 999,
	      revert: true,      // will cause the event to go back to its
	      revertDuration: 0  //  original position after the drag
	    });

	  });
	</c:if>
	<c:if test="${jobs==null}">	
	$('#calendar').fullCalendar({
		//中文化
		buttonText: {
	        today: "今天",
	        month: "月",
	        week: "周",
	        day: "日"
	    },
	    
	    monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	    monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	    dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
	    dayNamesShort: ["日", "一", "二", "三", "四", "五", "六"],
		
		defaultView : 'agendaWeek',
		header : {
			left : 'prev,next',
			center : 'title,addEventButton',
			right : 'month,agendaWeek,agendaDay'
		},
		<c:if test="${change!=null}">
		editable: ${change},   //是否可拖曳
		</c:if>
		eventLimit: true, // when too many events in a day, show the popover
		events : <c:out value="${json}" escapeXml="false">${json}</c:out>,
		timeFormat: "HH:mm",      // 所有事件24小時制
		businessHours: true,      //顯示左側時間
		slotLabelFormat:"HH:mm",  //左側時間24小時制 
		<c:if test="${change!=null}">
		eventMouseover:function(event,domEvent,view){

		    var el=$(this);

		    var layer='<div id="events-layer" class="fc-transparent"><span id="delbut'+event.id+'" class="btn btn-primary trash btn-xs">刪除</span></div>';
		    el.append(layer);

		    el.find(".fc-bg").css("pointer-events","none");

		    $("#delbut"+event.id).click(function(){
		    	$('#calendar').fullCalendar('removeEvents', event._id);
		    	delCount.push(event.id);
		    });
		},
		
		eventMouseout:function(event){
		    $("#events-layer").remove();
		},
		</c:if>
		droppable: true, // this allows things to be dropped onto the calendar
	    drop: function() {
	      // is the "remove after drop" checkbox checked?
	      if ($('#drop-remove').is(':checked')) {
	        // if so, remove the element from the "Draggable Events" list
	        $(this).remove();
	      }
	    }
	});
</c:if>
});

function saveEvent(){
	var scheduleJSON =[];
	var scheduleArray = $('#calendar').fullCalendar("clientEvents");
// 	console.log(scheduleArray[0])
// alert(delCount)
	var delString = delCount.toString();
	
	for(i=0;i<scheduleArray.length;i++){
// 		alert(scheduleArray[i].id)
		var id = scheduleArray[i].id
		var title = scheduleArray[i].title
		var start = scheduleArray[i].start
		var end = scheduleArray[i].end
		var json = {"scheduleId":id,"scheduleName":title,"startTime":start,"endTime":end}
// 		scheduleJSON = scheduleJSON+JSON.stringify(json);
		scheduleJSON.push(json);
	}
	scheduleJSONArray = JSON.stringify(scheduleJSON);
	
	$.ajax({
		url:"<c:url value='/ScheduleCalendar/save/'/>${interviewList[0].application.job.jobId}",
		type:"POST",
		dataType:"JSON",
		data:{"scheduleJSONArray":scheduleJSONArray,"delString":delString},
		success:function(data){
			window.alert("儲存成功");
			window.location.replace("<c:url value='/ScheduleCalendar/'/>${interviewList[0].application.job.jobId}")
		},
		error:function(data){
			window.alert("儲存失敗，請檢查上下班起訖時間有無正確填寫。");
		}
		
	});
// 	window.alert($('#calendar').fullCalendar("clientEvents"))
// 	$("#calendar").fullCalendar("removeEvents",$("#eventId").val())
}
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

.fc-event{
background-color:#8ED3F4;
border:none;
color:black;
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
						<div id='external-events'>
							<p>
								<strong>員工名單</strong>
							</p>
							<c:if test="${empty interviewList}"><h3>此工作尚無錄取者</h3></c:if>
							<c:forEach var="interviewList" items="${interviewList}">
							<div class='fc-event'>${interviewList.application.user.userName}</div>
							</c:forEach>
<!-- 							<p> -->
<!-- 								<input type='checkbox' id='drop-remove' /> <label -->
<!-- 									for='drop-remove'>remove after drop</label> -->
<!-- 							</p> -->
						</div>
					</div>
					<div class="col-sm-10">
					<c:if test="${jobs!=null}">	
					<table class="table table-hover dataTable" id="jobtable">
					<thead>
						<tr>
							<th>職缺名稱</th>
							<th>所在地區</th>
							<th>所屬公司</th>	
							<th>刊登狀態</th>					
							<th>詳細內容</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="job" items="${jobs}">
							<c:if test="${job.reviewStatus=='發布中'}">
								<tr>
									<td>${job.title}</td>
									<td>${job.city.cityName}</td>
									<td>${job.jobCompany.name}</td>
									<td>${job.reviewStatus}</td>								
									<td><a href="<c:url value='/ScheduleCalendar/${job.jobId}'/>"
										class="btn btn-primary"><span
											class="glyphicon-info-sigh glyphicon"></span>排班作業 </a></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				</c:if>
				<c:if test="${empty jobs}">			
						<div id='calendar-container'>
						輸入欲查詢的區間：<input type="Date" required="required" id="time1">~<input type="Date" required="required" id="time2">
						<input type="button" id="DateSearch" value="查詢">
						<h3>${interviewList[0].application.job.title}</h3>
							<div id='calendar'></div>
							<div style="float: right">
								<c:if test="${empty change && interviewList.size()!=0}">
									<a class="btn btn-primary"
										href="<c:url value='/ScheduleCalendar/change/'/>${interviewList[0].application.job.jobId}"><span
										class="glyphicon-info-sigh glyphicon"></span>編輯</a>
								</c:if>
								<c:if test="${change!=null}">
									<a class="btn btn-primary" id="saveEvent"><span
										class="glyphicon-info-sigh glyphicon"></span>儲存</a>
								</c:if>
							</div>
							<!-- 				<input type="button" onclick="saveEvent()" value="TEST"> -->
						</div>
						</c:if>	
					</div>
				</div>

			</div>

		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>

	<script type="text/javascript" src="<c:url value='/js/moment.min.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value='/js/fullcalendar.min.js'/>"></script>
	<script src='https://code.jquery.com/ui/1.11.3/jquery-ui.min.js'></script>
<script type="text/javascript"
		src="<c:url value='/DataTables/datatables.min.js/'></c:url>"></script>
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