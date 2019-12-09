package noti.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import noti.service.ReadMyNotiService;
import noti.service.UpdateMyNotiService;

public class UpdateMyNotiHandler implements CommandHandler {

	private UpdateMyNotiService updateNotiService = new UpdateMyNotiService();
	private ReadMyNotiService notiService = new ReadMyNotiService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User authUser = (User)request.getSession().getAttribute("authUser");
		
		String noti_no = request.getParameter("noti_no");
		int notiNo = Integer.parseInt(noti_no);

		updateNotiService.update(notiNo);
		
		boolean notiCheck = notiService.isNotiCheck(authUser.getId());
		request.getSession().setAttribute("notiCheck", notiCheck);

		return null;
	}

}