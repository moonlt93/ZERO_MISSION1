<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
</head>

<body>
   
        <a href="/index.jsp">홈</a>
    <a href="/history.do">히스토리</a>
    <a href="/api.do">openApi정보가져오기</a>
    <form action="/api.do" method="get">
    위도 <input type="text" name="latitude" value="${param.latitude}" />
   
    경도 <input type="text" name="longitude"value="${param.longitude }" />
   
     <button type="button" id="find-me">내 위치 가져오기</button>
     <button type="submit">postTest</button>
    <br />
    </form>


 <table border="1">
  <thead>
  <tr>
   <td>거리(Km)</td>
   <td>관리번호</td>
   <td>자치구</td>
   <td>와이파이명</td>
   <td>도로명주소</td>
   <td>상세주소</td>
   <td>설치위치(층)</td>
   <td>설치유형</td>
   <td>설치기관</td>
   <td>서비스구분</td>
   <td>망종류</td>
   <td>설치년도</td>
   <td>실내외구분</td>
   <td>WIFI접속환경</td>
   <td>x좌표</td>
   <td>Y좌표</td>
   <td>작업일자</td>
  </tr>
  </thead>
  <tbody>
        
   <c:forEach items="${list}"  var ="item" >
  <tr>
    <td><c:out value="${item.distance }"/></td>
    <td><c:out value="${item.authNum }"/></td>
    <td><c:out value="${item.resident }"/></td>
    <td><c:out value="${item.wifiName }"/></td>
    <td><c:out value="${item.roadAdd }"/></td>
    <td><c:out value="${item.detailAdd }"/></td>
    <td><c:out value="${item.netWorkSpot }"/></td>
    <td><c:out value="${item.installSpot }"/></td>
     <td><c:out value="${item.installType }"/></td>
    <td><c:out value="${item.agency }"/></td>
    <td><c:out value="${item.serviceType }"/></td>
    <td><c:out value="${item.installDate }"/></td>
    <td><c:out value="${item.sideType }"/></td>
     <td><c:out value="${item.connectView }"/></td>
    <td><c:out value="${item.longitude }"/></td>
    <td><c:out value="${item.latitude }"/></td>
    <td><c:out value="${item.contactDate }"/></td>
  </tr>
  </c:forEach> 
  </tbody>
  </table> 


    <script>
  
        function geoFindMe() {
        	navigator.geolocation.getCurrentPosition(success, onGeoError);

            function success(position) {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;

                $('input[name=latitude]').attr('value',
                        position.coords.latitude);
                $('input[name=longitude]').attr('value',
                        position.coords.longitude);
            }

            function onGeoError() {
              alert("I can't find you. No weather for you.");
            }

       
        }

        document.querySelector('#find-me').addEventListener('click', geoFindMe);
    
        </script>
</body>
</html>