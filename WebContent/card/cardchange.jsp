<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카드 수정</title>
<link rel="stylesheet" href="./assets/css/card.css">
</head>
<body>

	<section>
		<form method="post" action="CardiaryServlet?command=change_card" name="frm" enctype="multipart/form-data">
			<div id="card">
				<!-- 카드 헤더 -->
				<div id="card_header">
					<input type="hidden" name="cseq" value="${card.cseq}">
					
					<!-- 유저 이미지 -->
					<div id="card_image_member">
						<a href="CardiaryServlet?command=user_info&id=${card.id}">
							<img src="./images/info/${card.image_member}" id="image_info" />
						</a>
					</div>
					
					<!-- 유저 아이디 -->
					<div id="card_id">
						<a href="CardiaryServlet?command=user_info&id=${card.id}">
							${card.id}
							<input type="hidden" name="id" value="${card.id}">
						</a>		
					</div>
				</div>
				<!-- 카드 헤더 끝 -->
				
				<!-- 카드 콘텐트 -->
				<div id="card_content">
					
					<!-- 콘텐트 이미지 -->
					<div id="card_image_card">
						<input type="file" name="image" style="display:none">
						<img src="./images/card/${card.image_card}" id="image_card" onclick="document.frm.image.click()" />
						<input type="hidden" name="preimage" value="${card.image_card}">
					</div>
					
					<!-- 콘텐트 내용 -->
					<div id="card_content_article">
						<textarea style="overflow:hidden; resize:none;">${card.content}</textarea>
					</div>
				
				</div>
				<!-- 카드 콘텐트 끝 -->
				
				<hr style="border: 1px solid rgba(252, 252, 254, 0.6)">
				
				<!-- 카드 수정 및 삭제 버튼 -->
				<div id="card_button">
					<input type="submit" class="card_button" value="수정">
				</div>
			</div>
		</form>	
	</section>
	
</body>
</html>