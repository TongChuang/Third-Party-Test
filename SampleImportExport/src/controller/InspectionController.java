package controller;

import inspection.InspectionApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.jdom.output.XMLOutputter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import updown.UpDownApi;

import com.sun.org.apache.commons.beanutils.BeanUtils;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.LProcess;
import common.datamodel.LSample;
import common.datamodel.LTestresult;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.PubJsonUtil;
import common.util.XmlUtil;
import common.webmodel.Base_TestItem_XML;
import common.webmodel.DataSet_XML;
import common.webmodel.Process_XML;
import common.webmodel.SampleInfoList_XML;
import common.webmodel.SampleInfo_XML;
import common.webmodel.TestItem_XML;
import common.webmodel.TestObjective_XML;
import common.webmodel.TestObjectives_XML;
import common.webmodel.Testresult_Xml;

import dataaccess.DataAccessApi;

public class InspectionController extends MultiActionController {
	private Logger logger = null;
	private DataAccessApi dataAccessApi = null;
	private InspectionApi inspectionApi = null;

	public InspectionController() {
		logger = CommonUtil.getLogger();
	}

	public void setDataAccessApi(DataAccessApi dataAccessApi) {
		this.dataAccessApi = dataAccessApi;
	}

	public void setInspectionApi(InspectionApi inspectionApi) {
		this.inspectionApi = inspectionApi;
	}
	//inspectionInfo
	//界面
	public ModelAndView viewInspectionInfo(HttpServletRequest request, HttpServletResponse response) {
		try{
		logger.info((Object) (new StringBuilder("Begin to viewInspectionInfo")));
		ModelAndView modelAndView = new ModelAndView("/jsp/inspection/viewInspectionInfo.jsp");
		logger.info((Object) (new StringBuilder("End to viewInspectionInfo")));
		return modelAndView;
		}catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
			return null;
		}
	}
	//根据姓名或者搜索客户基本信息
	public void getCustomerInfoByNo(HttpServletRequest request, HttpServletResponse response) {
		try{
		logger.info((Object) (new StringBuilder("Begin to getCustomerInfoByNo")));
		String nameorid = request.getParameter("nameorid");
		List<DsfCustomerBaseInfo> resultList = new ArrayList<DsfCustomerBaseInfo>();
		resultList = inspectionApi.getCustomerInfoByNo(nameorid, nameorid);
		logger.info((Object) (new StringBuilder("End to getCustomerInfoByNo")));
		}catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
			//return null;
		}
	}
	//获取检验信息
	public void getInspectionInfo(HttpServletRequest request, HttpServletResponse response) {
		try{
		logger.info((Object) (new StringBuilder("Begin to getInspectionInfo")));
		String customerid = request.getParameter("customerid");
		
		List<DsfLYlxhdescribe> resultList = new ArrayList<DsfLYlxhdescribe>();
		resultList = inspectionApi.getYlxhdescribe(customerid);
		logger.info((Object) (new StringBuilder("End to getInspectionInfo")));
		}catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
			//return null;
		}
	}
	//根据医疗名称和医疗序号搜索检验信息
	public void getInspectionInfoByNo(HttpServletRequest request, HttpServletResponse response) {
		try{
		logger.info((Object) (new StringBuilder("Begin to getInspectionInfoByNo")));
		String nameorid = request.getParameter("nameorid");
		
		List<DsfLYlxhdescribe> resultList = new ArrayList<DsfLYlxhdescribe>();
		resultList = inspectionApi.getYlxhdescribeByNo(nameorid, nameorid);
		response.getWriter().print(JSONArray.fromObject(resultList));
		response.getWriter().flush();
		response.getWriter().close();
		logger.info((Object) (new StringBuilder("End to getInspectionInfoByNo")));
		}catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
		}
	}
	//检验信息增加
	public void addInspectionInfo(HttpServletRequest request, HttpServletResponse response) {
		try{
		logger.info((Object) (new StringBuilder("Begin to addInspectionInfo")));
		//获取检验信息
		String dsfLYlxhdescribe = request.getParameter("dsfLYlxhdescribe");
		//json对象转bean
		DsfLYlxhdescribe lYlxhdescribe = PubJsonUtil.jsonToBean(dsfLYlxhdescribe, DsfLYlxhdescribe.class);
		//增加
		inspectionApi.addYlxhdescribe(lYlxhdescribe);
		logger.info((Object) (new StringBuilder("End to addInspectionInfo")));
		}catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
		}
	}
	//检验信息编辑
	public void editInspectionInfo(HttpServletRequest request, HttpServletResponse response) {
		try{
		logger.info((Object) (new StringBuilder("Begin to editInspectionInfo")));
		//获取检验信息
		String dsfLYlxhdescribe = request.getParameter("dsfLYlxhdescribe");
		//json对象转bean
		DsfLYlxhdescribe lYlxhdescribe = PubJsonUtil.jsonToBean(dsfLYlxhdescribe, DsfLYlxhdescribe.class);
		//编辑
		inspectionApi.updateYlxhdescribe(lYlxhdescribe);
		
		
		logger.info((Object) (new StringBuilder("End to editInspectionInfo")));
		}catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
		}
	}
	//检验目的
	
}
