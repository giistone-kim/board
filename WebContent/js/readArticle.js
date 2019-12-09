$(document).ready(function() {
	console.log("Hello JavaScript!");

	var $heart = $("#heart");
	var articleNo = $("#article_no").val();
	var articleUserId = $("#article_userId").val();
	var userNickName = $("#usernickname").val();
	var userId = $("#memberId").val();
	var likeNo = $("#"+userId+"").val();
	var countzero = $("#countzero").val();
	
	$("#replyForm").on("submit", function() {
		event.preventDefault();
		var comment = $("#comment").val();
		replyInsert(articleNo, userNickName, userId, comment, articleUserId)
		$("#comment").val('');
	});
	
	$heart.click(function(){
		if($heart.attr("class") === "fa fa-heart") {
			$heart.attr("class", "fa fa-heart-o")
			$heart.attr("style", "color:#9C9C9C;")
			unlike(likeNo, articleNo);
		} else if($heart.attr("class") === "fa fa-heart-o") {
			$heart.attr("class", "fa fa-heart")
			$heart.attr("style", "color:red;")
			likeNo = like(articleNo, userNickName, userId, countzero, articleUserId);
		}
    });
	
});

function replyInsert(articleNo, userNickName, userId, comment, articleUserId) {
	$.ajax({
        url:'/board/writereply.do',
        dataType:'json',
        type : 'post',
        async: false,
        data: {article_no : articleNo,
        	nickname : userNickName,
        	member_id : userId, 
        	comment : comment, 
        	article_userId : articleUserId},
        success:function(data) {
        	$("#replyupdown").text("  "+data.totReplyCnt);
        	
        	var list = data.replyList;
        	var str = '';
        	var articleNo
        	
        	$.each(list, function(index, reply) {
        				str +=
            			"<li id='"+reply.replyNo+"'><p>" +
            			"<b><span id='reply_nickname'><a href='/user.do?user="+reply.memberId+"' style='color: black;'>"+reply.nickname+"</a></span></b> " +
            			"<span id='reply_content'>"+reply.content+"</span> " +
            			"<small style='color:#9C9C9C;'><i><span id='reply_regdate'>"+reply.transferRegDate+"</span>전</i> </small>" +
            			"<button style='background-color: white; border: none; width: 14px; height: 14px' " +
            			"onclick='deletereply("+reply.replyNo+", "+reply.articleNo+");'>" +
            			"<i class='material-icons' style='font-size:14px'>clear</i></button></p></li>";
        				articleNo = reply.articleNo
        	});
	
        	$('#replylist').html('<ul>'+str+'</ul>');
        	
        }
    });
}

function deletereply(replyNo, articleNo) {
	$.ajax({
        url:'/board/deletereply.do',
        dataType:'json',
        type : 'post',
        async: false,
        data: {reply_no : replyNo, 
        		article_no : articleNo},
        success:function(data){
        	$("#replyupdown").text("  "+data.totReplyCnt);
        	$('#replylist li').remove("#"+replyNo+"");
        }
    });
}

function like(articleNo, userNickName, userId, countzero, articleUserId) {
	var likeNo = "";
	
		$.ajax({
	        url:"/board/like.do",
	        dataType:'json',
	        type : 'post',
	        async: false,
	        data: {article_no : articleNo,
	        	nickname : userNickName,
	        	member_id : userId, 
	        	article_userId : articleUserId},
	        success:function(data) {
	        	$("#likeupdown").text(data.totReplyCnt);
	           
	        	if(countzero === undefined) {
	        		if(data.totLikeCnt > 10) {
			        $('#likelist').prepend("<li id='"+data.like_no+"' class='likelist' style='display: none;'><b>"+
			        		"<span id='like_nickname'>"+data.nickname+"</span>"+
			        		"<span id='"+data.member_id+"' style='display: none;'>"+data.like_no+"</span></b></li>");
	        		} else if (data.totLikeCnt <= 1) {
	        		$('li').remove("#countzero");
		        	$('#likelist').prepend("<li id='"+data.like_no+"' class='likelist'><b>"+
				        	"<span id='like_nickname'>"+data.nickname+"</span>"+
				        	"<span id='"+data.member_id+"' style='display: none;'>"+data.like_no+"</span></b></li>" +
				        	"<li id='like_end' class='likelist'>님이 좋아합니다.</li>");
	        		} else {
	        		$('#likelist').prepend("<li id='"+data.like_no+"' class='likelist'><b>"+
				        	"<span id='like_nickname'>"+data.nickname+"</span>"+
				        	"<span id='"+data.member_id+"' style='display: none;'>"+data.like_no+"</span></b></li>");
	        		}
	        	} else {
	        		$('li').remove("#countzero");
	        		$('#likelist').prepend("<li id='"+data.like_no+"' class='likelist'><b>"+
			        		"<span id='like_nickname'>"+data.nickname+"</span>"+
			        		"<span id='"+data.member_id+"' style='display: none;'>"+data.like_no+"</span></b></li>" +
			        				"<li id='like_end' class='likelist'>님이 좋아합니다.</li>");
	        	}
	            
	            likeNo = data.like_no;
	        }
	    });
		return likeNo;
}

function unlike(likeNo, articleNo) {
	$.ajax({
        url:'/board/unlike.do',
        dataType:'json',
        type : 'post',
        async: false,
        data: {like_no : likeNo, 
        		article_no : articleNo},
        success:function(data){
        	$("#likeupdown").text(data.totLikeCnt);
        	if (data.totLikeCnt > 0) {
        		$('li').remove("#"+likeNo+"");
        	} else {
        		$('li').remove('.likelist');
        		$('.likezone').html("<ul id='likelist'><li id='countzero' class='likelist'><p>가장 먼저 좋아요를 눌러주세요.</p></li></ul>");
        	}
            
        }
    });
}