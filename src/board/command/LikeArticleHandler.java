package board.command;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import board.model.ArticleLike;
import board.service.ArticleLikeData;
import board.service.ArticleLikeUpdateService;
import mvc.command.CommandHandler;
import noti.model.MyNotification;
import noti.service.WriteMyNotiService;

public class LikeArticleHandler implements CommandHandler {
	
	private ArticleLikeUpdateService service = new ArticleLikeUpdateService();
	private WriteMyNotiService writeMyNotiService = new WriteMyNotiService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberId = request.getParameter("member_id");
		String nickname = request.getParameter("nickname");
		String articleNo = request.getParameter("article_no");
		String articleUserId = request.getParameter("article_userId");
		
		int id = Integer.parseInt(memberId.trim());
		int no = Integer.parseInt(articleNo.trim());
		int myId = Integer.parseInt(articleUserId.trim());
		
		
		ArticleLike articleLike = new ArticleLike(null, id, nickname, new Date(), no);
		ArticleLikeData articleLikeData = service.like(articleLike);
		
		MyNotification myNoti 
			= new MyNotification(null, no, id, "like", false, new Date(), myId);
		writeMyNotiService.writeMyNoti(myNoti);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("like_no", articleLikeData.getArticleLike().getNumber());
		jsonObj.put("member_id", articleLikeData.getArticleLike().getMemberId());
		jsonObj.put("nickname", articleLikeData.getArticleLike().getNickname());
		jsonObj.put("totLikeCnt", articleLikeData.getTotCnt());
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out;
		out = response.getWriter();
		out.print(jsonObj.toString());
		out.flush();
		out.close();
		return null;
	}
	
}