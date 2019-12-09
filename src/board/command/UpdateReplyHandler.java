package board.command;

import java.io.PrintWriter;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import board.model.ArticleReply;
import board.service.ReplyData;
import board.service.ReplyService;
import mvc.command.CommandHandler;
import noti.model.MyNotification;
import noti.service.WriteMyNotiService;

public class UpdateReplyHandler implements CommandHandler {

	private ReplyService replyArticleService = new ReplyService();
	private WriteMyNotiService writeMyNotiService = new WriteMyNotiService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String article_no = request.getParameter("article_no");
		int articleNo = Integer.parseInt(article_no.trim());
		
		ReplyData replyData = replyArticleService.selectReply(articleNo);
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("replyList", replyData.getReplyList());
		jsonObj.put("totReplyCnt", replyData.getTotCnt());
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out;
		out = response.getWriter();
		out.print(jsonObj.toString());
		
		return null;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String article_no = request.getParameter("article_no");
		String nickname = request.getParameter("nickname");
		String member_id = request.getParameter("member_id");
		String comment = request.getParameter("comment");
		String articleUserId = request.getParameter("article_userId");
		
	
		int articleNo = Integer.parseInt(article_no);
		int memberId = Integer.parseInt(member_id);
		int myId = Integer.parseInt(articleUserId);

		
		ArticleReply articleReply = new ArticleReply(null, memberId, nickname, comment, new Date(), articleNo);
		int replyPage = 0;
		
		ReplyData replyData = replyArticleService.insertReply(articleReply, replyPage);
		
		MyNotification myNoti 
		= new MyNotification(null, articleNo, memberId, "reply", false, new Date(), myId);
		writeMyNotiService.writeMyNoti(myNoti);
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("replyList", replyData.getReplyList());
		jsonObj.put("totReplyCnt", replyData.getTotCnt());
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out;
		out = response.getWriter();
		out.print(jsonObj.toString());
		
		return null;
	
	}
	
}