package controller;

import java.io.File;	
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
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

import com.sun.org.apache.commons.beanutils.BeanUtils;

import updown.UpDownApi;

import common.SIEBeanFactory;
import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfProcess;
import common.datamodel.DsfTestitems;
import common.datamodel.LSample;
import common.datamodel.LTestresult;
import common.datamodel.LabUser;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.FileOperHelper;
import common.util.FileStoreUtil;
import common.util.FtpOperUtil;
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
import common.xmlmodel.SystemConfigSetting;

import dataaccess.DataAccessApi;

public class UpDownController extends MultiActionController {
	private Logger logger = null;
	private DataAccessApi dataAccessApi = null;
	private UpDownApi upDownApi = null;

	public UpDownController() {
		logger = CommonUtil.getLogger();
	}

	public void setDataAccessApi(DataAccessApi dataAccessApi) {
		this.dataAccessApi = dataAccessApi;
	}

	public void setUpDownApi(UpDownApi upDownApi) {
		this.upDownApi = upDownApi;
	}
	
	public void getCustomerInfoByBasicInfoState(HttpServletRequest request, HttpServletResponse response){
		try {
			logger.info((Object) (new StringBuilder("Begin to getSampleInfoByBarCode ")));
			String dsfcustomerid = request.getParameter("dsfcustomerid");
			DsfCustomerBaseInfo dcbiBaseInfo = upDownApi.getCustomerInfoById(dsfcustomerid);
			
			List resultList = new ArrayList();
			if("0".equals(dcbiBaseInfo.getBasicinfostate())){
				resultList = upDownApi.getLTestItemsByTestItem("", dcbiBaseInfo.getCustomerid());
			}else{
				resultList = upDownApi.getDsfTestItemsByTestItem("", dcbiBaseInfo.getCustomerid());
			}
			JSONObject jsonObject = new JSONObject();
			String resultJsonString = PubJsonUtil.list2json(resultList);
			jsonObject.put("resultJson", resultJsonString);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to getCustomerInfoByBasicInfoState ")));
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
		}
	}
	
	public void getSampleInfoByBarCode(HttpServletRequest request, HttpServletResponse response){
		String resultJson = "";
		String sampletJson = "";
		try {
			logger.info((Object) (new StringBuilder("Begin to getSampleInfoByBarCode ")));
			String barcode = request.getParameter("barcode");
			//System.out.println("扫描枪的扫描号码:"+barcode);
			String customerid = barcode.substring(0, 5);
			//根据本地条码号获取样本信息
			LSample lSample = null;
			List<LSample> lsList = upDownApi.getSamplesByBarCode(barcode);
			System.out.println("根据本地条码号获取样本信息"+lsList);
			if(null!=lsList&&lsList.size()>0){
				lSample = lsList.get(0);
			}
			System.out.println("根据条码查询样本信息："+lSample);
			//根据客户id获取Dsf检验目的
			List<DsfLYlxhdescribe> lYlxhdescribeList = upDownApi.getDsfTestObjectiveById(customerid);
			lYlxhdescribeList = upDownApi.getDsfTestObjectiveById(customerid);
			//流水值
			int num = 0;
			//流水号
			String serialNumber = "";
			//获取当天yyyyMMdd格式的日期字符串
			String dateString = DateUtil.getFolerDate(new Date());
			JSONArray jsonArray = new JSONArray();
			for(LSample ls: lsList){
				JSONObject jo = new JSONObject();
				for(DsfLYlxhdescribe ly :lYlxhdescribeList){
					if(ls.getYlxh().equals(ly.getYlxh())){
						//重新组织json
						jo.put("ylxh", ly.getYlxh());
						jo.put("professionalgroup", ly.getProfessionalgroup());
						jo.put("inspectionsection", ly.getInspectionsection());
						jo.put("id", ly.getId());
						jo.put("ylmc", ly.getYlmc());
						jo.put("customerid",ly.getCustomerid());
						jo.put("professiongroup", ly.getProfessionalgroup());
						jo.put("inspectionsection", ly.getInspectionsection());
						
						num = upDownApi.getSeralNumber(dateString+ly.getInspectionsection());
						num += 1; 
						//获取流水号
						serialNumber = dateString + "" +ly.getInspectionsection() +""+ String.format("%04d",num);
						jo.put("serialnumber", serialNumber);
						System.out.println("打印流水号："+serialNumber);
						jsonArray.add(jo);
						//更新流水号
						/*
						ls.setSampleno(serialNumber);
						upDownApi.saveData(ls, "LSample");
						*/
					}
				}
			}
			String jsonresult1 = "{Rows:"+jsonArray.toString()+"}";
			System.out.println("数据1："+jsonresult1);
			JSONObject jsonObject = new JSONObject();
			String sampleJsonString = "";
			if(null!=lSample){
				sampletJson = PubJsonUtil.bean2json(lSample);
				String ftpPath = FileStoreUtil.getSamplePic(true,lSample.getDsfcustomerid(),true,false);
				if("/null/".equals(ftpPath)){
					request.setAttribute("pic","/resources/images/no-pic.jpg");
				}else{
					request.setAttribute("pic",ftpPath);
				}
			}else {
				jsonObject.put("error", "无此条码信息！");
				jsonObject.put("pic", "/resources/images/top.jpg");
				
			}
			System.out.println("数据2："+resultJson);
			//jsonObject.put("result_json", resultJson);
			jsonObject.put("sample_json", sampletJson);
			jsonObject.put("jsonresult1", jsonresult1);
			sampleJsonString = jsonObject.toString();
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(sampleJsonString); 
			logger.info((Object) (new StringBuilder("End to getSampleInfoByBarCode ")));
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
		}
	}
	
