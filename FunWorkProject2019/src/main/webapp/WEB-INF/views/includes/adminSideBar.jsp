<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css" />

<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<title>側邊SideBar</title>

<style type="text/css">
.card {
background-color: #e3f2fd;
}

.card-header {
background-color: #e3f2fd;
}

</style>
</head>
<body>
	<div class="accordion text-center" id="accordionExample">
		<div class="card">
			<div class="card-header" id="heading1">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button" data-toggle="collapse"
						data-target="#collapse1" aria-expanded="true"
						aria-controls="collapse1">職缺作業</button>
				</h2>
			</div>

			<div id="collapse1" class="collapse" aria-labelledby="heading1"
				data-parent="#accordionExample">
				<div class="card-body">
					<a class="btn btn-link" href="<c:url value='/jobsReview'></c:url>">職缺審核</a>
					<a class="btn btn-link" href="<c:url value='/jobsReviewHistory'></c:url>">職缺紀錄</a>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" id="heading2">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button"
						data-toggle="collapse" data-target="#collapse2"
						aria-expanded="false" aria-controls="collapse2">公司作業</button>
				</h2>
			</div>
			<div id="collapse2" class="collapse" aria-labelledby="heading2"
				data-parent="#accordionExample">
				<div class="card-body">
					<a class="btn btn-link" href="<c:url value='/companysReview'></c:url>">公司審核</a>
					<a class="btn btn-link"
						href="<c:url value='/companysReviewHistory'></c:url>">公司紀錄</a>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" id="heading3">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button"
						data-toggle="collapse" data-target="#collapse3"
						aria-expanded="false" aria-controls="collapse3">問題回報</button>
				</h2>
			</div>
			<div id="collapse3" class="collapse" aria-labelledby="heading3"
				data-parent="#accordionExample">
				<div class="card-body">
					<a class="btn btn-link" href="<c:url value='/cpsProcess'></c:url>">申訴處理</a> <a
						class="btn btn-link" href="<c:url value='/cpsHistory'></c:url>">申訴紀錄</a>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" id="heading4">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button" data-toggle="collapse"
						data-target="#collapse4" aria-expanded="false"
						aria-controls="collapse4">會員專區</button>
				</h2>
			</div>
			<div id="collapse4" class="collapse" aria-labelledby="heading4"
				data-parent="#accordionExample">
				<div class="card-body">
					<a class="btn btn-link" href="<c:url value='#'></c:url>">查詢會員</a> <a
						class="btn btn-link" href="<c:url value='#'></c:url>">加值紀錄</a>
				</div>
			</div>
		</div>
		
	</div>

</body>
</html>