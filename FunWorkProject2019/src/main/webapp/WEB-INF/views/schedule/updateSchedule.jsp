<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<link type="text/css" rel="stylesheet"
	href="/FunWorkProject2019/css/jquery.simple-color-picker.css"
	media="screen, projection" />
<script type="text/javascript"
	src='<c:url value="/js/jquery.simple-color-picker.js"/>'></script>


<script>

	$('#datepairExample .time').timepicker({
		'showDuration' : true,
		'timeFormat' : 'H:i:s',
	    'scrollDefault': true,
	    'selectOnBlur': true,
	    'show2400': true,
	    'showDuration': true,
	    'showOn': ["click", "focus"],
	    'showOnFocus': true,
	    'durationTime':'minTime',
	    'forceRoundTime': true,
	    'maxTime': null,
	    'minTime': null,
	    'step': 30
	});

	$('#datepairExample').datepair();
</script>

<script type="text/javascript">
	jQuery132(document).ready(
			function() {
				$("input#name").focus();
				$('input#color').simpleColorPicker(
						{
							onChangeColor : function(color) {
								$('input#color').val(color);
							},
							showEffect : 'fade',
							hideEffect : 'slide',
							enableEscapeButton : true,
							colors : [ '#660000', '#783f04', '#7f6000',
									'#274e13', '#0c343d', '#073763', '#20124d',
									'#4C1130', '#990000', '#b45f06', '#bf9000',
									'#38761d', '#134f5c', '#0b5394', '#351c75',
									'#741b47', '#ff0000', '#ff9900', '#ffff00',
									'#00ff00', '#00ffff', '#0000ff', '#9900ff',
									'#ff00ff', '#cc0000', '#e69138', '#f1c232',
									'#6aa84f', '#45818e', '#3d85c6', '#674ea7',
									'#a64d79', '#e06666', '#f6b26b', '#ffd966',
									'#93c47d', '#76a5af', '#6fa8dc', '#8e7cc3',
									'#c27ba0' ]
						});

				jQuery132("input[name='cancel']").bind('click', function(e) {
					e.preventDefault();
					$.fancybox.close();
				});
			});
</script>

<title>Insert title here</title>
</head>
<body>
	<div>
		<div
			style="padding: 30px; width: 300px; height: auto; background-color: #f7d4a6">
			<div id="message"></div>
			<div class='worker'>
				<form:form method='POST' modelAttribute="schedule"
					enctype="multipart/form-data" id="work_form">
					<div style="display: none"></div>
					<div>
						<h3>編輯班別</h3>
						<hr>
						<div>
							<div>
								<label class="right">班別名稱</label>
							</div>
							<div>
								<form:input id="scheduleName" path="scheduleName" type='text' value=''
									class='form:input-large' /> <br>
							</div><br>
						</div>
						<div>
							<div>
								<label class="right">識別色</label>
							</div>
							<div>
								<form:input id="color" path="color" type='text'
									class='form:input-large' />
							</div>
						</div>
						<div>
							<p id="datepairExample">
								<label class="left">起始時間</label><br>
									<form:input id="startTime" path="startTime" type='text'
										class='time start' /><br><br>
								<label class="right">結束時間</label><br>
									<form:input id="endTime" path="endTime" type='text'
										class='time end' /><br>
								</p>
							</div>
						</div>
						<div>
							<div>
								<label class="right">休息時間(單位:小時)</label>
							</div>
							<div>
								<form:input id="restHour" path="restHour" type='text'
									class='form:input-large'  value='0.5'/>
							</div>
						</div><br>
						<div>
							<input type="submit" name="submit" value="送出"
								class="button span-2" /> <input type="submit" name="cancel"
								value="取消" class="button span-2" />
						</div>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>