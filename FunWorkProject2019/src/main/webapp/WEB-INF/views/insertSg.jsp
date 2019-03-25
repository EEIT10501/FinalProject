<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<title>帳戶設定</title>
<style>
.card-text-size {
    font-size: 14px;
}
.footerbackground {
    background: #343a40;
    color: white;
}
.asideblock {
    height: 600px;
}
</style>
</head>
<body>
    <%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
    <div style="height: 4rem"></div>
    <div class="container-fluid">
        <div class="row m-5 justify-content-around">
            <div class="col-sm-2"></div>
            <div class="col-sm-8 align-self-center">
                <form:form method="post" modelAttribute="sgBean" enctype="multipart/form-data">
                    <fieldset>
                        <h1>聯絡我們</h1>
                        <p style="color:red">${okMsg}</p>
                        <div class="form-group row">
                            <label for="name" class="col-sm-2 col-form-label">姓名:</label>
                            <div class="col-sm">
                                <form:input type="text" path="name" class="form-control"
                                            id="name" autocomplete="on" value="${name}"
                                            required="required" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="email" class="col-sm-2 col-form-label">Email:</label>
                            <div class="col-sm">
                                <form:input type="email" path="email" class="form-control"
                                            id="email" required="required" value="${email}"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="comment" class="col-sm-2 col-form-label">需要什麼協助：</label>
                            <div class="col-sm">
                                <form:textarea path="comment" rows="5" class="form-control"
                                               id="comment" required="required" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="attachment" class="col-sm-2 col-form-label">附件：</label>
                            <div class="col-sm">
                                <form:input type="file" path="attachmentPart" class="form-control"
                                            accept="image/gif, image/jpeg, image/png" 
                                            id="attachment" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm">
                                <input type="submit" class="btn btn-primary" id="submit" value="送出" /> 
                            </div>
                        </div>
                    </fieldset>
                </form:form>
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