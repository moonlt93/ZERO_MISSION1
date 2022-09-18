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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-1.11.0.js"></script>

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

</head>
<body>
	<h1>위치 히스토리 목록</h1>
		<a href="/">홈</a><span>&nbsp;|&nbsp;</span> 
		<a href="/history.do">위치히스토리 목록</a><span>&nbsp;|&nbsp;</span> 
		<a href="/api.do">openApi정보가져오기</a>

	<table class="table table-striped">
		<thead class="table_head">
			<tr>
				<td>ID</td>
				<td>X좌표</td>
				<td>Y좌표</td>
				<td>조회일자</td>
				<td>비고</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr class="tr_table">
					<td><c:out value="${item.extraId }" /></td>
					<td><c:out value="${item.myLatitude }" /></td>
					<td><c:out value="${item.myLongitude }" /></td>
					<td><c:out value="${item.visitedDate }" /></td>
					<td><button class="delete_btn" type="button">삭제</button>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script>
		$(".delete_btn").click(function() {
			var deletebtn = $(this);
			var tr = deletebtn.parent().parent();
			var td = tr.children();

			var no = td.eq(0).text();
			let check = confirm("삭제하시겠습니까?");

		
			if(check){

			$.ajax({
				url : "/history.do",
				type : "POST",
				dataType : "text", //서버로부터 넘겨받을 데이터 타입
				data : {
					"ExtraId" : no
				}, //서버로 전달할 데이터(key:value 형태의 객체 or String)
				success : function(data) {
					location.reload();
				},
				error : function(xhr, status, error) {
					console.log(status);
				},

			});
			
			}

		});
	</script>
</body>
</html>