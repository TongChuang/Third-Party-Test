package controller;
	
import common.util.CommonUtil;	
import dataaccess.DataAccessApi;
import security.SecurityApi;
import sysconf.SysConfApi;

import java.io.IOException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;	
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class LoginController extends MultiActionController {
	private Logger logger = null;

	private DataAccessApi dataAccessApi = null;

	private SecurityApi securityApi = null;
	private SysConfApi sysConfApi = null;


	public LoginController() {
		logger = CommonUtil.getLogger();
	}

	public void setSysConfApi(SysConfApi sysConfApi) {
		this.sysConfApi = sysConfApi;
	}

	public void setSecurityApi(SecurityApi securityApi) {
		this.securityApi = securityApi;
	}

	public void setDataAccessApi(DataAccessApi dataAccessApi) {
		this.dataAccessApi = dataAccessApi;
	}

	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.info(((Object) ((new StringBuilder("Begin to login "))
					.append(request.getRemoteAddr()).toString())));
			String userName = request.getParameter("userName");
			userName = CommonUtil.getUTFtoISOEncode(userName);
			String password = request.getParameter("passWord");
			int loginResult = securityApi.checkUser(userName, password);
			if (loginResult == 1) {
				request.getSession().setAttribute("userName",userName);
				String url = "/jsp/main.jsp";
				logger.info(((Object) (url)));
				response.sendRedirect(url);
			} else {
				request.setAttribute("loginResult", loginResult);
				response.sendRedirect((new StringBuilder(
						"/login.jsp?loginResult=")).append(loginResult)
						.toString());
			}
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
			return null;
		}
		return null;
	}

	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getSession().removeAttribute("userName");
			request.getSession().invalidate();
			response.sendRedirect("/login.jsp");
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
			return null;
		}
		return null;
	}
	

}