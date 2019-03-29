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
<title>職缺審核</title>
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
						<h3>職缺審核紀錄</h3>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>職缺名稱</h5>
					</div>
					<div class="col-sm-5">${jobBean.title}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>工作類型</h5>
					</div>
					<div class="col-sm-5">${jobBean.industry}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>工作內容</h5>
					</div>
					<div class="col-sm-5">${jobBean.description}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>工作地址</h5>
					</div>
					<div class="col-sm-5">${jobBean.address}(${jobBean.addresssup})</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>時薪</h5>
					</div>
					<div class="col-sm-5">$${jobBean.rateByHour}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>發薪日期</h5>
					</div>
					<div class="col-sm-5">${jobBean.paidDate}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>需求人數</h5>
					</div>
					<div class="col-sm-5">${jobBean.positionNum}人</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>詢問應徵者的問題</h5>
					</div>
					<div class="col-sm-5">${jobBean.other}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>聯絡人</h5>
					</div>
					<div class="col-sm-5">${jobBean.contact}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>聯絡電話</h5>
					</div>
					<div class="col-sm-5">${jobBean.jobPhone}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>聯絡信箱</h5>
					</div>
					<div class="col-sm-5">${jobBean.jobEmail}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>張貼期限</h5>
					</div>
					<div class="col-sm-5"><fmt:formatDate value="${jobBean.postEndDate}" pattern="yyyy/MM/dd HH:mm" /></div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>雇主姓名</h5>
					</div>
					<div class="col-sm-5">${jobBean.jobOwner.userName}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>公司名稱</h5>
					</div>
					<c:choose>
						<c:when test="${jobBean.jobCompany.name != null}">
							<div class="col-sm-5">${jobBean.jobCompany.name}</div>
						</c:when>
						<c:otherwise>
							<div class="col-sm-5">無</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>處理時間</h5>
					</div>
					<div class="col-sm-5"><fmt:formatDate value="${jobBean.submitTime}" pattern="yyyy/MM/dd HH:mm" /></div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>處理說明</h5>
					</div>
					<c:choose>
						<c:when test="${jobBean.failReason != null}">
							<div class="col-sm-5">${jobBean.failReason}</div>
						</c:when>
						<c:otherwise>
							<div class="col-sm-5">審核成功</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-sm-2">預留區塊</div>
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