package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.service.ArticleNotFoundException;
import board.service.DeleteArticleService;
import board.service.PermissionDeniedException;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {

	private DeleteArticleService deleteArticleService = new DeleteArticleService(); 
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String no = request.getParameter("no");
		int articleNo = Integer.parseInt(no);
		User authUser = (User) request.getSession().getAttribute("authUser");
		
		try {
			deleteArticleService.delete(articleNo, authUser.getId());
			return "/board/list.do";
		} catch (ArticleNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
	
}