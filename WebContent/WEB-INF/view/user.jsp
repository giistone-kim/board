<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style>
.btn {
	position: relative;
	top: -45px;
  	left: 20px;
}
.container ul {
	padding-left: 0;
}
.container ul li {
	display: inline;
}
.bg-1 { 
      background-color: #F6F6F6;
      padding-top: 30px;
    padding-bottom: 10px;
}
.bg-4 { 
    background-color: #2f2f2f;
    color: #ffffff;
}
</style>
<body>
<script>
function activity() {
	$(".activity-list").attr("style", "display:inherit;")
	$(".like-list").attr("style", "display:none;")
	$(".reply-list").attr("style", "display:none;")
	
	$("#activity").attr("class", "active")
	$("#likeit").attr("class", "default")
	$("#replyit").attr("class", "default")
}

function likeit() {
	$(".activity-list").attr("style", "display:none;")
	$(".like-list").attr("style", "display:inherit;")
	$(".reply-list").attr("style", "display:none;")
	
	$("#activity").attr("class", "default")
	$("#likeit").attr("class", "active")
	$("#replyit").attr("class", "default")
}

function replyit() {
	$(".activity-list").attr("style", "display:none;")
	$(".like-list").attr("style", "display:none;")
	$(".reply-list").attr("style", "display:inherit;")
	
	$("#activity").attr("class", "default")
	$("#likeit").attr("class", "default")
	$("#replyit").attr("class", "active")
}
</script>
		<!-- header -->
		<%@ include file="/WEB-INF/view/header.jsp" %>
		
		<section>
		<div class="bg-1">
			<div class="container text-center">
				<ul>
				<c:if test="${!empty userInfo.user.profileImage }">
				<li><img src="${userInfo.user.profileImage }" class="img-circle" width="100" height="100"></li>
				</c:if>
				
				<c:if test="${empty userInfo.user.profileImage }">
				<li><img src="${userInfo.user.profileImage }" class="img-circle" width="100" height="100"></li>
				</c:if>
				
				<li>
				  <strong style="font-size: 24px">${userInfo.user.nickname }</strong> 
				  <small>${userInfo.user.email }</small> <br>
				</li>
				</ul>
				<c:if test="${authUser.id == userInfo.user.id }">
				<a type="button" href= "./myinfo.do" class="btn btn-default"><i class="material-icons" style="font-size: 14px">settings</i>프로필 수정</a>
				</c:if>
			</div>
			
		</div>
		<br>
		<nav>
			<ul class="nav nav-tabs">
				<li class="active" id="activity">
				<a href="#" onclick="activity();">게시물 <span class="badge">${userInfo.getArticleListSize()}</span></a></li>
				<li class="default" id="likeit"><a href="#" onclick="likeit();">좋아요 <span class="badge">${userInfo.getLikeListSize()}</span></a></li>
				<li class="default" id="replyit"><a href="#" onclick="replyit();">댓글 <span class="badge">${userInfo.getReplyListSize()}</span></a></li>
			</ul>
		</nav>

		<div class="activity-list" style="display: inherit;">
			<table class="table">
				<thead>
					<tr>
						<th width="100%" nowrap>
						<i class="material-icons" style="font-size: 14px;">subject</i>Activity
						</th>
					</tr>
				</thead>
				<c:if test="${userInfo.getArticleListSize() <= 0 }">
					<tr>
						<td colspan="1">게시글이 없습니다.</td>
					</tr>
				</c:if>
				<tbody>
				<c:forEach var="article" items="${userInfo.articleList }">
					<tr class="active">
						<td width="90%" nowrap>
						<p><a href="/board/read.do?no=${article.number }">
						${article.number }번째 글</a>을 작성하였습니다. 
						<small><i>${article.getTransferRegDate() }전</i></small></p>
						<p><a href="/board/read.do?no=${article.number }">
						${article.title }</a></p>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="like-list" style="display: none;">
			<table class="table">
				<thead>
					<tr>
						<th width="100%" nowrap>
							<i class="material-icons" style="font-size: 14px;">favorite</i>Like it
						</th>
					</tr>
				</thead>
				<c:if test="${userInfo.getLikeListSize() <= 0 }">
					<tr>
						<td colspan="1">게시글이 없습니다.</td>
					</tr>
				</c:if>
				<tbody>
				<c:forEach var="like" items="${userInfo.likeList }">
					<tr class="active">
						<td width="90%" nowrap>
						<p><a href="/board/read.do?no=${like.articleNo }">
						${like.articleNo }번째 글</a>을 좋아요 하였습니다. 
						<small><i>${like.getTransferRegDate() }전</i></small></p>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="reply-list" style="display: none;">
			<table class="table">
				<thead>
					<tr>
						<th width="100%" nowrap>
							<i class="material-icons" style="font-size: 14px;">comment</i>Reply it
						</th>
					</tr>
				</thead>
				<c:if test="${userInfo.getReplyListSize() <= 0 }">
					<tr>
						<td colspan="1">게시글이 없습니다.</td>
					</tr>
				</c:if>
				<tbody>
				<c:forEach var="reply" items="${userInfo.replyList }">
					<tr class="active">
						<td width="90%" nowrap>
						<p><a href="/board/read.do?no=${reply.articleNo }">
						${reply.articleNo }번째 글</a>에 댓글을 작성 하였습니다. 
						<small><i>${reply.getTransferRegDate() }전</i></small></p>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		
		</section>

	

</body>
</html>