package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.DuplicateIdException;
import member.service.EmailNotFoundException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	private JoinService joinService = new JoinService();

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

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		JoinRequest joinRequest 
			= new JoinRequest(request.getParameter("email"),
					request.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);

		try {
			joinService.join(joinRequest);
			return "./login.do";
		} catch (DuplicateIdException e) {
			errors.put("duplicatedId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

	
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("authUser");
			String email = user.getEmail();
			
			if (email.equals("") && email.isEmpty()) { // 회원 인증 이메일 재전송 시 이메일 체크
				email = request.getParameter("email");
				if (email.equals("") && email.isEmpty()) {
					throw new EmailNotFoundException();
				}
			}

			joinService.sendEmail(email, null); // 회원인증 이메일 재전송
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return null;
		} catch (EmailNotFoundException e) {
			return FORM_VIEW; // 기본 가입 폼
		} catch (Exception e) {
			return FORM_VIEW;
		}
		
	}

}
