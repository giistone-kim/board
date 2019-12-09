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
		<h3>계정 삭제</h3>
		<br>
		
		<p>정말로 계정을 삭제하시겠습니까?</p>
		<p>계정을 삭제하시려면 비밀번호를 입력하세요.</p><br>
		<form action="./deleteMyAccount.do" method="post">
			<div class="form-group">
				<label for="password">비밀번호</label> 
                <input type="password"
						class="form-control" id="password" name="password" placeholder="비밀번호">
				<c:if test="${!errors.badCurPwd }"> <p class="help-block" id="password-error"></p> </c:if>
				<c:if test="${errors.badCurPwd }"> <p class="help-block" id="password-error">*비밀번호가 일치하지 않습니다.</p> </c:if>
			</div>
		<button type="submit" id="btn_submit" class="btn btn-primary btn-block">삭제 하기</button>
		<a href="./myinfo.do" class="btn btn-primary btn-block" role="button">취소</a>
		</form>
		</section>
	
	<script src="js/deleteAccountForm.js"></script>

</body>
</html>