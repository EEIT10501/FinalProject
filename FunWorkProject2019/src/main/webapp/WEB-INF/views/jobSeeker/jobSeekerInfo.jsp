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
<title>基本資訊</title>
</head>

<style>

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

							<!-- Breadcrumbs-->
							<ol class="breadcrumb">
								<li class="breadcrumb-item active"><h3>近期信用評價</h3></li>
							</ol>

							<!-- Icon Cards-->
							<a>當你應徵一份工作時，雇主可以看到你近三個月的信用評價，提升應約次數減少缺席次數，有助於提升你的錄取機率。</a><br>
							<a href="" data-toggle="modal" data-target="#note"><span
								class="glyphicon glyphicon-thumbs-up"></span>了解更多</a>

							<!--以下是了解更多彈出視窗的區塊 -->
							<div class="modal fade" id="note" tabindex="0" role="dialog"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<br>
										<div style="margin: 10px">
											<h4 style="">什麼是出席/缺席次數</h4>
											<p style="">出/缺席次數為平台中「邀約管理」的使用紀錄，當雇主邀請你來「面試/上工」，且確定有出席時，就可以增加出席次數；反之，雇主可以標記為缺席。</p>
											<br>
											<h4 style="">對我有什麼影響？</h4>
											<p style="">越多的出席次數可以讓你越快找到工作！根據調查，出席次數越高，雇主願意採用的意願也越高。</p>
											<br>
											<h4 style="">要如何增加出席紀錄</h4>
											<p style="">當你面試/上工一份工作時，雇主會發送一則「邀約」給你。確認「日期、時間、地點」都沒問題後，即可接受邀約。等到邀約約定時間一到，並且確實出席，雇主就可以標記「應約」，出席次數就會增加。</p>
											<br>
											<h4 style="">若有缺席紀錄怎麼辦</h4>
											<p style="">若不幸有缺席紀錄，也別擔心，缺席紀錄只會保留 3 個月，3 個月後紀錄即重置。</p>
											<br>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-xl-3 col-sm-6 mb-3">
									<div class="card text-white bg-primary o-hidden h-100">
										<div class="card-body">
											<div class="card-body-icon">
												<i class="fas fa-fw fa-life-ring"></i>
											</div>
											<h2>${loginUser.presence}</h2>
										</div>
										<a class="card-footer text-white clearfix small z-1" href="#">
											<span class="float-left">近期邀約應約次數</span> <span
											class="float-right"> <i class="fas fa-angle-right"></i>
										</span>
										</a>
									</div>
								</div>
								<div class="col-xl-3 col-sm-6 mb-3">
                                    <div class="card text-white bg-danger o-hidden h-100">
                                        <div class="card-body">
                                            <div class="card-body-icon">
                                                <i class="fas fa-fw fa-life-ring"></i>
                                            </div>
                                            <h2>${loginUser.abscence}</h2>
                                        </div>
                                        <a class="card-footer text-white clearfix small z-1" href="#">
                                            <span class="float-left">近期邀約缺席次數</span> <span
                                            class="float-right"> <i class="fas fa-angle-right"></i>
                                        </span>
                                        </a>
                                    </div>
                                </div>
							</div>

							<!-- Area Chart Example-->
							<div class="card mb-3">
								<div class="card-header">
									<i class="fas fa-table"></i>
									<h3>最近邀約</h3>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<c:if test="${empty interviewList}">
											<a>你目前沒有任何邀約，請繼續尋找打工！</a>
										</c:if>
										<c:if test="${!empty interviewList}">
											<!-- ------------------------------------------------------------------------- -->
											<div style="height: 30px;"></div>
											<section class="container">
												<div class="col-sm-12">
													<c:forEach var="interview" items="${interviewList}">
														<div class="row">
															<div class="col-sm-12">
																<div class="panel panel-default text-left">
																	<div class="panel-body">
																		<h6>
																			<strong>您收到一則「<a style="color:blue;";>${interview.interviewType}</a>」邀約，來自您應徵的工作「
																				<a
																				href='<c:url value="/jobDetail/${interview.application.job.jobId}"/>'>${interview.application.job.title}</a>」，請於到期前回覆此邀約
																			</strong>
																		</h6>
																		<p></p>
																		<strong>地點:</strong> ${interview.interviewPlace}<br>
																		<strong>邀約時間:</strong>
																		<fmt:formatDate type="both" dateStyle="short"
																			timeStyle="short" value="${interview.interviewTime}" />
																		<br> <strong>雇主問題回應:</strong>
																		${interview.interviewComment}<br>
																		<!-- Button trigger modal -->
																		<c:choose>																																			
																		<c:when test="${interview.interviewStatus == '待回應'}">
																				<br>
																				<button type="button"
																					class="btn btn btn-warning btn-sm"
																					data-toggle="modal"
																					data-target="#interviewModal${interview.interviewId}">
																					<span class="glyphicon glyphicon-thumbs-up"></span>
																					確定前往
																				</button>
																				<button type="button"
																					class="btn btn btn-danger btn-sm"
																					data-toggle="modal"
																					data-target="#cancelinterviewModal${interview.interviewId}">
																					<span class="glyphicon glyphicon-thumbs-up"></span>回絕邀約
																				</button>
																			</c:when>
																			<c:otherwise>
																				<br>
																				<a style="color:blue;">您已${interview.interviewStatus}此邀約。</a>
																			</c:otherwise>
																		</c:choose>
																		<hr>
																		<!--以下是確定前往彈出視窗的區塊 -->
																		<div class="modal fade"
																			id="interviewModal${interview.interviewId}"
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
																					<form action="${pageContext.request.contextPath}/updateInterviewStatus" method="post">
																						<div class="modal-body">
																							<input type="hidden" name="interviewId"
																								value="${interview.interviewId}"> <input
																								type="hidden" name="interviewStatus" value="接受">
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
																			id="cancelinterviewModal${interview.interviewId}"
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
																						action="${pageContext.request.contextPath}/updateInterviewStatus"
																						method="post">
																						<div class="modal-body">
																							<input type="hidden" name="interviewId"
																								value="${interview.interviewId}"> <input
																								type="hidden" name="interviewStatus" value="拒絕">
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

								<div class="card-footer small text-muted"></div>
							</div>

							<!-- DataTables Example -->
							<div class="card mb-3">
								<div class="card-header">
									<i class="fas fa-table"></i>
									<h3>最近應徵</h3>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<c:if test="${empty applicatioList}">
											<a>你目前沒有應徵任何工作，請繼續尋找打工！</a>
										</c:if>
										<c:if test="${!empty applicatioList}">
											<table class="table table-bordered" id="dataTable"
												width="100%" cellspacing="0">
												<thead>
													<tr>
														<th>工作</th>
														<th>薪資</th>
														<th>給僱主的話</th>
														<th>狀態</th>
														<th>應徵時間</th>
														<th>聯絡雇主</th>
													</tr>
												</thead>
												<c:forEach var="applicatioList" items="${applicatioList}">
													<tr>
														<td><a
															href='<c:url value="/jobDetail/${applicatioList.job.jobId}"/>'>${applicatioList.job.title}</a></td>
														<td>${applicatioList.job.rateByHour}</td>
														<td>${applicatioList.answer}</td>
														<td>${applicatioList.appliedStatus}</td>
														<td><fmt:formatDate type="both" dateStyle="short"
																timeStyle="short"
																value="${applicatioList.applicationTime}" /></td>
														<td><a href="${pageContext.request.contextPath}/chat/${applicatioList.applicationId}">
																<button type="button" class="btn btn-primary btn-sm" style="float:inherit;">
																	<span class="glyphicon glyphicon-thumbs-up"></span>傳訊給雇主
																</button>
														</a></td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</c:if>
									</div>
								</div>
								<div class="card-footer small text-muted"></div>
							</div>
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
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights
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