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
<script type="text/javascript" src=""></script>
</head>
<body>
	<section>
		<form method="post" action="CardiaryServlet?command=change_info" name="frm" enctype="multipart/form-data">
			<div id="wrap">
				<table id="mypage_header">
					<tr>
						<td style="text-align:center" id="mypage_log">
							<input type="submit" id="reinfo_check" value="확인">
						</td>
						<th>
							<input type="text" id="mypage_log_id" value="${loginUser.id}" readonly disabled>
						</th>
					</tr>
					<tr>
						<td>
							
							<img src="./images/info/${loginUser.image}" id="mypage_log_image" onclick="document.frm.image.click()" />
							
							<input type="file" name="image" style="display:none">
							
							<input type="hidden" name="preimage" value="${loginUser.image}" id="mypage_log_image" >
						</td>
						<td>
							<textarea name="info" style="overflow:hidden; resize:none">${loginUser.info}</textarea>
						</td>
					</tr>
				
				</table>

			</div>
		</form>	
	</section>
</body>
</html>