<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>首頁</title>
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
<body>
	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			<div class="col-sm-8">
				<br>
				<section>
					<div>
						<div class="container" style="text-align: center">
							<h1>
								<strong>所有應徵紀錄</strong>
							</h1>
						</div>
					</div>
				</section>
				<div style="height: 30px;"></div>
				<section class="container">
					<div class="col-sm-12">
						<c:forEach var="applicant" items="${applicantsByJob}">
							<div class="row">
								<div class="col-sm-12">
									<div class="panel panel-default text-left">
										<div class="panel-body">
											<h3>
												<strong><a href="#">${applicant.user.userName}</a></strong>
											</h3>
											<p></p>
											<strong>應徵公司單位:</strong> ${applicant.job.jobCompany.name}<br>
											<strong>應徵編號:</strong> ${applicant.applicationId}<br>
											<strong>雇主問題回應:</strong> ${applicant.answer}<br>
											<strong>應徵送出時間:</strong> ${applicant.applicationTime}
											<hr>
											<button type="button" class="btn btn btn-success btn-sm">
												<span class="glyphicon glyphicon-thumbs-up"></span> 履歷
											</button>
											<button type="button" class="btn btn btn-warning btn-sm" data-toggle="modal" data-target="#interviewModal">
												<span class="glyphicon glyphicon-thumbs-up"></span> 邀約
											</button>
											<div class="modal fade" id="interviewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="exampleModalLabel">邀請${applicant.user.userName}面試/上工</h5>
															<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<form action="${pageContext.request.contextPath}/interSend" method="post">
															<div class="modal-body">
																<c:if test="${empty sessionScope.loginUser}">
																	<h5 style="color: red">請先登入系統</h5>
																</c:if>		
																<fieldset class="form-group">
																	<div class="row">
																    	<legend class="col-form-label col-sm-3 pt-0">邀請類型：</legend>
																    	<div class="col-sm-9">
																        	<div class="form-check form-check-inline">
																        		<input class="form-check-input" type="radio" name="interType" id="exampleRadios1" value="面試" checked required="required">
																  				<label class="form-check-label" for="exampleRadios1">面試</label>
																        	</div>
																        	<div class="form-check form-check-inline">
																       			<input class="form-check-input" type="radio" name="interType" id="exampleRadios2" value="上工">
																  				<label class="form-check-label" for="exampleRadios2">上工</label>
																        	</div>
																    	</div>
																	</div>
																</fieldset>
																<div class="form-group row">
																	<label for="des" class="col-sm-3 col-form-label">描述：</label>
																	<div class="col-sm-9">
																    	<input type="text" class="form-control" name="interComment" id="des" required="required">
																   	</div>
																</div>
																<div class="form-group row">
																	<label for="place" class="col-sm-3 col-form-label">地點：</label>
																	<div class="col-sm-9">
																    	<input type="text" class="form-control" name="interPlace" id="place" required="required">
																   	</div>
																</div>
																<div class="form-group row">
																	<label for="time" class="col-sm-3 col-form-label">時間：</label>
																	<div class="col-sm-9">
																    	<input type="datetime-local" class="form-control" name="interTime" id="time" required="required">
																   	</div>
																</div>
																<input type="hidden" name="apId" value="${applicant.applicationId}">
															</div>							
															<c:if test="${!empty sessionScope.loginUser}">
																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary cancel" data-dismiss="modal">取消</button>		
																	<button type="submit" class="btn btn-primary addapplication">送出</button>
																</div>
															</c:if>
														</form>
													</div>
												</div>
											</div>
											<a href="${pageContext.request.contextPath}/refuseUser/${applicant.applicationId}/${applicant.job.jobId}">
												<button type="button" class="btn btn btn-danger btn-sm">
													<span class="glyphicon glyphicon-thumbs-up"></span>婉拒
												</button>
											</a>
											<a href="${pageContext.request.contextPath}/chat/${applicant.applicationId}">
												<button type="button" class="btn btn-primary btn-sm" style="float: right">									
												<span class="glyphicon glyphicon-thumbs-up"></span>傳訊給應徵者
											</button>
											</a>
										</div>
										<p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</section>
			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>