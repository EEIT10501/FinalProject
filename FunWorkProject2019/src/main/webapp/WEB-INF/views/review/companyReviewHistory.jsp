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
<title>公司審核紀錄</title>
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
						<h3>公司審核紀錄</h3>
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