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


<title>排班</title>
<script>
var delCount = [];
$(document).ready(function() {
	$("#saveEvent").click(function(){
		saveEvent();
	});
	
	$('#external-events .fc-event').each(function() {

	    // store data so the calendar knows to render an event upon drop
	    $(this).data('event', {
	      title: $.trim($(this).text()), // use the element's text as the event title
	      stick: true , // maintain when user navigates (see docs on the renderEvent method)
	      allDay: true,
// 	      id:parseInt($(this).attr("id"))
	    });

	    // make the event draggable using jQuery UI
	    $(this).draggable({
	      zIndex: 999,
	      revert: true,      // will cause the event to go back to its
	      revertDuration: 0  //  original position after the drag
	    });

	  });

	$('#calendar').fullCalendar({
		defaultView : 'month',
		header : {
			left : 'prev,next',
			center : 'title,addEventButton',
			right : 'month,agendaWeek,agendaDay'
		},
// 		customButtons: { //新增事件按鈕
// 		      addEventButton: {
// 		        text: '陳奕璋',
// 		        click: function() {
// 		          var dateStr = prompt('Enter a date in YYYY-MM-DD format');
// 		          var date = moment(dateStr);

// 		          if (date.isValid()) {
// 		            $('#calendar').fullCalendar('renderEvent', {
// 		            	id:id,
// 		              title: '陳奕璋:'+id,
// 		              start: date,
// 		              allDay: true
		              
// 		            });
// 		            id=id+1;
// 		            alert('Great. Now, update your database...');
// 		          } else {
// 		            alert('Invalid date.');
// 		          }
// 		        }
// 		      }
// 		    },
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
		url:"<c:url value='/ScheduleCalendar/save'/>",
		type:"POST",
		dataType:"JSON",
		data:{"scheduleJSONArray":scheduleJSONArray,"delString":delString},
		success:function(data){
			window.alert("儲存成功");
			window.location.replace("<c:url value='/ScheduleCalendar/'/>")
		},
		error:function(data){
			window.alert("儲存失敗，請再檢查班表。");
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
	position: fixed;
	z-index: 2;
	top: 20px;
	left: 20px;
	width: 150px;
	padding: 0 10px;
	border: 1px solid #ccc;
	background: #eee;
}

#external-events .fc-event {
	margin: 1em 0;
	cursor: move;
}

#calendar-container {
	position: relative;
	z-index: 1;
	margin-left: 200px;
}

#calendar {
	max-width: 900px;
	margin: 20px auto;
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
		<div class="col-sm-2">預留區塊</div>

			<div class="col-sm-10 align-self-center">
				<!--             程式寫在這 -->
				<div id='external-events'>
					<p>
						<strong>Draggable Events</strong>
					</p>
					<div class='fc-event' >石偉庭</div>
					<div class='fc-event' >鄭揚</div>
					<div class='fc-event' >涂哲賢</div>
					<div class='fc-event' >陳奕璋</div>
					<div class='fc-event' >毛尊佑</div>
					<div class='fc-event' >楊立台</div>
					<p>
						<input type='checkbox' id='drop-remove' /> <label
							for='drop-remove'>remove after drop</label>
					</p>
				</div>

				<div id='calendar-container'>
					<div id='calendar'></div>
					<div style="float:right">
				<c:if test="${empty change}">
				<a class="btn btn-primary" href="<c:url value='/ScheduleCalendar/change'/>"><span class="glyphicon-info-sigh glyphicon"></span>編輯</a>
				</c:if>
				<c:if test="${change!=null}">
				<a class="btn btn-primary" id="saveEvent"><span class="glyphicon-info-sigh glyphicon"></span>儲存</a>
				</c:if>
				</div>
<!-- 				<input type="button" onclick="saveEvent()" value="TEST"> -->
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
<script type="text/javascript" src="<c:url value='/js/fullcalendar.min.js'/>"></script>
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