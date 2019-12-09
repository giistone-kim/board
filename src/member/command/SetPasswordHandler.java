package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePasswordService;
import member.service.EmailNotFoundException;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class SetPasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/changePwdForm.jsp";
	private ChangePasswordService changePasswordService = new ChangePasswordService();
	
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

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		request.getSession().setAttribute("email", email);
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User authUser = null;
		String email = (String)request.getSession().getAttribute("email");
		String newPwd = request.getParameter("newPwd");
		
		if(email == null) {
			authUser = (User)request.getSession().getAttribute("authUser");
			email = authUser.getEmail();
			if (email == null) {
				throw new EmailNotFoundException();
			}
		}
		
		try {
			changePasswordService.setPwd(email, newPwd);
			authUser.setHasPassword(true);
			request.getSession().setAttribute("authUser", authUser);
			response.sendRedirect("/myinfo.do");
			return null;
		} catch (MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

}