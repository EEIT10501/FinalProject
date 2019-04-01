<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/DataTables/datatables.min.css/'></c:url>">
	<link rel="shortcut icon" href="<c:url value='/image/favicon.ico'/>">
<link rel="icon" href="<c:url value='/image/favicon.ico'/>">

<title>找工作</title>


<style>

.btn-group {
	margin-bottom: 5px;
}

#map {
	height: 400px;
	margin-bottom: 5px;
}

#jobtable img{
width:30px;
margin:0px;
padding:0px;

}

#jobtable {
font-weight:700;
color:#03353E;
}

.jobtitle{
color:#0878A4;
}

.jobtitle:hover{
color:#8A140E;
font-weight:900;
text-decoration:none;
}

.jobcompany{
font-weight:700;
color:#0294A5;
}

.jobcompany:hover{
color:#003D73;
text-decoration:none;
}

.citybutton{
background-color:white;
color:#0878A4;
border:1px solid #0878A4;
font-weight:700;

}

.citybutton:hover{
background-color:#0878A4;
color:white;
}

.citybutton:focus{
background-color:#0878A4;
color:white;
}

.cityname{
color:#0878A4;
font-weight:600;
margin:7px;
}

.cityname:hover{
background-color:#1ECFD6;
color:white;
text-decoration:none;
}

.dropdown-menu{
width:200px;
}
</style>

</head>

<body>


	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>

	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around align-items-center">

			<div class="col-sm-7 text-center">
				<!--             程式寫在這 -->
				<div class="btn-group">
					<button type="button" class="btn shadow-sm citybutton dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<c:if test="${citys!=null}">${citys[0].cityArea}</c:if><c:if test="${empty citys}">選擇縣市</c:if></button>
					<div class="dropdown-menu">
						<a class="cityname" href="<c:url value='/cityArea/5'/>">台北市</a>
						<a class="cityname" href="<c:url value='/cityArea/15'/>">新北市</a>

					</div>
				</div>

				<div class="btn-group">
					<button type="button" class="btn shadow-sm citybutton dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<c:if test="${city!=null}">${city.cityName}</c:if><c:if test="${empty city}">選擇行政區</c:if></button>
					<div class="dropdown-menu">
						<c:forEach var="city" items="${citys}">
							<a class="cityname" href="<c:url value='/cityName/${city.cityId}'/>">${city.cityName}</a>
						</c:forEach>
					</div>
				</div>

				<c:if test="${empty jobs}">
					<h3 style="color: black">該區域目前無工作，請選擇其他區域</h3>
				</c:if>

				<table class="table table-hover dataTable" id="jobtable">
					<thead>
						<tr class="align-middle">
						    <th>推薦</th>
							<th>行業</th>
							<th>名稱</th>
							<th>地區</th>
							<th>聯絡人</th>
							<th>截止時間</th>		
						</tr>
					</thead>
					<tbody>
						<c:forEach var="job" items="${jobs}">
							<c:if test="${job.isExposure==true}">
								<tr>
									<td class="align-middle"><img class="img-fluid" src="<c:url value='/image/staryellow.png'/>"></td>
									<td class="align-middle">${job.industry}</td>
									<td class="text-left align-middle"><a class="jobtitle" href="<c:url value='/jobDetail/${job.jobId}'/>" class="">${job.title}</a>
									<p style="font-size:12px">
									<a class="jobcompany" href='<spring:url value="company?id=${job.jobCompany.companyId}"/>'>${job.jobCompany.name}</a>
										<c:if test="${job.jobCompany.name==null}">個人</c:if></p></td>
									<td class="align-middle">${job.city.cityName}</td>
									<td class="align-middle">${job.jobOwner.userName}</td>
									<td class="align-middle"><fmt:formatDate type="DATE" value="${job.postEndDate}" /></td>					
								</tr>
							</c:if>
						</c:forEach>
						<c:forEach var="job" items="${jobs}">
							<c:if test="${job.isExposure==false}">
								<tr>
								    <td class="align-middle">一般</td>
								    <td class="align-middle">${job.industry}</td>
									<td class="text-left align-middle"><a class="jobtitle" href="<c:url value='/jobDetail/${job.jobId}'/>" class="">${job.title}</a>
									<p style="font-size:12px">
									<a class="jobcompany" href='<spring:url value="company?id=${job.jobCompany.companyId}"/>'>${job.jobCompany.name}</a>
										<c:if test="${job.jobCompany.name==null}">個人</c:if></p>
									</td>
									<td class="align-middle">${job.city.cityName}</td>
									<td class="align-middle">${job.jobOwner.userName}</td>
									<td class="align-middle"><fmt:formatDate type="DATE" value="${job.postEndDate}" /></td>							
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-5">
				<div id="map"></div>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBw-HiRWQLCjwq6fWJ-tFBcxECgNjWZZus&callback=initMap" async defer></script>
<script>
	$(document).ready(function() {
		$("#jobtable").DataTable();

	});

	var map, infoWindow;
	function initMap() {
		var latlng = new google.maps.LatLng(25.052,121.532);
		map = new google.maps.Map(document.getElementById("map"), {
			center : latlng,
			zoom : 12
		});

		infoWindow = new google.maps.InfoWindow;

		// Try HTML5 geolocation.
// 		if (navigator.geolocation) {
// 			navigator.geolocation.getCurrentPosition(function(position) {
// 				var pos = {
// 					lat : position.coords.latitude,
// 					lng : position.coords.longitude
// 				};

// 				infoWindow.setPosition(pos);
// 				infoWindow.setContent("我的位置");
// 				infoWindow.open(map);
// 				map.setCenter(pos);
// 			}, function() {
// 				handleLocationError(true, infoWindow, map.getCenter());
// 			});
// 		} else {
// 			// Browser doesn't support Geolocation
// 			handleLocationError(false, infoWindow, map.getCenter());
// 		}
		addMarker()
	}

	function addMarker() {
		<c:forEach var="job" items="${jobs}"> 
			var latLng = new google.maps.LatLng(${job.jobLat}, ${job.jobLng});
			var marker${job.jobId} = new google.maps.Marker({
				position : latLng,
				map : map,
				title: "${job.title}"
			});
			
			var contentString = "<div><h6>${job.title}<h6></div><div style='margin-bottom:5px'>${job.address}</div><a href='<c:url value='/jobDetail/${job.jobId}'/>' class='btn btn-primary'><span class='glyphicon-info-sigh glyphicon'></span> 詳細資料 </a>"
			var infowindow${job.jobId} = new google.maps.InfoWindow({
			    content: contentString
			  });
			
			marker${job.jobId}.addListener("click", function() {
		          infowindow${job.jobId}.open(map, marker${job.jobId});
		        });
			</c:forEach>
		}
	

// 	function handleLocationError(browserHasGeolocation, infoWindow, pos) {
// 		infoWindow.setPosition(pos);
// 		infoWindow
// 				.setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
// 						: 'Error: Your browser doesn\'t support geolocation.');
// 		infoWindow.open(map);
// 	}
</script>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value='/DataTables/datatables.min.js/'></c:url>"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>