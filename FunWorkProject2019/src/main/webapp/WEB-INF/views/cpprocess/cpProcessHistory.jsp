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
<link rel="shortcut icon" href="<c:url value='/image/favicon.ico'/>">
<link rel="icon" href="<c:url value='/image/favicon.ico'/>">
<title>申訴處理</title>
</head>
<style>

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
						<h3>申訴處理紀錄</h3>
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
						<h5>處理說明</h5>
					</div>
					<div class="col-sm-5">${cpBean.processDescription}(<fmt:formatDate value="${cpBean.processTime}" pattern="yyyy/MM/dd HH:mm" />)</div>
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>