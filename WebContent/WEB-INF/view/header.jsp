<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Map"%>
<%@ page import="util.CookieBox"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- include libraries(jQuery, bootstrap) --> 
  

   
   <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
   <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
   <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> 
   <!-- include summernote css/js--> 
   <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.1/summernote.css" rel="stylesheet"> 
   <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.1/summernote.js"></script>
  <!--   <script src="/WebContent/js/summernote-ko-KR.js"></script>-->
</head>
<style>
.menu {
	float: right;
}
.notlogin {
	padding-bottom: 10px;
}
.help-block {
        color: red;
        font-size: 12px;
    }
h1 > a {
	color: black;
}	
</style>
<body>
	<div class="container">
		<header>
			<h3><a href="${pageContext.request.contextPath}/index.jsp">자유게시판</a></h3>
			
			<div class="notlogin">
			<!-- login false -->
			<c:if test="${empty authUser.email}">
				<a class="menu" href="./join.do">[회원가입]</a>
				<a class="menu" href="./login.do">[로그인]</a>
				<a class="menu" href="./list.do">[게시판]</a>
				
			</c:if>
			</div>
			<!-- login true -->
			<c:if test="${!empty authUser.email}">
			
				<c:if test="${!notiCheck }">			
				<a class="menu" href="./noti.do">[<i class="fa fa-bell" style="font-size:14px; color: red;"></i>알림]</a>
				</c:if>
				
				<c:if test="${notiCheck }">			
				<a class="menu" href="./noti.do">[<i class="fa fa-bell-o" style="font-size:14px">알림</i>]</a>
				</c:if>
				
				<a class="menu" href="./myinfo.do">[<i class="fa fa-cog" style="font-size:14px">내정보</i>]</a>
				<a class="menu" href="./logout.do">[로그아웃]</a>
				<a class="menu" href="./list.do">[게시글 리스트]</a>
 				<c:if test="${!empty authUser.nickname}">
					<p><a href="/board/user.do?user=${authUser.id }" style="color: black;"><strong id="usernickname">${authUser.nickname}</strong></a>님, 반가워요.<strong id="memberId" style="visibility: hidden;">${authUser.id}</strong></p>
				</c:if>
				<c:if test="${empty authUser.nickname }">
					<p><strong style="color: red;">닉네임</strong>을 설정하세요.</p>
				</c:if>									
				
				<!-- email register false -->
				<c:if test="${!authUser.register_check}">
					<div class="alert alert-warning alert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
						<strong>주의!</strong> 이메일 인증을 하지 않으면 서비스를 이용하실 수 없습니다. 
						<a href="/board/join.do?email=${authUser.email }" class="alert-link"><i class="fa fa-retweet" style="font-size:18px"></i>이메일 인증 재전송</a>
					</div>
				</c:if>
				
			</c:if>
			
		</header>
		<hr>
		</div>
</body>
</html>