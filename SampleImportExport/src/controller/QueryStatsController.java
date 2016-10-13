package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import common.datamodel.DsfCustomerBarCode;
import common.datamodel.DsfCustomerBaseInfo;
import common.util.CommonUtil;
import common.util.PubJsonUtil;

import queryStats.QueryStatsApi;

/**
 * @author zjn
 * @createTime 2016-10-3
 */
public class QueryStatsController extends MultiActionController {
	private QueryStatsApi queryStatsApi = null;
	private Logger logger = null;

	public QueryStatsController() {
		logger = CommonUtil.getLogger();
	}

	public void setQueryStatsApi(QueryStatsApi queryStatsApi) {
		this.queryStatsApi = queryStatsApi;
	}

	public ModelAndView viewQueryStats(HttpServletRequest request, HttpServletResponse response) {
		logger.info((Object) (new StringBuilder("Begin to viewQueryStats ")));

		ModelAndView modelAndView = new ModelAndView("viewQueryStats.jsp");

		logger.info((Object) (new StringBuilder("End to viewQueryStats ")));
		return modelAndView;
	}

	public ModelAndView viewPrintCode(HttpServletRequest request, HttpServletResponse response) {
		logger.info((Object) (new StringBuilder("Begin to viewPrintCode")));
		try {
			List<DsfCustomerBaseInfo> resultList = new ArrayList<DsfCustomerBaseInfo>();
			resultList = queryStatsApi.getBaseCustomerInfo();
			String resultJson = "";
			if (null != resultList && resultList.size() > 0) {
				resultJson = PubJsonUtil.list2json(resultList);
			}
			ModelAndView modelAndView = new ModelAndView("viewPrintCode.jsp");
			modelAndView.addObject("result_json", resultJson);
			logger.info((Object) (new StringBuilder("End to viewPrintCode")));
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

	public synchronized void printCode(HttpServletRequest request, HttpServletResponse response) {
		logger.info((Object) (new StringBuilder("Begin to printCode")));
		List <DsfCustomerBarCode>oldBarCodeList = new ArrayList<DsfCustomerBarCode>();
		List <String>resultList = new ArrayList<String>();
		try {
			String clientnumber = request.getParameter("clientnumber");
			String printNum = request.getParameter("printNum");
			String codeType = request.getParameter("codeType");
			
			String barCode = "";
			String oldBarCode = "0";
			
			oldBarCodeList = queryStatsApi.getNowCode(clientnumber);
			if(null!=oldBarCodeList&&oldBarCodeList.size()>0){
				oldBarCode = oldBarCodeList.get(0).getBarcode();;
				for (int i = 0; i < Integer.parseInt(printNum); i++) {
					oldBarCode = String.format("%07d",Integer.parseInt(oldBarCode)+1);
					barCode = clientnumber+oldBarCode;
					resultList.add(barCode);
				}
			}else{
				for (int i = 0; i < Integer.parseInt(printNum); i++) {
					barCode = clientnumber + String.format("%07d", Integer.parseInt(oldBarCode)+1);
					resultList.add(barCode);
					oldBarCode = Integer.parseInt(oldBarCode)+1+"";
				}
			}
			//保存当前的最大值如数据库
			DsfCustomerBarCode dsfCustomerBarCode = new DsfCustomerBarCode();
			dsfCustomerBarCode.setCustomerid(clientnumber);
			dsfCustomerBarCode.setBarcode(oldBarCode);
			queryStatsApi.saveBarCode(dsfCustomerBarCode);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("list", resultList);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to printCode")));
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
		}
	}

}
