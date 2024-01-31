<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>마커에 마우스 이벤트 등록하기</title>
</head>
<body>
<div id="map" style="width:100%;height:350px;"></div>
<div>주소 : 서울특별시 영등포구 양평동5가 41-1 x층 xxx호</div>
<div>전화 : 010 - xxxx - xxxx</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a8760ca7995b2a820719c22ade72e7cd"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(37.538997952792684, 126.8908852681712), // 지도의 중심좌표
            level: 2 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 마커를 표시할 위치입니다
    var position =  new kakao.maps.LatLng(37.538997952792684, 126.8908852681712);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: position
    });

    // 마커를 지도에 표시합니다.
    marker.setMap(map);

    // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
    var iwContent = '<div style="padding:5px;">IT Company</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent
    });

    // 마커에 마우스오버 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'mouseover', function() {
        // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
        infowindow.open(map, marker);
    });

    // 마커에 마우스아웃 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'mouseout', function() {
        // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
        infowindow.close();
    });
</script>
</body>
</html>