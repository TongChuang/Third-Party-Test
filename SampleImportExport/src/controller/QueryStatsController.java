package controller;

import java.io.IOException	;	
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

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
	
	public String viewPrintCode(HttpServletRequest request, HttpServletResponse response) {
		logger.info((Object) (new StringBuilder("Begin to viewPrintCode ")));
		List <DsfCustomerBaseInfo>resultList = new ArrayList<DsfCustomerBaseInfo>();
		resultList = queryStatsApi.getBaseCustomerInfo();
		String resultJson = "";
		if(null!=resultList&&resultList.size()>0){
			resultJson = PubJsonUtil.list2json(resultList);
		}
		logger.info((Object) (new StringBuilder("End to viewPrintCode ")));
		return resultJson;
	}
	

}
