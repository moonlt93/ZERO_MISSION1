<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
</head>

<body >


	지금
	<%=new java.util.Date()%></br>
	위도<input type="text" name="latitude" value=""/></br>
	경도<input type="text" name="logitude" value=""/></br>
<button id ="find-me">Show my location</button><br/>
<p id = "status"></p>
<a id = "map-link" target="_blank"></a>
<button id = "apiGet">가져오기</button>

  
    

<script>

 $("#apiGet").click(function(){  
    
    $.ajax({          
        url: '/API.do',
        type: 'POST',
        dataType: 'json',
        success: function(data){
              let str = JSON.stringify(data);
              alert(str);
        }
    });    

}); 
function geoFindMe() {

	  const status = document.querySelector('#status');
	  const mapLink = document.querySelector('#map-link');

	  mapLink.href = '';
	  mapLink.textContent = '';

	  function success(position) {
	    const latitude  = position.coords.latitude;
	    const longitude = position.coords.longitude;

	    status.textContent = '';
	    mapLink.href = `https://www.openstreetmap.org/#map=18/${latitude}/${longitude}`;
	    mapLink.textContent = `Latitude: ${latitude} °, Longitude: ${longitude} °`;
	    
	    $('input[name=latitude]').attr('value',position.coords.latitude);
	    $('input[name=logitude]').attr('value',position.coords.longitude);
	  }

	  function error() {
	    status.textContent = 'Unable to retrieve your location';
	  }

	  if(!navigator.geolocation) {
	    status.textContent = 'Geolocation is not supported by your browser';
	  } else {
	    status.textContent = 'Locating…';
	    navigator.geolocation.getCurrentPosition(success, error);
	  }

	}

	document.querySelector('#find-me').addEventListener('click', geoFindMe);
  
 
   
</script>
</body>
</html>