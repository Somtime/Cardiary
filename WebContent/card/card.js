function new_card() {
	if (docuemnt.frm.image.value == "") {
		alert("이미지를 삽입해주세요.");
		document.frm.image.focus();
	} 
	
	document.frm.encoding = "multipart/form-data";
	
	document.frm.action = "CardiaryServlet?command=new_card";
	document.frm.submit();
}


