<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>친구 목록</title>
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
									<input type="text" name="serach_id"
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
					<div id="friend_request_list">
						<c:forEach items="${friendRequestList}" var="request">
							<p>친구 요청</p>

							<div>
								<a href="CardiaryServlet?command=user_info&id=${request.id}">
									<img src="./images/info/${request.image}"
									id="request_image_member" />
								</a>
							</div>

							<div>
								<a href="CardiaryServlet?command=user_info&id=${request.id}">
									<input type="hidden" name="id" value="${request.id}">
									<input type="text" value="${request.id}" disabled>
								</a>
							</div>
							<div id="clear"></div>

						</c:forEach>
					</div>
					<br>
					<div id="friend_list">
						<p>친구</p>
						
						<c:forEach items="${friendList}" var="friend">
							<div>
								<a href="CardiaryServlet?command=user_info&id=${friend.id}"> <img
									src="./images/info/${friend.image}" id="request_image_member" />
								</a>
							</div>

							<div>
								<a href="CardiaryServlet?command=user_info&id=${friend.id}"> 
									<input type="hidden" name="id" value="${friend.id}">
									<input type="text" value="${friend.id}" disabled>
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