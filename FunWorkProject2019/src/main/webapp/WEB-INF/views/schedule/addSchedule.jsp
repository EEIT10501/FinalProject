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
	// initialize input widgets first
	$('#datepairExample .time').timepicker({
		'showDuration' : true,
		'timeFormat' : 'H:i:s',
		'scrollDefault' : true,
		'selectOnBlur' : true,
		'show2400' : true,
		'showDuration' : true,
		'showOn' : [ "click", "focus" ],
		'showOnFocus' : true,
		'durationTime' : 'minTime',
		'forceRoundTime' : true,
		'maxTime' : null,
		'minTime' : null,
	    'step': 30
	});

	//     $('#datepairExample .date').datepicker({
	//         'format': 'yyyy-m-d',
	//         'autoclose': true
	//     });

	// initialize datepair
	$('#datepairExample').datepair();
</script>

<script type="text/javascript">
	$(document).ready(
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
				// 						$("form#work_form #message").hide();
				// 						$("form#work_form #name").focus();
				// 						$("form#work_form input[name='submit']")
				// 								.bind(
				// 										'click',
				// 										function(e) {
				// 											e.preventDefault();
				// 											$
				// 													.ajax({
				// 														url : 'http://localhost:8080/FunWorkProject2019/scheduleManage',
				// 														type : 'POST',
				// 														dataType : 'json',
				// 														data : {
				// 															scheduleName : $(
				// 																	"input[name='scheduleName']")
				// 																	.val(),
				// 															color : $(
				// 																	"input[name='color']")
				// 																	.val(),
				// 															startTime : $(
				// 																	"input[name='startTime']")
				// 																	.val(),
				// 															endTime : $(
				// 																	"input[name='endTime']")
				// 																	.val(),
				// 															restHour : $(
				// 																	"input[name='restHour']")
				// 																	.val(),
				// 														},
				// 														success : function(data) {
				// 															if (data.status == 'error') {
				// 																$("div#message")
				// 																		.html(
				// 																				data.msg)
				// 																		.show();
				// 															}
				// 															if (data.status == 'success') {
				// 																location
				// 																		.replace('http://localhost:8080/FunWorkProject2019/scheduleManage');
				// 																/*
				// 																$.ajax({
				// 																	url: 'https://shift.ekko.com.tw/shift/ajax_get_workers.html',
				// 																	type: 'GET',
				// 																	dataType : 'json',
				// 																	success: function(data){
				// 																		$('div#workers').html(data);
				// 																		$('div#workers a').each(function() {

				// 																		// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
				// 																		// it doesn't need to have a start or end
				// 																		var eventObject = {
				// 																			id: $(this).attr('id'),
				// 																			title: $.trim($(this).text()), // use the element's text as the event title
				// 																			backgroundColor: $(this).css('backgroundColor')
				// 																		};

				// 																		// store the Event Object in the DOM element so we can get to it later
				// 																		$(this).data('eventObject', eventObject);

				// 																		// make the event draggable using jQuery UI
				// 																		$(this).draggable({
				// 																			helper: 'clone',
				// 																			opacity: 0.5,
				// 																			zIndex: 999,
				// 																			revert: true,      // will cause the event to go back to its
				// 																			revertDuration: 0  //  original position after the drag
				// 																		});

				// 																	});	
				// 																	}
				// 																})
				// 																 */
				// 																$.fancybox
				// 																		.close();

				// 															}
				// 														}
				// 													});
				// 											return false;
				// 										});
				$("input[name='cancel']").bind('click', function(e) {
					e.preventDefault();
					$.fancybox.close();
				});
			});
</script>

<title>新增班別</title>
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
						<h3>新增班別</h3>
						<hr>
						<div>
							<div>
								<label class="right">班別名稱</label>
							</div>
							<div>
								<form:input id="scheduleName" path="scheduleName" type='text'
									class='form:input-large' />
							</div>
							<br>
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
								<!-- 							<div> -->
								<label class="left">起始時間</label><br>
								<!-- 							</div> -->
								<!-- <p id="datepairExample"> -->
								<!--     <input type="text" class="time start" /> to -->
								<!--     <input type="text" class="time end" /> -->
								<!-- </p> -->
								<!-- 							<div> -->

								<form:input id="startTime" path="startTime" type='text'
									class='time start' />
								<br>
								<br>
								<!-- 								</p> -->
								<!-- 							</div> -->
								<!-- 						</div> -->
								<!-- 						<div> -->
								<!-- 							<div> -->
								<label class="right">結束時間</label><br>
								<!-- 							</div> -->
								<!-- 							<div> -->
								<!-- 								<p id="datepairExample"> -->
								<form:input id="endTime" path="endTime" type='text'
									class='time end' />
								<br>
							</p>
						</div>
					</div>
					<div>
						<div>
							<label class="right">休息時間(單位:小時)</label>
						</div>
						<div>
							<select name="restHour" id="restHour" >
								<option value="0.5">0.5小時</option>
								<option value="1.0">1.0小時</option>
								<option value="1.5">1.5小時</option>
								<option value="2.0">2.0小時</option>

							</select>
							<%-- 								<form:input id="restHour" path="restHour" type='text' --%>
							<%-- 									class='form:input-large'  value='0.5'/> --%>
						</div>
					</div>
					<br>
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