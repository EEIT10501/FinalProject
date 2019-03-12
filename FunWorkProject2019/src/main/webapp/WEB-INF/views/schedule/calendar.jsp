<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset='UTF-8' />

<link href='/FunWorkProject2019/css/fullcalendar.min.css' rel='stylesheet' />
<link href='/FunWorkProject2019/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<!-- /FunWorkProject2019/image/101.jpg -->
<!-- /FunWorkProject2019/js/moment.min.js -->
<!-- /FunWorkProject2019/css/fullcalendar.min.css -->
<script type="text/javascript" src='<c:url value="/js/moment.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.min.js"/>'></script>
<script type="text/javascript"src='<c:url value="/js/jquery-ui.min.js"/>'></script>
<script type="text/javascript"src='<c:url value="/js/fullcalendar.min.js"/>'></script>



<script>



	$(document).ready(function() {

		$('#calendar').fullCalendar({
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,basicWeek,basicDay'
			},
			defaultDate : '2019-03-04',
			navLinks : true, // can click day/week names to navigate views
			editable : true,
			eventLimit : true, // allow "more" link when too many events
// 			monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
// 			monthNamesShort: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
			dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
			dayNamesShort: ['日','一','二','三','四','五','六'],
			events : [ {
				title : 'All Day Event',
				start : '2019-01-01'
			}, {
				title : 'Long Event',
				start : '2019-01-07',
				end : '2019-01-10'
			}, {
				id : 999,
				title : 'Repeating Event',
				start : '2019-01-09T16:00:00'
			}, {
				id : 999,
				title : 'Repeating Event',
				start : '2019-01-16T16:00:00'
			}, {
				title : 'Conference',
				start : '2019-01-11',
				end : '2019-01-13'
			}, {
				title : 'Meeting',
				start : '2019-01-12T10:30:00',
				end : '2019-01-12T12:30:00'
			}, {
				title : 'Lunch',
				start : '2019-01-12T12:00:00'
			}, {
				title : 'Meeting',
				start : '2019-01-12T14:30:00'
			}, {
				title : 'Happy Hour',
				start : '2019-01-12T17:30:00'
			}, {
				title : 'Dinner',
				start : '2019-01-12T20:00:00'
			}, {
				title : 'Birthday Party',
				start : '2019-01-13T07:00:00'
			}, {
				title : 'Click for Google',
				url : 'http://google.com/',
				start : '2019-01-28'
			} ]
		});

	});
	
	function changeState(dom) {
		if ($(dom).prop("checked")) {
			$('#Form').parent().parent().css('height', '400px');
			$('#Form').parent().css('height', '366px');
			$("#divEndTime").show();
		} else {
			$('#Form').parent().parent().css('height', '330px');
			$('#Form').parent().css('height', '296px');
			$("#divEndTime").hide();
		}
	}
</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;

}
</style>
</head>
<body>
	<h2>calendar</h2>
	<div id='calendar'>calendar</div>



</body>
</html>