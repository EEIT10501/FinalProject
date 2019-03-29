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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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
				<fieldset>
					<section style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
	<!------------------------------------------工作標題始----------------------------------- -->
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i>
				<h3>工作應徵管理</h3>
			</div>

			<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
				<li class="nav-item"><a class="nav-link active"
					id="pills-home-tab" data-toggle="pill" href="#pills-home"
					role="tab" aria-controls="pills-home" aria-selected="true">全部</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					id="pills-profile-tab" data-toggle="pill" href="#pills-profile"
					role="tab" aria-controls="pills-profile" aria-selected="false">未邀約</a></li>
				<li class="nav-item"><a class="nav-link"
					id="pills-contact-tab" data-toggle="pill" href="#pills-contact"
					role="tab" aria-controls="pills-contact" aria-selected="false">已邀約</a></li>
			</ul>
			<div class="tab-content" id="pills-tabContent">
				<div class="tab-pane fade show active" id="pills-home"
					role="tabpanel" aria-labelledby="pills-home-tab">
		<!---------------------------------工作標題終----------------------------------- -->
		<c:set var="pendingInviteCheck" value="0" />
		<c:choose>
			<c:when test="${empty applicantsByJob}">
				<div style="height: 30px; color: red">此筆職缺尚無應徵紀錄</div>
			</c:when>
			<c:otherwise>
				<section class="container">
					<div class="row" style="margin-left: 650px">
						<a href='resumes.xls?jobId=${jobId}'><button>匯出Excel</button></a>&nbsp&nbsp
						<a href='resumes.pdf?jobId=${jobId}'><button>匯出PDF</button></a>
					</div>
					<p></p>
					<div class="col-sm-14">
						<c:set var="pendingInviteCheck" value="1" />
						<c:set var="invitedCheck" value="0" />
						<c:set var="totalApplicants" value="0"></c:set>
	   <!------------------------------------------------------------------------------------>
		<c:forEach var="applicant" items="${applicantsByJob}" varStatus="loop">
			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-12" style="border: 1px solid black">
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
									value="${applicant.job.title}"> <strong>自我介紹:</strong><br>
								${resumes[loop.count-1].selfIntro}
								<p></p>
								<br>
							</div>
							<div class="col-sm-3 rows">
								<strong>工作經驗 1: </strong><br>
								${resumes[loop.count-1].type1}:
								${resumes[loop.count-1].term1}
								<p></p>
								<strong>工作經驗 2: </strong><br>
								${resumes[loop.count-1].type2}:
								${resumes[loop.count-1].term2}
							</div>
							<div class="col-sm-3 rows">
								<strong>最高學歷: </strong><br>
								${resumes[loop.count-1].educationLevel}
								<p></p>
								<strong>${applicant.job.other} : </strong><br>
								${applicant.answer}
							</div>
							<div class="text-right row"
								style="margin-bottom: 40px;">
								<table class="table table-bordered"
									style="border: 1px solid black">
									<thead class="thead-light">
										<tr>
											<th><strong>出席次數:</strong></th>
											<th><strong>缺席次數:</strong></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><h6>${users[loop.count-1].presence}</h6></td>
											<td><h6>${users[loop.count-1].abscence}</h6></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
			<!-------------------------------最底下的應徵狀態------------------------------------------>
			<div class="row" style="text-align: center; height: auto">
			<br>
			<div style="margin-right: 110px; margin-left: 10px">
				<strong>應徵編號:&nbsp</strong>
				${applicant.applicationId}&nbsp&nbsp&nbsp&nbsp
			</div>
			<div style="margin-right: 80px">
				<strong>應徵送出時間:&nbsp</strong>
				<fmt:formatDate type="both"
					value="${applicant.applicationTime}" />
			</div>
			<div style="float: right">
			<button type="button"
				class="btn btn-warning btn-sm"
				data-toggle="modal"
				data-target="#${applicant.user.userName}">
				<span class="glyphicon glyphicon-thumbs-up"></span>邀約
			</button>
			<a
				href="${pageContext.request.contextPath}/refuseUser/${applicant.applicationId}/${applicant.job.jobId}">
				<button type="button"
					class="btn btn btn-danger btn-sm">
					<span class="glyphicon glyphicon-thumbs-up"></span>婉拒
				</button>
			</a> <a
				href="${pageContext.request.contextPath}/chat/${applicant.applicationId}">
				<button type="button"
					class="btn btn-primary btn-sm"
					style="float: right">
					<span class="glyphicon glyphicon-thumbs-up"></span>傳訊給使用者
				</button>
			</a>
		  <!------------------------彈跳出來的邀約確認視窗始----------------------------------------------------->
		 <div class="modal fade"
			id="${applicant.user.userName}" tabindex="-1"
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

		<!-----------------彈跳出來的登入確認視窗始------------------------------------------->
		<form action="${pageContext.request.contextPath}/interSend" method="post">
			<div class="modal-body">
				<c:if test="${empty sessionScope.loginUser}">
					<h5 style="color: red">請先登入系統</h5>
				</c:if>
		<!-----------------彈跳出來的登入確認視窗終------------------------------------------->
			<fieldset class="form-group">
				<div class="row">
				<label class="col-form-label col-sm-3 pt-0">邀請類型：</label>
				<div class="col-sm-9">
					<div class="form-check form-check-inline">
						<input class="form-check-input"
							type="radio" name="interType"
							id="exampleRadios1" value="面試" checked
							required="required"> <label
							class="form-check-label"
							for="exampleRadios1">面試</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input"
							type="radio" name="interType"
							id="exampleRadios2" value="錄取">
						<label class="form-check-label"
							for="exampleRadios2">錄取</label>
					</div>
				</div>
				</div>
			</fieldset>
					<div class="form-group row">
						<label for="des"
							class="col-sm-3 col-form-label">描述：</label>
						<div class="col-sm-9">
							<input type="text" class="form-control"
								name="interComment" id="des"
								required="required">
						</div>
					</div>
					<div class="form-group row">
						<label for="place"
							class="col-sm-3 col-form-label">地點：</label>
						<div class="col-sm-9">
							<input type="text" class="form-control"
								name="interPlace" id="place"
								required="required">
						</div>
					</div>
					<div class="form-group row">
						<label for="time"
							class="col-sm-3 col-form-label">時間：</label>
						<div class="col-sm-9">
							<input type="datetime-local"
								class="form-control" name="interTime"
								id="time" required="required">
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
	<!----------------------------------彈跳出來的邀約確認視窗終----------------------------------------->

	<!-------------------------------最底下的應徵狀態終------------------------------------------>
					</div>
					</div>
					</div>
					</div>
					<p>
					</div>
					</div>
					</div>
				<c:set var="totalApplicants" value="${totalApplicants + 1}" />
