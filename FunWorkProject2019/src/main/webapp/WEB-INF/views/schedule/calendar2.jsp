<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='/FunWorkProject2019/css/fullcalendar.min.css' rel='stylesheet' />
<link href='/FunWorkProject2019/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<link href='/FunWorkProject2019/css/scheduler.min.css' rel='stylesheet' />

<script type="text/javascript" src='<c:url value="/js/moment.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/fullcalendar.min.js"/>'></script>
<script type="text/javascript"src='<c:url value="/js/scheduler.min.js"/>'></script>

<script>

  $(function() { // document ready

    $('#calendar').fullCalendar({
      now: '2018-04-07',
      editable: true,
      aspectRatio: 1.8,
      scrollTime: '00:00',
      header: {
        left: 'promptResource today prev,next',
        center: 'title',
        right: 'timelineDay,timelineThreeDays,agendaWeek,month'
      },
      customButtons: {
        promptResource: {
          text: '+ room',
          click: function() {
            var title = prompt('Room name');
            if (title) {
              $('#calendar').fullCalendar(
                'addResource',
                { title: title },
                true // scroll to the new resource?
              );
            }
          }
        }
      },
      defaultView: 'timelineDay',
      views: {
        timelineThreeDays: {
          type: 'timeline',
          duration: { days: 3 }
        }
      },
      resourceLabelText: 'Rooms',
      resourceRender: function(resource, cellEls) {
        cellEls.on('click', function() {
          if (confirm('Are you sure you want to delete ' + resource.title + '?')) {
            $('#calendar').fullCalendar('removeResource', resource);
          }
        });
      },
      resources: [
        { id: 'a', title: 'Auditorium A' },
        { id: 'b', title: 'Auditorium B', eventColor: 'green' },
        { id: 'c', title: 'Auditorium C', eventColor: 'orange' },
        { id: 'd', title: 'Auditorium D', children: [
          { id: 'd1', title: 'Room D1' },
          { id: 'd2', title: 'Room D2' }
        ] },
        { id: 'e', title: 'Auditorium E' },
        { id: 'f', title: 'Auditorium F', eventColor: 'red' },

      ],
      events: [
        { id: '1', resourceId: 'b', start: '2018-04-07T02:00:00', end: '2018-04-07T07:00:00', title: 'event 1' },
        { id: '2', resourceId: 'c', start: '2018-04-07T05:00:00', end: '2018-04-07T22:00:00', title: 'event 2' },
        { id: '3', resourceId: 'd', start: '2018-04-06', end: '2018-04-08', title: 'event 3' },
        { id: '4', resourceId: 'e', start: '2018-04-07T03:00:00', end: '2018-04-07T08:00:00', title: 'event 4' },
        { id: '5', resourceId: 'f', start: '2018-04-07T00:30:00', end: '2018-04-07T02:30:00', title: 'event 5' }
      ]
    });
  
  });

</script>
<style>

  body {
    margin: 0;
    padding: 0;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
    font-size: 14px;
  }

  p {
    text-align: center;
  }

  #calendar {
    max-width: 900px;
    margin: 50px auto;
  }

  .fc-resource-area td {
    cursor: pointer;
  }

</style>
</head>
<body>

  <p>
    HINT: click on a resource to delete it.
  </p>

  <div id='calendar'></div>

</body>
</html>
