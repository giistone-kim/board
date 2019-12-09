package board.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.model.Writer;
import board.service.WriteArticleService;
import board.service.WriteRequest;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/board/newArticleForm.jsp";
	private WriteArticleService writeArticleService = new WriteArticleService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User authUser = (User)request.getSession().getAttribute("authUser");
		WriteRequest writeRequest = createWriteRequest(authUser, request);
		
		int articleNo = writeArticleService.write(writeRequest);
		
		response.sendRedirect("/board/read.do?no="+articleNo);
		return null;
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	
	private WriteRequest createWriteRequest(User authUser, HttpServletRequest request) {
		return new WriteRequest(new Writer(authUser.getId(), authUser.getNickname(), authUser.getProfileImage()), 
				request.getParameter("title"), 
				request.getParameter("content"));
	}

}