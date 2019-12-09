package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AESFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("AESFILTER");
		AESRequestWrapper reqWrapper = new AESRequestWrapper((HttpServletRequest) req);
		//AESResponseWrapper respWrapper = new AESResponseWrapper((HttpServletResponse) resp);
		chain.doFilter(reqWrapper, resp);

		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}