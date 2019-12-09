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
		<h3>비밀번호 변경</h3>

		<c:if test="${isSuccess }">
		<div class="alert alert-info alert-dismissable">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
			<strong>알림!</strong> 비밀번호가 성공적으로 변경되었습니다.
		</div>
		</c:if>

		<!-- change password form -->		
		<form id="pwdForm" action="./changePwd.do" method="post" >
			<c:if test="${authUser.hasPassword }">			
				<div class="form-group">
					<label for="curPwd">현재 비밀번호</label> 
	                <input type="password"
							class="form-control" id="curPwd" name="curPwd" placeholder="현재 비밀번호">
					<c:if test="${!errors.badCurPwd }"> <p class="help-block" id="curPwd-error"></p> </c:if>
					<c:if test="${errors.badCurPwd }"> <p class="help-block" id="curPwd-error">*현재 비밀번호가 일치하지 않습니다.</p> </c:if>
				</div>
	        </c:if>
	        
			<div class="form-group">
				<label for="newPwd">새로운 비밀번호</label> 
                <input type="password"
						class="form-control" id="newPwd" name="newPwd" placeholder="새로운 비밀번호 (영문+숫자혼합 8자리 이상)">
						<p class="help-block" id="newPwd-error"></p>
			</div>
            
			<div class="form-group">
				<label for="Confirm">새로운 비밀번호 확인</label> 
                <input type="password"
						class="form-control" id="confirmNewPwd" name="confirmNewPwd" placeholder="새로운 비밀번호 확인">
						<p class="help-block" id="confirmNewPwd-error"></p>
			</div>
			
			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title"><i class="fa fa-exclamation-circle" style="font-size:20px;color:red"></i> 알림</h4>
						</div>
						<div class="modal-body">
							<p>정말 취소 하시겠습니까?</p>
						</div>
						<div class="modal-footer">
							<a href="./myinfo.do" class="btn btn-default" role="button">확인</a>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>
			
            <button type="submit" id="btn_submit" class="btn btn-primary btn-block">변경 하기</button>
            <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#myModal">취소</button>
		</form>
		</section>
		<br>
	
	<script src="js/changePwdForm.js?ver=2"></script>
</body>
</html>
</html>