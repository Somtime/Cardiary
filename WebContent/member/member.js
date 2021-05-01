function join() {
	if (document.frm.id.value == "") {
		alert("아이디를 입력해주세요.");
		document.frm.id.focus();
		return false;
	} else if (document.frm.id_check.value == "") {
		alert("아이디 중복체크를 해주세요.")
		document.frm.id_check.focus();
		return false;
	} else if (document.frm.pwd.value == "") {
		alert("비밀번호를 입력해주세요.")
		document.frm.pwd.focus();
		return false;
	} else if (document.frm.pwd_check.value == "") {
		alert("비밀번호 확인을 입력해주세요.")
		document.frm.pwd_check.focus();
		return false;
	} else if (document.frm.pwd.value != document.frm.pwd_check.value) {
		alert("비밀번호를 확인해주세요.")
		document.frm.pwd.focus();
		return false;
	} else if (document.frm.name.value == "") {
		alert("이름을 입력해주세요.")
		document.frm.name.focus();
		return false;
	}
	
	return true;
}

function idcheck() {
	if (document.frm.id.value == "") {
		alert("아이디를 입력해주세요.");
		document.frm.id.focus;
		return;
	}
	
	var url = "CardiaryServlet?command=idcheck_form&id=" + document.frm.id.value;
	 window.open( url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=300, top=300, left=300, ");
}


