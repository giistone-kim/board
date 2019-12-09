$(document).ready(function (){
	console.log("Hello JavaScript!");
	
	var $email = $('#email');
	var $password = $('#password');
	var $passwordConfirm = $('#confirmPassword');
	var $btSubmit = $('#btn_submit');
	
	$btSubmit.on('click', function() {
		  var email = $email.val();
		  var password = $password.val();
		  var passwordConfirm = $passwordConfirm.val();

		  if(!email_check(email)) {
		  	$("#email-error").text('*이메일 주소가 올바르지 않습니다.');
		  	$email.focus();
		    return false;
		  } else {
			$("#email-error").text('');
		  	if(!password_check(password)) {
		  		$("#password-error").text('*비밀번호가 올바르지 않습니다.');
		  		$password.focus();
		  		return false;
		  	} else {
		  		$("#password-error").text('');
		  		if(!password_check(passwordConfirm)) {
		  			$("#passwordConfirm-error").text('*비밀번호 확인이 올바르지 않습니다..');
		  			$passwordConfirm.focus();
		  			return false;
		  		} else {
		  			$("#passwordConfirm-error").text('');
		  			if(password!=passwordConfirm) {
		  				$("#passwordConfirm-error").text('*비밀번호와 확인이 일치하지 않습니다.');
		  				$passwordConfirm.focus();
		  				return false;
		  			} else {
		  				$("#passwordConfirm-error").text('');
		  				return true;
		  			}
		  			
		  		}
		  	}
		 }
	});
	
});


function email_check(email) { 
	var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return regex.test(email);
}

function password_check(password) {
	var regex = /^.*(?=.{8,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	return regex.test(password);
}