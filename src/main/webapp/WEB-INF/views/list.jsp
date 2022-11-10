<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>글목록</h2>
	<div style="float:right; text-align:right">
	
		<%
			int idflag = Integer.parseInt(request.getAttribute("idflag").toString());
			if(idflag == 0){
				//0 이면 로그인 해야 됨
		
		%>		
		
		<input type="button" value="로그인" onclick="javascript:window.location='login'">
		
		<%
			} else { //아니면 로그인 되어있음
		%>
	 	
		${sid }님 로그인 중입니다.
		<%
			}
		%>
	
	</div>
	<hr>
	총 게시글 갯수 : ${boardSum}개<br>
	
	<table width="1000" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th width="600">글제목</th>
			<th>이름</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
		<c:forEach items="${list }" var="fbdto">
		<tr>
			<td>${fbdto.fnum }</td>
			<td>${fbdto.fid }</td>
			<td>
				<a href="content_view?fnum=${fbdto.fnum } ">
				${fbdto.ftitle }
				</a>
			</td>
			<td>${fbdto.fname }</td>
			<td>${fbdto.fhit }</td>
			<td>${fbdto.fdate }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="right"><a href="writeForm">글쓰기</a></td>
		</tr>
	</table>
</body>
</html>