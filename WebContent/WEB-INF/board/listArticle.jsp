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
		<!-- board sort -->
		<div class="boardsort">
			<a href="/board/list.do?sort=article_no">[최신순]</a>
			<a href="/board/list.do?sort=tot_like_cnt">[추천순]</a>
			<a href="/board/list.do?sort=tot_reply_cnt">[댓글순]</a>
			<a href="/board/list.do?sort=read_cnt">[조회순]</a>
			<a href="/board/write.do" class="btn btn-primary" role="button"><i class="fa fa-pencil"></i> 새 글쓰기</a>
		</div>
		<br>
		<!-- board list -->
		<div class="boardlist"></div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th width="10%">번호</th>
					<th width="48%">제목</th>
					<th width="12%">작성자</th>
					<th width="10%" class="hidden-xs"><i class="fa fa-clock-o" style="font-size:15px"></i></th>
					<th width="10%" class="hidden-xs"><i class="fa fa-eye" style="font-size:18px"></i></th>
					<th width="10%" class="hidden-xs"><i class="fa fa-heart" style="font-size:18px;color:red"></i></th>
				</tr>
			</thead>
			<c:if test="${articlePage.hasNoArticles() }">
				<tr>
					<td colspan="6">게시글이 없습니다.</td>
				</tr>
			</c:if>
				<tbody>
				<c:forEach var="article" items="${articlePage.articleList }">
					<tr>
						<td><p>${article.number}</p></td>
						<td>
						<p><a href="/board/read.do?no=${article.number}&page=${articlePage.currentPage}&sort=${articlePage.sort }">
						<c:out value="${article.title }" />
						<span class="badge">${article.getArticleReplySize()}</span>
						</a></p>
						</td>
						<td>
						<!--  <a href="./user.do?user=${article.writer.id }"><img src="${article.writer.profileImage}" class="img-circle" id="profile" style="width: 40px; height: 40px;"></a>-->
						<a href="./user.do?user=${article.writer.id }">${article.writer.nickname }</a>
						</td>
						<td class="hidden-xs"><p>${article.transferRegDate }전</p></td>
						<td class="hidden-xs"><p>${article.readCnt }</p></td>
						<td class="hidden-xs"><p>${article.getArticleLikeSize() }</p></td>
					</tr>
				</c:forEach>
				</tbody>
		</table>
		
		<!-- board pagination -->
		<c:if test="${articlePage.hasArticles() }">
			<div class="paginator">
				<ul class="pagination">
					<c:if test="${1 >= articlePage.currentPage }">
						<li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
					</c:if>
					<c:if test="${1 < articlePage.currentPage }">
						<li><a href="/board/list.do?page=${articlePage.currentPage - 1 }&sort=${articlePage.sort }"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
					</c:if>
					
					<c:forEach var="page" begin="${articlePage.startPage }"
						end="${articlePage.endPage }">
						<c:if test="${articlePage.currentPage == page }">
							<li class="active"><a href="/board/list.do?page=${page }&sort=${articlePage.sort }">${page }</a></li>
						</c:if>
						<c:if test="${articlePage.currentPage != page }">
							<li><a href="/board/list.do?page=${page }&sort=${articlePage.sort }">${page }</a></li>
						</c:if>
					</c:forEach>
					
					<c:if test="${articlePage.totalPages > articlePage.currentPage }">
						<li><a href="/board/list.do?page=${articlePage.currentPage + 1 }&sort=${articlePage.sort }"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
					</c:if>
					<c:if test="${articlePage.totalPages <= articlePage.currentPage }">
						<li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
					</c:if>
				</ul>
			</div>
		</c:if>
		</section>
		
	
</body>
</html>