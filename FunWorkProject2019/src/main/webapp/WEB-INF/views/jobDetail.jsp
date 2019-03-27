<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBw-HiRWQLCjwq6fWJ-tFBcxECgNjWZZus&callback=initMap" async defer></script>
<title>詳細工作頁面</title>
<script>
	$(document).ready(function() {
		<c:if test="${resumeBean!=null}" >
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
// 						$(".cancel").trigger("click");
						location.reload();
					}
					});		
			else
			window.alert("請回答問題!");			
			});
		</c:if>
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
	
	var map, infoWindow;
	function initMap() {
		map = new google.maps.Map(document.getElementById("map"), {
			center : {
				lat : 25.052,
				lng : 121.532
			},
			zoom : 12
		});

		infoWindow = new google.maps.InfoWindow;

		// Try HTML5 geolocation.
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				var pos = {
					lat : position.coords.latitude,
					lng : position.coords.longitude
				};

				infoWindow.setPosition(pos);
				infoWindow.setContent("我的位置");
				infoWindow.open(map);
				map.setCenter(pos);
			}, function() {
				handleLocationError(true, infoWindow, map.getCenter());
			});
		} else {
			// Browser doesn't support Geolocation
			handleLocationError(false, infoWindow, map.getCenter());
		}
		addMarker();	
		
	}

	function addMarker() {

			var latLng = new google.maps.LatLng(${jobBean.jobLat}, ${jobBean.jobLng});
			var marker${jobBean.jobId} = new google.maps.Marker({
				position : latLng,
				map : map,
				title: "${jobBean.title}"
			});
			
			var contentString = "<div><h6>${jobBean.title}<h6></div><div style='margin-bottom:5px'>${jobBean.address}</div>"
			var infowindow${jobBean.jobId} = new google.maps.InfoWindow({
			    content: contentString
			  });
			
			marker${jobBean.jobId}.addListener("click", function() {
		          infowindow${jobBean.jobId}.open(map, marker${jobBean.jobId});
		        });

		}

	function handleLocationError(browserHasGeolocation, infoWindow, pos) {
		infoWindow.setPosition(pos);
		infoWindow
				.setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
						: 'Error: Your browser doesn\'t support geolocation.');
		infoWindow.open(map);
	}
</script>
<style>
.showjobdetail h5 {
color:#04060F;
font-weight:900;
}

.showjobdetail h3 {
color:#003D73;
font-weight:900;
}

.showjobdetail h6 {
font-weight:700;
}

#map {
	height: 400px;
	margin-bottom: 5px;
}

.jobdetailrow {
margin-bottom:10px;
}

</style>
</head>

<body>
	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around align-items-center">
			<div class="col-sm-2">
			<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			</div>
			<div class="col-sm-6 showjobdetail">
				<!--             程式寫在這 -->
				<div class="row">
					<div class="col-sm-12">
						<h3>${jobBean.title}</h3>
						<p style="color:#C05640;font-weight:600">時薪：${jobBean.rateByHour}</p>
					</div>
				</div>
				<div class="row justify-content-center jobdetailrow">
					<div class="col-sm-3">
						<h5>工作內容</h5>
					</div>
					<div class="col-sm-5">${jobBean.description}</div>
				</div>
				<div class="row justify-content-center jobdetailrow">
					<div class="col-sm-3">
						<h5>工作時間</h5>
					</div>
					<div class="col-sm-5">
<%-- 						<c:forEach var="schedules" items="${schedules}"> --%>
							<h6>${jobBean.workDate}</h6>
				${jobBean.workTime}
