<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				 <%@ include file="/WEB-INF/views/includes/sideNavBar.jsp" %>
			</div>
			<div class="col-sm-8">


				<!--  enctype="multipart/form-data" -->
				<fieldset>
					<section
						style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
						<!-- demo page inserted -->
						<div class="container-fluid">
							<!-- Area Chart Example-->
							<div class="card mb-3">
								<div class="card-header">
									<i class="fas fa-table"></i>
									<h3>最近邀約</h3>
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
										<!-- ------------------------------------------------------------------------- -->	
										<div class="card-body">
											<div class="table-responsive">
												<c:if test="${empty interviewListProcessing}">
													<a>你目前沒有任何邀約，請繼續尋找打工！</a>
												</c:if>
												<c:if test="${!empty interviewListProcessing}">
													<!-- ------------------------------------------------------------------------- -->
													<div style="height: 30px;"></div>
													<section class="container">
														<div class="col-sm-12">
															<c:forEach var="interview1" items="${interviewListProcessing}">
																<div class="row">
																	<div class="col-sm-12">
																		<div class="panel panel-default text-left">
																			<div class="panel-body">
																				<h6>
																					<strong>您收到一則「<a href="#">${interview1.interviewType}</a>」邀約，來自您應徵的「
																						<a
																						href='<c:url value="/jobDetail/${interview1.application.job.jobId}"/>'>${interview1.application.job.title}</a>」工作，請於到期前回覆此邀約
																					</strong>
																				</h6>
																				<p></p>
																				<strong>地點:</strong> ${interview1.interviewPlace}<br>
																				<strong>邀約時間:</strong>
																				<fmt:formatDate type="both" dateStyle="short"
																					timeStyle="short"
																					value="${interview1.interviewTime}" />
																				<br> <strong>雇主問題回應:</strong>
																				${interview1.interviewComment}<br>
																				
																				<!-- Button trigger modal -->	
																				<c:choose>																																			
																				<c:when test="${interview1.interviewStatus == '待回應'}"><br>
																				<button type="button"
																					class="btn btn btn-warning btn-sm"
																					data-toggle="modal"
																					data-target="#interviewModal${interview1.interviewId}">
																					<span class="glyphicon glyphicon-thumbs-up"></span>
																					確定前往
																				</button>
																				<button type="button"
																					class="btn btn btn-danger btn-sm"
																					data-toggle="modal"
																					data-target="#cancelinterviewModal${interview1.interviewId}">
																					<span class="glyphicon glyphicon-thumbs-up"></span>回絕邀約
																				</button>
																				</c:when>
																				<c:otherwise>
																				<br>
																				<a href="">您已${interview1.interviewStatus}此邀約。</a>
																				</c:otherwise>	
																				</c:choose>
																				<hr>
																				<!--以下是確定前往彈出視窗的區塊 -->
																				<div class="modal fade"
																					id="interviewModal${interview1.interviewId}"
																					tabindex="0" role="dialog"
																					aria-labelledby="exampleModalLabel"
																					aria-hidden="true">
																					<div class="modal-dialog" role="document">
																						<div class="modal-content">
																							<br> <a align="center"><img
																								src='<c:url value="/image/prohibit.png"/>'
																								title="編輯" alt="編輯" width="150px"></a><br>
																							<h2 style="text-align: center;">確定要前往此邀約?</h2>
																							<p style="text-align: center;">臨時取消邀約可能會留下紀錄，請詳細閱讀邀約地點和時間</p>
																							<form
																								action="${pageContext.request.contextPath}/updateInterviewStatusOther"
																								method="post">
																								<div class="modal-body">
																									<input type="hidden" name="interviewId"
																										value="${interview1.interviewId}"> <input
																										type="hidden" name="interviewStatus"
																										value="接受">
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
																				<!--以上是確定前往彈出視窗的區塊 -->
																				<!--以下是取消邀約彈出視窗的區塊 -->
																				<div class="modal fade"
																					id="cancelinterviewModal${interview1.interviewId}"
																					tabindex="0" role="dialog"
																					aria-labelledby="exampleModalLabel"
																					aria-hidden="true">
																					<div class="modal-dialog" role="document">
																						<div class="modal-content">
																							<br> <a align="center"><img
																								src='<c:url value="/image/prohibit.png"/>'
																								title="編輯" alt="編輯" width="150px"></a><br>
																							<h2 style="text-align: center;">確定要取消此邀約?</h2>
																							<br>
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
															</c:forEach>
														</div>
													</section>
													<!-- ------------------------------------------------------------------------- -->
												</c:if>
											</div>
										</div>
									<!-- ------------------------------------------------------------------------- -->	
									</div>
									<div class="tab-pane fade" id="pills-profile" role="tabpanel"
										aria-labelledby="pills-profile-tab">
										<div class="card-body">
											<div class="table-responsive">
												<c:if test="${empty interviewListCompleted}">
													<a>你目前沒有任何已完成的邀約</a>
												</c:if>
												<c:if test="${!empty interviewListCompleted}">
													<!-- ------------------------------------------------------------------------- -->
													<div style="height: 30px;"></div>
													<section class="container">
														<div class="col-sm-12">
															<c:forEach var="interview2" items="${interviewListCompleted}">
																<div class="row">
																	<div class="col-sm-12">
																		<div class="panel panel-default text-left">
																			<div class="panel-body">
																				<h6>
																					<strong>您已完成一則「<a href="#">${interview2.interviewType}</a>」邀約，來自您應徵的「
																						<a
																						href='<c:url value="/jobDetail/${interview2.application.job.jobId}"/>'>${interview2.application.job.title}</a>」工作
																					</strong>
																				</h6>
																				<p></p>
																				<strong>地點:</strong> ${interview2.interviewPlace}<br>
																				<strong>邀約時間:</strong>
																				<fmt:formatDate type="both" dateStyle="short"
																					timeStyle="short"
																					value="${interview2.interviewTime}" />
																				<br> <strong>雇主問題回應:</strong>
																				${interview2.interviewComment}<br>
																				<hr>
																			</div>
																			<p>
																		</div>
																	</div>
																</div>
															</c:forEach>
														</div>
													</section>
													<!-- ------------------------------------------------------------------------- -->
												</c:if>
											</div>
										</div>
									<!-- ------------------------------------------------------------------------- -->	
										
										</div>
									<div class="tab-pane fade" id="pills-contact" role="tabpanel"
										aria-labelledby="pills-contact-tab">							
											<div class="card-body">
											<div class="table-responsive">
												<c:if test="${empty interviewListExpired}">
													<a>你目前沒有任何已失效的邀約</a>
												</c:if>
												<c:if test="${!empty interviewListExpired}">
													<!-- ------------------------------------------------------------------------- -->
													<div style="height: 30px;"></div>
													<section class="container">
														<div class="col-sm-12">
															<c:forEach var="interview3" items="${interviewListExpired}">
																<div class="row">
																	<div class="col-sm-12">
																		<div class="panel panel-default text-left">
																			<div class="panel-body">
																				<h6>
																					<strong>您有一則失效的「<a href="#">${interview3.interviewType}</a>」邀約，來自您應徵的「
																						<a
																						href='<c:url value="/jobDetail/${interview3.application.job.jobId}"/>'>${interview3.application.job.title}</a>」工作
																					</strong>
																				</h6>
																				<p></p>
																				<strong>地點:</strong> ${interview3.interviewPlace}<br>
																				<strong>邀約時間:</strong>
																				<fmt:formatDate type="both" dateStyle="short"
																					timeStyle="short"
																					value="${interview3.interviewTime}" />
																				<br> <strong>雇主問題回應:</strong>
																				${interview3.interviewComment}<br>
																				<hr>

																			</div>
																			<p>
																		</div>
																	</div>
																</div>
															</c:forEach>
														</div>
													</section>
													<!-- ------------------------------------------------------------------------- -->
												</c:if>
											</div>
										</div>
									<!-- ------------------------------------------------------------------------- -->	
										
										</div>
								</div>
								<div class="card-footer small text-muted">Updated
									yesterday at 11:59 PM</div>
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