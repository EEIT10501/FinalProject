<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="zh-TW">
<head>
<title>Calendar Test</title>
<meta charset='UTF-8' />

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
	

<!-- 以下是fullcalendar使用 -->

<link href="<c:url value='/css/fullcalendar.min.css'/>" rel="stylesheet" />
<link href="<c:url value='/css/fullcalendar.print.css'/>" rel="stylesheet" media='print' />
<script type="text/javascript" src="<c:url value='/js/moment.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/fullcalendar.min.js'/>"></script>
<script src='https://code.jquery.com/ui/1.11.3/jquery-ui.min.js'></script>


<script>
var id = 1;
	$(document).ready(function() {
		
		$('#external-events .fc-event').each(function() {

		    // store data so the calendar knows to render an event upon drop
		    $(this).data('event', {
		      title: $.trim($(this).text()), // use the element's text as the event title
		      stick: true , // maintain when user navigates (see docs on the renderEvent method)
		      allDay: true,
		      id:$(this).attr("id")
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
			customButtons: { //新增事件按鈕
			      addEventButton: {
			        text: '陳奕璋',
			        click: function() {
			          var dateStr = prompt('Enter a date in YYYY-MM-DD format');
			          var date = moment(dateStr);

			          if (date.isValid()) {
			            $('#calendar').fullCalendar('renderEvent', {
			            	id:id,
			              title: '陳奕璋:'+id,
			              start: date,
			              allDay: true
			              
			            });
			            id=id+1;
			            alert('Great. Now, update your database...');
			          } else {
			            alert('Invalid date.');
			          }
			        }
			      }
			    },
			<c:if test="${change!=null}">
			editable: ${change},   //是否可拖曳
			</c:if>
			businessHours: true, //顯示小時
			eventLimit: true, // when too many events in a day, show the popover
			events : 'https://fullcalendar.io/demo-events.json',
			eventMouseover:function(event,domEvent,view){

			    var el=$(this);

			    var layer='<div id="events-layer" class="fc-transparent"><span id="delbut'+event.id+'" class="btn btn-primary trash btn-xs">刪除</span></div>';
			    el.append(layer);

			    el.find(".fc-bg").css("pointer-events","none");

			    $("#delbut"+event.id).click(function(){
			    	$('#calendar').fullCalendar('removeEvents', event._id);
			    });
			},
			eventMouseout:function(event){
			    $("#events-layer").remove();
			},
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
	
	function deleteEvent(){
		
		$("#calendar").fullCalendar("removeEvents",$("#eventId").val())
	}
	
</script>
<style>
html, body {
	margin: 0;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
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

.btn{
margin-right:5px;
}

</style>
</head>
<body>
	<h2>Test Calendar</h2>
	<div id='external-events'>
  <p>
    <strong>Draggable Events</strong>
  </p>
  <div class='fc-event' id="001">My Event 1</div>
  <div class='fc-event' id="002">My Event 2</div>
  <div class='fc-event' id="003">My Event 3</div>
  <div class='fc-event' id="004">My Event 4</div>
  <div class='fc-event' id="005">My Event 5</div>
  <p>
    <input type='checkbox' id='drop-remove' />
    <label for='drop-remove'>remove after drop</label>
  </p>
</div>

<div id='calendar-container'>
  <div id='calendar'></div>
</div>
<!-- <div> -->
<!-- <input type="text" id="eventId"><input type="button" onclick="deleteEvent()" value="刪除"> -->
<!-- </div> -->
<a class="btn btn-primary" href="<c:url value='/calendarTest/change'/>"><span class="glyphicon-info-sigh glyphicon"></span>編輯</a>
<a class="btn btn-primary" href="<c:url value='/calendarTest'/>"><span class="glyphicon-info-sigh glyphicon"></span>儲存</a>
</body>
</html>

