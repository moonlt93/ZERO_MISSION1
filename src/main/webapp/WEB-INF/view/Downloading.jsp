<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
<style>
.home {
    text-align: center;
    font-weight: bold;
    
}
.home.things {
     text-align: center;
     font-weight: 700;
}
  
</style>
</head>
<body>
<div class="home">
		<div class="things">
			<h1>
		<%
		Long total =(Long)request.getAttribute("total");
		
		%>
				<%=total %>개의 정보를 불러오는데 성공했습니다.
			</h1>
		</div>
		<a href="/">홈으로 가기</a>
</div>

</body>
</html>