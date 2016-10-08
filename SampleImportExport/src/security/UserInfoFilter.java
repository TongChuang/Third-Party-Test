package security;

import common.util.CommonUtil;	

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class UserInfoFilter implements Filter {

	private Logger logger = null;
	private FilterConfig filterConfig = null;
	private String redirect = null;

	public UserInfoFilter() {
		logger = null;
		filterConfig = null;
		redirect = null;
		logger = CommonUtil.getLogger();
		filterConfig = null;
		redirect = null;
	}

	public final void destroy() {
		filterConfig = null;
	}

	public final void doFilter(ServletRequest request,
			ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		String userName = (String) ((HttpServletRequest) request).getSession()
				.getAttribute("userName");
		String path = ((HttpServletRequest) request).getRequestURI();
		boolean hasLogin = hasLogin(path, userName);
		if (!hasLogin) {
			logger.info((new StringBuilder("path=")).append(path).append(
					" no login,will redirect to loginpage").toString());
			((HttpServletResponse) response).sendRedirect(redirect);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean hasLogin(String path, String userName) {
		boolean flag = false;
		if(userName != null && userName.trim().length() != 0)
			flag = true;
		else if (path.indexOf("login.do") != -1)
			flag = true;
		else if (path.endsWith("redirect.jsp"))
			flag = true;
		else if (path.endsWith("/"))
			flag = true;
		else if (path.indexOf("login.jsp") != -1)
			flag = true;
		return flag;
	}

	public final void init(FilterConfig arg0) throws ServletException {
		filterConfig = arg0;
		redirect = filterConfig.getInitParameter("redirect");
	}
}