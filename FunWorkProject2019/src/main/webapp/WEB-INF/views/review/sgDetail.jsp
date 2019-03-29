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
<title>建議回報</title>
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
						<h3>建議回報</h3>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>姓名</h5>
					</div>
					<div class="col-sm-5">${sgBean.name}</div>
				</div>
				<div class="row justify-content-center">
                    <div class="col-sm-3">
                        <h5>建議內容</h5>
                    </div>
                    <div class="col-sm-5">${sgBean.comment}</div>
                </div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>聯絡信箱</h5>
					</div>
					<div class="col-sm-5">${sgBean.email}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3">
						<h5>提交時間</h5>
					</div>
					<div class="col-sm-5"><fmt:formatDate value="${sgBean.submitTime}" pattern="yyyy/MM/dd HH:mm" /></div>
				</div>
				<div class="row justify-content-center">
                    <div class="col-sm-3">
                        <button type="button" class="btn btn-link pl-0" data-toggle="modal" data-target="#attachment">
                            <h5>查看附件</h5>
                        </button>
                    </div>
                    <div class="col-sm-5"></div>
                    <div class="modal fade" id="attachment" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-body">
                                       <img class="img-rounded" width="100%" src="<c:url value='/getSgPicture/${sgBean.suggestionId}'/>">
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
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>