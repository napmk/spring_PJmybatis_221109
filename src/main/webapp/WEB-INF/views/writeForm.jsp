<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글쓰기</title>
</head>
<body>
	<h2>자유게시판 글쓰기</h2>
	<hr>
	<form action="write">
	아이디 : ${mid} <br>
	이름 : ${mname} <br>
	제목 : <input type="text" size="60" name="ftitle"><br><br>
	내용 : <textarea rows="10" cols="40" name="fcontent"></textarea><br><br>
	
	<input type="submit" value="글입력">&nbsp;<input type="reset" value="취소">
	</form>
</body>
</html>