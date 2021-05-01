<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="./assets/css/member.css" />
<script type="text/javascript">
function login() {
	if (document.frm.content.value == "") {
		alert("아이디를 입력해주세요.");
		document.frm.id.focus;
		return false;
	} else if (document.frm.pwd.value == "") {
		alert("비밀번호를 입력해주세요.");
		document.frm.pwd.focus;
		return false;
	} 
	
	if (${message == null}) {
		${message}
	}
	
	return true;
}
</script>
</head>
<body>

	<div id="login">
		<img src="./images/cardiary.png" class="logo" alt="Logo" />
		<form method="post" action="CardiaryServlet?command=login" name="frm">
			<input type="text" name="id" placeholder="ID" value="zonnari"><br>
			<input type="password" name="pwd" placeholder="Password" value="zonnari"><br>
			
			<input type="submit" class="submit" value="login" onclick="return login()"><br><br>
			
			
				<p style="color:white"> ${message } </p>
			
			
			<br>

			<input type="button" class="submit" value="Join" 
					onclick="location='CardiaryServlet?command=join_form'">
		</form>
	</div>

</body>
</html>