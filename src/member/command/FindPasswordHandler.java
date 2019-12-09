package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.FindPasswordService;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class FindPasswordHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/findPwdForm.jsp";
	private FindPasswordService findPasswordService = new FindPasswordService();
	
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
		String email = request.getParameter("email");
			
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		try {
			boolean isSuccess = findPasswordService.find(email);
			request.setAttribute("isSuccess", isSuccess);
			return FORM_VIEW;
		} catch (MemberNotFoundException e) {
			errors.put("emailNotFound", Boolean.TRUE);
			return FORM_VIEW;
		}
		
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}

}