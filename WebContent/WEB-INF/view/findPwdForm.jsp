<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>

<style>
    .help-block {
        color: red;
        font-size: 12px;
    }    
</style>
</head>
<body>

	<%@ include file="/WEB-INF/view/header.jsp" %>
		<section>
		<h3>비밀번호 찾기</h3>
		<c:if test="${isSuccess }">
		<div class="alert alert-info alert-dismissable">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
			<strong>알림!</strong> 입력하신 이메일 계정으로 비밀번호 재설정 메일을 전송하였습니다.
		</div>
		</c:if>
		<br>
		<p>비밀번호가 기억나지 않으시다구요?</p>
		<p>가입한 이메일 계정을 입력하세요.</p>
		<br>
		<form action="./findPwd.do" method="post">
			<div class="form-group">
				<label for="email">이메일</label> 
                <input type="text"
						class="form-control" id="email" name="email" placeholder="이메일">
				<c:if test="${!errors.emailNotFound }"> <p class="help-block" id="email-error"></p> </c:if>
				<c:if test="${errors.emailNotFound }"> <p class="help-block" id="email-error">*존재하지 않는 계정입니다.</p> </c:if>
			</div>
		<button type="submit" id="btn_submit" class="btn btn-primary btn-block">비밀번호 찾기</button>
		<a href="./login.do" class="btn btn-primary btn-block" role="button">취소</a>
		</form>
		</section>
	</div>
	<script src="js/findPwdForm.js"></script>

</body>
</html>