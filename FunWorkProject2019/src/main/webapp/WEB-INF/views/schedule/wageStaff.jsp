<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css" />

<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/bootstrap-datetimepicker.css"/>'
	media="screen, projection" />


<title>薪資概算</title>

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


<body>
	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<!-- 複製這裡↓ -->
			<div class="col-sm-2">
				<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			</div>
			<!-- 複製這裡 ↑ -->
			<div class="col-sm-8">
				<h2>打工薪資試算</h2>
				<hr>

				<!--		日期篩選條件			-->
				<c:if test="${empty staffSchedules}">
					<h5>
						您目前尚未錄取任何工作，請先至<a href="<c:url value='/jobs'></c:url>">工作專區</a>應徵工作。
					</h5>

				</c:if>
				<c:if test="${!empty staffSchedules}">
					<form action="${pageContext.request.contextPath}/selectWageStaff"
						method="post">
						<div>
							<!-- 						<span style="padding-left: 10px">請輸入您所刊登的職缺： <select name="jobId"> -->
							<%-- 								<c:forEach var="postJob" items="${postJobList}"> --%>
							<%-- 									<option value="${postJob.jobId}">${postJob.title}</option> --%>
							<%-- 								</c:forEach> --%>
							<!-- 						</select></span> <br>  -->
							<span style="padding-left: 10px"> 請輸入欲查詢的月份：</span> <span
								class="input-group  col-md-6" data-date-format="yyyy-mm-dd">
								<input type="text" class="form-control" name="start"
								id="qBeginTime" readonly /> <span>~ </span> <input type="text"
								class="form-control" name="end" id="qEndTime" readonly /> <span><input
									class="btn btn-primary" style="margin-left: 20px" type="submit"
									value="查詢"></span>
							</span>
							<!-- -------------- -->

							<!-- -------------- -->

						</div>
					</form>
					<!--		薪資資料表			-->
					<section
						style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
						<table class="table table-hover display" id="example">
							<thead>
								<tr>
									<th>姓名</th>
									<th>日期</th>
									<th>起時</th>
									<th>訖時</th>
									<th>休息時數</th>
									<th>時薪</th>
									<th>每日工時</th>
									<th>每日小計</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="admitSchedule" items="${staffScheduleList}">
									<c:set var="hrTotal"
										value="${hrTotal+admitSchedule.workingHours}"></c:set>
									<c:set var="wageTotal"
										value="${hrTotal*admitSchedule.interview.application.job.rateByHour}"></c:set>
									<tr>
										<td>${admitSchedule.scheduleName}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd"
												value="${admitSchedule.startTime}" /></td>
										<td><fmt:formatDate value="${admitSchedule.startTime}"
												pattern="HH:mm" /></td>
										<td><fmt:formatDate value="${admitSchedule.endTime}"
												pattern="HH:mm" /></td>
										<td>${admitSchedule.restHour}</td>
										<td>${admitSchedule.interview.application.job.rateByHour}</td>
										<td>${admitSchedule.workingHours}</td>
										<td><fmt:formatNumber
												value="${admitSchedule.workingHours*admitSchedule.interview.application.job.rateByHour}"
												type="currency" /></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>總計</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th>${hrTotal}</th>
									<th><fmt:formatNumber value="${wageTotal}" type="currency" /></th>
								</tr>
							</tfoot>
						</table>
						<table class="table table-hover display" id="testField">
						</table>
					</section>
					</c:if>
			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
</div>
	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>

	<script type="text/javascript" src='<c:url value="/js/bootstrap.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/js/bootstrap-datetimepicker.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/js/bootstrap-datetimepicker.zh-TW.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/js/moment.min.js"/>'></script>

	<script type="text/javascript">

		$('#qBeginTime').datetimepicker({
			language : "zh-TW",
			format : "yyyy-mm-dd",
			minView: "month", 
			todayBtn : "linked",
			autoclose : true,
			todayHighlight : true,
			endDate : new Date()
		}).on('changeDate', function(e) {
// 			var startTime = e.date;
// 			$('#qEndTime').datetimepicker('setStartDate',2019-03-20);
		});
		

// 		$('#qBeginTime').change(function(){
// 			var end = $('#qBeginTime').val();
// 			alert(end);
// 			$('#qEndTime').datetimepicker('setStartDate', '2019-03-20');
// 		});
		
		//结束时间： 
		$('#qEndTime').datetimepicker({
			language : "zh-TW",
			format : "yyyy-mm-dd",
			minView: "month",
			todayBtn : "linked",
			autoclose : true,
			todayHighlight : true,
// 			startDate:'2019-03-20',
			endDate : new Date()
		});
	</script>

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