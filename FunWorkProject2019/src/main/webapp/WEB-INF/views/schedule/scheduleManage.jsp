<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
<link type="text/css" rel="stylesheet" href='<c:url value="/css/jquery.dataTables.min.css"/>'  media="screen, projection" />
<link type="text/css" rel="stylesheet" href='<c:url value="/css/jquery.timepicker.css"/>' media="screen, projection" />
<link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/jquery.fancybox-1.3.4.css" media="screen, projection" />

<!-- <script type='text/javascript'> var jQuery132 = jQuery.noConflict(true); </script> -->

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<%-- <script type="text/javascript" src='<c:url value="/js/jquery-1.7.1.min.js"/>'></script> --%>

<script type="text/javascript" src='<c:url value="/js/jquery.fancybox-1.3.4.pack.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.timepicker.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/datepair.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.datepair.js"/>'></script>

<script type="text/javascript" src='<c:url value="/js/jquery.dataTables.min.js"/>'></script>


<script>
	$(document).ready(function() {
		$.noConflict();
		$('#example').DataTable({
			language: {
				   url: "https://cdn.datatables.net/plug-ins/1.10.16/i18n/Chinese-traditional.json" ,
					"processing":   "處理中...",
					"loadingRecords": "載入中...",
					"lengthMenu":   "顯示 _MENU_ 項結果",
					"zeroRecords":  "沒有符合的結果",
					"info":         "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
					"infoEmpty":    "顯示第 0 至 0 項結果，共 0 項",
					"infoFiltered": "(從 _MAX_ 項結果中過濾)",
					"infoPostFix":  "",
					"search":       "搜尋:",
					"paginate": {
						"first":    "第一頁",
						"previous": "上一頁",
						"next":     "下一頁",
						"last":     "最後一頁"
					},
					"aria": {
						"sortAscending":  ": 升冪排列",
						"sortDescending": ": 降冪排列"
					}
				   
				  },
					"bLengthChange" : false, //改變每頁顯示數據數量
					"bFilter" : false,
					"bInfo" : false,
       	  });
		});
</script>

<title>班別管理</title>
<style>
.card-text-size {
	font-size: 14px;
}

.footerbackground {
	background: #343a40;
	color: white;
}

</style>
</head>

<body>
	
	<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>
<!-- 	<script type='text/javascript'> var jQuery132 = jQuery.noConflict(true); </script> -->
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2">
				<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp" %>
<!-- 					<script type='text/javascript'> var jQuery132 = jQuery.noConflict(true); </script> -->
			</div>
			<div class="col-sm-8">
				<!--             程式寫在這 -->

<div class="" style="background-color: ">
		<h2>班別管理</h2>
		<div class="" style="background-color: ">
			<a href='<c:url value="/addSchedule"/>' title="新增班別" id="addSchedule">新增班別</a>
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
					<td><fmt:formatDate value="${schedule.startTime}"
							pattern="HH:mm" /></td>
					<td><fmt:formatDate value="${schedule.endTime}"
							pattern="HH:mm" /></td>
					<td>${schedule.restHour}</td>
					<td>${schedule.workingHours}</td>
					<td><a
						href="<spring:url value='/updateSchedule?scheduleId=${schedule.scheduleId}' />"
						title="編輯" class="image edit span-1" id="updateSchedule"><img
							src='<c:url value="/image/edit.png"/>' title="編輯" alt="編輯"
							width="20px"></a> <a
						href="<spring:url value='/deleteSchedule?scheduleId=${schedule.scheduleId}' />"
						title="刪除" class="image delete span-1" id="delete"><img
							src='<c:url value="/image/delete.png"/>' title="刪除" alt="刪除"
							width="22px"></a></td>
				</tr>
				<!-- 						<div class="" style="background-color: #CCFFFF"> -->
				<%-- 			<a href='<c:url value="/addSchedule"/>' title="新增班別" --%>
				<!-- 				class="worker span-2 button" id="addSchedule">新增班別</a> -->
				<!-- 		</div> -->
			</c:forEach>
		</tbody>
<!-- 		<tfoot> -->
<!-- 			<tr> -->
<!-- 				<th>班別代碼</th> -->
<!-- 				<th>班別名稱</th> -->
<!-- 				<th>識別色</th> -->
<!-- 				<th>起始時間</th> -->
<!-- 				<th>結束時間</th> -->
<!-- 				<th>休息時間</th> -->
<!-- 				<th>工時</th> -->
<!-- 				<th>編輯</th> -->
<!-- 			</tr> -->
<!-- 		</tfoot> -->
	</table>


<!-- <script type='text/javascript'> var jQuery132 = jQuery.noConflict(true); </script> -->

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

			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>
<!-- 	<script src="https://code.jquery.com/jquery-3.3.1.js" -->
<!-- 		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" -->
<!-- 		crossorigin="anonymous"></script> -->
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