<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link type="text/css" rel="stylesheet"
	href="/FunWorkProject2019/css/jquery.dataTables.min.css"
	media="screen, projection" />

<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/jquery.fancybox-1.3.4.css"
	media="screen, projection" />

<link type="text/css" rel="stylesheet"
	href="/FunWorkProject2019/css/jquery.timepicker.css"
	media="screen, projection" />

<script type="text/javascript"
	src='<c:url value="/js/jquery-3.3.1.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/js/jquery.dataTables.min.js"/>'></script>
<script type='text/javascript'>
	var jQuery132 = jQuery.noConflict(true);
</script>
<script type="text/javascript"
	src='<c:url value="/js/jquery-1.7.1.min.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/js/jquery.fancybox-1.3.4.pack.js"/>'></script>

	
<script type="text/javascript"
	src='<c:url value="/js/jquery.timepicker.js"/>'></script>	
<script type="text/javascript"
	src='<c:url value="/js/datepair.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/js/jquery.datepair.js"/>'></script>

<script type="text/javascript">
	jQuery132(document).ready(function() {
		jQuery132('#example').DataTable({
			"language" : {
				"lengthMenu" : "Display _MENU_ records per page",
				"zeroRecords" : "Nothing found - sorry",
				"info" : "Showing page _PAGE_ of _PAGES_",
				"infoEmpty" : "No records available",
				"infoFiltered" : "(filtered from _MAX_ total records)"
			}
		});
	});

	
</script>

<title>班別管理</title>
</head>

<body>

	<div class="" style="background-color: #FFFFCC">
		<h2>班別管理</h2>
		<div class="" style="background-color: #CCFFFF">
			<a href='<c:url value="/addSchedule"/>' title="新增班別"
				 id="addSchedule">新增班別</a>
		</div>
	</div>
	<table id="example" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>班別代碼</th>
				<th>班別名稱</th>
				<th>識別色</th>
				<th>起始時間</th>
				<th>結束時間</th>
				<th>休息時間</th>
				<th>工時</th>
				<th>編輯</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="schedule" items="${schedules}">
				<tr>
					<td>${schedule.scheduleId}</td>
					<td>${schedule.scheduleName}</td>
					<td><div width="20" height="20"
							style="width: 20px; height: 20px; border: 1px solid #000000; background-color: ${schedule.color}; margin: auto;">&nbsp;</div></td>
					<td><fmt:formatDate value="${schedule.startTime}" pattern="HH:mm"/></td>
					<td><fmt:formatDate value="${schedule.endTime}" pattern="HH:mm"/></td>					
					<td>${schedule.restHour}</td>
					<td>${schedule.workingHours}</td>
					<td><a   
						href="<spring:url value='/updateSchedule?scheduleId=${schedule.scheduleId}' />"
						title="編輯" class="image edit span-1" id="updateSchedule"><img src='<c:url value="/image/edit.png"/>' title="編輯" alt="編輯" width="20px"></a>
						<a
						href="<spring:url value='/deleteSchedule?scheduleId=${schedule.scheduleId}' />"
						title="刪除" class="image delete span-1" id="delete"><img src='<c:url value="/image/delete.png"/>' title="刪除" alt="刪除" width="22px"></a>
						</td>
				</tr>
<!-- 						<div class="" style="background-color: #CCFFFF"> -->
<%-- 			<a href='<c:url value="/addSchedule"/>' title="新增班別" --%>
<!-- 				class="worker span-2 button" id="addSchedule">新增班別</a> -->
<!-- 		</div> -->
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>班別代碼</th>
				<th>班別名稱</th>
				<th>識別色</th>
				<th>起始時間</th>
				<th>結束時間</th>
				<th>休息時間</th>
				<th>工時</th>
				<th>編輯</th>
			</tr>
		</tfoot>
	</table>



	<script type="text/javascript">
		$(document).ready(function() {
			$("a#addSchedule,a#updateSchedule").live('click', function(e) {
				e.preventDefault();
				$.fancybox(this, {
					'scrolling' : 'no',
					'titleShow' : false,
					'centerOnScroll' : true,
					'autoScale' : false,
					'enableEscapeButton' : true,
					'type' : 'inline'
				});
			});

		})
	</script>
</body>

</html>

</body>
</html>