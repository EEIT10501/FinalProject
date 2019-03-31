<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>已應徵工作</title>
</head>

<style>

</style>
<body>
	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
	
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
				 <%@ include file="/WEB-INF/views/includes/sideNavBar.jsp" %>
			</div>
			<div class="col-sm-8">

				<form:form class='form-horizontal' modelAttribute="companyBean"
					method="POST">
					<!--  enctype="multipart/form-data" -->
					<fieldset>
						<section
							style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
							<!-- demo page inserted -->
							
						

      <div class="container-fluid">

        <!-- Area Chart Example-->

<!-- ------------------------------------------------------------------------- -->

        <!-- DataTables Example -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-table"></i>
       		    <h3>最近應徵</h3></div>
          <div class="card-body">
            <div class="table-responsive">
            <c:if test="${empty applicatioList}">
			<a>你目前沒有應徵任何工作，請繼續尋找打工！</a>
			</c:if>
            <c:if test="${!empty applicatioList}">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>工作</th>
                    <th>薪資</th>
                    <th>給僱主的話</th>
                    <th>狀態</th>
                    <th>應徵時間</th>
                    <th>聯絡雇主</th>
                  </tr>
                </thead>
                <c:forEach var="applicatioList" items="${applicatioList}">
                  <tr>
                    <td><a href='<c:url value="/jobDetail/${applicatioList.job.jobId}"/>'>${applicatioList.job.title}</a></td>
                    <td>${applicatioList.job.rateByHour}</td>
                    <td>${applicatioList.answer}</td>
                    <td>${applicatioList.appliedStatus}</td>
					<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${applicatioList.applicationTime}" /></td>
                    <td><a href="${pageContext.request.contextPath}/chat/${applicatioList.applicationId}">
						<button type="button" class="btn btn-primary btn-sm" style="float:inherit;">									
						<span class="glyphicon glyphicon-thumbs-up"></span>傳訊給雇主
						</button></a></td>
                  </tr>
                 </c:forEach>
                </tbody>
              </table>
              </c:if>
            </div>
          </div>
          <div class="card-footer small text-muted"></div>
        </div>
      <!-- /.container-fluid -->

      <!-- Sticky Footer -->
      <footer class="sticky-footer">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright © Your Website 2019</span>
          </div>
        </div>
      </footer>

    </div>
    <!-- /.content-wrapper -->
							
							
							<!-- demo page ended -->
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