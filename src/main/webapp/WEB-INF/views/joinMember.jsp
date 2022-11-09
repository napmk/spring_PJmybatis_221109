<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	<table width="600" border="1" cellpadding="0" cellspacing="0">
		<form action="checkId">
			<tr>
				<th align="center">
					아이디체크
				</th>
				<td>
					<input type="text" name="checkId" size="40">
					<input type="submit" value="중복체크">
				</td>
			</tr>
		</form>		
		<form action="joinOk" method="post">
			<tr height="40">
				<td width="100">아이디</td>
				<td><input type="text" size="60" name="mid"></td>
			</tr>
			<tr height="40">
				<td>비밀번호</td>
				<td><input type="text" size="60" name="mpw"></td>
			</tr>
			<tr height="40">
				<td>이름</td>
				<td><input type="text" size="60" name="mname"></td>
			</tr>
			<tr height="40">
				<td>이메일</td>
				<td><input type="text" size="60" name="memail"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="회원가입">
					<input type="reset" value="취소">
				</td>
			</tr>
		</form>
	
	</table>

</body>
</html>