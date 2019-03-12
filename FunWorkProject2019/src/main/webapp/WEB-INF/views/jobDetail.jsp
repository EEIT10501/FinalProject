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


<title>詳細工作頁面</title>
<script>
	$(document).ready(function() {
		$(".addapplication").click(function() {
			var que = $("#question").val();
			if(${resumeBean.user.userId}==${jobBean.jobOwner.userId})
				alert("請勿應徵您自己刊登的工作");
				
			else if(que!="")
				$.ajax({
					url : "<c:url value='/insertApplication/${resumeBean.user.userId}/${jobBean.jobId}/"+que+"/'></c:url>",
					type : "GET",
					success : function(data) {
						window.alert("投遞履歷成功!");
						notification()
						$(".cancel").trigger("click");
					}
					});
			
			else
			window.alert("請回答問題!");
			
			
			
			});
		});
	
	function notification(){
		$.ajax({
			url : "<c:url value='/insertNotification/${resumeBean.user.userId}/${jobBean.jobOwner.userId}'></c:url>",
			type : "GET",
			success : function(data) {
				window.alert("敬請留意廠商通知喔!");
			}
			});
	}
</script>
<style>
.footerbackground {
	background: #343a40;
	color: white;
}

.asideblock {
	height: 600px;
}

.showjobdetail h5 {
	font-weight: bolder;
}

.showjobdetail h3 {
	font-weight: 900;
}
</style>
</head>

<body>
	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
				<div class="list-group">
					<a href="#" class="list-group-item list-group-item-action">基本資訊</a>
					<a href="#" class="list-group-item list-group-item-action">工作管理</a>
					<a href="#" class="list-group-item list-group-item-action">邀約管理</a>
					<a href="#" class="list-group-item list-group-item-action">公司管理</a>
					<a href="#" class="list-group-item list-group-item-action">加值服務</a>
					<a href="#" class="list-group-item list-group-item-action">黃金會員</a>
					<a href="#" class="list-group-item list-group-item-action">訂單管理</a>
					<a href="#" class="list-group-item list-group-item-action">優惠兌換</a>
				</div>
			</div>
			<div class="col-sm-6 showjobdetail">
				<!--             程式寫在這 -->
				<div class="row">
					<div class="col-sm-12">
						<h3>${jobBean.title}</h3>
						<p>時薪：${jobBean.rateByHour}</p>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>工作內容</h5>
					</div>
					<div class="col-sm-5">${jobBean.description}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>工作時間</h5>
					</div>
					<div class="col-sm-5">
						<c:forEach var="schedules" items="${schedules}">
							<h6>${schedules.workDate}</h6>
				${schedules.startTime}-${schedules.endTime}
				</c:forEach>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>需求人數</h5>
					</div>
					<div class="col-sm-5">${jobBean.positionNum}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>給薪日期</h5>
					</div>
					<div class="col-sm-5">${jobBean.paidDate}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>其他條件</h5>
					</div>
					<div class="col-sm-5">${jobBean.other}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>工作地點</h5>
					</div>
					<div class="col-sm-5">${jobBean.city.cityName}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>詳細地址</h5>
					</div>
					<div class="col-sm-5">${jobBean.address}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>地點說明</h5>
					</div>
					<div class="col-sm-5">${jobBean.addresssup}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>聯絡資訊</h5>
					</div>
					<div class="col-sm-5">
						<p>聯絡人：${jobBean.contact}</p>
						<p>手機：${jobBean.jobPhone}</p>
						<p>信箱：${jobBean.jobEmail}</p>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>所屬公司</h5>
					</div>
					<div class="col-sm-5">${jobBean.jobCompany.name}</div>
				</div>

				<div class="row justify-content-center">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#resumeModal">我要應徵</button>
				</div>

				<div class="modal fade" id="resumeModal" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">我的履歷</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<!-- 彈出視窗：寫程式的地方 -->
								<c:if test="${empty resumeBean}">
									<h5 style="color: red">請先登入系統</h5>
								</c:if>
								<table class="table table-striped">
									<tbody>
										<tr>
											<td>${resumeBean.profilePic}</td>
											<td></td>
										</tr>
										<tr>
											<td>姓名</td>
											<td>${resumeBean.user.userName}</td>
										</tr>
										<tr>
											<td>E-mail</td>
											<td>${resumeBean.user.email}</td>
										</tr>
										<tr>
											<td>手機</td>
											<td>${resumeBean.phoneNum}</td>
										</tr>
										<tr>
											<td>生日</td>
											<td>${resumeBean.birth}</td>
										</tr>
										<tr>
											<td>教育程度</td>
											<td>${resumeBean.educationLevel}</td>
										</tr>
										<tr>
											<td>自我介紹</td>
											<td>${resumeBean.selfIntro}</td>
										</tr>
										<tr>
											<td>${jobBean.other}</td>
											<td><textarea class="form-control" id="question"
													name="question" rows="3"></textarea></td>
										</tr>

									</tbody>
								</table>
							</div>
							<c:if test="${resumeBean!=null}">
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary cancel"
										data-dismiss="modal">取消</button>

									<button type="button" class="btn btn-primary addapplication">送出</button>

								</div>
							</c:if>
						</div>
					</div>
				</div>

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