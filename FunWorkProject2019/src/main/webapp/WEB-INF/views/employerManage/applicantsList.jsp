<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>首頁</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
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
<script type="text/javascript">
	$(document).ready(
			  
				var jobTitle = $("#jobTitleInput").val();
				$("#JobTitle").val(jobTitle);
				
				if(jobTitle!=null){
				document.getElementById("JobTitle").innerHTML = "["
						+ $("#JobTitle").val() + "]";
				}
			});
</script>
<body>
	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
		<div class="col-sm-2">
			<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			</div>
			<div class="col-sm-8">
				<br>
				<section>
<!-- 					<div style="float: right"> -->
<%-- 						<a href='resumes.xls?jobId=${jobId}'><button>匯出Excel</button></a> --%>
<%-- 						<a href='resumes.pdf?jobId=${jobId}'><button>匯出PDF</button></a> --%>
<!-- 					</div> -->
					<div>
						<div class="container" style="text-align: center">
							<h1>
								<strong><label id="JobTitle"></label>所有應徵紀錄</strong>
							</h1>
						</div>
					</div>
				</section>
				<c:choose>
				<c:when test="${empty applicantsByJob}">
				<div style="height: 30px; color: red">此筆職缺尚無應徵紀錄</div>
				</c:when>
				<c:otherwise>
				<section class="container">
					<div style="float: right">
						<a href='resumes.xls?jobId=${jobId}'><button>匯出Excel</button></a>
						<a href='resumes.pdf?jobId=${jobId}'><button>匯出PDF</button></a>
					</div>
					<div class="col-sm-14">
						<c:forEach var="applicant" items="${applicantsByJob}"
							varStatus="loop">
							<div class="row">
								<div class="col-sm-12">
									<div class="col-sm-12" style="background-color: #F8F8FF">
										<div class="panel panel-default text-left">
											<div class="panel-body">
												<h3 style="margin: 10px">
													<strong><a href="#">${applicant.user.userName}</a></strong>
												</h3>
												<div class="row">
													<div class="col-sm-3">
														<img style="width: 100px; height: 100px"
															src="<c:url value='/getProfilePic/${applicant.user.userId}'/>"><br>
														<input type="hidden" id="jobTitleInput"
															value="${applicant.job.title}">
														<!-- 													<strong>應徵公司單位:</strong> -->
														<%-- 												${applicant.job.jobCompany.name}<br>  --%>
														<strong>應徵編號:</strong> ${applicant.applicationId}<br>
														<strong>應徵送出時間:</strong><br>
														<fmt:formatDate type="both"
															value="${applicant.applicationTime}" />
													</div>

													<div class="col-sm-3">
														<strong>自我介紹與工作經驗:</strong><br>
														<br> ${resumes[loop.count-1].selfIntro}
													</div>

													<div class="col-sm-3">
														<strong>給雇主的話:</strong><br>
														<br> ${applicant.answer}
													</div>
													<div class="col-sm-3">
														<div class="text-right" style="margin-bottom: 80px">
															<strong>缺席次數</strong><br>
															<br>
															<h6>${users[loop.count-1].abscence}</h6>
														</div>
														<div>
															<button type="button" class="btn btn-warning btn-sm"
																data-toggle="modal" data-target="#interviewModal">
																<span class="glyphicon glyphicon-thumbs-up"></span> 邀約
															</button>
															<div class="modal fade" id="interviewModal" tabindex="-1"
																role="dialog" aria-labelledby="exampleModalLabel"
																aria-hidden="true">
																<div class="modal-dialog" role="document">
																	<div class="modal-content">
																		<div class="modal-header">
																			<h5 class="modal-title" id="exampleModalLabel">邀請${applicant.user.userName}面試/上工</h5>
																			<button type="button" class="close"
																				data-dismiss="modal" aria-label="Close">
																				<span aria-hidden="true">&times;</span>
																			</button>
																		</div>

																		<form
																			action="${pageContext.request.contextPath}/interSend"
																			method="post">
																			<div class="modal-body">
																				<c:if test="${empty sessionScope.loginUser}">
																					<h5 style="color: red">請先登入系統</h5>
																				</c:if>
																				<fieldset class="form-group">
																					<div class="row">
																						<label class="col-form-label col-sm-3 pt-0">邀請類型：</label>
																						<div class="col-sm-9">
																							<div class="form-check form-check-inline">
																								<input class="form-check-input" type="radio"
																									name="interType" id="exampleRadios1" value="面試"
																									checked required="required"> <label
																									class="form-check-label" for="exampleRadios1">面試</label>
																							</div>
																							<div class="form-check form-check-inline">
																								<input class="form-check-input" type="radio"
																									name="interType" id="exampleRadios2" value="上工">
																								<label class="form-check-label"
																									for="exampleRadios2">上工</label>
																							</div>
																						</div>
																					</div>
																				</fieldset>
																				<div class="form-group row">
																					<label for="des" class="col-sm-3 col-form-label">描述：</label>
																					<div class="col-sm-9">
																						<input type="text" class="form-control"
																							name="interComment" id="des" required="required">
																					</div>
																				</div>
																				<div class="form-group row">
																					<label for="place" class="col-sm-3 col-form-label">地點：</label>
																					<div class="col-sm-9">
																						<input type="text" class="form-control"
																							name="interPlace" id="place" required="required">
																					</div>
																				</div>
																				<div class="form-group row">
																					<label for="time" class="col-sm-3 col-form-label">時間：</label>
																					<div class="col-sm-9">
																						<input type="datetime-local" class="form-control"
																							name="interTime" id="time" required="required">
																					</div>
																				</div>
																				<input type="hidden" name="apId"
																					value="${applicant.applicationId}">
																			</div>
																			<c:if test="${!empty sessionScope.loginUser}">
																				<div class="modal-footer">
																					<button type="button"
																						class="btn btn-secondary cancel"
																						data-dismiss="modal">取消</button>
																					<button type="submit"
																						class="btn btn-primary addapplication">送出</button>
																				</div>
																			</c:if>
																		</form>
																	</div>
																</div>
															</div>
															<a
																href="${pageContext.request.contextPath}/refuseUser/${applicant.applicationId}/${applicant.job.jobId}">
																<button type="button" class="btn btn btn-danger btn-sm">
																	<span class="glyphicon glyphicon-thumbs-up"></span>婉拒
																</button>
															</a> <a
																href="${pageContext.request.contextPath}/chat/${applicant.applicationId}">
																<button type="button" class="btn btn-primary btn-sm"
																	style="float: right">
																	<span class="glyphicon glyphicon-thumbs-up"></span>傳訊
																</button>
															</a>
														</div>
													</div>
												</div>
												<p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</section>
				</c:otherwise>
				</c:choose>
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