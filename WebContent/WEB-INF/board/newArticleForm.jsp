<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>
<script src="./js/summernote-ko-KR.js"></script>

	
    <title></title>
</head>



<body>

	    <%@ include file="/WEB-INF/view/header.jsp" %>
	    <section>
        <h3>게시글 쓰기</h3>
		
		<!-- write article form -->
        <form action="/board/write.do" method="post">
        	<!--<div class="form-group" style="margin-bottom: 0px;">
        	<img src="${authUser.profileImage}" class="img-circle" id="profile" style="width: 70px; height: 70px;">
        	${authUser.nickname }
        	</div>-->
        	
            <div class="form-group">
                <label for="title"></label>
                <input type="text" class="form-control" id="title" name="title" value="${param.title }" placeholder="제목을 입력하세요.">
                <p class="help-block" id="title-error"></p>
            </div>
			
			<textarea id="summernote" name="content" rows="" cols=""></textarea>
			<script>
			$(document).ready(function(){
				
				var $title = $('#title');
				var $btSubmit = $('#btn_submit');
				
				$btSubmit.on('click', function(){
					var title = $title.val();
					
					if($.trim(data)=="OK"){
						(title == "" || title == null || title == undefined ||
							(title != null && typeof title == "object" && !Object.keys(title).length))
							$("#title-error").text('*내용을 입력하세요.');
							$title.focus();
							return false;
				}else {
					$("#title-error").text('');
					return true;
					}
					
				});
				$('#summernote').summernote({
				height : 300,
				minHeight : null,
				maxHeight : null,
				focus : true,
				onImageUpload : function(files, editor, selEditable){
					sendFile(files[0], editor, welEditable);
				}
				
				});
			
		
				});
			
			function sendFile(file, editor, welEditable) {
				data = new FormData();
				data.append("uploadFile", file);
				$.ajax({
					data : data,
					type : "/articleimage.do",
					cache : false,
					contentType : false,
					processData : false,
					success : function(data){
						editor.insertImage(welEditalbe, data.url);
					}
				});
			}
			
			</script>
			
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
							<a href="/board/list.do" class="btn btn-default" role="button" 
								>확인</a>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>

			<button type="submit" id="btn_submit" class="btn btn-primary btn-block">새 글 등록</button>
            <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#myModal">취소</button>
        </form>
        </section>
        <br>
    

</body>

</html>