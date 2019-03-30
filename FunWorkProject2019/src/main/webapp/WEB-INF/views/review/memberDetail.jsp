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
<title>會員查詢</title>
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
				<h1>會員查詢</h1>
				<c:choose>
				    <c:when test="${empty user}">
						<div class="row justify-content-center">
		                    <div class="col-sm-3">
		                        <h5 style="color:red">查無此會員</h5>
		                    </div>
		                </div>
	                </c:when>
	                <c:otherwise>
	                   <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>大頭照</h5>
                            </div>
                            <div class="col-sm-5">
                                <img width='100' height='100' src="<c:url value='/getPicture/${user.userId}'/>">
                            </div>
                        </div>
	                    <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>會員編號</h5>
                            </div>
                            <div class="col-sm-5">${user.userId}</div>
                        </div>
                         <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>姓名</h5>
                            </div>
                            <div class="col-sm-5">${user.userName}</div>
                        </div>
                         <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>信箱</h5>
                            </div>
                            <div class="col-sm-5">${user.email}</div>
                        </div>
                         <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>連絡電話</h5>
                            </div>
                            <div class="col-sm-5">${user.phoneNum}</div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>會員等級</h5>
                            </div>
                            <c:choose>
	                            <c:when test="${user.mebershipLevel == 1}">
	                               <div class="col-sm-5">免費會員</div>
	                            </c:when>
	                             <c:when test="${user.mebershipLevel == 2}">
                                   <div class="col-sm-5">黃金會員(到期日:<fmt:formatDate value="${user.vipEndDate}" pattern="yyyy/MM/dd" />)</div>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>職缺曝光額度</h5>
                            </div>
                            <div class="col-sm-5">${user.exposureLimit}件<span id="expNum"></span></div>
                        </div>
                        <c:choose>
                                <c:when test="${user.mebershipLevel == 1}">
                                   <div class="row justify-content-center">
						                 <div class="col-sm-3">
						                     <h5>職缺刊登額度</h5>
						                 </div>
						                 <div class="col-sm-5">${user.jobPostLimit}件<span id="postNum"></span></div>
			                       </div>
                                </c:when>
                                 <c:when test="${user.mebershipLevel == 2}">
                                   <div class="row justify-content-center">
			                          <div class="col-sm-3">
			                              <h5>職缺刊登額度</h5>
			                          </div>
			                          <div class="col-sm-5">無上限<span id="postNum"></span></div>
			                      </div>
                                </c:when>
                            </c:choose>
                        
                        <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>職缺刊登期限</h5>
                            </div>
                            <div class="col-sm-5">${user.jobPostPeriod}日</div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>會員評分</h5>
                            </div>
                            <div class="col-sm-5">${user.rating}</div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>應約次數</h5>
                            </div>
                            <div class="col-sm-5">${user.presence}次</div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-sm-3">
                                <h5>缺席次數</h5>
                            </div>
                            <div class="col-sm-5">${user.abscence}次</div>
                        </div>
	                </c:otherwise>
                </c:choose>
			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
	</div> 
	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script>
	$(function() {
		$.ajax({
            url : "${pageContext.request.contextPath}/jobExposureCount/${user.userId}",
            type : "GET",
            success : function(data) {                
                $("#expNum").html("(目前曝光：" + data + "件)");          
            }
        }); 
		
		$.ajax({
            url : "${pageContext.request.contextPath}/getJobPostedCount/${user.userId}",
            type : "GET",
            success : function(data) {                
                $("#postNum").html("(目前刊登：" + data + "件)");          
            }
        }); 
	});
	</script>
</body>
</html>