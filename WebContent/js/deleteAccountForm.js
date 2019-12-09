$(document).ready(function() {
	console.log("Hello JavaScript!");

	var $password = $('#password');
	var $btSubmit = $('#btn_submit');

	$btSubmit.on('click', function() {
		var password = $password.val();

		if (!password_check(password)) {
			$("#password-error").text('*비밀번호가 올바르지 않습니다.');
			$password.focus();
			return false;
		} else {
			$("#password-error").text('');
			return true;
		}
	});

});

function password_check(password) {
	var regex = /^.*(?=.{8,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	return regex.test(password);
}