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
<title>公司審核</title>
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
				<div class="list-group">
					<a href="<c:url value='/companysReview'/>" class="list-group-item list-group-item-action">公司審核</a> 
					<a href="<c:url value='#'/>" class="list-group-item list-group-item-action">審核紀錄</a>
				</div>
			</div>
			<div class="col-sm-8 showjobdetail">
				<div class="row">
					<div class="col-sm-12">
						<h3>公司審核</h3>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>公司名稱</h5>
					</div>
					<div class="col-sm-5">${companyBean.name}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>公司統一編號</h5>
					</div>
					<div class="col-sm-5">${companyBean.taxId}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>公司登記地址</h5>
					</div>
					<div class="col-sm-5">${companyBean.address}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>雇主姓名</h5>
					</div>
					<div class="col-sm-5">${companyBean.user.userName}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>聯絡信箱</h5>
					</div>
					<div class="col-sm-5">${companyBean.user.email}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>提交時間</h5>
					</div>
					<div class="col-sm-5"><fmt:formatDate value="${companyBean.submitTime}" pattern="yyyy/MM/dd HH:mm" /></div>
				</div>
				<div class="row justify-content-center">
                    <div class="col-sm-3">
                        <button type="button" class="btn btn-link pl-0" data-toggle="modal" data-target="#licensure">
                            <h5>身分驗證文件</h5>
                        </button>
                    </div>
                    <div class="col-sm-5"></div>
                    <div class="modal fade" id="licensure" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-body">
                                       <img class="img-rounded" width="100%" src="<c:url value='/getLicPicture/${companyBean.companyId}'/>">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">關閉</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
				<div class="row justify-content-center">
					<form action="<c:url value='/companyReview/${companyBean.companyId}'/>" method="post" id="isPassForm">
						<input type="hidden" id="isPass" name="isPass" value=""> 
						<input type="button" class="btn btn-info btn-lg" id="pass" value="審核通過" />
						<button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#myModal">審核失敗</button>
						<div class="modal fade" id="myModal" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title">請輸入審核失敗原因</h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>
									<div class="modal-body">
										<p>審核失敗原因</p>
										<input type="text" name="failReason" class="form-control">
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-info" data-dismiss="modal" id="fail">送出</button>
										<button type="button" class="btn btn-danger" data-dismiss="modal">關閉</button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<script>
					$("#pass").click(function() {
						$("#isPass").attr("value", "pass");
						$("#isPassForm").submit();
					});
					$("#fail").click(function() {
						$("#isPass").attr("value", "fail");
						$("#isPassForm").submit();
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