<%-- 				</c:if> --%>
			<c:if test="${applicant.appliedStatus =='已邀約'}">
					<c:set var="invitedCheck" value="${invitedCheck+1}" />
			</c:if>
		</c:forEach>
		<c:if test="${pendingInviteCheck == 0}"> 
				此筆工作無未回應的邀約。
		</c:if>
		<c:if
			test="${(totalApplicants > 0) or (invitedCheck > 0) }">
			<b>共用 ${totalApplicants + invitedCheck} 筆應徵紀錄: </b>
			<p></p>
		</c:if>
		<c:if test="${totalApplicants > 0}"> 
			${totalApplicants} 筆等待回應的應徵紀錄。<p></p>
		</c:if>
		<c:if test="${invitedCheck > 0}">
			已回應 ${invitedCheck} 則應徵 。<p></p>
			可前往
			<a href="${pageContext.request.contextPath}/applicationNInterview">邀約管理</a>查看更新結果。
		</c:if>
			</div>
		</section>
		</c:otherwise>
		</c:choose>
		</div>
		<!----------------------------------------------------------------------------->
		<div class="tab-pane fade" id="pills-profile" 
		            role="tabpanel" aria-labelledby="pills-profile-tab">
		<!-- ------------------------------------------------------------------------- -->
			<div class="card-header">
				<i class="fas fa-table"></i>
				<h5>
					<strong>未回應的應徵</strong>
				</h5>
			</div>
		<!-- ------------------------------------------------------------------------- -->
			<div class="card-body">
			<div class="table-responsive">
				<c:if test="${empty interviewsPerJobOwner}">
					<a>目前沒有任何邀約任何邀約或是任何應徵者前來面試 /上工</a>
				</c:if>
		<!-- ------------------------------------------------------------------------- -->
				<c:if test="${!empty interviewsPerJobOwner}">
				<div style="height: 30px;"></div>
				<section class="container">
				<div class="col-sm-12">
					<c:set var="checkEmpty" value="1"></c:set>
					<!-- ----------如果checkEmpty value維持 1 無已回應的邀約------------------------->
					<c:forEach var="interview1" items="${interviewsPerJobOwner}">
					
					<c:choose>
						<c:when test="${(interview1.interviewStatus == '接受') and(current.time gt interviewTime.time)}">
							<c:set var="checkEmpty" value="0"/>
							<div class="row">
							<div class="col-sm-12">
							<div class="panel panel-default text-left">
							<div class="panel-body">
							<a href="${pageContext.request.contextPath}/chat/${interview.application.applicationId}">
								<button type="button"
									class="btn btn btn-info btn-sm"
									style="float: right; margin: 10px" data-toggle="modal"
									data-target="#interviewModal${interview.interviewId}">
									<span class="glyphicon glyphicon-thumbs-up"></span>
									傳訊給使用者
								</button>
							</a>
							<h6>
								<strong>
									${interview1.application.user.userName} 已完成「<a
									href="${pageContext.request.contextPath}/jobDetail/${interview1.application.job.jobId}">
										${interview1.application.job.title} </a>」的
									「${interview1.interviewType}」 邀約，你可以根據到場狀況註記該應徵者，你的註記將攸關應徵者未來的求職履歷，請勿做出不實登記，並於
									59 小時內完成註記。
								</strong>
							</h6>
							<p></p>
							<strong>地點:</strong> ${interview1.interviewPlace}<br>
							<strong>邀約時間:</strong>
							<fmt:formatDate type="both" dateStyle="short"
								timeStyle="short"
								value="${interview1.interviewTime}" />
							<br> <strong>後續動作:</strong>將於${interview1.application.job.workDate}上工
							<br> <strong>邀約結果:</strong> <br>
							<!-- Button trigger modal -->
								<c:choose>
									<c:when test="${interview1.interviewResult == '等待資料'}">
										<br>
										<button type="button"
											class="btn btn btn-secondary btn-sm"
											data-toggle="modal"
											data-target="#attInterviewModal${interview1.interviewId}">
											<span class="glyphicon glyphicon-thumbs-up"></span>
											應約
										</button>
										<button type="button"
											class="btn btn btn-secondary btn-sm"
											data-toggle="modal"
											data-target="#absInterviewModal${interview1.interviewId}">
											<span class="glyphicon glyphicon-thumbs-up"></span>
											缺席
										</button>
										<button type="button"
											class="btn btn btn-secondary btn-sm"
											data-toggle="modal"
											data-target="#cancelInterviewModal${interview1.interviewId}">
											<span class="glyphicon glyphicon-thumbs-up"></span>
											邀約取消
										</button>
									</c:when>
									<c:otherwise>
									<br>
										<a href="">您已註記此邀約為${interview1.interviewResult}。</a>
									</c:otherwise>
								</c:choose>
								<hr>
						 <!--以下是出席彈出視窗的區塊 -->
						<div class="modal fade"
								id="attInterviewModal${interview1.interviewId}"
								tabindex="0" role="dialog"
								aria-labelledby="exampleModalLabel"
								aria-hidden="true">
							<div class="modal-dialog" role="document">
									<div class="modal-content">
										<br> <a align="center"><img
											src='<c:url value="/image/prohibit.png"/>'
											title="編輯" alt="編輯" width="150px"></a><br>
										<h2 style="text-align: center;">
											確定註記應徵者於<br>
											<fmt:formatDate type="both" dateStyle="short"
												timeStyle="short"
												value="${interview1.interviewTime}" />
											出席？
										</h2>
										<p style="text-align: center;">一旦註記結果送出，將無法更改。</p>
										<div align="center">
										<c:if test="${interview1.interviewType =='錄取'}">
										</c:if>
										</div>
										<form
											action="${pageContext.request.contextPath}/updateInterviewResult"
											method="post">
											<div class="modal-body">
											<h6>請幫使用者評分: <input type="number" min="1" max="5" name="interviewRating" required="required"></h6> 
												<input type="hidden" name="interviewId"
													value="${interview1.interviewId}"> 
												<input
													type="hidden" name="interviewResult"
													value="應約">
												<input
												type="hidden" name="interviewRatingHidden" id="interviewRatingHidden"
												value="">
											</div>
											<c:if test="${!empty sessionScope.loginUser}">
												<div class="modal-footer">
													<button type="button"
														class="btn btn-secondary cancel"
														data-dismiss="modal">取消</button>
													<button type="submit"
														class="btn btn-primary updateinterview">送出</button>
												</div>
											</c:if>
										</form>
									</div>
								</div>
							</div>
							<!--以上是出席彈出視窗的區塊 -->
							<!--以下是缺席彈出視窗的區塊 -->
							<div class="modal fade"
								id="absInterviewModal${interview1.interviewId}"
								tabindex="0" role="dialog"
								aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<br> <a align="center"><img
											src='<c:url value="/image/prohibit.png"/>'
											title="編輯" alt="編輯" width="150px"></a><br>
										<h2 style="text-align: center;">
											確定註記應徵者未出席<br>
											<fmt:formatDate type="both" dateStyle="short"
												timeStyle="short"
												value="${interview1.interviewTime}" />
											邀約？
										</h2>
										<p style="text-align: center;">一旦註記結果送出，將無法更改。</p>
										<form
											action="${pageContext.request.contextPath}/updateInterviewResult"
											method="post">
											<div class="modal-body">
