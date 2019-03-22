<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
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
				<section>
					<div>
						<div class="container" style="text-align: center; font: 微軟正黑體">
							<h1>
								<strong>工作職缺內容</strong>
							</h1>
						</div>
					</div>
				</section>
				<div style="height: 30px;"></div>
				<section class="container">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-12">
								<div class="panel panel-default text-left">
									<div class="panel-body">
										<h3>
											<strong><a href='<c:url value="applications?id=${job.jobId}"/>'>${job.title}</a></strong>
										</h3>
										<p></p>
										<strong>單位名稱:</strong> ${job.jobCompany.name}<br> 
										<strong>單位地址:</strong>${job.address}<br> 
										<strong>職缺點擊次數:</strong>${job.viewTimes}<br> 
										<strong>
										 剩<fmt:parseNumber value="${(job.postEndDate.time - now.time) / (1000*60*60*24) }" integerOnly="true" />天刊登時間
										</strong>
										<p>
										<hr>
										<c:choose>
											<c:when test="${job.isExposure}">
											<a href="<c:url value="/jobExposure/${job.jobId}"/>">
											<button type="button" class="btn btn btn-primary btn-sm" id="exp">
												<span class="glyphicon glyphicon-thumbs-up"></span>取消置頂
											</button>
											</a>
											</c:when>
											<c:otherwise>
											<a href="<c:url value="/jobExposure/${job.jobId}"/>">
	                                        <button type="button" class="btn btn btn-primary btn-sm" id="exp">
	                                            <span class="glyphicon glyphicon-thumbs-up"></span>置頂曝光
	                                        </button>
	                                        </a>
											</c:otherwise>
										</c:choose>
										<a href="<c:url value="/modJobProfile?jobId=${job.jobId}"/>">
										<button type="button" class="btn btn btn-primary btn-sm">
											<span class="glyphicon glyphicon-thumbs-up"></span>編輯
										</button>
										</a>
										<a href="<c:url value="/replicate?jobId=${job.jobId}"/>">
										<button name="replicate" value="複製" type="button" class="btn btn btn-primary btn-sm">
											<span class="glyphicon glyphicon-thumbs-up"></span>複製
										</button>
										</a>
<%-- 										<form action='<c:url value="/replicate"/>' method='post' id='jobRep'> --%>
<!-- 										<input type='hidden' id='product_id' name='product_id' value=''> -->
<!-- 										</form> -->
<!-- 										<script> -->
<!-- // 										　function which_one(product_icon){ -->
<!-- // 　											　$('#product_id').val(product_icon);  -->
<!-- // 　											　$('#jobRep').submit(); -->
<!-- // 　											} -->
<!-- 										</script> -->
										<c:choose>
                                            <c:when test="${job.isFilled}">
												<a href="<c:url value="/jobFilled/${job.jobId}"/>">
												<button type="button" class="btn btn-warning btn-sm" style="float: right">
													<span class="glyphicon glyphicon-thumbs-up"></span>取消額滿
												</button>
											    </a>  
										    </c:when>
										    <c:otherwise>
	                                            <a href="<c:url value="/jobFilled/${job.jobId}"/>">
	                                            <button type="button" class="btn btn-warning btn-sm" style="float: right">
	                                                <span class="glyphicon glyphicon-thumbs-up"></span>設為額滿
	                                            </button>
	                                            </a>
                                            </c:otherwise>
										</c:choose>
									</div>
									<p>
								</div>
							</div>
						</div>
						<div class="row">
						<div class="col-sm-12">
						<small style="color:red" id="cantExp"></small>
						</div>
						</div>
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
	<script type="text/javascript">
		$(document).ready(function() {
			if ("${error1}" != "") {
				alert("發布中的工作無法修改");
			}
			
			if ("${error2}" != "") {
				alert("超出工作刊登上限額度");
			}
		});
	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script>
	   $(function() {
		   $.ajax({
               url : "${pageContext.request.contextPath}/jobExposureCount/${sessionScope.loginUser.userId}",
               type : "GET",
               success : function(data) {
                 if(data>=${sessionScope.loginUser.exposureLimit}){
                	 $("#exp").attr('disabled',true);
                	 $("#cantExp").html("您目前已無置頂額度");
                 }
               }
           }); 
	   });
	</script>
</body>
</html>