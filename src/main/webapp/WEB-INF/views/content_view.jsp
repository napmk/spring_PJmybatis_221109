<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보기페이지</title>
</head>
<body>
	<h2>게시판보기</h2>
	<hr>
	<table border="1" cellpadding="0" cellspacing="0" width="500">
		<tr>
			<th width="100">글번호</th>
			<td>${viewdto.fnum }</td>
		</tr>
		<tr>
			<th>글쓴이</th>
			<td>${viewdto.fname}</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${viewdto.fid}</td>
		</tr>
		<tr>
			<th>글제목</th>
			<td>${viewdto.ftitle }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td height="300">${viewdto.fcontent }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${viewdto.fdate }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${viewdto.fhit }</td>
		</tr>
	
		<tr>
			<td colspan="2" align="right">
	 	<%
			int idflag = Integer.parseInt(request.getAttribute("idflag").toString());
			if(idflag == 1){
				//로그인상태에 따라서 삭제 수정이 보이고 안보이고 함
		
		%>		
	 			<input type="button" value="수정" onclick="javascript:window.location='modifyView?fnum=${viewdto.fnum}'">
	 			<input type="button" value="삭제" onclick="javascript:window.location='delete?fnum=${viewdto.fnum}'">
	 	<%
	 	
			}
	 	%>		
	 			<input type="button" value="목록" onclick="javascript:window.location='list'">
	 			
	 		</td>
		</tr>
		
	</table>

</body>
</html>