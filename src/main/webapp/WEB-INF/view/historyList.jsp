<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>히스토리 list 불러오기</h1>
<a href="/index.jsp">홈으로</a>

 <table border="1">
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
		    <td><button type="button">삭제</button>
		  </tr>
  </c:forEach> 
  </tbody>
  </table> 
</body>
</html>