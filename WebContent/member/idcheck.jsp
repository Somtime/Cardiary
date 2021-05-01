<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID Check Page</title>
<link rel="stylesheet" href="./assets/css/member.css" />
<script type="text/javascript">
function iduse() {
	opener.frm.id.value = "${userid}";
	opener.frm.id_check.value = "${userid}";
	self.close();
}
</script>
</head>
<body>

	<div id="join">
	<form method="post" name="frm" action="CardiaryServlet?command=idcheck_form">
		<input type="text" name="id" placeholder="ID">
		<input type="submit" value="ID Check" class="submit" onclick="idcheck()">
		<p style="color:white">${userid }는  ${message } </p>
		<input type="button" class="submit" value="Use" onclick="iduse()">
	</form>
	</div>
	
</body>
</html>