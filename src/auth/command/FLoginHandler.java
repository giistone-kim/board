package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginRequest;
import auth.service.FLoginService;
import auth.service.User;
import mvc.command.CommandHandler;
import noti.service.ReadMyNotiService;

public class FLoginHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private FLoginService fLoginService = new FLoginService();
	private ReadMyNotiService notiService = new ReadMyNotiService();
	
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
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(request.getParameter("email"));
		loginRequest.setName(request.getParameter("nickname"));
		loginRequest.setProfile_image(request.getParameter("profile_image"));
		loginRequest.setAccessToken(request.getParameter("access_token"));
		
		User authUser = fLoginService.login(loginRequest);
		
		boolean notiCheck = notiService.isNotiCheck(authUser.getId());
		request.getSession().setAttribute("notiCheck", notiCheck);
		
		request.getSession().setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return null;
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	
	
}