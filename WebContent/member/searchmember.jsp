<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 검색</title>
<link rel="stylesheet" href="./assets/css/member.css" />
</head>
<body>
	<section>
		<form method="post" action="CardiaryServlet?command=search_user"
			name="frm">
			<div id="friendlist">
				<div id="search">
					<table>
						<tr>
							<td style="width: 65%">
								<div id="search_id">
									<input type="text" name="search_id" value="${searchid}"
										placeholder="검색할 ID를 입력해주세요.">
								</div>
							</td>
							<td style="width: 25%">
								<div id="serach_button">
									<input type="submit" value="검색" class="submit">
								</div>
							</td>
						</tr>
					</table>
				</div>
				<section>
					<div id="search_friend_list">
						<p>친구</p>
						<c:forEach items="${searchListFriend}" var="friend">
							<div>
								<a href="CardiaryServlet?command=user_info&id=${friend.id}">
									<img src="./images/info/${friend.image}" id="request_image_member" />
								</a>
							</div>

							<div>
								<a href="CardiaryServlet?command=user_info&id=${friend.id}">
									<input type="hidden" name="id" value="${firend.id}">
									<input type="text" value="${friend.id}" disabled>
								</a>
							</div>
							<div id="clear"></div>

						</c:forEach>
					</div>
					<br>
					<div id="search_list">
						<p></p>
						<c:forEach items="${searchList}" var="user">
							<div>
								<a href="CardiaryServlet?command=user_info&id=${user.id}"> <img
									src="./images/info/${user.image}" id="request_image_member" />
								</a>
							</div>

							<div>
								<a href="CardiaryServlet?command=user_info&id=${user.id}"> 
									<input type="hidden" name="id" value="${user.id}">
									<input type="text" value="${user.id}" disabled>
								</a>
							</div>

							<div id="clear"></div>
						</c:forEach>
					</div>
				</section>
			</div>
		</form>
	</section>
</body>
</html>