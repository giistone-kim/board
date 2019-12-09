package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.PasswordNotFoundException;
import auth.service.User;
import mvc.command.CommandHandler;
import noti.service.ReadMyNotiService;

public class LoginHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private LoginService loginService = new LoginService();
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

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = trim(request.getParameter("email"));
		String password = trim(request.getParameter("password"));
		String remember = request.getParameter("remember");
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		try {
			User authUser = loginService.login(email, password, remember);
			
			if (authUser.getRememberToken() != null) { // 자동 로그인 유저 쿠키 재발급
				Cookie cookie = new Cookie("aT", authUser.getRememberToken());
				cookie.setMaxAge(14*24*60*60);
				response.addCookie(cookie);
			}
			
			boolean notiCheck = notiService.isNotiCheck(authUser.getId()); // 로그인 한 유저의 노티 체크
			
			// 해당 유저 정보 세션에 저장 (노티, 유저)
			request.getSession().setAttribute("notiCheck", notiCheck); 
			request.getSession().setAttribute("authUser", authUser);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return null;
			
		} catch (LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		} catch(PasswordNotFoundException e) { // *네아로 유저 패스워드 입력 처리
			request.getSession().setAttribute("email", email);
			response.sendRedirect("/setPwd.do");
			return null;
		}
		
	}
	
	private String trim(String str) {
		return str == null ? null : str.trim();
	}

}
