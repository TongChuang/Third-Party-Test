package controller;
	
import common.SIEContext;
import common.xmlmodel.SystemConfigSetting;
import sysconf.SysConfApi;
import java.io.IOException;

import javax.servlet.http.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class SysConfController extends MultiActionController {
	private SysConfApi sysConfApi = null;

	public SysConfController() {
	}

	public void setSysConfApi(SysConfApi sysConfApi) {
		this.sysConfApi = sysConfApi;
	}

	public ModelAndView viewSystemConfig(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to showSystemBasicInfo ")));
			
			SystemConfigSetting sysConfig = sysConfApi.getSystemConfig();
			request.setAttribute("accesscode", sysConfig.getAccessCode());
			request.setAttribute("hospital", sysConfig.getHospital());
			request.setAttribute("userid", sysConfig.getUserId());
			request.setAttribute("weburl", sysConfig.getWebserviceUrl());
			request.setAttribute("bloodbank", sysConfig.getBloodbank());
			
			String msg = request.getParameter("msg");
			request.setAttribute("msg", msg);
			logger.info((Object) (new StringBuilder("End to showSystemBasicInfo ")));
			return new ModelAndView("systemBasicInfo.jsp");
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
			return null;
		}
	}
	


	public ModelAndView updateSystemConfig(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String hospital = ((SystemConfigSetting) SIEContext
					.getSystemConfigTable().getConfigs().get(0))
					.getHospital();
			if (request.getParameter("hospital") != null){
				hospital = request.getParameter("hospital");
			}
			String webServiceUtl = ((SystemConfigSetting) SIEContext
					.getSystemConfigTable().getConfigs().get(0)).getWebserviceUrl();
			if (request.getParameter("webServiceUtl") != null) {
				webServiceUtl = request.getParameter("webServiceUtl");
			}
			String accessCode = ((SystemConfigSetting) SIEContext
					.getSystemConfigTable().getConfigs().get(0)).getAccessCode();
			if (request.getParameter("accessCode") != null) {
				accessCode = request.getParameter("accessCode");
			}
			String userId = ((SystemConfigSetting) SIEContext
					.getSystemConfigTable().getConfigs().get(0)).getUserId();
			if (request.getParameter("userId") != null) {
				userId = request.getParameter("userId");
			}
			String bloodbank = ((SystemConfigSetting) SIEContext
					.getSystemConfigTable().getConfigs().get(0)).getBloodbank();
			if (request.getParameter("bloodbank") != null) {
				bloodbank = request.getParameter("bloodbank");
			} 

			SystemConfigSetting config = new SystemConfigSetting();
			config.setHospital(hospital);
			config.setWebserviceUrl(webServiceUtl);
			config.setAccessCode(accessCode);
			config.setUserId(userId);
			config.setBloodbank(bloodbank);

			try {
				sysConfApi.updateSystemConfig(config);
				response.sendRedirect("/jsp/sysconf/sysConf.do?method=viewSystemConfig&msg=success");
			} catch (Exception exception) {
				request.setAttribute("msg", "保存出错，请检查输入的数据是否正确！");
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


}