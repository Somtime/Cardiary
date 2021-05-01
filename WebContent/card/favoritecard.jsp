<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Favorite Card Page</title>
<link rel="stylesheet" href="./assets/css/card.css">
</head>
<body>

	<section>
		<div id="wrap">
			<c:forEach items="${cardList}" var="CardVO">
				<c:forEach items="${CardVO.favorite}" var="favorite">
					<!-- 좋아요한 카드만 보여줌 -->
					<c:if test="${favorite.favorite_yn == 'y'}">
						<a href="CardiaryServlet?command=card&cseq=${CardVO.cseq}">
							<img src="./images/card/${CardVO.image_card}" id="favorite_card" />
						</a>
					</c:if>
				</c:forEach>
			</c:forEach>
		</div>
	</section>
	
</body>
</html>