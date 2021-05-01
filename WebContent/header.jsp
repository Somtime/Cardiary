<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" href="assets/css/card.css" />
<script type="text/javascript" src="member/member.js"></script>
<script type="text/javascript" src="card/card.js"></script>
</head>
<body>
	<header>
		<div id="logo">
			<a href="CardiaryServlet?command=card_list"><img src="images/cardiary.png" width="50px" height="50px" /></a>
		</div>
		<div id="menu">
			
			<a href="CardiaryServlet?command=new_card_form"><img src="images/new.png" width="50px" height="50px" /></a>
			<a href="CardiaryServlet?command=friend_list"><img src="images/friends.png" width="50px" height="50px" /></a>
			<a href="CardiaryServlet?command=favorite_card"><img src="./images/redheart.png" width="50px" height="50px" /></a>
			<a href="CardiaryServlet?command=mypage"><img src="./images/info/${sessionScope.loginUser.image}" width="50px" height="50px" /></a>
			
		</div>
		
	</header>

</body>
</html>