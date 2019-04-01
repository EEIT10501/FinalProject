<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="<c:url value='/DataTables/datatables.min.css/' />">
<link rel="shortcut icon" href="<c:url value='/image/favicon.ico'/>">
<link rel="icon" href="<c:url value='/image/favicon.ico'/>">
<title>會員查詢</title>
</head>
<style>

</style>
<body>
	<%@ include file="/WEB-INF/views/includes/adminnavbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
                <jsp:include page="/WEB-INF/views/includes/adminSideBar.jsp"></jsp:include>
            </div>
			<div class="col-sm-8">
				<h1>會員查詢</h1>
				<form action="<c:url value='/queryMember'/>" method="post">
				<div class="form-group row">
				    <div class="col-sm-10">
				        <input class="form-control" type="email" name="email" placeholder="請輸入會員Eamil" required="required">
				    </div>
				     <div class="col-sm-2">
				        <input class="btn btn-info" type="submit" value="送出">
				    </div>
				</div>
				</form>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div> 
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
</body>
</html>