<%-- 				</c:forEach> --%>
					</div>
				</div>
				<div class="row justify-content-center jobdetailrow">
					<div class="col-sm-3">
						<h5>需求人數</h5>
					</div>
					<div class="col-sm-5">${jobBean.positionNum}</div>
				</div>
				<div class="row justify-content-center jobdetailrow">
					<div class="col-sm-3">
						<h5>給薪日期</h5>
					</div>
					<div class="col-sm-5">${jobBean.paidDate}</div>
				</div>
				<div class="row justify-content-center jobdetailrow">
					<div class="col-sm-3">
						<h5>其他條件</h5>
					</div>
					<div class="col-sm-5">${jobBean.other}</div>
				</div>
				<div class="row justify-content-center jobdetailrow">
					<div class="col-sm-3">
						<h5>工作地點</h5>
					</div>
					<div class="col-sm-5">${jobBean.city.cityName}</div>
				</div>
				<div class="row justify-content-center jobdetailrow">
					<div class="col-sm-3">
						<h5>詳細地址</h5>
					</div>
					<div class="col-sm-5">${jobBean.address}</div>
				</div>
				<div class="row justify-content-center jobdetailrow">
					<div class="col-sm-3">
						<h5>地點說明</h5>
					</div>
					<div class="col-sm-5">${jobBean.addresssup}</div>
				</div>
				<div class="row justify-content-center jobdetailrow">
					<div class="col-sm-3">
						<h5>聯絡資訊</h5>
					</div>
					<div class="col-sm-5">
						<p>聯絡人：${jobBean.contact}</p>
						<p>手機：${jobBean.jobPhone}</p>
						<p>信箱：${jobBean.jobEmail}</p>
					</div>
				</div>
				<div class="row justify-content-center jobdetailrow">
					<div class="col-sm-3">
						<h5>所屬公司</h5>
					</div>
					<div class="col-sm-5">${jobBean.jobCompany.name}</div>
				</div>

				<div class="row justify-content-center">
				<div class="col-sm-4" style="margin-top:15px">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-light" data-toggle="modal"
						data-target="#reportModal" style="margin-right: 30px">檢舉</button>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#resumeModal">我要應徵</button>
				</div>
				</div>
				<!--以下是履歷的區塊 -->
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
									<h5 style="color: red;margin-left:10px">請先登入系統及填寫履歷</h5>
								</c:if>
								<table class="table table-striped">

									<tbody>
										<tr>
											<td><img width='100' height='100' src="${pageContext.request.contextPath}/getPicture/${resumeBean.user.userId}"></td>
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
							<c:if test="${resumeBean.user.userId==applicationBean.user.userId}">
							<h5 style="color: red;margin-left:10px">您已應徵過此工作</h5>
							</c:if>
							<c:if test="${empty applicationBean}">		
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary cancel"
										data-dismiss="modal">取消</button>

									<button type="button" class="btn btn-primary addapplication">送出</button>

								</div>
							</c:if>
							</c:if>
						</div>
					</div>
				</div>

				<!--以下是檢舉的區塊 -->
				<div class="modal fade" id="reportModal" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">舉報此工作</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form action="${pageContext.request.contextPath}/cpApply" method="post">
							<div class="modal-body">
								<!-- 彈出視窗：寫程式的地方 -->
								<div class="form-group">
									<label for="exampleInputEmail1">請選擇舉報的原因，為保帳您的權益，我們不會透露您的個人資訊給雇主。</label>
								</div>
								<fieldset class="form-group">
									<div class="row">
								    	<div class="col-sm-12">
								        	<div class="form-check">
								        		<input class="form-check-input" type="radio" name="type" id="type1" value="八大行業" checked required="required">
								  				<label class="form-check-label" for="type1">八大行業</label>
								        	</div>
								        	<div class="form-check">
								       			<input class="form-check-input" type="radio" name="type" id="type2" value="傳直銷產業">
								  				<label class="form-check-label" for="type2">傳直銷產業</label>
								        	</div>
								        	<div class="form-check">
								       			<input class="form-check-input" type="radio" name="type" id="type3" value="詐騙">
								  				<label class="form-check-label" for="type3">詐騙</label>
								        	</div>
								        	<div class="form-check">
								       			<input class="form-check-input" type="radio" name="type" id="type4" value="薪水待遇與實際刊登不符">
								  				<label class="form-check-label" for="type4">薪水待遇與實際刊登不符</label>
								        	</div>
								        	<div class="form-check">
								       			<input class="form-check-input" type="radio" name="type" id="type5" value="薪資工時違反勞基法">
								  				<label class="form-check-label" for="type5">薪資工時違反勞基法</label>
								        	</div>
											<div class="form-check">
								       			<input class="form-check-input" type="radio" name="type" id="type6" value="僱主惡意拖欠薪水">
								  				<label class="form-check-label" for="type6">僱主惡意拖欠薪水</label>
								        	</div>
								        	<div class="form-check">
								       			<input class="form-check-input" type="radio" name="type" id="type7" value="逾期">
								  				<label class="form-check-label" for="type7">逾期</label>
								        	</div>
								        	<div class="form-check">
								       			<input class="form-check-input" type="radio" name="type" id="type8" value="其他">
								  				<label class="form-check-label" for="type8">其他</label>
								        	</div>
								    	</div>
									</div>
								</fieldset>
								<div class="form-group">
								    <label for="desc">請描述為何舉報此工作</label>
								    <textarea class="form-control" id="desc" name="content" rows="3"></textarea>
								</div>		
							</div>
							<div class="modal-footer">
								<input  type="hidden" name="jobId" value="${jobBean.jobId}" >
								<button type="button" class="btn btn-secondary cancel" data-dismiss="modal">取消</button>
								<button type="submit" class="btn btn-primary">送出</button>
							</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">			
				<div id="map"></div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground"><footer>Copyright© 2019 趣打工 All rights reserved.</footer></div>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>