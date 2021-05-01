<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보</title>
<link rel="stylesheet" href="./assets/css/member.css" />
</head>
<body>
	<section>
		<div id="wrap">
			<table id="mypage_header">
				<tr>
					<td style="text-align:center" id="mypage_log">
						<c:if test="${friend.friendyn == null}">
							<a href="CardiaryServlet?command=add_friend&id=${userInfo.id}">친구 추가</a>
						</c:if>
						<c:if test="${friend.friendyn == 'y'}">
							<a href="CardiaryServlet?command=add_delete&id=${userInfo.id}">친구 삭제</a>
						</c:if>
						<c:if test="${friend.friendyn == 'n'}">
							<a href="CardiaryServlet?command=update_friend&id=${userInfo.id}">친구 추가</a>
						</c:if>
					</td>
					<th>
						<input type="text" id="mypage_log_id" value="${userInfo.id}" readonly disabled>
						<input type="hidden" name="id" value="${userInfo.id}">
					</th>
				</tr>
				<tr>
					<td>
						<img src="./images/info/${userInfo.image}" id="mypage_log_image" />
					</td>
					<td>
						<textarea name="info" id="mypage_log_info" disabled style="overflow:hidden; resize:none">${userInfo.info}</textarea>
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