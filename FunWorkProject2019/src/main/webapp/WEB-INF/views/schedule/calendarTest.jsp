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


<script>
var id = 1;
	$(document).ready(function() {

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
			editable: true,   //是否可拖曳
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

#calendar {
	max-width: 900px;
	margin: 40px auto;
}



</style>
</head>
<body>
	<h2>Test Calendar</h2>
	<div id='calendar'></div>
</body>
</html>