	public ModelAndView addManualEntry(HttpServletRequest request, HttpServletResponse response,LSample lSample) {
		try {
			logger.info((Object) (new StringBuilder("Begin to addManualEntry ")));
			String collectionpersonnel = request.getParameter("collectionpersonnel");
			String collectiontime = request.getParameter("collectiontime");
			//获取上传照片
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;     
		        // 获得文件：     
		        MultipartFile file = multipartRequest.getFile("mpImg");  
		        if(!file.isEmpty()){
		        String fileName = CommonUtil.getUTFtoISOEncode(file.getOriginalFilename());
		        String newFileName = CommonUtil.renameFileName(lSample.getLocalbarcode(), fileName);
		        String uploadpath = FileStoreUtil.getSamplePic(true,lSample.getDsfcustomerid(),true,false);
		        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadpath, newFileName)); 
		        SystemConfigSetting config = SIEBeanFactory.getSysConfApi().getSystemConfig();
		        String ftpRoot = config.getUpftpRoot();
		    	File uploadFile = new File(uploadpath+newFileName);
		    	String ftpPath = FileStoreUtil.getSamplePic(true,lSample.getDsfcustomerid(),true,false);
		    	lSample.setImgurl(config.getDownftpRoot()+ftpPath+newFileName);
				try {
					FtpOperUtil.uploadFile(uploadFile, ftpRoot, ftpPath);
					FileOperHelper.deleteFileAndFolder(uploadpath+newFileName);
					logger.info("文件上传成功！");
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
		      //获取上传照片完成
		     }
		       
		        
		    //保存FORM表单的数据
		    String dsfSampSeq = dataAccessApi.getSeqString("DSF_YLXH_SEQUENCE");    
		    lSample.setId(new BigDecimal(dsfSampSeq));
		    lSample.setAgeunit(CommonUtil.getUTFtoISOEncode(lSample.getAgeunit()));
		    upDownApi.saveData(lSample, "LSample");
		    
		    //保存时间，采集时间，采集人，录入时间录入人
		    DsfProcess dsfProcess = new DsfProcess();
		    dsfProcess.setCollectionpersonnel(collectionpersonnel);
		    dsfProcess.setCollectiontime(DateUtil.parseLongDate(collectiontime));
		    dsfProcess.setInputpersonnel(request.getSession().getAttribute("userName").toString());
		    dsfProcess.setInputtime(new Date());
		    upDownApi.saveData(dsfProcess, "DsfProcess");
			
			ModelAndView modelAndView = new ModelAndView("/jsp/upLoadFile/viewManualEntry.jsp");
			modelAndView.addObject("pic","/resources/images/no-pic.jpg");
			modelAndView.addObject("success","数据保存成功！");
			logger.info((Object) (new StringBuilder("End to addManualEntry ")));
			return modelAndView;
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			ModelAndView modelAndView = new ModelAndView("/jsp/upLoadFile/viewManualEntry.jsp");
			modelAndView.addObject("pic","/resources/images/no-pic.jpg");
			modelAndView.addObject("error","数据保存失败，请检查数据！");
			return modelAndView;
		}
	}

	/**
	 * 手工录入样本信息
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView viewManualEntry(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to viewManualEntry ")));
			
			List <DsfCustomerBaseInfo>reList = upDownApi.getCustomerInfo();
			List <LabUser> userList = upDownApi.getUserInfo("");
			
			ModelAndView modelAndView = new ModelAndView("/jsp/upLoadFile/viewManualEntry.jsp");
			modelAndView.addObject("pic","/resources/images/no-pic.jpg");
			modelAndView.addObject("customerList",reList);
			modelAndView.addObject("userList",userList);
			logger.info((Object) (new StringBuilder("End to viewManualEntry ")));
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
	
	/**
	 * 前处理
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView viewPretreatment(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to viewPretreatment ")));
			
			
			
			ModelAndView modelAndView = new ModelAndView("/jsp/upLoadFile/viewPretreatment.jsp");
			modelAndView.addObject("pic","/resources/images/no-pic.jpg");
			logger.info((Object) (new StringBuilder("End to viewPretreatment ")));
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
	

	public ModelAndView viewUpdatePage(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to viewUpdatePage ")));
			String type = request.getParameter("type");
			List<DsfCustomerBaseInfo> resultList = new ArrayList<DsfCustomerBaseInfo>();
			resultList = upDownApi.getCustomerInfo();
			logger.info((Object) (new StringBuilder("End to viewUpdatePage ")));
			ModelAndView modelAndView = new ModelAndView("/jsp/upLoadFile/viewImpExpData.jsp");
			modelAndView.addObject("type", type);
			modelAndView.addObject("customerInfoList", resultList);
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

	public ModelAndView uploadExcelFile(HttpServletRequest request, HttpServletResponse response) {
		String errorMsg = "";
		try {
			logger.info((Object) (new StringBuilder("Begin to uploadExcelFile ")));
			String userName = request.getSession().getAttribute("userName").toString();
			String nowDate = DateUtil.getLongDate(new Date());

			InputStream in = null;
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("uploadFile");
			if (file.isEmpty()) {
				throw new Exception("文件不存在！");
			}
			in = file.getInputStream();
			// 创建Excel工作薄
			Workbook work = WorkbookFactory.create(in);
			if (null == work) {
				throw new Exception("创建Excel工作薄为空！");
			}
			/* 获取第1个sheet标签的内容，（标本信息） start */
			Sheet sheet1 = work.getSheetAt(0);
			Row row1 = null;
			Cell cell1 = null;
			System.out.println("总行数：" + sheet1.getLastRowNum());
			List<SampleInfo_XML> sResultList = new ArrayList<SampleInfo_XML>();
			List<Process_XML> pResultList = new ArrayList<Process_XML>();
			// 遍历当前sheet1中的所有行
			for (int i = sheet1.getFirstRowNum(); i <= sheet1.getLastRowNum(); i++) {
				row1 = sheet1.getRow(i);
				if (row1 == null || row1.getFirstCellNum() == i || row1.getCell(0) == null) {
					continue;
				} else {
					SampleInfo_XML sampleInfo = new SampleInfo_XML();
					Process_XML process = new Process_XML();
					errorMsg = "标签1,第" + i + "行,第1列,出现错误,数据不规范。";
					sampleInfo.setAge(row1.getCell(0).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第2列,出现错误,数据不规范。";
					sampleInfo.setAgeunit(row1.getCell(1).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第3列,出现错误,数据不规范。";
					sampleInfo.setBirthday(row1.getCell(2).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第4列,出现错误,数据不规范。";
					sampleInfo.setCycle(row1.getCell(3).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第5列,出现错误,数据不规范。";
					sampleInfo.setDepartBed(row1.getCell(4).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第6列,出现错误,数据不规范。";
					sampleInfo.setDiagnostic(row1.getCell(5).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第7列,出现错误,数据不规范。";
					sampleInfo.setDsfbarcode(row1.getCell(6).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第8列,出现错误,数据不规范。";
					sampleInfo.setHossection(row1.getCell(7).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第9列,出现错误,数据不规范。";
					sampleInfo.setInspectionname(row1.getCell(8).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第10列,出现错误,数据不规范。";
					sampleInfo.setPatientblh(row1.getCell(9).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第11列,出现错误,数据不规范。";
					sampleInfo.setPatientid(row1.getCell(10).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第12列,出现错误,数据不规范。";
					sampleInfo.setPatientname(row1.getCell(11).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第13列,出现错误,数据不规范。";
					sampleInfo.setSampletype(row1.getCell(12).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第14列,出现错误,数据不规范。";
					sampleInfo.setSex(row1.getCell(13).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第15列,出现错误,数据不规范。";
					sampleInfo.setStayhospitalmode(row1.getCell(14).getStringCellValue());
					errorMsg = "标签1,第" + i + "行,第16列,出现错误,数据不规范。";
					sampleInfo.setYlxh(row1.getCell(15).getStringCellValue());

					process.setCollectionpersonnel(row1.getCell(16).getStringCellValue());
					process.setCollectiontime(row1.getCell(17).getStringCellValue());
					process.setDsfbarcode(row1.getCell(6).getStringCellValue());

					sResultList.add(sampleInfo);
					pResultList.add(process);
				}
			}
			String excelSDate = PubJsonUtil.list2json(sResultList);
			String excelPDate = PubJsonUtil.list2json(pResultList);
			/* 获取第一个sheet标签的内容，（标本信息） end */

			/* 获取第2个sheet标签的内容，（标本信息） start */
			Sheet sheet2 = work.getSheetAt(1);
			Row row2 = null;
			Cell cell2 = null;
			// System.out.println("总行数：" + sheet2.getLastRowNum());
			List<TestItem_XML> tResultList = new ArrayList<TestItem_XML>();
			// 遍历当前sheet2中的所有行
			for (int i = sheet2.getFirstRowNum(); i <= sheet2.getLastRowNum(); i++) {
				row2 = sheet2.getRow(i);
				// System.out.println("行：" + i);
				if (row2 == null || row2.getFirstCellNum() == i || row2.getCell(0) == null) {
					continue;
				} else {
					TestItem_XML testitems = new TestItem_XML();
					testitems.setName(row2.getCell(1).getStringCellValue());
					testitems.setDsfbarcode(row2.getCell(2).getStringCellValue());
					testitems.setTestitem(row2.getCell(0).getStringCellValue());
					tResultList.add(testitems);
				}
			}
			String excelTDate = PubJsonUtil.list2json(tResultList);
			/* 获取第2个sheet标签的内容，（标本信息） end */
			work.close();
			in.close();

			List<DsfCustomerBaseInfo> resultList = new ArrayList<DsfCustomerBaseInfo>();
			resultList = upDownApi.getCustomerInfo();
			ModelAndView modelAndView = new ModelAndView("/jsp/upLoadFile/viewImpExpData.jsp");
			modelAndView.addObject("type", "excel");
			modelAndView.addObject("result_Sjson", excelSDate);
			modelAndView.addObject("result_Pjson", excelPDate);
			modelAndView.addObject("result_Tjson", excelTDate);
			modelAndView.addObject("customerInfoList", resultList);

			logger.info((Object) (new StringBuilder("End to uploadExcelFile ")));
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

	public ModelAndView uploadXmlFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to uploadXmlFile ")));
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("uploadFile");
			if (file.isEmpty()) {
				throw new Exception("文件不存在！");
			}
			String xmlString = null;
			// 创建SAXReader对象
			SAXReader reader = new SAXReader();
			// 读取文件 转换成Document
			// Document document = reader.read(new
			// File("C:/Users/kewell/Desktop/a.xml"));
			String fileName = file.getName();
			// 根据上传的文件路径解析文件
			InputStream in = null;
			in = file.getInputStream();
			Document document = reader.read(in);
			// document转换为String字符串
			xmlString = document.asXML();
			System.out.println("document 字符串：" + xmlString);
			DataSet_XML dataSet_XML = XmlUtil.converyToJavaBean(xmlString, DataSet_XML.class);
			List<SampleInfo_XML> xmlSDateList = new ArrayList<SampleInfo_XML>();
			List<Process_XML> xmlPDateList = new ArrayList<Process_XML>();
			List<TestItem_XML> xmlTDateList = new ArrayList<TestItem_XML>();
			for (SampleInfoList_XML sInfoList_XML : dataSet_XML.getsXmlList()) {
				xmlSDateList.add(sInfoList_XML.getSampleInfo());
				xmlPDateList.add(sInfoList_XML.getProcess());
				xmlTDateList.addAll(sInfoList_XML.getTestItem_XMLs());
			}

			String xmlSDate = PubJsonUtil.list2json(xmlSDateList);
			String xmlPDate = PubJsonUtil.list2json(xmlPDateList);
			String xmlTDate = PubJsonUtil.list2json(xmlTDateList);

			List<DsfCustomerBaseInfo> resultList = new ArrayList<DsfCustomerBaseInfo>();
			resultList = upDownApi.getCustomerInfo();
			ModelAndView modelAndView = new ModelAndView("/jsp/upLoadFile/viewImpExpData.jsp");
			modelAndView.addObject("type", "xml");
			modelAndView.addObject("result_Sjson", xmlSDate);
			modelAndView.addObject("result_Pjson", xmlPDate);
			modelAndView.addObject("result_Tjson", xmlTDate);
			modelAndView.addObject("customerInfoList", resultList);
			logger.info((Object) (new StringBuilder("End to uploadExcelFile ")));
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
	//导入数据
	public ModelAndView saveData(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to saveData ")));
			String updateJson = request.getParameter("updateJson");
			String pjson = request.getParameter("pjson");
			String tjson = request.getParameter("tjson");
			String customerid = request.getParameter("customerid");

			JSONArray sjsonArray = JSONArray.fromObject(updateJson);
			JSONArray pjsonArray = JSONArray.fromObject(pjson.substring(8, pjson.length() - 1));
			JSONArray tjsonArray = JSONArray.fromObject(tjson.substring(8, tjson.length() - 1));
			for (int i = 0; i < sjsonArray.size(); i++) {
				LSample ls = new LSample();
				ls = PubJsonUtil.jsonToBean(sjsonArray.get(i).toString(), LSample.class);
				String []ylxhStrings = ls.getYlxh().split(",");
				for(int x=0;x<ylxhStrings.length;x++){
					String pseqString = dataAccessApi.getSeqString("SAMPLE_SEQUENCE");
					ls.setId(new BigDecimal(pseqString));
					ls.setYlxh(ylxhStrings[x]);
					ls.setDsfcustomerid(customerid);
					upDownApi.saveData(ls, "LSample");
				}

				for (int j = 0; j < pjsonArray.size(); j++) {
					Process_XML prXml = new Process_XML();
					prXml = PubJsonUtil.jsonToBean(pjsonArray.get(j).toString(), Process_XML.class);
					if (ls.getDsfbarcode().equals(prXml.getDsfbarcode())) {
						DsfProcess lp = new DsfProcess();
						lp.setSampleId(ls.getId().toString());
						String lpseqString = dataAccessApi.getSeqString("PROCESS_SEQUENCE");
						lp.setId(new BigDecimal(lpseqString));
						upDownApi.saveData(lp, "LProcess");
					}
				}

				// 检查基础表中检验目的是否存在，已存在不添加，不存在新增
				String ylxh = ls.getYlxh();
				
				List<DsfLYlxhdescribe> ylxhList = upDownApi.getYlxhdescribeByYlxh(ylxh, customerid);
				if (null != ylxh && ylxhList.size() > 0) {

				} else {
					DsfLYlxhdescribe dsfYlxhdescribe = new DsfLYlxhdescribe();
					dsfYlxhdescribe.setYlmc(ls.getInspectionname());
					dsfYlxhdescribe.setYlxh(ls.getYlxh());
					String dsfylxhseqString = dataAccessApi.getSeqString("DSF_YLXH_SEQUENCE");
					dsfYlxhdescribe.setId(new BigDecimal(dsfylxhseqString));
					
					List dsfctiList = new ArrayList<DsfControltestitems>();

					StringBuffer profiletest = new StringBuffer();
					for (int k = 0; k < tjsonArray.size(); k++) {
						TestItem_XML tXml = new TestItem_XML();
						tXml = PubJsonUtil.jsonToBean(pjsonArray.get(k).toString(), TestItem_XML.class);
						if (ls.getDsfbarcode().equals(tXml.getDsfbarcode())) {
							//获取当前客户的检验项目列表，条件是检验项目编号
							List<DsfTestitems> dsftList = upDownApi.getDsfTestItemsByTestItem(tXml.getTestitem(),
									customerid);
							//如果不存在就添加，存在就不操作
							if (null == dsftList || dsftList.size() < 1) {
								DsfTestitems dTestitems = new DsfTestitems();
								dTestitems.setCustomerid(customerid);
								dTestitems.setName(tXml.getName());
								dTestitems.setIndexId(tXml.getTestitem());
								String dsftseqString = dataAccessApi.getSeqString("DSF_TESTITEMS_SEQUENCE");
								dTestitems.setId(new BigDecimal(dsftseqString));
								upDownApi.saveData(dTestitems, "DsfTestitems");
								
								//把该条记录放入List,插入对照表
								DsfControltestitems  dsfcti = new DsfControltestitems();
								dsfcti.setCustomerid(customerid);
								dsfcti.setCustomeritems(tXml.getTestitem());
								dsfcti.setCustomeritemsname(tXml.getName());
								String dsfctiseqString = dataAccessApi.getSeqString("DSF_CONTROLTESTITEMS_SEQ");
								dsfcti.setId(new BigDecimal(dsfctiseqString));
								dsfctiList.add(dsfcti);
								
							}
							profiletest.append(tXml.getTestitem());
						}
					}
					//保存检验目的
					dsfYlxhdescribe.setProfiletest(profiletest.toString());
					upDownApi.saveData(dsfYlxhdescribe, "DsfLYlxhdescribe");
					//对照表数据添加
					upDownApi.saveDataByList(dsfctiList, "DsfControltestitems");
				}
			}
			
			
			
			
			logger.info((Object) (new StringBuilder("Begin to saveData ")));
			ModelAndView modelAndView = new ModelAndView("/jsp/upLoadFile/viewImpExpData.jsp");
			modelAndView.addObject("state", "success");
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

	public ModelAndView viewDownLoadPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to viewDownLoadPage")));
			List<DsfCustomerBaseInfo> resultList = new ArrayList<DsfCustomerBaseInfo>();
			resultList = upDownApi.getCustomerInfo();

			logger.info((Object) (new StringBuilder("End to viewDownLoadPage")));
			ModelAndView modelAndView = new ModelAndView("/jsp/downLoadFile/viewDownLoadPage.jsp");
			modelAndView.addObject("customerInfoList", resultList);
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
	
	public ModelAndView queryExpData(HttpServletRequest request, HttpServletResponse response) {
		List<LSample> resutlList = new ArrayList<LSample>();
		String resultString = "";
		try {
			logger.info((Object) (new StringBuilder("Begin to queryExpData")));
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			String customerid = request.getParameter("customerid");

			resutlList = upDownApi.getSampleByTime(beginTime, endTime, customerid);
			
			resultString = PubJsonUtil.list2json(resutlList);
			//System.out.println("根据时间查询:"+resultString);
			List<DsfCustomerBaseInfo> resultCList = new ArrayList<DsfCustomerBaseInfo>();
			resultCList = upDownApi.getCustomerInfo();

			logger.info((Object) (new StringBuilder("End to queryExpData")));
			ModelAndView modelAndView = new ModelAndView("/jsp/downLoadFile/viewDownLoadPage.jsp");
			modelAndView.addObject("result_json", resultString);
			modelAndView.addObject("customerInfoList", resultCList);
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
	public void getQueryResult(HttpServletRequest request, HttpServletResponse response) {
		List<LTestresult> resutlList = new ArrayList<LTestresult>();
		String resultString = "";
		try {
			logger.info((Object) (new StringBuilder("Begin to queryExpData")));
			String sampleno = request.getParameter("sampleno");
			//System.out.println(sampleno);
			resutlList = upDownApi.getLTestresultByNo(sampleno);
			
			resultString = PubJsonUtil.list2json(resutlList);
			//System.out.println(resultString);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result_json", resultString);
			response.setContentType("application/json;charset=utf-8");     
			response.getWriter().write(jsonObject.toString()); 
			logger.info((Object) (new StringBuilder("End to queryExpData")));
		} catch (Exception e) {
			logger.error(((Object) (e.getMessage())), ((Throwable) (e)));
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
		}
	}

	// ！！！！！！！！！！！！！哪种状态是已经有结果的？！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	public ModelAndView exportResult(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> initMap = new HashMap<String, String>();
		List<LTestresult> resultList = new ArrayList<LTestresult>();
		List sampleNoresultList = new ArrayList<String>();
		ModelAndView modelAndView = new ModelAndView("/jsp/downLoadFile/downLoadFilePage.jsp");
		try {
			logger.info((Object) (new StringBuilder("Begin to exportResult")));
			String customerid = request.getParameter("customerid");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			String type = request.getParameter("type");

			// 通过本地testid 获取第三方的testid
			initMap = upDownApi.getConversionTestItem("local");
			sampleNoresultList = upDownApi.getExpSampleNoData(beginTime, endTime, customerid);
			resultList = upDownApi.queryExpData(beginTime, endTime, customerid);
			// if ("xml".equals(type)) {
			if (null != sampleNoresultList && sampleNoresultList.size() > 0) {
				DataSet_XML dataSet_XML = new DataSet_XML();
				List<SampleInfoList_XML> sXmls = new ArrayList<SampleInfoList_XML>();

				for (int i = 0; i < sampleNoresultList.size(); i++) {
					List<Testresult_Xml> trList_Xmls = new ArrayList<Testresult_Xml>();
					List<TestItem_XML> testItem_XMLs = new ArrayList<TestItem_XML>();
					SampleInfoList_XML sList_XML = new SampleInfoList_XML();
					// 以下是当前同一个sampleno的信息
					for (LTestresult lTestresult : resultList) {
						if (sampleNoresultList.get(i).equals(lTestresult.getSampleno())) {
							System.out.println(sampleNoresultList.get(i));
							lTestresult.setTestid(initMap.get(lTestresult.getTestid()));
							Testresult_Xml testresult_Xml = new Testresult_Xml();
							BeanUtils.copyProperties(testresult_Xml, lTestresult);
							trList_Xmls.add(testresult_Xml);

							TestItem_XML tXml = new TestItem_XML();
							tXml.setDsfbarcode(lTestresult.getDsfbarcode());
							tXml.setName(lTestresult.getTestname());
							tXml.setTestitem(lTestresult.getTestid());
							testItem_XMLs.add(tXml);
						}
					}
					// 把result list结果放入
					sList_XML.setTestresult_Xmls(trList_Xmls);
					// 把检验项目放入LIST
					sList_XML.setTestItem_XMLs(testItem_XMLs);
					// 下面开始放入原来客户导入的数据
					List<LSample> sList = new ArrayList<LSample>();
					List<DsfTestitems> tList = new ArrayList<DsfTestitems>();
					sList = upDownApi.getSampleNoByLSample(sampleNoresultList);
					for (LSample lSample : sList) {
						if (sampleNoresultList.get(i).equals(lSample.getSampleno())) {
							SampleInfo_XML sXml = new SampleInfo_XML();
							BeanUtils.copyProperties(sXml, lSample);
							sList_XML.setSampleInfo(sXml);

							DsfProcess lProcess = new DsfProcess();
							Process_XML pXml = new Process_XML();
							lProcess = upDownApi.getLProcessByLSampleId(lSample.getId());
							BeanUtils.copyProperties(pXml, lProcess);
							sList_XML.setProcess(pXml);

							// 把3个对象都放入LIST中
							sXmls.add(sList_XML);
						}
					}
					dataSet_XML.setsXmlList(sXmls);
				}

				if ("xml".equals(type)) {
					String xmlresultString = XmlUtil.convertToXml(dataSet_XML, "utf-8");
					XMLOutputter xmlOut = new XMLOutputter();
					xmlOut.output(XmlUtil.string2Doc(xmlresultString), new FileOutputStream("d:/resultXml.xml"));

					modelAndView.addObject("fileUrl", "d:/resultXml.xml");
				}
				if ("excel".equals(type)) {
					WritableWorkbook workbook = jxl.Workbook.createWorkbook(new File("d:/resultExcel.xls"));
					WritableSheet sheet1 = workbook.createSheet("标本信息", 0);
					WritableSheet sheet2 = workbook.createSheet("检验项目", 1);
					WritableSheet sheet3 = workbook.createSheet("检验结果", 2);
					// Label 参数中第一个参数0表示列，第二个参数1表示行，第三个参数是内容。excel都是从0行0列开始的

					sheet1.addCell(new Label(0, 0, "年龄"));
					sheet1.addCell(new Label(1, 0, "年龄单位(岁，日，月，周)"));
					sheet1.addCell(new Label(2, 0, "生日"));
					sheet1.addCell(new Label(3, 0, "生理周期"));
					sheet1.addCell(new Label(4, 0, "病床号"));
					sheet1.addCell(new Label(5, 0, "诊断"));
					sheet1.addCell(new Label(6, 0, "第三方条码号"));
					sheet1.addCell(new Label(7, 0, "申请科室"));
					sheet1.addCell(new Label(8, 0, "检验目的"));
					sheet1.addCell(new Label(9, 0, "病人唯一号"));
					sheet1.addCell(new Label(10, 0, "就诊卡号"));
					sheet1.addCell(new Label(11, 0, "病人名称"));
					sheet1.addCell(new Label(12, 0, "样本类型"));
					sheet1.addCell(new Label(13, 0, "性别"));
					sheet1.addCell(new Label(14, 0, "就诊方式"));
					sheet1.addCell(new Label(15, 0, "检验目的ID"));
					sheet1.addCell(new Label(16, 0, "开单人"));
					sheet1.addCell(new Label(17, 0, "开单时间"));
					sheet1.addCell(new Label(18, 0, "采集人"));
					sheet1.addCell(new Label(19, 0, "采集时间"));

					sheet2.addCell(new Label(0, 0, "检验项目ID"));
					sheet2.addCell(new Label(1, 0, "检验项目名称"));
					sheet2.addCell(new Label(2, 0, "第三方条码号"));

					sheet3.addCell(new Label(0, 0, "样本编号"));
					sheet3.addCell(new Label(1, 0, "检验项目ID"));
					sheet3.addCell(new Label(2, 0, "出结果时间"));
					sheet3.addCell(new Label(3, 0, "审核人"));
					sheet3.addCell(new Label(4, 0, "参考高值"));
					sheet3.addCell(new Label(5, 0, "参考低值"));
					sheet3.addCell(new Label(6, 0, "样本类型"));
					sheet3.addCell(new Label(7, 0, "检验结果"));
					sheet3.addCell(new Label(8, 0, "单位"));
					sheet3.addCell(new Label(9, 0, "检测项目名称"));
					sheet3.addCell(new Label(10, 0, "检测方法"));
					sheet3.addCell(new Label(11, 0, "第三方客户ID"));
					sheet3.addCell(new Label(12, 0, "第三方条码号"));

					for (int j = 0; j < dataSet_XML.getsXmlList().size(); j++) {
						// 标签1
						SampleInfo_XML sInfo_XML = dataSet_XML.getsXmlList().get(j).getSampleInfo();
						Process_XML pXml = dataSet_XML.getsXmlList().get(j).getProcess();

						sheet1.addCell(new Label(0, j + 1, sInfo_XML.getAge()));
						sheet1.addCell(new Label(1, j + 1, sInfo_XML.getAgeunit()));
						sheet1.addCell(new Label(2, j + 1, sInfo_XML.getBirthday()));
						sheet1.addCell(new Label(3, j + 1, sInfo_XML.getCycle()));
						sheet1.addCell(new Label(4, j + 1, sInfo_XML.getDepartBed()));
						sheet1.addCell(new Label(5, j + 1, sInfo_XML.getDiagnostic()));
						sheet1.addCell(new Label(6, j + 1, sInfo_XML.getDsfbarcode()));
						sheet1.addCell(new Label(7, j + 1, sInfo_XML.getHossection()));
						sheet1.addCell(new Label(8, j + 1, sInfo_XML.getInspectionname()));
						sheet1.addCell(new Label(9, j + 1, sInfo_XML.getPatientblh()));
						sheet1.addCell(new Label(10, j + 1, sInfo_XML.getPatientid()));
						sheet1.addCell(new Label(11, j + 1, sInfo_XML.getPatientname()));
						sheet1.addCell(new Label(12, j + 1, sInfo_XML.getSampletype()));
						sheet1.addCell(new Label(13, j + 1, sInfo_XML.getSex()));
						sheet1.addCell(new Label(14, j + 1, sInfo_XML.getStayhospitalmode()));
						sheet1.addCell(new Label(15, j + 1, sInfo_XML.getYlxh()));
						sheet1.addCell(new Label(16, j + 1, pXml.getCollectionpersonnel()));
						sheet1.addCell(new Label(17, j + 1, pXml.getCollectiontime()));
				

						// 标签2

						for (int x = 0; x < dataSet_XML.getsXmlList().get(j).getTestItem_XMLs().size(); x++) {
							TestItem_XML tXml = dataSet_XML.getsXmlList().get(j).getTestItem_XMLs().get(x);
							sheet2.addCell(new Label(17, x + 1, tXml.getTestitem()));
							sheet2.addCell(new Label(18, x + 1, tXml.getName()));
							sheet2.addCell(new Label(19, x + 1, tXml.getDsfbarcode()));
						}

						for (int k = 0; k < dataSet_XML.getsXmlList().get(j).getTestresult_Xmls().size(); k++) {
							Testresult_Xml trXml = dataSet_XML.getsXmlList().get(j).getTestresult_Xmls().get(k);
							sheet3.addCell(new Label(0, k + 1, trXml.getSampleno()));
							sheet3.addCell(new Label(1, k + 1, trXml.getTestid()));
							sheet3.addCell(new Label(2, k + 1, trXml.getMeasuretime()));
							sheet3.addCell(new Label(3, k + 1, trXml.getOperator()));
							sheet3.addCell(new Label(4, k + 1, trXml.getRefhi()));
							sheet3.addCell(new Label(5, k + 1, trXml.getReflo()));
							sheet3.addCell(new Label(6, k + 1, trXml.getSampletype()));
							sheet3.addCell(new Label(7, k + 1, trXml.getTestresult()));
							sheet3.addCell(new Label(8, k + 1, trXml.getUnit()));
							sheet3.addCell(new Label(9, k + 1, trXml.getTestname()));
							sheet3.addCell(new Label(10, k + 1, trXml.getMethod()));
							sheet3.addCell(new Label(11, k + 1, trXml.getCustomerid()));
							sheet3.addCell(new Label(12, k + 1, trXml.getDsfbarcode()));
						}
					}
					workbook.wait();
					workbook.close();
					modelAndView.addObject("fileUrl", "/downLoadFile/resultExcel.xls");
				}
			}
			logger.info((Object) (new StringBuilder("End to exportResult")));
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

	public ModelAndView viewImpBaseData(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to viewImpBaseData ")));
			
			List<DsfCustomerBaseInfo> resultList = new ArrayList<DsfCustomerBaseInfo>();
			resultList = upDownApi.getCustomerInfo();
			
			ModelAndView modelAndView = new ModelAndView("/jsp/upLoadFile/viewImpBaseData.jsp");
			modelAndView.addObject("customerInfoList", resultList);
			logger.info((Object) (new StringBuilder("End to viewImpBaseData ")));
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

	/**
	 * 导入检验基础信息数据
	 * 因为页面要上传XML文件，所以必须把HttpServletRequest转为MultipartHttpServletRequest
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView ImpBaseDataInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info((Object) (new StringBuilder("Begin to ImpBaseDataInfo ")));
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("uploadFile");
			if (file.isEmpty()) {
				throw new Exception("文件不存在！");
			}
			String customerid_PageSelect = request.getParameter("customerid");
			
			String xmlString = null;
			// 创建SAXReader对象
			SAXReader reader = new SAXReader();
			// 读取文件 转换成Document
			// Document document = reader.read(new
			// File("C:/Users/kewell/Desktop/a.xml"));
			String fileName = file.getName();
			// 根据上传的文件路径解析文件
			InputStream in = null;
			in = file.getInputStream();
			Document document = reader.read(in);
			// document转换为String字符串
			xmlString = document.asXML();
			System.out.println("document 字符串：" + xmlString);
			TestObjectives_XML tObjectives_XML = XmlUtil.converyToJavaBean(xmlString, TestObjectives_XML.class);
			System.out.println("xml转为javas对象:"+tObjectives_XML);

			List dsftODateList = new ArrayList();
			List dsftiDateList = new ArrayList();
			// 返回数据的集合
			List resultTOList = new ArrayList();
			List resultTIlist = new ArrayList();
			// 当前导入的客户ID定义
			String customerid = tObjectives_XML.getCustomerid();
			if(customerid_PageSelect.equals(customerid)){
				// 获取所有当前用户的已存在数据
				List<DsfLYlxhdescribe> ylxhList = upDownApi.getYlxhdescribeByYlxh("", customerid);
				List<DsfTestitems> dsftList = upDownApi.getDsfTestItemsByTestItem("", customerid);
				// 查询数据库中的历史数据放入SET以防止新上来的数据会和原来的有重复的
				Set ylxhSet = new HashSet();
				Set tiSet = new HashSet();

				// 用来放入本次导入的数据，来验证导入的数据中是否有重复的检验项目
				Set newTiSet = new HashSet();
				// 用来放入当前
				Map<String, Base_TestItem_XML> xmlTiMao = new HashMap<String, Base_TestItem_XML>();

				for (DsfLYlxhdescribe dy : ylxhList) {
					ylxhSet.add(dy.getYlxh());
				}
				for (DsfTestitems dy : dsftList) {
					tiSet.add(dy.getIndexId());
				}
				List testList = new ArrayList();
				for (TestObjective_XML testObjective_XML : tObjectives_XML.getTestObjectList()) {
					// 返回页面数据需要的JSON集合
					resultTOList.add(testObjective_XML);
					DsfLYlxhdescribe dYlxhdescribe = new DsfLYlxhdescribe();
					if (!ylxhSet.contains(testObjective_XML.getYlxh())) {
						String dsfylxhseqString = dataAccessApi.getSeqString("DSF_YLXH_SEQUENCE");
						BeanUtils.copyProperties(dYlxhdescribe, testObjective_XML);
						dYlxhdescribe.setCustomerid(customerid);
						dYlxhdescribe.setId(new BigDecimal(dsfylxhseqString));
						dsftODateList.add(dYlxhdescribe);
					}
				}

				List dsfctiList = new ArrayList();
				for (Base_TestItem_XML tItemList_XML : tObjectives_XML.getBase_testitemList()) {
					if (null != tItemList_XML) {
						if (!tiSet.contains(tItemList_XML.getTestitem())) {
							DsfTestitems dsfTestitems = new DsfTestitems();
							BeanUtils.copyProperties(dsfTestitems, tItemList_XML);
							String dsftseqString = dataAccessApi.getSeqString("DSF_TESTITEMS_SEQUENCE");
							dsfTestitems.setId(new BigDecimal(dsftseqString));
							dsfTestitems.setIndexId(tItemList_XML.getTestitem());
							dsfTestitems.setCustomerid(customerid);
							if (!newTiSet.contains(dsfTestitems.getIndexId())) {
								dsftiDateList.add(dsfTestitems);
							}
							newTiSet.add(dsfTestitems.getIndexId());
							
							//把该条记录放入List,插入对照表
							DsfControltestitems  dsfcti = new DsfControltestitems();
							dsfcti.setCustomerid(customerid);
							dsfcti.setCustomeritems(tItemList_XML.getTestitem());
							dsfcti.setCustomeritemsname(tItemList_XML.getName());
							String dsfctiseqString = dataAccessApi.getSeqString("DSF_CONTROLTESTITEMS_SEQ");
							dsfcti.setId(new BigDecimal(dsfctiseqString));
							dsfctiList.add(dsfcti);
						}
						// 返回页面的JSON List数据
						xmlTiMao.put(tItemList_XML.getTestitem(), tItemList_XML);
					}
				}
				//对照表数据添加
				System.out.println("对照表信息："+dsfctiList);
				upDownApi.saveDataByList(dsfctiList, "DsfControltestitems");
				
				// 返回页面数据需要的JSON集合
				for (Map.Entry entry : xmlTiMao.entrySet()) {
					resultTIlist.add(entry.getValue());
				}
				upDownApi.saveDataByList(dsftiDateList, "DsfTestitems");
				upDownApi.saveDataByList(dsftODateList, "DsfLYlxhdescribe");
				request.setAttribute("success", "导入基础数据成功！");
			}else {
				request.setAttribute("error", "数据文件里的客户编号和选择的客户编号不一致！");
			}

			

			ModelAndView modelAndView = new ModelAndView("/jsp/upLoadFile/viewImpBaseData.jsp");
			String result_TOList = PubJsonUtil.list2json(resultTOList);
			String result_TIlist = PubJsonUtil.list2json(resultTIlist);
			System.out.println("检验项目返回值："+result_TIlist);
			modelAndView.addObject("result_TOjson", result_TOList);
			modelAndView.addObject("result_TIjson", result_TIlist);
			modelAndView.addObject("customerid", customerid);
			logger.info((Object) (new StringBuilder("End to ImpBaseDataInfo ")));
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
}
