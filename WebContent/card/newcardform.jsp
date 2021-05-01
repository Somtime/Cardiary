<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Card Form Page</title>
<link rel="stylesheet" href="assets/css/card.css" />
<script type="text/javascript" src="card/card.js"></script>
</head>
<body>
	<section>
		<div id="card">
			<form method="post" action="CardiaryServlet?command=new_card" enctype="multipart/form-data">
				<div id="new_card_image">
					<input type="file" name="image"><br>
				</div>
				<div id="new_card_content">
					<textarea name="content" placeholder="글을 작성해주세요." style="overflow:hidden; resize:none"></textarea><br>
				</div>
				<div id="new_card_submit">
					<input type="submit" value="작성" id="submit" onclick="new_card()">
				</div>
				
				<div id="clear"></div>
			</form>
		</div>
	</section>
</body>
</html>