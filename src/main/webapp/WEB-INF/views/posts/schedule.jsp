<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8' />
    <!-- 화면 해상도에 따라 글자 크기 대응(모바일 대응) -->
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <!-- jquery CDN -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- fullcalendar CDN -->
    <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet' />
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
    <!-- fullcalendar 언어 CDN -->
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>

    <script src="/js/common.js"></script>

    <style>
        /* body 스타일 */
        html, body {
            overflow: hidden;
            font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
            font-size: 14px;
        }
        /* 캘린더 위의 해더 스타일(날짜가 있는 부분) */
        .fc-header-toolbar {
            padding-top: 1em;
            padding-left: 1em;
            padding-right: 1em;
        }
    </style>
</head>
<body style="padding:30px;">
<!-- calendar 태그 -->
<div id='calendar-container'>
    <div id='calendar'></div>
</div>
<script>

    const param = {
        id : ""
        , title : ""
        , start :
            String(new Date().getFullYear()) + "-"
            + String(new Date().getMonth() + 1).padStart(2, "0") + "-"
            + String(new Date().getDate()).padStart(2, "0")
        , end : ""
    };
    let chosenDate = param.start;

    (function(){
        $(function(){

            var calendarEl = $('#calendar')[0]; // get calendar element

            // create full-calendar
            var calendar = new FullCalendar.Calendar(calendarEl, {
                height: '700px', // calendar 높이 설정
                expandRows: true, // 화면에 맞게 높이 재설정
                // slotMinTime: '08:00', // Day 캘린더에서 시작 시간
                // slotMaxTime: '20:00', // Day 캘린더에서 종료 시간
                // 해더에 표시할 툴바
                headerToolbar: {
                    left: '',
                    center: 'title',
                    right: 'prev today next'
                        // 'dayGridMonth' +
                        // ',timeGridWeek' +
                        // ',timeGridDay' +
                        // ' listWeek'
                },
                initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
                initialDate: chosenDate, // 초기 날짜 설정 (설정하지 않으면 오늘 날짜가 보인다.)
                navLinks: true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
                editable: true, // 수정 가능
                selectable: true, // 달력 일자 드래그 설정가능
                nowIndicator: true, // 현재 시간 마크
                dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
                locale: 'ko', // 한국어 설정
                eventAdd: function(obj) { // 이벤트가 추가되면 발생하는 이벤트

                    param.title = obj.event.title;
                    param.start = obj.event.startStr;
                    param.end = obj.event.endStr;

                    chosenDate = param.start;

                    apiPost("/insertSchedule", param)
                        .then(data => {
                            if (data) {

                                alert("일정이 등록되었습니다.");
                                calendar.removeAllEvents();

                                apiPost("/selectSchedule")
                                    .then(data => {
                                        calendar.addEventSource(data);
                                    })
                                    .catch(error => {
                                        console.log(error);
                                    });

                            }
                        })
                        .catch(error => {
                            console.log(error);
                        });

                },
                eventChange: function(obj) { // 이벤트가 수정되면 발생하는 이벤트

                    param.title = obj.event.title;
                    param.start = obj.event.startStr;
                    param.end = obj.event.endStr;
                    param.id = obj.event.id;

                    apiPost("/updateSchedule", param)
                        .then(data => {
                            if (data) {
                                alert("일정이 변경되었습니다.")
                            }
                        })
                        .catch(error => {
                            console.log(error);
                        });

                },
                // eventRemove: function(obj){ // 이벤트가 삭제되면 발생하는 이벤트
                //     console.log(obj);
                // },
                eventClick: function (info){

                    if (confirm(`${info.event.title} 일정을 삭제할까요?`)) {

                        info.event.remove();

                        param.id = info.event.id;

                        apiPost("/deleteSchedule", param)
                            .then(data => {
                                if (data) {
                                    alert("삭제가 완료되었습니다.");
                                }
                            })
                            .catch(error => {
                                console.log(error);
                            });
                    }
                },
                select: function(arg) { // 캘린더에서 드래그로 이벤트를 생성할 수 있다.
                    var title = prompt('어떤 일정을 등록하시겠습니까?');
                    if (title) {
                        calendar.addEvent({
                            title: title,
                            start: arg.start,
                            end: arg.end,
                            allDay: arg.allDay
                        })
                    }
                    calendar.unselect()
                },
                // 이벤트
                events: [
                    // {
                    //     title: 'Meeting',
                    //     start: '2021-07-12',
                    //     end: '2021-07-12'
                    // },
                ]
            });

            apiPost("/selectSchedule")
                .then(data => {
                    // FullCalendar 의 events 옵션은 직접 변경할 수 없다. FullCalendar 의 api 를 직접 호출에서 화면에 표시헤준다.
                    // calendar.events.push(data);
                    calendar.addEventSource(data);
                })
                .catch(error => {
                    console.log(error);
                });

            calendar.render(); // 캘린더 랜더링
        });
    })();
</script>
</body>
</html>
