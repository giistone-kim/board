package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.service.ArticleContentNotFoundException;
import board.service.ArticleData;
import board.service.ArticleLikeUpdateService;
import board.service.ArticleNotFoundException;
import board.service.ReadArticleService;
import mvc.command.CommandHandler;

public class ReadArticleHandler implements CommandHandler {

	private ReadArticleService readArticleService = new ReadArticleService();
	private ArticleLikeUpdateService articleLikeUpdateService = new ArticleLikeUpdateService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User authUser = (User)request.getSession().getServletContext().getAttribute("authUser");
		int memberId = 0;
		
		if(authUser != null) {
			memberId = authUser.getId();
		
		}
		
		String no = request.getParameter("no");
		int articleNo = Integer.parseInt(no.trim());
		
		System.out.println("올ㄹ가?");
		
		try {
			ArticleData articleData = readArticleService.getArticle(articleNo, true);
			request.setAttribute("articleData", articleData);
			
			boolean isLikeIt = articleLikeUpdateService.isLikeIt(articleNo, memberId);
			request.setAttribute("isLikeIt", isLikeIt);
			
			return "/WEB-INF/board/readArticle.jsp";
					
					
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			request.getServletContext().log("no article", e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}