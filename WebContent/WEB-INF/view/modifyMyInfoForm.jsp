<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
<style type="text/css">
	.content {
		text-align: left;
	}
</style>
</head>
<body>
    	<%@ include file="/WEB-INF/view/header.jsp" %>
        <section>
        <h3>회원정보</h3>

		<c:if test="${empty authUser.nickname}">
			<div class="alert alert-warning alert-dismissable">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
				<strong>주의!</strong> 닉네임을 설정하지 않으면 게시글을 작성하실 수 없습니다.
			</div>
		</c:if>

		<c:if test="${isSuccess }">
		<div class="alert alert-info alert-dismissable">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
			<strong>알림!</strong> 입력하신 정보가 성공적으로 변경되었습니다.
		</div>
		</c:if>
		<br>
		
        <!-- modify myinfo form -->
        <form action="./myinfo.do" method="post" enctype="multipart/form-data">
            <div class="content">
                <p><img src="${authUser.profileImage}" class="img-circle" id="profile" style="width: 100px; height: 100px;">
                <a id="profileChange" href="#">사진 변경하기</a>
                <input type="file" id="file" name="profile_image" style="display:none;"><p>
            	<p><i class="fa fa-user"> ${authUser.nickname}</i></p>
            	<p><i class="fa fa-envelope"> ${authUser.email}</i></p>
            </div>
            <div class="form-group">
            	<h3>닉네임 : </h3>
                <input type="text" class="form-control" id="nickname" name="nickname" value="${authUser.nickname}">
                <c:if test="${errors.duplicatedNickname }"> 
					<p class="help-block" id="nickname-error">*중복된 닉네임 입니다.</p>
				</c:if>
            </div>

            <button type="submit" id="btn_submit" class="btn btn-primary btn-block">회원정보 변경</button>
            <a href="/board/changePwd.do" class="btn btn-primary btn-block" role="button">비밀번호 변경</a>
            <a href="/board/deleteMyAccount.do" class="btn btn-primary btn-block" role="button">계정 삭제</a>
        </form>
        </section>
            <script type="text/javascript">
	    	$('#profileChange').click(
	    		function() {
					MyFunction();
					return false;
			});
	    	
	    	function MyFunction() {
				console.log("Hello jquery");
				$('#file').click();
			}
	    	
	    	$('#file').change(
	    		function () {
					if(window.FileReader){ 
						//image 파일만 
						if (!$(this)[0].files[0].type.match(/image\//)) 
							return; 
						var reader = new FileReader(); 
						reader.onload = function(e){ 
							var src = e.target.result; 
							$('#profile').attr("src", src); // 미리보기
						} 
						reader.readAsDataURL($(this)[0].files[0]);
					}
					
				}		
	    	);
	    </script>
        
        
        
        
        <br>
   
    
	
    	    
	    		
    	
	    
    
</body>
</html>