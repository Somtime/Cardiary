<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join page</title>
<link rel="stylesheet" href="./assets/css/member.css" />
<script type="text/javascript" src="member/member.js"></script>
</head>
<body>

	<div id="join">
		<img src="./images/cardiary.png" class="logo" alt="Logo" />
		<form method="post" action="CardiaryServlet?command=join" name="frm">
			<input type="text" name="id" placeholder="ID"><br>
			<input type="hidden" name="id_check">
			<input type="button" value="ID Check" class="submit" onclick="idcheck()"><br>
			<input type="password" name="pwd" placeholder="Password"><br>
			<input type="password" name="pwd_check" placeholder="Check Password"><br>
			<input type="text" name="name" placeholder="Name"><br>
			<input type="email" name="email" placeholder="Email"><br>
			<input type="text" name="phone" placeholder="Phone Number"><br><br>
			
			<input type="submit" class="submit" value="Join" onclick="return join()">
		</form>
	</div>
	

</body>
</html>