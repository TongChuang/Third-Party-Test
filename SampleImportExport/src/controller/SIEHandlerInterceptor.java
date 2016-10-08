package controller;

import common.util.CommonUtil;
import security.SecurityApi;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SIEHandlerInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = null;

	private SecurityApi securityApi = null;

	public SIEHandlerInterceptor() {
		logger = CommonUtil.getLogger();
	}

	public void setSecurityApi(SecurityApi securityApi) {
		this.securityApi = securityApi;
	}

	// 这里可以控制权限
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String handlerName = handler.getClass().getSimpleName();
		String methodName = request.getParameter("method");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		if (userName == null) {
			userName = request.getParameter("userName");
		}

		logger.info(((Object) ((new StringBuilder("handler="))
				.append(handlerName).append(", methodName=").append(methodName)
				.toString())));
		// boolean hasPass = hasPass(methodName);
		// if (hasPass) {
		// return true;
		// } else {
		// response.sendRedirect("/error.jsp");
		// return false;
		// }
		return true;
	}

	private boolean hasPass(String methodName) {
		boolean flag = false;
		if ("login".equals(methodName)) {
			flag = true;
		} else if ("logout".equals(methodName)) {
			flag = true;
		}
		return flag;
	}
}