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