<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>
<jsp:useBean id="current" class="java.util.Date" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script language="javascript">
　var Today=new Date();
</script>

<title>基本資訊</title>
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
			<div class="col-sm-2 asideblock">
				<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			</div>
			<div class="col-sm-8">
				<fieldset>
					<section
						style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
						<div class="container-fluid">
							<div class="card mb-3">
								<div class="card-header">
									<i class="fas fa-table"></i>
									<h3>邀約管理</h3>
								</div>

								<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
									<li class="nav-item"><a class="nav-link active"
										id="pills-home-tab" data-toggle="pill" href="#pills-home"
										role="tab" aria-controls="pills-home" aria-selected="true">進行中</a>
									</li>
									<li class="nav-item"><a class="nav-link"
										id="pills-profile-tab" data-toggle="pill"
										href="#pills-profile" role="tab" aria-controls="pills-profile"
										aria-selected="false">已完成</a></li>
									<li class="nav-item"><a class="nav-link"
										id="pills-contact-tab" data-toggle="pill"
										href="#pills-contact" role="tab" aria-controls="pills-contact"
										aria-selected="false">已失效</a></li>
								</ul>
								<div class="tab-content" id="pills-tabContent">
									<div class="tab-pane fade show active" id="pills-home"
										role="tabpanel" aria-labelledby="pills-home-tab">
										<!-- -----------------代回應的邀約始--------------------------------------- -->
										<div class="card-header">
											<i class="fas fa-table"></i>
											<h5>
												<strong>等待回應的邀約</strong>
											</h5>
										</div>
										<!-- ------------------------------------------------------------------------- -->
										<div class="card-body">
											<div class="table-responsive">
												<c:if test="${empty interviewsPerJobOwner}">
													<a>目前沒有任何邀約任何邀約或是任何應徵者前來面試 /上工</a>
												</c:if>
												<c:if test="${!empty interviewsPerJobOwner}">
													<section class="container">
														<div class="col-sm-12">
														<c:set var="emptyCheckPending" value="1"/>
														<c:forEach var="interviewPending" items="${interviewsPerJobOwner}">
															<fmt:setLocale value="zh_TW" />
															<fmt:parseDate var="interviewTimeE" value="${interviewPending.interviewTime}" 
															pattern="yyyy-MM-dd HH:mm:ss" />
																<c:choose>
																	<c:when test="${(interviewPending.interviewStatus == '待回應')and(current.time lt interviewTimeE.time)}">
																		<div class="row">
																		<div class="col-sm-12">
																		<div class="panel panel-default text-left">
																		<div class="panel-body">
																		<c:choose>
																		<c:when
																			test="${interviewPending.interviewStatus == '待回應'}">
																			<c:set var="emptyCheckPending" value="0"/>
																			<br>
																			<a href="${pageContext.request.contextPath}/chat/${interviewPending.application.applicationId}">
																			<button type="button" class="btn btn btn-info btn-sm"
																			style="float: right" data-toggle="modal"
																			data-target="#interviewModal${interviewPending.interviewId}">
																			<span class="glyphicon glyphicon-thumbs-up"></span>
																			傳訊給使用者
																			</button>
																			</a>
																			<h6><strong>你已回應「<a href="#">${interviewPending.application.user.userName}</a>」的「
																				<a href='<c:url value="/jobDetail/${interviewPending.application.job.jobId}"/>'>
																				${interviewPending.application.job.title}
																				</a>」工作應徵，目前等待對方回應中。 
																				</strong>
																			</h6>
																			<p></p>
																		</c:when>
																		</c:choose>
																		<strong>邀約類別:</strong>
																		${interviewPending.interviewType}<br> <strong>狀態:</strong>
																		${interviewPending.interviewStatus}<br> <strong>地點:</strong>
																		${interviewPending.interviewPlace}<br> <strong>邀約時間:</strong>
																		<fmt:formatDate type="both" dateStyle="short"
																			timeStyle="short"
																			value="${interviewPending.interviewTime}" />
																		<hr>
																		<!--以下是取消邀約彈出視窗的區塊 -->
																		<div class="modal fade"
																			id="cancelinterviewModal${interviewPending.interviewId}"
																			tabindex="0" role="dialog"
																			aria-labelledby="exampleModalLabel"
																			aria-hidden="true">
																		<div class="modal-dialog" role="document">
																		<div class="modal-content">
																			<br> <a align="center"><img src='<c:url value="/image/prohibit.png"/>'
																			title="編輯" alt="編輯" width="150px"></a><br>
																			<h2 style="text-align: center;">確定要取消此邀約?</h2><br>
																			<form
																				action="${pageContext.request.contextPath}/updateInterviewStatusOther"
																				method="post">
																				<div class="modal-body">
																					<input type="hidden" name="interviewId"
																						value="${interview1.interviewId}"> <input
																						type="hidden" name="interviewStatus"
																						value="拒絕">
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
															<c:if test="${emptyCheckPending}==1">
																<a>目前沒有任何邀約任何邀約或是任何應徵者前來面試 /上工</a>
															</c:if>
														</div>
													</section>
												</c:if>
											</div>
										</div>
										<!-- -----------------代回應的邀約終--------------------------------------- -->
										<!-- -----------------即將到來的邀約始--------------------------------------- -->
										<div class="card-header">
											<i class="fas fa-table"></i>
											<h5>
												<strong>即將到來的邀約</strong>
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
												<div style="height: 10px;"></div>
												<section class="container">
												<div class="col-sm-12">
												<c:set var="checkEmpty" value="1"></c:set>
												<c:forEach var="interview" items="${interviewsPerJobOwner}">
													<fmt:setLocale value="zh_TW" />
													<fmt:parseDate var="interviewTime" value="${interview.interviewTime}" 
													pattern="yyyy-MM-dd HH:mm:ss" />
												<c:choose>
													<c:when test="${(interview.interviewStatus == '接受') and (interview.interviewResult == '等待資料') 
													and (current.time lt interviewTime.time)}">
													<c:set var="checkEmpty" value="0"/>
													<div class="row">
													<div class="col-sm-12">
													<div class="panel panel-default text-left">
													<div class="panel-body">
															<br>
															<a
																href="${pageContext.request.contextPath}/chat/${interview.application.applicationId}">
																<button type="button"
																	class="btn btn btn-info btn-sm"
																	style="float: right" data-toggle="modal"
																	data-target="#interviewModal${interview.interviewId}">
																	<span class="glyphicon glyphicon-thumbs-up"></span>
																	傳訊給使用者
																</button>
															</a>
															<h6>
																<strong>你已回應「<a href="#">${interview.application.user.userName}</a>」的應徵，來自應徵的「
																	<a
																	href='<c:url value="/jobDetail/${interview.application.job.jobId}"/>'>${interview.application.job.title}</a>」工作
																</strong>
															</h6>
															<p></p>
															<strong>邀約類別:</strong>
															${interview.interviewType}<br> 
															<strong>狀態:</strong>
															${interview.interviewStatus}<br> 
															<strong>地點:</strong>
															${interview.interviewPlace}<br> 
															<strong>邀約時間:</strong>
															<fmt:formatDate type="both" dateStyle="short"
																timeStyle="short"
																value="${interview.interviewTime}" />
															<!-- Button trigger modal -->
																	<p></p>
																	<c:if test="${(interview.interviewResult == '等待資料')}">
																	<button type="button"
																		class="btn btn btn-warning btn-sm"
																		data-toggle="modal"
																		data-target="#cancelInterviewModal${interview.interviewId}">
																		<span class="glyphicon glyphicon-thumbs-up"></span>
																		取消邀約
																	</button>
																	</c:if>
																	<hr>
													
													<!--以下是取消邀約彈出視窗的區塊 -->
													<div class="modal fade"
														id="cancelInterviewModal${interview.interviewId}"
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
																		value="${interview.interviewTime}" /><br>
																	與${interview.application.user.userName}邀約？
																</h2>
																<form
																	action="${pageContext.request.contextPath}/updateInterviewResult"
																	method="post">
																	<div class="modal-body">
																		<input type="hidden" name="interviewId"
																			value="${interview.interviewId}"> 
																		<input
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
													<a>沒有任即將開始的邀約或是應徵者前來面試 /上工</a>
												</c:if>
												</div>
												</section>
											</c:if>
											<!------------------------------------------------------------------------ -->
											</div>
											</div>
								</div>
								<!-----------------------即將到來的邀約終--------------------------------------------------->
								<!-- ----------------以下為已完成的邀約------------------------------------------------- -->
								<div class="tab-pane fade" id="pills-profile" 
									role="tabpanel" aria-labelledby="pills-profile-tab">
								<!-- ------------------------------------------------------------------------- -->
									<div class="card-header">
										<i class="fas fa-table"></i>
										<h5>
											<strong>已完成的邀約</strong>
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
												<c:when test="${(interview1.interviewStatus != '待回應') and(current.time gt interviewTime.time)}">
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
						<!-- ------------以上為已完成的邀約終------------------------------------ -->
						<!-- ------------以下為已過期的邀約始------------------------------------ -->
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
					<!-- ------------------------------------------------------------------------- -->
							<div class="card-footer small text-muted">
								Updated
								<fmt:formatDate value="${current}" type="both" />
							</div>
						</div>
							<!-- DataTables Example -->

							<!-- /.container-fluid -->

							<!-- Sticky Footer -->
							<footer class="sticky-footer">
								<div class="container my-auto">
									<div class="copyright text-center my-auto">
										<span>Copyright © Your Website 2019</span>
									</div>
								</div>
							</footer>
						</div>
						<!-- /.content-wrapper -->
						<!-- demo page ended -->
					</section>
				</fieldset>
				<div id="content1"></div>
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

	<script src="https://code.jquery.com/jquery-3.3.1.js"
		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		crossorigin="anonymous"></script>
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