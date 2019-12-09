$(document).ready(function() {
	console.log("Hello JavaScript!");

	var $email = $('#email');
	var $btSubmit = $('#btn_submit');

	$btSubmit.on('click', function() {
		var email = $email.val();

		if (!email_check(email)) {
			$("#email-error").text('*이메일이 올바르지 않습니다.');
			$email.focus();
			return false;
		} else {
			$("#email-error").text('');
			return true;
		}
	});

});

function email_check(email) { 
	var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return regex.test(email);
}