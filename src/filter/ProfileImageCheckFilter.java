package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import auth.service.User;
import util.RandomBox;

public class ProfileImageCheckFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		User authUser = (User)req.getSession().getAttribute("authUser");
		String profileImage = authUser.getProfileImage();
		
		if (profileImage == null || profileImage.length() == 0) {
			System.out.println("profileImage="+profileImage);
			profileImage = RandomBox.getRandomImage();
			authUser.setProfileImage(profileImage);;
			req.getSession().setAttribute("authUser", authUser);
		}
		
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}