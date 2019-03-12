<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset='UTF-8' />

<link type="text/css" rel="stylesheet"
	href="/FunWorkProject2019/css/fullcalendar.css"
	media="screen, projection" />
<link type="text/css" rel="stylesheet"
	href="/FunWorkProject2019/css/fullcalendar.print.css" media='print' />
<link type="text/css" rel="stylesheet"
	href="/FunWorkProject2019/css/scheduler.css" />
<script type="text/javascript" src='<c:url value="/js/moment.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.min.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/js/fullcalendar.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/scheduler.js"/>'></script>
<script src='https://code.jquery.com/ui/1.11.3/jquery-ui.min.js'></script>

<script>
	$(function() { // document ready
		
		$('#external-events .fc-event').each(function() {

			// store data so the calendar knows to render an event upon drop
			$(this).data('event', {
				title : $.trim($(this).text()), // use the element's text as the event title
				stick : true
			// maintain when user navigates (see docs on the renderEvent method)
			});

			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex : 999,
				revert : true, // will cause the event to go back to its
				revertDuration : 0
			//  original position after the drag
			});

		$('#calendar')
				.fullCalendar(
						{
							now : '2018-04-07',
							editable : true,
							aspectRatio : 1.8,
							scrollTime : '00:00',
							header : {
								left : 'today prev,next',
								center : 'title',
								right : 'timelineDay,timelineTenDay,timelineMonth,timelineYear,timelineMonth'
							},
							editable : true,
							droppable : true, // this allows things to be dropped onto the calendar
							drop : function() {
								// is the "remove after drop" checkbox checked?
								if ($('#drop-remove').is(':checked')) {
									// if so, remove the element from the "Draggable Events" list
									$(this).remove();
								}
							},
							defaultView : 'month',
							views : {
								timelineDay : {
									buttonText : ':15 slots',
									slotDuration : '00:15'
								},
								timelineTenDay : {
									type : 'timeline',
									duration : {
										days : 10
									}
								}
							},
							navLinks : true,
							resourceAreaWidth : '25%',
							resourceLabelText : 'Rooms',
							resources : [ {
								id : 'a',
								title : 'Auditorium A'
							}, {
								id : 'b',
								title : 'Auditorium B',
								eventColor : 'green'
							}, {
								id : 'c',
								title : 'Auditorium C',
								eventColor : 'orange'
							}, {
								id : 'd',
								title : 'Auditorium D'
							}, {
								id : 'e',
								title : 'Auditorium E'
							}, {
								id : 'f',
								title : 'Auditorium F',
								eventColor : 'red'
							}, {
								id : 'z',
								title : 'Auditorium Z'
							} ],
							events : [ {
								id : '1',
								resourceId : 'b',
								start : '2018-04-07T02:00:00',
								end : '2018-04-07T07:00:00',
								title : 'event 1'
							}, {
								id : '2',
								resourceId : 'c',
								start : '2018-04-07T05:00:00',
								end : '2018-04-07T22:00:00',
								title : 'event 2'
							}, {
								id : '3',
								resourceId : 'd',
								start : '2018-04-06',
								end : '2018-04-08',
								title : 'event 3'
							}, {
								id : '4',
								resourceId : 'e',
								start : '2018-04-07T03:00:00',
								end : '2018-04-07T08:00:00',
								title : 'event 4'
							}, {
								id : '5',
								resourceId : 'f',
								start : '2018-04-07T00:30:00',
								end : '2018-04-07T02:30:00',
								title : 'event 5'
							} ]
						});
		

	});



	});
</script>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 1024px;
	margin: 50px auto;
}
</style>

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

.demo-topbar+#external-events { /* will get stripped out */
	top: 60px;
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
</style>
</head>
<body>

	<div class='demo-topbar'>
		<button data-codepen class='codepen-button'>Edit in CodePen</button>
		Drag external events into the calendar with the help of jquery-ui draggable
	</div>

	<div id='external-events'>
		<p>
			<strong>Draggable Events</strong>
		</p>
		<div class='fc-event'>My Event 1</div>
		<div class='fc-event'>My Event 2</div>
		<div class='fc-event'>My Event 3</div>
		<div class='fc-event'>My Event 4</div>
		<div class='fc-event'>My Event 5</div>
		<div class='fc-event'>
			<a href="https://shift.ekko.com.tw/#.html"
				class="worker button span-2" id="14140"
				style="background-color: #ffff00" rel="1"> Maxson</a>
		</div>

		<p>
			<input type='checkbox' id='drop-remove' /> <label for='drop-remove'>remove
				after drop</label>
		</p>
	</div>

	<div id='calendar-container'>
		<div id='calendar'></div>
	</div>
</body>
</html>