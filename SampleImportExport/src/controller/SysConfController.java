package controller;
	
import common.SIEContext;
import common.datamodel.DsfCustomerBaseInfo;
import common.util.PubJsonUtil;
import common.xmlmodel.SystemConfigSetting;
import sysconf.SysConfApi;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class SysConfController extends MultiActionController {
	private SysConfApi sysConfApi = null;

	public SysConfController() {
	}

	public void setSysConfApi(SysConfApi sysConfApi) {
		this.sysConfApi = sysConfApi;
	}
	
	public ModelAndView viewTestObjective(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to viewTestObjective ")));
			
			
			logger.info((Object) (new StringBuilder("End to viewTestObjective ")));
			return null;
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
		}
		return null;
	}
	
	public void addBaseCustomerInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to addBaseCustomerInfo ")));
			
			
			logger.info((Object) (new StringBuilder("End to addBaseCustomerInfo ")));
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "新增数据失败，出现错误！");
				response.setContentType("application/json;charset=utf-8");     
				response.getWriter().write(jsonObject.toString()); 
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
		}
	}
	
	public void deleteBaseCustomerInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to deleteBaseCustomerInfo ")));
			String customerid = request.getParameter("customerid");
			
			sysConfApi.deleteCustomerInfo(customerid);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "删除客户信息成功！");
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to deleteBaseCustomerInfo ")));
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "删除数据失败，出现错误！");
				response.setContentType("application/json;charset=utf-8");     
				response.getWriter().write(jsonObject.toString()); 
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
		}
	}
	
	public void modifyBaseCustomerInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to modifyBase ")));
			List <DsfCustomerBaseInfo>cList = new ArrayList<DsfCustomerBaseInfo>();
			JSONObject jsonObject = new JSONObject();
			
			String customerid = request.getParameter("customerid");
			String customerKey = request.getParameter("customerKey");
			String clientnumber = request.getParameter("clientnumber");
			String customername = request.getParameter("customername");
			String address = request.getParameter("address");
			
			DsfCustomerBaseInfo dcbi = new DsfCustomerBaseInfo();
			dcbi.setAddress(address);
			dcbi.setClientnumber(clientnumber);
			dcbi.setCustomerid(new BigDecimal(customerid));
			dcbi.setCustomerKey(customerKey);
			dcbi.setCustomername(customername);      
			
			sysConfApi.saveCustomerInfo(dcbi);
			jsonObject.put("success", "保存数据成功！");
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			
			logger.info((Object) (new StringBuilder("End to modifyBase ")));
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "保存数据失败，出现错误！");
				response.setContentType("application/json;charset=utf-8");     
				response.getWriter().write(jsonObject.toString()); 
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
		}
	}
	
	public ModelAndView viewCustomerInfo(HttpServletRequest request,
			HttpServletResponse response){
		List <DsfCustomerBaseInfo> cinfoList = new ArrayList<DsfCustomerBaseInfo>();
		try {
			logger.info((Object) (new StringBuilder("Begin to viewCustomerInfo ")));
			
			cinfoList = sysConfApi.getCustomerInfoList("");
			String resultJson = PubJsonUtil.list2json(cinfoList);
			
			ModelAndView modelAndView = new ModelAndView("viewCustomerInfo.jsp");
			modelAndView.addObject("result_json",resultJson);
			logger.info((Object) (new StringBuilder("End to viewCustomerInfo ")));
			return modelAndView;
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