<!-- 																	<h6>請幫使用者評分: <input type="number" value="0" name="interviewRating" readonly="readonly" required="required"></h6>  -->
												<h6>註記缺自動給0分</h6>
												<input type="hidden" name="interviewId"
													value="${interview1.interviewId}"> <input
													type="hidden" name="interviewResult"
													value="缺席">
											</div>
											<c:if test="${!empty sessionScope.loginUser}">
												<div class="modal-footer">
													<button type="button"
														class="btn btn-secondary cancel"
														data-dismiss="modal">取消</button>
													<button type="submit"
														class="btn btn-primary updateinterview">送出</button>
												</div>
											</c:if>
										</form>
									</div>
								</div>
							</div>
							<!--以上是缺席彈出視窗的區塊 -->
							<!--以下是取消邀約彈出視窗的區塊 -->
							<div class="modal fade"
											id="cancelInterviewModal${interview1.interviewId}"
											tabindex="0" role="dialog"
											aria-labelledby="exampleModalLabel"
											aria-hidden="true">
								<div class="modal-dialog" role="document">
										<div class="modal-content">
													<br> <a align="center"><img
														src='<c:url value="/image/prohibit.png"/>'
														title="編輯" alt="編輯" width="150px"></a><br>
													<h2 style="text-align: center;">
														確定取消<br>
														<fmt:formatDate type="both" dateStyle="short"
															timeStyle="short"
															value="${interview1.interviewTime}" />
														的邀約？
													</h2>
													<p style="text-align: center;">一旦註記結果送出，將無法更改。</p>
													<form
														action="${pageContext.request.contextPath}/updateInterviewResult/"
														method="post">
														<div class="modal-body">
															<input type="hidden" name="interviewId"
																value="${interview1.interviewId}"> <input
																type="hidden" name="interviewResult"
																value="邀約取消">
														</div>
														<c:if test="${!empty sessionScope.loginUser}">
														<div class="modal-footer">
																<button type="button"
																	class="btn btn-secondary cancel"
																	data-dismiss="modal">取消</button>
																<button type="submit"
																	class="btn btn-primary updateinterview">送出</button>
														</div>
														</c:if>
													</form>
												</div>
											</div>
										</div>
										<!--以上是取消邀約彈出視窗的區塊 -->
									</div>
										<p>
									</div>
								</div>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${checkEmpty == 1}">
						<a>你目前沒有已完成的邀約</a>
					</c:if>
				</div>
			</section>
			</c:if>
			<!-- ------------------------------------------------------------------------- -->
			</div>
			</div>
	  </div>
	  <div class="tab-pane fade" id="pills-contact" 
	role="tabpanel" aria-labelledby="pills-contact-tab">
						<!-- ------------------------------------------------------------------------- -->
								<div class="card-header">
									<i class="fas fa-table"></i>
									<h5>
										<strong>已過期的邀約</strong>
									</h5>
								</div>
						<!-- ------------------------------------------------------------------------- -->
								<div class="card-body">
								<div class="table-responsive">
									<c:if test="${empty interviewsPerJobOwner}">
										<a>你目前沒有任何已失效的邀約</a>
									</c:if>
									<c:if test="${!empty interviewsPerJobOwner}">
										<section class="container">
										<div class="col-sm-12">
											<c:set var="emptyCheckExpir" value="1"/>
											<c:forEach var="interviewExpired" items="${interviewsPerJobOwner}">
											<fmt:setLocale value="zh_TW" />
											<fmt:parseDate var="interviewTimeExpired" value="${interviewExpired.interviewTime}" 
												pattern="yyyy-MM-dd HH:mm:ss" />
											<c:choose>
											<c:when test="${(interviewExpired.interviewStatus == '待回應')and
											(interviewExpired.interviewResult == '等待資料')and(current.time gt interviewTimeExpired.time)}">
											<c:set var="emptyCheckExpir" value="0"/>
											<div class="row">
											<div class="col-sm-12">
											<div class="panel panel-default text-left">
											<div class="panel-body">
													<a href="${pageContext.request.contextPath}/chat/${interviewExpired.application.applicationId}">
														<button type="button" class="btn btn btn-info btn-sm"
														style="float: right" data-toggle="modal"
														data-target="#interviewModal${interinterviewExpiredviewPending.interviewId}">
														<span class="glyphicon glyphicon-thumbs-up"></span>
														傳訊給使用者
														</button>
													</a>
												<h6>
													<strong>發送至 應徵者 ${interviewExpired.application.user.userName}
													的「 <a
														href='<c:url value="/jobDetail/${interviewExpired.application.job.jobId}"/>'>
														${interviewExpired.application.job.title}」</a>
													「<a href="#">${interviewExpired.interviewType}</a>」邀約已失效
													</strong>
												</h6>
												<p></p>
												<strong>地點:</strong> ${interviewExpired.interviewPlace}<br>
												<strong>邀約時間:</strong>
												<fmt:formatDate type="both" dateStyle="short"
													timeStyle="short"
													value="${interviewExpired.interviewTime}" /><br> 
												<hr>
											</div>
											<p>
											</div>
											</div>
											</div>
											</c:when>
											<c:otherwise>
											</c:otherwise>
											</c:choose>
											</c:forEach>
											<c:if test="${emptyCheckExpir == 1}">
												<a>你目前沒有任何已失效的邀約</a>
											</c:if>
										</div>
										</section>
								</c:if>
							<!-- ------------------------------------------------------------------------- -->
							</div>
						</div>
					</div>
							</div>
						</div>
					</section>
				</fieldset>
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
			<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
			<script
				src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>