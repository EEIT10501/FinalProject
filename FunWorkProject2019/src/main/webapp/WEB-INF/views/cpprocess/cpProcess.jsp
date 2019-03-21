<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>申訴處理</title>
</head>
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
<body>
	<%@ include file="/WEB-INF/views/includes/adminnavbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
                <jsp:include page="/WEB-INF/views/includes/adminSideBar.jsp"></jsp:include>
            </div>
			<div class="col-sm-8 showjobdetail">
				<div class="row">
					<div class="col-sm-12">
						<h3>申訴處理</h3>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>申訴類別</h5>
					</div>
					<div class="col-sm-5">${cpBean.type}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>申訴內容</h5>
					</div>
					<div class="col-sm-5">${cpBean.content}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>申訴職缺</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.title}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>雇主姓名</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.jobOwner.userName}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>工作類型</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.industry}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>工作內容</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.description}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>工作地址</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.address}(${cpBean.job.addresssup})</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>時薪</h5>
					</div>
					<div class="col-sm-5">$${cpBean.job.rateByHour}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>發薪日期</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.paidDate}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>需求人數</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.positionNum}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>詢問應徵者的問題</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.other}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>聯絡人</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.contact}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>聯絡電話</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.jobPhone}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>聯絡信箱</h5>
					</div>
					<div class="col-sm-5">${cpBean.job.jobEmail}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>公司名稱</h5>
					</div>
					<c:choose>
						<c:when test="${cpBean.job.jobCompany.name != null}">
							<div class="col-sm-5">${cpBean.job.jobCompany.name}</div>
						</c:when>
						<c:otherwise>
							<div class="col-sm-5">無</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>提交時間</h5>
					</div>
					<div class="col-sm-5"><fmt:formatDate value="${cpBean.submitTime}" pattern="yyyy/MM/dd HH:mm" /></div>
				</div>
				<div class="row justify-content-center">
					<form action="<c:url value='/cpProcess/${cpBean.complaintId}'/>" method="post" id="isRemoveForm">
						<input type="hidden" id="isRemove" name="isRemove" value="">
						<input type="hidden" id="jobid" name="jobId" value="${cpBean.job.jobId}">
						<input type="button" class="btn btn-info btn-lg" id="remove" value="職缺下架" />
						<button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#myModal">結案</button>
						<div class="modal fade" id="myModal" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">請輸入結案原因</h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>
									<div class="modal-body">
										<p>結案原因</p>
										<input type="text" name="closeReason" class="form-control">
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-info" data-dismiss="modal" id="close">送出</button>
										<button type="button" class="btn btn-danger" data-dismiss="modal">關閉</button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<script>
					$("#remove").click(function() {
						$("#isRemove").attr("value", "remove");
						$("#isRemoveForm").submit();
					});
					$("#close").click(function() {
						$("#isRemove").attr("value", "close");
						$("#isRemoveForm").submit();
					});
				</script>
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>