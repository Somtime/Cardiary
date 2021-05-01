<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="./assets/css/member.css" />
</head>
<body>
	<section>
		<div id="wrap">
			<table id="mypage_header">
				<tr>
					<td style="text-align:center" id="mypage_log">	
						<a href="CardiaryServlet?command=change_info_form">정보 수정</a> &nbsp;&nbsp;
						<a href="CardiaryServlet?command=logout">로그아웃</a>
					</td>
					<td>
						<input type="text" id="mypage_log_id" value="${loginUser.id}" readonly disabled>
					</td>
				</tr>
				<tr>
					<td>
						<img src="./images/info/${loginUser.image}" id="mypage_log_image" />
					</td>
					<td>
						<textarea name="mypage_log_info" disabled style="overflow:hidden; resize:none">${loginUser.info }</textarea>
					</td>
				</tr>
			</table>
		
			<br><br>
		
			<div id="card_preview">
				<c:forEach items="${cardList}" var="CardVO">
					<a href="CardiaryServlet?command=card&cseq=${CardVO.cseq}">
						<img src="./images/card/${CardVO.image_card}" id="card_preview_card" />
					</a>
				</c:forEach>
			</div>
		</div>
	</section>
</body>
</html>