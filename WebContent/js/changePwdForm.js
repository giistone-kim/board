$(document).ready(function() {
	console.log("Hello JavaScript!");

	var $curPwd = $('#curPwd');
	var $newPwd = $('#newPwd');
	var $confirmNewPwd = $('#confirmNewPwd');
	var $btSubmit = $('#btn_submit');
	
	if($curPwd.val() === undefined) {
		$("#pwdForm").attr("action", "/setPwd.do");
	}

	$btSubmit.on('click', function() {
		var curPwd = $curPwd.val();
		var newPwd = $newPwd.val();
		var confirmNewPwd = $confirmNewPwd.val();
		
		if (curPwd !== undefined && !password_check(curPwd)) {
			$("#curPwd-error").text('*비밀번호가 올바르지 않습니다.');
			$curPwd.focus();
			return false;
		} else {
			$("#curPwd-error").text('');
			if(!password_check(newPwd)) {
				$("#newPwd-error").text('*비밀번호가 올바르지 않습니다.');
				$newPwd.focus();
				return false;
			} else {
				$("#newPwd-error").text('');
				if (!password_check(confirmNewPwd)) {
					$("#confirmNewPwd-error").text('*비밀번호 확인이 올바르지 않습니다..');
					$confirmNewPwd.focus();
					return false;
				} else {
					$("#confirmNewPwd-error").text('');
					if (newPwd != confirmNewPwd) {
						$("#confirmNewPwd-error").text('*비밀번호와 확인이 일치하지 않습니다.');
						$confirmNewPwd.focus();
						return false;
					} else {
						$("#confirmNewPwd-error").text('');
						return true;
					}
	
				}
			}
		}
	});

});

function password_check(password) {
	var regex = /^.*(?=.{8,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	return regex.test(password);
}