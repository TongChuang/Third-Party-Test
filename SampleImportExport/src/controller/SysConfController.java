package controller;
	
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import sysconf.SysConfApi;

import common.SIEContext;
import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.LTestitem;
import common.datamodel.LTestobjective;
import common.util.PubJsonUtil;
import common.xmlmodel.SystemConfigSetting;

public class SysConfController extends MultiActionController {
	private SysConfApi sysConfApi = null;

	public SysConfController() {
	}

	public void setSysConfApi(SysConfApi sysConfApi) {
		this.sysConfApi = sysConfApi;
	}
	
	public ModelAndView viewTestItem(HttpServletRequest request,
			HttpServletResponse response) {
		List<DsfCustomerBaseInfo> cinfoList = null;
		List<LTestitem> tinfoList = null;
		try {
			logger.info((Object) (new StringBuilder("Begin to viewTestItem ")));
			cinfoList = sysConfApi.getCustomerInfoList("");
			String resultJson = PubJsonUtil.list2json(cinfoList);
			
			tinfoList = sysConfApi.getLocalTestItems();
			ModelAndView modelAndView = new ModelAndView("viewTestItem.jsp");
			modelAndView.addObject("customer_json",resultJson);
			modelAndView.addObject("items_list",tinfoList);


			logger.info((Object) (new StringBuilder("End to viewTestItem ")));
			return modelAndView;
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
	
	//检验目的信息基础页面
	public ModelAndView viewTestObjective(HttpServletRequest request,
			HttpServletResponse response) {
		List<DsfCustomerBaseInfo> cinfoList = null;
		List<DsfTestitems> iinfoList = null;
		String resultJson = "";
		try {
			logger.info((Object) (new StringBuilder("Begin to viewTestObjective ")));
			cinfoList = sysConfApi.getCustomerInfoList("");
			resultJson = PubJsonUtil.list2json(cinfoList);
			
			iinfoList = sysConfApi.getTestItems();
			ModelAndView modelAndView = new ModelAndView("viewTestObjective.jsp");
			modelAndView.addObject("customer_json",resultJson);
			modelAndView.addObject("items_list",iinfoList);
			resultJson = PubJsonUtil.list2json(iinfoList);
			System.out.println(resultJson);
			modelAndView.addObject("items_json",resultJson);

			logger.info((Object) (new StringBuilder("End to viewTestObjective ")));
			return modelAndView;
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
	//根据姓名或者搜索客户基本信息
		public void getCustomerInfoByNo(HttpServletRequest request, HttpServletResponse response) {
			String resultJson = "";
			try{
			logger.info((Object) (new StringBuilder("Begin to getCustomerInfoByNo")));
			String nameorid = request.getParameter("nameorid");
			//System.out.println("参数获取1:"+nameorid);
			
			List<DsfCustomerBaseInfo> resultCList = new ArrayList<DsfCustomerBaseInfo>();
			resultCList = sysConfApi.getCustomerInfoByNo(nameorid, nameorid);
			resultJson = PubJsonUtil.list2json(resultCList);
			//System.out.println(resultJson);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result_json", resultJson);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to getCustomerInfoByNo")));
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
				//return null;
			}
		}
		//选中ajax展现检验信息
		public void getInspectionInfo(HttpServletRequest request, HttpServletResponse response) {
			String resultJson = "";
			try{
			logger.info((Object) (new StringBuilder("Begin to getInspectionInfo")));
			String customerid = request.getParameter("customerid");
			//System.out.println("参数获取2："+customerid);
			List<DsfLYlxhdescribe> resultYList = new ArrayList<DsfLYlxhdescribe>();
			resultYList = sysConfApi.getYlxhdescribe(customerid);
			
			resultJson = PubJsonUtil.list2json(resultYList);
			//System.out.println(resultJson);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "搜索数据成功！");
			jsonObject.put("result_json", resultJson);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to getInspectionInfo")));
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
				//return null;
			}
		}	
		//根据医疗名称和医疗序号搜索检验信息
		public void getTestOjectivefoByNo(HttpServletRequest request, HttpServletResponse response) {
			String resultJson = "";
			try{
			logger.info((Object) (new StringBuilder("Begin to getInspectionInfoByNo")));
			String nameorid = request.getParameter("nameorid");
			
			List<DsfLYlxhdescribe> resultYList = new ArrayList<DsfLYlxhdescribe>();
			resultYList = sysConfApi.getYlxhdescribeByNo(nameorid, nameorid);
			resultJson = PubJsonUtil.list2json(resultYList);
			//System.out.println(resultJson);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result_json", resultJson);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to getInspectionInfoByNo")));
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
			}
		}
		//增加检验目的页面显示
		public ModelAndView viewAddTestObjective(HttpServletRequest request, HttpServletResponse response) {
			String resultJson = "";
			try{
				logger.info((Object) (new StringBuilder("Begin to viewAddTestObjective")));
				String clientnumber = request.getParameter("clientnumber");
				//System.out.println("客户编号:"+clientnumber);
				ModelAndView modelAndView = new ModelAndView("viewAddTestObjective.jsp");
				modelAndView.addObject("customerid",clientnumber);
				logger.info((Object) (new StringBuilder("End to viewTestObjective ")));
				return modelAndView;
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
				return null;
			}	
		}
		//检验目的增加
		public void addTestObjective(HttpServletRequest request, HttpServletResponse response) {
			String resultString = "";
			try{
			logger.info((Object) (new StringBuilder("Begin to addTestObjective")));
			//获取检验信息
			/*
			String id = request.getParameter("form_id");
			String customerid = request.getParameter("form_customerid");
			String ylxh = request.getParameter("form_ylxh");
			String ylmc = request.getParameter("form_ylmc");
			String profiletest = request.getParameter("form_profiletest");
			String professionalgroup = request.getParameter("form_professionalgroup");
			String inspectionsection = request.getParameter("form_inspectionsection");
			*/
			//String id = request.getParameter("id");
			String customerid = request.getParameter("customerid");
			String ylxh = request.getParameter("ylxh");
			String ylmc = request.getParameter("ylmc");
			String profiletest = request.getParameter("profiletest");
			String professionalgroup = request.getParameter("professionalgroup");
			String inspectionsection = request.getParameter("inspectionsection");
			
			String id = sysConfApi.getSequence("DSF_L_YLXHDESCRIBE_SEQUENCE");
			System.out.println(id+":"+customerid+":"+ylxh+":"+ylmc+":"+profiletest+":"+professionalgroup+":"+inspectionsection);
			BigDecimal idBig = new BigDecimal(id);
			DsfLYlxhdescribe lYlxhdescribe = new DsfLYlxhdescribe();
			lYlxhdescribe.setId(idBig);
			lYlxhdescribe.setCustomerid(customerid);
			lYlxhdescribe.setYlxh(ylxh);
			lYlxhdescribe.setYlmc(ylmc);
			lYlxhdescribe.setProfiletest(profiletest);
			lYlxhdescribe.setInspectionsection(inspectionsection);
			lYlxhdescribe.setProfessionalgroup(professionalgroup);

			//System.out.println("检验目的对象:"+lYlxhdescribe);
			//增加
			sysConfApi.addYlxhdescribe(lYlxhdescribe);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "增加检验目的成功！");
			
			List<DsfLYlxhdescribe> resultYList = sysConfApi.getYlxhdescribe(customerid);
			resultString = PubJsonUtil.list2json(resultYList);
			//System.out.println(resultString);
			jsonObject.put("result_json", resultString);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to addTestObjective")));
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
			}
		}
		//修改检验目的页面显示
		public ModelAndView viewUpdateTestObjective(HttpServletRequest request, HttpServletResponse response) {
			String resultString = "";
			try{
				logger.info((Object) (new StringBuilder("Begin to viewUpdateTestObjective")));
				String id = request.getParameter("id");
				/*
				String ylxh = request.getParameter("ylxh");
				String ylmc = request.getParameter("ylmc");
				String customerid = request.getParameter("customerid");
				String profiletest = request.getParameter("profiletest");
				String professionalgroup = request.getParameter("professionalgroup");
				String inspectionsection = request.getParameter("inspectionsection");
				System.out.println("医疗名称："+ylmc);
				
				ModelAndView modelAndView = new ModelAndView("viewUpdateTestObjective.jsp");
				modelAndView.addObject("id",id);
				modelAndView.addObject("ylxh",ylxh);
				modelAndView.addObject("ylmc",ylmc);
				modelAndView.addObject("customerid",customerid);
				modelAndView.addObject("profiletest",profiletest);
				modelAndView.addObject("professionalgroup",professionalgroup);
				modelAndView.addObject("inspectionsection",inspectionsection);
				
				DsfLYlxhdescribe rs = new DsfLYlxhdescribe();
				rs.setCustomerid(customerid);
				BigDecimal bigId = new BigDecimal(id);
				rs.setId(bigId);
				rs.setInspectionsection(inspectionsection);
				rs.setProfessionalgroup(professionalgroup);
				rs.setProfiletest(profiletest);
				rs.setYlmc(ylmc);
				rs.setYlxh(ylxh);
				*/
				ModelAndView modelAndView = new ModelAndView("viewUpdateTestObjective.jsp");
				DsfLYlxhdescribe rs =  new DsfLYlxhdescribe();
				System.out.println("id:"+id);
				List<DsfLYlxhdescribe> resultList = sysConfApi.getYlxhdescribeById(id);
				System.out.println("返回结果2："+resultList);
				if(null != resultList && resultList.size()>0){
					rs = resultList.get(0);
					resultString = PubJsonUtil.bean2json(rs);
					System.out.println("返回结果1："+resultString);
					modelAndView.addObject("result_json",resultString);
				}
				logger.info((Object) (new StringBuilder("End to viewUpdateTestObjective ")));
				return modelAndView;
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
				return null;
			}	
		}
		//修改检验目的
		public void updateTestObjective(HttpServletRequest request, HttpServletResponse response) {
			String resultString = null;
			try{
			logger.info((Object) (new StringBuilder("Begin to updateTestObjective")));
			//获取检验信息
			String id = request.getParameter("id");
			String customerid = request.getParameter("customerid");
			String ylxh = request.getParameter("ylxh");
			String ylmc = request.getParameter("ylmc");
			String profiletest = request.getParameter("profiletest");
			String professionalgroup = request.getParameter("professionalgroup");
			String inspectionsection = request.getParameter("inspectionsection");
			System.out.println(id+":"+customerid+":"+ylxh+":"+ylmc+":"+profiletest+":"+professionalgroup+":"+inspectionsection);
			BigDecimal idBig = new BigDecimal(id);
			DsfLYlxhdescribe lYlxhdescribe = new DsfLYlxhdescribe();
			lYlxhdescribe.setId(idBig);
			lYlxhdescribe.setCustomerid(customerid);
			lYlxhdescribe.setYlxh(ylxh);
			lYlxhdescribe.setYlmc(ylmc);
			lYlxhdescribe.setProfiletest(profiletest);
			lYlxhdescribe.setInspectionsection(inspectionsection);
			lYlxhdescribe.setProfessionalgroup(professionalgroup);

			//System.out.println("检验目的对象:"+lYlxhdescribe);
			sysConfApi.updateYlxhdescribe(lYlxhdescribe);
			
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("success", "修改检验目的成功！");
			
			List<DsfLYlxhdescribe> resultYList = sysConfApi.getYlxhdescribe(customerid);
			resultString = PubJsonUtil.list2json(resultYList);
			System.out.println(resultString);
			jsonObject.put("result_json", resultString);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to updateTestObjective")));
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
			}
		}	
		//检验目的删除
		public void deleteTestObjective(HttpServletRequest request, HttpServletResponse response) {
			String resultJson = "";
			try{
			logger.info((Object) (new StringBuilder("Begin to deleteTestObjective")));
			String id = request.getParameter("id");
			String customerid = request.getParameter("customerid");
			
			BigDecimal idBig = new BigDecimal(id);
			sysConfApi.deleteYlxhdescribe(idBig);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "删除数据成功！");
			
			List<DsfLYlxhdescribe> resultYList = new ArrayList<DsfLYlxhdescribe>();
			resultYList = sysConfApi.getYlxhdescribe(customerid);
			resultJson = PubJsonUtil.list2json(resultYList);
			jsonObject.put("result_json", resultJson);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to deleteTestObjective")));
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
			}
		}
		//ajax展现检验项目
		public void getInspectionItem(HttpServletRequest request, HttpServletResponse response) {
			String resultJson = "";
			try{
			logger.info((Object) (new StringBuilder("Begin to getInspectionItem")));
			String profiletest = request.getParameter("profiletest");
			String customerid = request.getParameter("customerid");
			//将传入的参数以','分割并组装成字符串
			String proList = "";
			if(!profiletest.equals("")){
				for(String s:profiletest.split(",")){
					if(!proList.equals("")){
						proList+=",'"+s+"'";
					}
					else
						proList+="'"+s+"'";
				}
			}
			//System.out.println("检验项目参数获取："+proList);
			
			List<DsfTestitems> resultTList = new ArrayList<DsfTestitems>();
			//List<DsfTestitems> resultList = new ArrayList<DsfTestitems>();
			resultTList = sysConfApi.getTestItemsByNo(proList,customerid);
			//resultList = sysConfApi.getTestItems();
			
			System.out.println(resultJson);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "搜索数据成功！");
			resultJson = PubJsonUtil.list2json(resultTList);
			jsonObject.put("result_json", resultJson);
			//resultJson = PubJsonUtil.list2json(resultList);
			//System.out.println(resultJson);
			//System.out.println(resultList);
			//jsonObject.put("result_json2", resultList);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to getInspectionItem")));
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
				//return null;
			}
		}	
		//检验项目智能查询
		public void addTestItemsSearch(HttpServletRequest request, HttpServletResponse response) {
			String resultJson = "";
			List<DsfTestitems> iinfoList = null;
			try {
				logger.info((Object) (new StringBuilder("Begin to addTestItemsSearch ")));
				
				iinfoList = sysConfApi.getTestItems();
				JSONObject jsonObject = new JSONObject();
				resultJson = PubJsonUtil.list2json(iinfoList);
				jsonObject.put("result_json", resultJson);
				response.setContentType("application/json;charset=utf-8");     
				response.getWriter().write(jsonObject.toString()); 
				logger.info((Object) (new StringBuilder("End to addTestItemsSearch ")));
			} catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					response.sendRedirect("/error.jsp");
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
			}
		}
		//增加检验项目
		public void addTestItems(HttpServletRequest request, HttpServletResponse response) {
			String resultJson = "";
			try{
			logger.info((Object) (new StringBuilder("Begin to addTestItems")));
			String customerid = request.getParameter("customerid");
			String indexId = request.getParameter("indexId");
			String ylxh = request.getParameter("ylxh");
			String profiletest = request.getParameter("profiletest");
			//System.out.println("第三方客户id:"+customerid+"项目编号:"+indexId+" 医疗序号:"+ylxh+" 项目编号主要:"+profiletest);
			DsfLYlxhdescribe testItems = new DsfLYlxhdescribe();
			//根据医疗序号修改profileTest字段
			String indexIds = profiletest+indexId.trim()+",";
			sysConfApi.saveTestObjective(ylxh,indexIds);
			//System.out.println(profiletest+indexId.trim());
			//拆分profiletest
			String proList = "";
			if(!profiletest.equals("")){
				for(String s:profiletest.split(",")){
					if(!proList.equals("")){
						proList+=",'"+s+"'";
					}
					else
						proList+="'"+s+"'";
				}
				proList += ",'"+indexId.trim()+"'";
			}else{
				proList += indexId.trim()+"'";
			}
			
			//System.out.println("主要编号："+proList);
			List<DsfTestitems> resultTList = new ArrayList<DsfTestitems>();
			//查询修改后的数据
			resultTList = sysConfApi.getTestItemsByNo(proList,customerid);
			resultJson = PubJsonUtil.list2json(resultTList);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result_json", resultJson);
			
			List<DsfLYlxhdescribe> resultYList = new ArrayList<DsfLYlxhdescribe>();
			resultYList = sysConfApi.getYlxhdescribe(customerid);
			resultJson = PubJsonUtil.list2json(resultYList);
			jsonObject.put("result_json2", resultJson);
			
			jsonObject.put("indexIds", indexIds);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 		
			logger.info((Object) (new StringBuilder("End to addTestItems")));
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
				//return null;
			}
		}
		//2001检验项目对照
		public ModelAndView viewInspectionItemControl(HttpServletRequest request,
				HttpServletResponse response) {
			List<DsfCustomerBaseInfo> cinfoList = null;
			try {
				logger.info((Object) (new StringBuilder("Begin to viewInspectionItemControl")));
				cinfoList = sysConfApi.getCustomerInfoList("");
				String resultJson = PubJsonUtil.list2json(cinfoList);
				
				ModelAndView modelAndView = new ModelAndView("viewCustomerInfo.jsp");
				modelAndView.addObject("result_json",resultJson);
				logger.info((Object) (new StringBuilder("End to viewInspectionItemControl")));
				return modelAndView;
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
		//根据客户检验名称和编号 搜索项目对照
		//ajax展现检验项目对照
		public void getControltestitems(HttpServletRequest request,
				HttpServletResponse response) {
			String resultJson = "";
			try {
				logger.info((Object) (new StringBuilder("Begin to getControltestitems")));
				String customerid = request.getParameter("customerid");
				System.out.println(customerid);
				List<DsfControltestitems> resultCList = new ArrayList<DsfControltestitems>();
				resultCList = sysConfApi.getControltestitems(customerid);
				
				resultJson = PubJsonUtil.list2json(resultCList);
				System.out.println(resultJson);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("result_json", resultJson);
				response.setContentType("application/json;charset=utf-8");     
				response.getWriter().write(jsonObject.toString()); 
				logger.info((Object) (new StringBuilder("End to getControltestitems")));
			} catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
			}
		}	
		//项目对照搜索
		public void getControltestitemsByNo(HttpServletRequest request,
				HttpServletResponse response) {
			String resultJson = "";
			try {
				logger.info((Object) (new StringBuilder("Begin to getControltestitemsByNo")));
				String nameorid = request.getParameter("nameorid");
				List<DsfControltestitems> resultCList = new ArrayList<DsfControltestitems>();
				resultCList = sysConfApi.getControltestitemsByNo(nameorid,nameorid);
				
				resultJson = PubJsonUtil.list2json(resultCList);
				//System.out.println(resultJson);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("result_json", resultJson);
				response.setContentType("application/json;charset=utf-8");     
				response.getWriter().write(jsonObject.toString()); 
				logger.info((Object) (new StringBuilder("End to getControltestitemsByNo")));
			} catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
			}
		}	
		//检验项目对照
		public void autoControlTestItems(HttpServletRequest request,
				HttpServletResponse response) {
			String resultJson = "";
			try {
				logger.info((Object) (new StringBuilder("Begin to autoControlTestItems")));
				//需要选中
				String customerid = request.getParameter("customerid");
				//获取第三方检验项目
				List<DsfControltestitems> resultCList = new ArrayList<DsfControltestitems>();
				resultCList = sysConfApi.getControltestitems(customerid);
				List<LTestitem> resultTList = new ArrayList<LTestitem>();
				resultTList = sysConfApi.getLocalTestItems();
				/*
				System.out.println(customerid);
				System.out.println("对照表信息:"+resultCList);
				System.out.println("本地检验项表:"+resultTList);
				*/
				List<LTestobjective> resultOList = new ArrayList<LTestobjective>();
				
				DsfControltestitems dctt = new DsfControltestitems();
				
				List<DsfControltestitems> dcttList = new ArrayList<DsfControltestitems>();
				
				List<String> resultLList= new ArrayList<String>();
				if(null!=resultCList){
					for(DsfControltestitems resultControltestitems : resultCList){
						if(null!=resultTList){
							for(LTestitem iTestitem:resultTList ){
								if(resultControltestitems.getCustomeritemsname().trim().equals(iTestitem.getName().trim())){
									dctt.setCustomerid(customerid);
									dctt.setCustomeritems(resultControltestitems.getCustomeritems());
									dctt.setCustomeritemsname(resultControltestitems.getCustomeritemsname());
									dctt.setLocalitems(iTestitem.getIndexId());
									dctt.setLocalitemsname(iTestitem.getName());
									dctt.setId(resultControltestitems.getId());
									//System.out.println("对照信息："+dctt);
									dcttList.add(dctt);
								}
							}
						}
					}
				}
				if(null!=dcttList){
					sysConfApi.saveAll(dcttList);
				}
				
				List<DsfControltestitems> results = sysConfApi.getControltestitems(customerid);
				resultJson = PubJsonUtil.list2json(results);
				System.out.println(resultJson);
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("result_json", resultJson);
				response.setContentType("application/json;charset=utf-8");     
				response.getWriter().write(jsonObject.toString()); 
				logger.info((Object) (new StringBuilder("End to autoControlTestItems")));
			} catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
			}
		}	
		public void addControlItems(HttpServletRequest request, HttpServletResponse response) {
			String resultJson = "";
			try{
			logger.info((Object) (new StringBuilder("Begin to addControlItems")));
			String id = request.getParameter("id");
			String customerid = request.getParameter("customerid");
			String indexId = request.getParameter("indexId");
			System.out.println("id:"+id+"customerid:"+customerid);
			//根据id可以获取选中行唯一的对照表信息
			BigDecimal bigId = new BigDecimal(id);
			List<DsfControltestitems> dctiList = sysConfApi.getControltestitemsById(bigId);
			System.out.println(dctiList);
			//
			List<LTestitem> ltocList = sysConfApi.getTestItemsByIndexId(indexId);
			System.out.println(ltocList);
			String customeritemsname = "";
			if(null!=ltocList&&ltocList.size()>0){
				customeritemsname = ltocList.get(0).getName();
			}
			if(null!=dctiList&&dctiList.size()>0){
				dctiList.get(0).setLocalitems(indexId);
				dctiList.get(0).setLocalitemsname(customeritemsname);
			}
			//更新
			sysConfApi.saveData(dctiList.get(0), "DsfControltestitems");
			
			resultJson = PubJsonUtil.list2json(dctiList);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result_json", resultJson);
			
			dctiList = sysConfApi.getControltestitems(customerid);
			resultJson = PubJsonUtil.list2json(dctiList);
			jsonObject.put("result_json2", resultJson);
			
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 		
			logger.info((Object) (new StringBuilder("End to addControlItems")));
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
				//return null;
			}
		}
	//ajax获取本地检验项目
		
		public void getLocalInspectionItem(HttpServletRequest request, HttpServletResponse response) {
			String resultJson = "";
			try{
			logger.info((Object) (new StringBuilder("Begin to getLocalInspectionItem")));
			String customerid = request.getParameter("customerid");
			
			List<LTestitem> resultTList = new ArrayList<LTestitem>();
			resultTList = sysConfApi.getLocalTestItemsByNo(customerid);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "搜索数据成功！");
			resultJson = PubJsonUtil.list2json(resultTList);
			jsonObject.put("result_json", resultJson);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to getLocalInspectionItem")));
			}catch (Exception e) {
				logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "搜索数据失败，出现错误！");
					response.setContentType("application/json;charset=utf-8");     
					response.getWriter().write(jsonObject.toString()); 
				} catch (IOException e1) {
					logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
				}
				//return null;
			}
		}	
		
	//3001客户信息增加页面
		public ModelAndView viewAddBaseCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
		String resultJson = "";
		try{
			logger.info((Object) (new StringBuilder("Begin to viewAddTestObjective")));
			
			//获取sequence先
			String id = sysConfApi.getSequence("DSFCUSTOMERBASEINFO_SEQUENCE");
			System.out.println("新增序号:"+id);
			ModelAndView modelAndView = new ModelAndView("viewAddBaseCustomerInfo.jsp");
			modelAndView.addObject("baseInfo_id",id);
			logger.info((Object) (new StringBuilder("End to viewAddBaseCustomerInfo ")));
			return modelAndView;
		}catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "搜索数据失败，出现错误！");
				response.setContentType("application/json;charset=utf-8");     
				response.getWriter().write(jsonObject.toString()); 
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
			return null;
		}	
	}
	//客户信息增加页面
	public void addBaseCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
		String resultString = "";
		try{
		logger.info((Object) (new StringBuilder("Begin to addTestObjective")));
		String customerid = request.getParameter("customerid");
		String customername = request.getParameter("customername");
		String address = request.getParameter("address");
		String clientnumber = request.getParameter("clientnumber");
		String customerkey = request.getParameter("customerkey");
		String basicinfostate = request.getParameter("basicinfostate");
		//System.out.println(customerid+":"+customername+":"+address+":"+clientnumber+":"+customerkey+":"+basicinfostate);
		JSONObject jsonObject = new JSONObject();
		List<DsfCustomerBaseInfo> dcbiList = sysConfApi.getCustomerInfoList("");
		//验证名字是否已经存在
		List<DsfCustomerBaseInfo> nameList =  sysConfApi.getCustomerInfoByName(customername);
		if(null==nameList||nameList.size()>0){
			jsonObject.put("nameExist", "名字已经存在或未填！");
			
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to addTestObjective")));
		}else{
			DsfCustomerBaseInfo dcbi = new DsfCustomerBaseInfo();
			dcbi.setAddress(address);
			dcbi.setBasicinfostate(basicinfostate);
			BigDecimal bigId = new BigDecimal(customerid);
			dcbi.setCustomerid(bigId);
			dcbi.setClientnumber(clientnumber);
			dcbi.setCustomername(customername);
			System.out.println(dcbi);
			sysConfApi.saveData(dcbi, "DsfCustomerBaseInfo");
			
			jsonObject.put("success", "增加检验目的成功！");
			
			//需要跟新页父页面的客户信息表格
			//List<DsfCustomerBaseInfo> dcbiList = sysConfApi.getCustomerBaseInfoByCustomerId(clientnumber);
			
			resultString = PubJsonUtil.list2json(dcbiList);
			System.out.println(resultString);
			 
			jsonObject.put("result_json", resultString);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to addTestObjective")));
		}
		}catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "搜索数据失败，出现错误！");
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
			String basicinfostate = request.getParameter("basicinfostate");
			
			DsfCustomerBaseInfo dcbi = new DsfCustomerBaseInfo();
			dcbi.setAddress(address);
			dcbi.setClientnumber(clientnumber);
			dcbi.setCustomerid(new BigDecimal(customerid));
			dcbi.setCustomerKey(customerKey);
			dcbi.setCustomername(customername);  
			dcbi.setBasicinfostate(basicinfostate);
			
			sysConfApi.saveCustomerInfo(dcbi);
			
			cList = sysConfApi.getCustomerInfoByNo("", "");
			String cjson = PubJsonUtil.list2json(cList);
			jsonObject.put("success", "保存数据成功！");
			jsonObject.put("cjson", cjson);
			System.out.println(cjson);
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
			request.setAttribute("defaultPassword", sysConfig.getDefaultPassword());
			request.setAttribute("upftpRoot", sysConfig.getUpftpRoot());
			request.setAttribute("downftpRoot", sysConfig.getDownftpRoot());
			request.setAttribute("updateServerAddress", sysConfig.getUpdateServerAddress());
			request.setAttribute("webserviceUrl", sysConfig.getWebserviceUrl());
			
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
			String defaultPassword = ((SystemConfigSetting) SIEContext
					.getSystemConfigTable().getConfigs().get(0)).getDefaultPassword()
					;
			if (request.getParameter("defaultPassword") != null){
				defaultPassword = request.getParameter("defaultPassword");
			}
			String upftpRoot = ((SystemConfigSetting) SIEContext
					.getSystemConfigTable().getConfigs().get(0)).getUpftpRoot();
			if (request.getParameter("upftpRoot") != null) {
				upftpRoot = request.getParameter("upftpRoot");
			}
			String downftpRoot = ((SystemConfigSetting) SIEContext
					.getSystemConfigTable().getConfigs().get(0)).getDownftpRoot();
			if (request.getParameter("downftpRoot") != null) {
				downftpRoot = request.getParameter("downftpRoot");
			}
			String updateServerAddress = ((SystemConfigSetting) SIEContext
					.getSystemConfigTable().getConfigs().get(0)).getUpdateServerAddress();
			if (request.getParameter("updateServerAddress") != null) {
				updateServerAddress = request.getParameter("updateServerAddress");
			}
			String webserviceUrl = ((SystemConfigSetting) SIEContext
					.getSystemConfigTable().getConfigs().get(0)).getWebserviceUrl();
			if (request.getParameter("webserviceUrl") != null) {
				webserviceUrl = request.getParameter("webserviceUrl");
			}

			SystemConfigSetting config = new SystemConfigSetting();
			config.setDefaultPassword(defaultPassword);
			config.setUpftpRoot(upftpRoot);
			config.setDownftpRoot(downftpRoot);
			config.setUpdateServerAddress(updateServerAddress);
			config.setWebserviceUrl(webserviceUrl);

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