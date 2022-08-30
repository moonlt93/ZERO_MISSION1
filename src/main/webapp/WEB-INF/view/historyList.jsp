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
<h1>히스토리 list 불러오기</h1>
<a href="/index.jsp">홈으로</a>

 <table border="1" id="historyList">
  <thead>
  <tr>
   <td>ID</td>
   <td>X좌표</td>
   <td>Y좌표</td>
   <td>조회일자</td>
    <td>비고</td>
  </tr>
  </thead>
  <tbody>
   <c:forEach items="${list}"  var ="item" >
		  <tr>
		    <td><c:out value="${item.extraId }"/></td>
		    <td><c:out value="${item.myLatitude }"/></td>
		    <td><c:out value="${item.myLongitude }"/></td>
		    <td><c:out value="${item.visitedDate }"/></td>
		    <td><button id="delete_btn" type="button">삭제</button>
		  </tr>
  </c:forEach> 
  </tbody>
  </table> 
  
  <script>
  $("#delete_btn").click(function(){ 

      var deletebtn = $(this);

      var tr = deletebtn.parent().parent();
      var td = tr.children();

      
      var no = td.eq(0).text();
   
      $.ajax({
          url: "/history.do",
          type: "POST",
          dataType: "text", //서버로부터 넘겨받을 데이터 타입
          data: {"ExtraId": no }, //서버로 전달할 데이터(key:value 형태의 객체 or String)
          success : function(data) {
        	  location.reload();
          alert("성공했습니다.");
          }, 
          error : function(xhr, status, error){
          alert("통신실패시에만 실행");
          },
         
      });
   
  });

  
  </script>
</body>
</html>