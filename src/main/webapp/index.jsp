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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<style>
        a:visited{
        color:purple;
        }
 <style type="text/css">
     
         .tr_table {
            background-color: #3fc83f;
            color: white;
            text-align: center;
        }
        .table_head {
          
            background-color: #34D889;
            min-width: 80px;
            border: 0.1px solid #29e595;
            font-style: normal;
            font-weight: bold;
            text-align: center;
            color: white;
        }
        body{
     
          font-weight: bold;
        }
      h1{
          display: flex;
         font-weight: bold;
        }
     td{
      text-align:center;
      }
       .updown {
        border: 2px solid black;
        width: 0.3px;
        height: 30px;
    }
    
</style>
<body>
    
    <h1>와이파이 정보 구하기</h1>
    
    <div class="location_form">
        <a href="/">홈</a><span>&nbsp;|&nbsp;</span> 
        <a href="/history.do" >위치 히스토리 목록</a><span>&nbsp;|&nbsp;</span> 
        <a href="/api.do" >openApi정보가져오기</a>
    </div>
    <div class="formArea">
        <form id="formClass" action="/api.do" method="post">
                 LAT: <input type="text" id="latitude" name="latitude" value="${param.latitude}"
                placeholder="x좌표를 입력해주세요" /> 
                ,LNT: <input type="text"id="longitude" name="longitude" value="${param.longitude }"
                placeholder="y좌표를 입력해주세요" />
                 <button type="button" id="find-me"style="font-weight:bold">내 위치 가져오기</button>
                 <button type="button" id="wifi"style="font-weight:bold">근처 WIFI 정보 보기</button>
        </form>
    </div>


    <table class="table table-striped">
        <thead class="table_head">
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
        <c:choose>
          <c:when test="${list == null}">
          <tr>
              <td colspan="17" align="center"> 위치정보를 조회한 후에 조회해주세요 </td>
          </tr>
        </c:when>
        <c:when test="${list != null }">
          <c:forEach items="${list}" var="item">
                <tr class="tr_table">
                    <td><c:out value="${item.distance }" /></td>
                    <td><c:out value="${item.authNum }" /></td>
                    <td><c:out value="${item.resident }" /></td>
                    <td><c:out value="${item.wifiName }" /></td>
                    <td><c:out value="${item.roadAdd }" /></td>
                    <td><c:out value="${item.detailAdd }" /></td>
                    <td><c:out value="${item.netWorkSpot }" /></td>
                    <td><c:out value="${item.installSpot }" /></td>
                    <td><c:out value="${item.installType }" /></td>
                    <td><c:out value="${item.agency }" /></td>
                    <td><c:out value="${item.serviceType }" /></td>
                    <td><c:out value="${item.installDate }" /></td>
                    <td><c:out value="${item.sideType }" /></td>
                    <td><c:out value="${item.connectView }" /></td>
                    <td><c:out value="${item.longitude }" /></td>
                    <td><c:out value="${item.latitude }" /></td>
                    <td><c:out value="${item.contactDate }" /></td>
                </tr>
            </c:forEach>
          </c:when>
       </c:choose>      
        </tbody>
    </table>


    <script>
    $('#wifi').click(function(){
         if($('#latitude').val()=='')  {
             alert('내 위치를 가져와주세요');
             $('#latitude').focus();
             window.location.replace("/");
       }else{
           document.getElementById('formClass').submit();
       }
    });
    
    
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