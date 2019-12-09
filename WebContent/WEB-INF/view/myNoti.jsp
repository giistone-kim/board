<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="/board/js/myNoti.js?version=1"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style>
div .paginator {
	text-align: center; 
}
.table > tbody > tr > td > p {
	padding-top: 10px;
}
.btn {
	float: right;
	margin-bottom: 20px;
}
</style>
<body>
		<!-- header -->
		<%@ include file="/WEB-INF/view/header.jsp" %>
		
		<section>
		<h3>알림</h3>
		<!-- board list -->
		<div class="notilist"></div>
		<table class="table">
			<thead>
				<tr>
					<th width="10%" nowrap>번호</th>
					<th>내용</th>
				</tr>
			</thead>
			<c:if test="${!notiData.hasNotiList() }">
				<tr>
					<td colspan="2">새로운 알림이 없습니다.</td>
				</tr>
			</c:if>
				<tbody>
				<c:forEach var="noti" items="${notiData.notiList }">
				
				<c:if test="${!noti.readCheck }">
					<tr class="active">
						<td width="10%" nowrap><p>${noti.number}</p></td>

						<c:if test="${noti.type == 'like'}">
						<td><p><a href="/user.do?user=${noti.anotherMemberId }">${noti.anotherUserNickname }</a> 님이 
									<a href="/board/read.do?no=${noti.articleNo }" onclick="readNoti(${noti.number});" >
									${noti.articleNo }번째 글</a>에 
									<strong>좋아요</strong> 하였습니다.</p> 
									<small><i>${noti.getTransferRegDate() }전</i></small> 
									<small style="float: right; margin-left: 10px">
									<a href="noti.do" onclick="deleteNoti(${noti.number});">삭제</a></small>
									<small style="float: right;">안읽음</small>
									</td>
						</c:if>
						
						<c:if test="${noti.type == 'reply'}">
						<td><p><a href="/user.do?user=${noti.anotherMemberId }">${noti.anotherUserNickname }</a> 님이 
									<a href="/board/read.do?no=${noti.articleNo }" onclick="readNoti(${noti.number});">
									${noti.articleNo }번째 글</a>에 
									<strong>댓글</strong>을 작성하였습니다.</p> 
									<small><i>${noti.getTransferRegDate() }전</i></small>
									<small style="float: right; margin-left: 10px">
									<a href="noti.do" onclick="deleteNoti(${noti.number});">삭제</a></small>
									<small style="float: right;">안읽음</small>
									</td>
						</c:if>
					</tr>
					</c:if>
					
					
					<c:if test="${noti.readCheck }">
					<tr>
						<td width="10%" nowrap><p>${noti.number}</p></td>

						<c:if test="${noti.type == 'like'}">
						<td><p><a href="/user.do?user=${noti.anotherMemberId }">${noti.anotherUserNickname }</a> 님이 
									<a href="/board/read.do?no=${noti.articleNo }">
									${noti.articleNo }번째 글</a>에 <strong>좋아요</strong> 하였습니다.</p> 
									<small><i>${noti.getTransferRegDate() }전</i></small> 
									<small style="float: right; margin-left: 10px">
									<a href="noti.do" onclick="deleteNoti(${noti.number});">삭제</a></small>
									<small style="float: right;">읽음</small>
									</td>
						</c:if>
						
						<c:if test="${noti.type == 'reply'}">
						<td><p><a href="/user.do?user=${noti.anotherMemberId }">${noti.anotherUserNickname }</a> 님이 
									<a href="/board/read.do?no=${noti.articleNo }">
									${noti.articleNo }번째 글</a>에 <strong>댓글</strong>을 작성하였습니다.</p> 
									<small><i>${noti.getTransferRegDate() }전</i></small>
									<small style="float: right; margin-left: 10px">
									<a href="noti.do" onclick="deleteNoti(${noti.number});">삭제</a></small>
									<small style="float: right;">읽음</small>
									</td>
						</c:if>
					</tr>
					</c:if>
					
				</c:forEach>
				</tbody>
		</table>
		
		</section>
		
	
</body>
</html>