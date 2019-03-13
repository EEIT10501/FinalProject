<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- <link rel="stylesheet" href="/eeit105finalterm/css/bootstrap.min.css"> -->
<title>公司詳細資料頁面</title>
</head>
<script>
	$(document).ready(function() {
		
// 		var formFields = $('*:not(.no-serialize)', '#newJob').serialize();

		$("#addJobButton").click(function() {
			
			$("#newJob").ajaxSubmit({
				url: contextPath + "/addJobProfile", 
				type: 'post'
				
			})

// 			$.ajax({
// 				url : contextPath + "/newJobPost",
// 				contentType : 'application/json; charset=utf-8',
// 				cache : false,
// 				type : "POST",
// 				data:JSON.stringify(fieldArray),
// 				success : function(result) {
// 						console.log("Success");
// 						$('#newJob').submit
// 				},
// 				error : function(xhr) {
// 					alert("failure");
// 				}
// 			});
		});
	});
</script>
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
	<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
		<a class="navbar-brand" href="#"> <img
			src="/FunWorkProject2019/image/LOGO.jpg" width="30" height="30"
			class="d-inline-block align-top"> EEIT趣打工
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/FunWorkProject2019/">首頁 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">想找打工</a></li>
				<li class="nav-item"><a class="nav-link" href="#">想要徵人</a></li>
				<li class="nav-item"><a class="nav-link" href="#">聯絡我們</a></li>
			</ul>
			<form class="form-inline">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
			<span class="navbar-text"> <a class="nav-link" href="#">登入</a>
			</span> <span class="navbar-text"> <a class="nav-link" href="#">註冊</a>
			</span>
		</div>
	</nav>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<input type="hidden" id="contextPath"
				value="${pageContext.request.contextPath}">
			<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			<div class="col-sm-8">

				<form:form class='form-horizontal' id="newJobPost" modelAttribute="newJobPost" 
				method="POST" enctype="multipart/form-data">
					<fieldset>
						<section
							style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
							<div class="col-md-12 col-lg-8 mb-5">
								<h6>工作單位</h6>
								<hr>
<!-- 								<div class="row form-group"> -->
<!-- 									<div class="col-md-12 mb-3 mb-md-0"> -->
<!-- 										<label for="option-price-1">  -->
<%-- 										<form:input type="checkbox" --%>
<%-- 											id="option-price-1" path="personal"/> <span class="text-success">個人</span> --%>
<!-- 										</label> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-12 mb-3 mb-md-0"> -->
<!-- 										<label for="option-price-2">  -->
<%-- 										<form:input type="checkbox" --%>
<%-- 											id="option-price-2" path="corporal"/> <span class="text-success">公司</span> --%>
<!-- 										</label> -->
<!-- 									</div> -->
<!-- 								</div> -->

								<div class="row form-group">
									<div class="col-md-12 mb-3 mb-md-0">
										<label class="font-weight-bold" for="fullname">Job
											Title</label> 
										<form:input type="text" id="title" class="form-control" path="title"
											placeholder="eg. Professional UI/UX Designer"/>
									</div>
								</div>

								<div class="row form-group mb-5">
									<div class="col-md-12 mb-3 mb-md-0">
										<label class="font-weight-bold" for="fullname">Company</label>
										<form:input type="text" id="industry" class="form-control"
											placeholder="eg. Facebook, Inc." path="industry"/>
									</div>
								</div>


								<!--               <div class="row form-group"> -->
								<!--                 <div class="col-md-12"><h3>Job Type</h3></div> -->
								<!--                 <div class="col-md-12 mb-3 mb-md-0"> -->
								<!--                   <label for="option-job-type-1"> -->
								<!--                     <input type="radio" id="option-job-type-1" name="job-type"> Full Time -->
								<!--                   </label> -->
								<!--                 </div> -->
								<!--                 <div class="col-md-12 mb-3 mb-md-0"> -->
								<!--                   <label for="option-job-type-2"> -->
								<!--                     <input type="radio" id="option-job-type-2" name="job-type"> Part Time -->
								<!--                   </label> -->
								<!--                 </div> -->

								<!--                 <div class="col-md-12 mb-3 mb-md-0"> -->
								<!--                   <label for="option-job-type-3"> -->
								<!--                     <input type="radio" id="option-job-type-3" name="job-type"> Freelance -->
								<!--                 </div> -->
								<!--                 <div class="col-md-12 mb-3 mb-md-0"> -->
								<!--                   <label for="option-job-type-4"> -->
								<!--                     <input type="radio" id="option-job-type-4" name="job-type"> Internship -->
								<!--                   </label> -->
								<!--                 </div> -->
								<!--                 <div class="col-md-12 mb-3 mb-md-0"> -->
								<!--                   <label for="option-job-type-4"> -->
								<!--                     <input type="radio" id="option-job-type-4" name="job-type"> Termporary -->
								<!--                   </label> -->
								<!--                 </div> -->

								<!--               </div> -->

								<!--               <div class="row form-group mb-4"> -->
								<!--                 <div class="col-md-12"><h3>Location</h3></div> -->
								<!--                 <div class="col-md-12 mb-3 mb-md-0"> -->
								<!--                   <input type="text" class="form-control" placeholder="Western City, UK -->
								<!-- "> -->
								<!--                 </div> -->
								<!--               </div> -->



								<!--               <div class="row form-group"> -->
								<!--                 <div class="col-md-12"><h3>Job Description</h3></div> -->
								<!--                 <div class="col-md-12 mb-3 mb-md-0"> -->
								<!--                   <textarea name="" class="form-control" id="" cols="30" rows="5"></textarea> -->
								<!--                 </div> -->
								<!--               </div> -->

								<!--               <div class="row form-group"> -->
								<!--                 <div class="col-md-12"> -->
								<!--                   <input type="submit" value="Post" class="btn btn-primary  py-2 px-5"> -->
								<!--                 </div> -->
								<!--               </div> -->

								<!-- 			   <div class="row form-group mb-4"> -->
								<!--                 <div class="col-md-12"><h3>Contact Info</h3></div> -->
								<!--                 <div class="col-md-12 mb-3 mb-md-0"> -->
								<!-- 				  Address<input type="text" class="form-control" placeholder="203 Fake St. Mountain View, San Francisco, California, USA"> -->
								<!-- 				  Phone<input type="text" class="form-control" placeholder="+1 232 3235 324"> -->
								<!-- 				  Email Address<input type="text" class="form-control" placeholder="JohnSon@gmail.com"> -->
								<!--                 </div> -->
								<!--               </div> -->
								<!--             <div class="p-4 mb-3 bg-white"> -->
								<!--                 <h3 class="h5 text-black mb-3">More Info</h3> -->
								<!--                 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ipsa ad iure porro mollitia architecto hic consequuntur. Distinctio nisi perferendis dolore, ipsa consectetur</p> -->
								<!--                 <p> -->
								<!--               <a href="#" class="btn btn-primary  py-2 px-4">Learn More</a></p> -->
								<!--             </div> -->

								<%--             </form> --%>
								<div class="form-group">
									<div class='col-lg-offset-2 col-lg-10'>
										<input id="addJobButton" type='submit' class='btn btn-primary'
											value="<spring:message code='spring.addProduct.form.submit.label'/>" />
									</div>
								</div>
							</div>
						</section>
					</fieldset>
				</form:form>
				<div id="content1"></div>
			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.js"
		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>