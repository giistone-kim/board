package auth.command;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.AutoLoginService;
import auth.service.User;
import mvc.command.CommandHandler;
import noti.service.ReadMyNotiService;
import util.CookieBox;

public class AutoLoginHandler implements CommandHandler {

	private AutoLoginService autoLoginService = new AutoLoginService();
	private ReadMyNotiService notiService = new ReadMyNotiService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> cookiesMap = null;
		cookiesMap = CookieBox.getCookiesValueMap(request);
		String token = cookiesMap.get("aT");
		
		// 세션이 있다면 유지하자
		User authUser = (User)request.getSession().getAttribute("authUser");
		
		if (authUser == null) { 
			authUser = autoLoginService.autoLogin(token);
		}
		 
		if (authUser.getRememberToken() != null) {
			System.out.println("userToken=" + authUser.getRememberToken());
			Cookie cookie = new Cookie("aT", authUser.getRememberToken());
			cookie.setMaxAge(14*24*60*60);
			response.addCookie(cookie);
		}
		
		boolean notiCheck = notiService.isNotiCheck(authUser.getId());
		request.getSession().setAttribute("notiCheck", notiCheck);
		request.getSession().setAttribute("authUser", authUser);
		response.sendRedirect("/board/list.do");
		return null;
	}

}