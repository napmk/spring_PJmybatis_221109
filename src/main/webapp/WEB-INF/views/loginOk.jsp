<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인확인</title>
</head>
<body>
	<%
		int checkId = Integer.parseInt(request.getAttribute("checkIdFlag").toString());//${checkIdFlag}
		int checkPw = Integer.parseInt(request.getAttribute("checkPwFlag").toString());//${checkIdFlag}
		if(checkId == 0) {
			// Integer 값으로 바꾸기 위해 이걸 사용.
	%>
	<script language="JavaScript">
		alert("입력하신 아이디가 존재하지 않습니다. 다시 확인후 로그인해 주세요.")
		history.go(-1);
	</script>
	<%
		}else if(checkPw == 0){
	%>
	<script language="JavaScript">
		alert("입력하신 비밀번호가 맞지 않습니다. 다시 확인후 로그인해 주세요.")
		history.go(-1);
	</script>
	<%
		}
	%>
	
	<h2>로그인 성공하였습니다.</h2>
	<h2>${mid }님 반갑습니다.</h2>
	<h2>회원이름 :${mname }님</h2>
	<a href="writeForm">글쓰기</a>
</body>
</html>