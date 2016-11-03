package webService.server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.beanutils.BeanUtils;

import webService.server.help.ConversionBean;

import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfTestobjective;
import common.datamodel.DsfTestitems;
import common.datamodel.DsfProcess;
import common.datamodel.DsfSampleInfo;
import common.datamodel.DsfTestResult;
import common.util.DateUtil;
import common.util.XmlUtil;
import common.webmodel.Base_TestItem_XML;
import common.webmodel.DataSet_XML;
import common.webmodel.Process_XML;
import common.webmodel.SampleInfoList_XML;
import common.webmodel.SampleInfo_XML;
import common.webmodel.TestItem_XML;
import common.webmodel.TestObjective_XML;
import common.webmodel.TestObjectives_XML;

import dataaccess.DataAccessApi;

/**
 * @author zjn
 * @createTime 2016-9-13
 */
@WebService()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SIE_ServiceApiImpl implements SIE_ServiceApi {
	public StartService service = null;
	public DataAccessApi dataAccessApi = null;

	public void setDataAccessApi(DataAccessApi dataAccessApi) {
		this.dataAccessApi = dataAccessApi;
	}

	public DataAccessApi getDataAccessApi() {
		return dataAccessApi;
	}

	/*获取检验结果*/
	@WebMethod
	public String getTestResults(
			@WebParam(name = "customerid") String customerid,
			@WebParam(name = "customerKey") String customerKey,
			@WebParam(name = "testItems") String testItems,
			@WebParam(name = "dsfbarcode") String dsfbarcode) {
		List<DsfTestResult> trList = new ArrayList<DsfTestResult>();
		ReturnErrorXml rXml = new ReturnErrorXml();
		Map<String, String> initMap = new HashMap<String, String>();
		try {
			if (null == customerid || "".equals(customerid)
					|| null == customerKey || "".equals(customerKey)) {
				rXml.setCode("04");
				rXml.setMsg("参数不合法");
				rXml.setContent("输入的参数不能为空值");
				String resultXml = XmlUtil.convertToXml(rXml, "utf-8");
				return resultXml;
			}

			initMap = dataAccessApi.getConversionTestItem("local");
			boolean flag = dataAccessApi.checkDsfUserInfo(customerid,
					customerKey);
			if (flag) {
				DsfTestResult ltr = new DsfTestResult();
				ltr.setCustomerid(customerid);
				ltr.setTestid(testItems);
				ltr.setDsfbarcode(dsfbarcode);
				trList = dataAccessApi.getTestResultsByWebService(ltr);
				if (null == trList) {
					rXml.setCode("03");
					rXml.setMsg("没有查询到结果");
					rXml.setContent("当前条件查无结果，请更换查询条件再次查询");
				} else {
					for (DsfTestResult lTestresult : trList) {
						lTestresult.setTestid(initMap.get(lTestresult
								.getTestid()));
					}
					String reusltXml = XmlUtil.convertToXml(trList, "utf-8");
					return reusltXml;
				}
			} else {
				rXml.setCode("02");
				rXml.setMsg("验证客户合法性失败");
				rXml.setContent("输入的验证信息失败,查无次客户");
			}

			String errorXml = XmlUtil.convertToXml(rXml, "utf-8");
			return errorXml;
		} catch (Exception e) {
			rXml.setCode("04");
			rXml.setMsg("参数不合法");
			rXml.setContent("违法参数导致错误，输入的参数有误，请核对后再使用本接口");
			String errorXml = XmlUtil.convertToXml(rXml, "utf-8");
			return errorXml;
		}
	}

	/**
	 * 上传样本信息
	 */
	@WebMethod
	public String uploadTestItem(@WebParam(name = "xmlData") String xmlData,
			@WebParam(name = "customerid") String customerid,
			@WebParam(name = "customerKey") String customerKey) {
		ReturnErrorXml rXml = new ReturnErrorXml();
		try {
			if (null == customerid || "".equals(customerid)
					|| null == customerKey || "".equals(customerKey)
					|| null == xmlData || "".equals(xmlData)) {
				rXml.setCode("04");
				rXml.setMsg("参数不合法");
				rXml.setContent("输入的参数不能为空值");
				String resultXml = XmlUtil.convertToXml(rXml, "utf-8");
				return resultXml;
			}
			// 判断上传客户是否合法
			boolean flag = dataAccessApi.checkDsfUserInfo(customerid,customerKey);
			if (flag) {
				// 获取客户的信息，设置样本编号
				List<DsfCustomerBaseInfo> dsfcList = dataAccessApi.getCustomerInfo();
				DsfCustomerBaseInfo dsfcbiBaseInfo = new DsfCustomerBaseInfo();
				for (DsfCustomerBaseInfo dsfc : dsfcList) {
					if (customerid.equals(String.valueOf(dsfc.getCustomerid()))) {
						dsfcbiBaseInfo = dsfc;
					}
				}

				// 把上传的XML解析为BEAN
				DataSet_XML dXml = new DataSet_XML();
				dXml = XmlUtil.converyToJavaBean(xmlData, DataSet_XML.class);
				for (SampleInfoList_XML slXml : dXml.getsXmlList()) {
					SampleInfo_XML sampleInfo_XML = new SampleInfo_XML();
					DsfSampleInfo ls = new DsfSampleInfo();
					sampleInfo_XML = slXml.getSampleInfo();
					String pseqString = dataAccessApi
							.getSeqString("SAMPLE_SEQUENCE");
					ls.setId(new BigDecimal(pseqString));
					ls.setDsfcustomerid(customerid);
					ConversionBean.cSample(ls, sampleInfo_XML);
					dataAccessApi.saveData(ls, "LSample");
					// 保存Process 表
					Process_XML prXml = new Process_XML();
					prXml = slXml.getProcess();
					DsfProcess lp = new DsfProcess();
					lp.setCollectionpersonnel(prXml.getCollectionpersonnel());
					lp.setCollectiontime(DateUtil.parseLongDate(prXml.getCollectiontime()));
					lp.setSampleId(ls.getId().toString());
					String lpseqString = dataAccessApi
							.getSeqString("PROCESS_SEQUENCE");
					lp.setId(new BigDecimal(lpseqString));
					dataAccessApi.saveData(lp, "LProcess");

					// 保存TestItem表
					StringBuffer profiletest = new StringBuffer();
					for (TestItem_XML tXml : slXml.getTestItem_XMLs()) {
						List<DsfTestitems> dsftList = dataAccessApi
								.getDsfTestItemsByTestItem(tXml.getTestitem(),
										customerid);
						if (null == dsftList || dsftList.size() < 1) {
							DsfTestitems dTestitems = new DsfTestitems();
							dTestitems.setCustomerid(customerid);
							dTestitems.setName(tXml.getName());
							dTestitems.setIndexId(tXml.getTestitem());
							String dsftseqString = dataAccessApi
									.getSeqString("DSF_TESTITEMS_SEQUENCE");
							dTestitems.setId(new BigDecimal(dsftseqString));
							dataAccessApi.saveData(dTestitems, "DsfTestitems");
						}
						profiletest.append(tXml.getTestitem());
					}
					// 检验目的判断
					List<DsfTestobjective> ylxhList = dataAccessApi
							.getYlxhdescribeByYlxh(ls.getYlxh(), customerid);
					if (null == ylxhList || ylxhList.size() < 1) {
						DsfTestobjective dsfYlxhdescribe = new DsfTestobjective();
						dsfYlxhdescribe.setYlmc(ls.getInspectionname());
						dsfYlxhdescribe.setYlxh(ls.getYlxh());
						String dsfylxhseqString = dataAccessApi
								.getSeqString("DSF_YLXH_SEQUENCE");
						dsfYlxhdescribe.setId(new BigDecimal(dsfylxhseqString));
						dsfYlxhdescribe.setProfiletest(profiletest.toString());
						dataAccessApi.saveData(dsfYlxhdescribe,
								"DsfLYlxhdescribe");
					}
				}
				rXml.setCode("01");
				rXml.setMsg("上传数据成功");
				rXml.setContent("提交的数据已经全部上传成功");
			} else {
				rXml.setCode("02");
				rXml.setMsg("验证客户合法性失败");
				rXml.setContent("输入的验证信息失败,查无次客户");
			}
			String resultXml = XmlUtil.convertToXml(rXml, "utf-8");
			return resultXml;
		} catch (Exception e) {
			rXml.setCode("04");
			rXml.setMsg("参数不合法");
			rXml.setContent("输入的参数有误，请核对后再使用本接口");
			String resultXml = XmlUtil.convertToXml(rXml, "utf-8");
			return resultXml;
		}
	}

	/**
	 * 上传基础信息数据，该数据包含客户ID，检验目的，检验项目。
	 */
	@WebMethod
	public String uploadBaseDataInfo(
			@WebParam(name = "xmlData") String xmlData,
			@WebParam(name = "customerid") String customerid,
			@WebParam(name = "customerKey") String customerKey) {
		// 返回的错误XML对象
		ReturnErrorXml rXml = new ReturnErrorXml();
		try {
			// 简单判断传入的参数是不是有空的
			if (null == customerid || "".equals(customerid)
					|| null == customerKey || "".equals(customerKey)
					|| null == xmlData || "".equals(xmlData)) {
				rXml.setCode("04");
				rXml.setMsg("参数不合法");
				rXml.setContent("输入的参数不能为空值");
				String resultXml = XmlUtil.convertToXml(rXml, "utf-8");
				return resultXml;
			}
			//验证该上传用户是否合法
			boolean flag = dataAccessApi.checkDsfUserInfo(customerid,customerKey);
			// 判断客户是否合法
			if (flag) {
				// 获取所有当前用户的已存在数据
				List<DsfTestobjective> ylxhList = dataAccessApi.getYlxhdescribeByYlxh("", customerid);
				List<DsfTestitems> dsftList = dataAccessApi.getDsfTestItemsByTestItem("", customerid);
				// 查询数据库中的历史数据放入SET以防止新上来的数据会和原来的有重复的
				Set ylxhSet = new HashSet();
				Set tiSet = new HashSet();
				for (DsfTestobjective dy : ylxhList) {
					ylxhSet.add(dy.getYlxh());
				}
				for (DsfTestitems dy : dsftList) {
					tiSet.add(dy.getIndexId());
				}

				// 把上传的XML解析为BEAN
				TestObjectives_XML testObjectives_XML = new TestObjectives_XML();
				testObjectives_XML = XmlUtil.converyToJavaBean(xmlData,
						TestObjectives_XML.class);

				// 用来放入本次导入的数据，来验证导入的数据中是否有重复的检验项目
				Set newTOSet = new HashSet();
				// 检验目的集合dsftODateList
				List dsftODateList = new ArrayList();
				for (TestObjective_XML testObjective_XML : testObjectives_XML
						.getTestObjectList()) {
					if (!ylxhSet.contains(testObjective_XML.getYlxh())) {
						DsfTestobjective dYlxhdescribe = new DsfTestobjective();
						String dsfylxhseqString = dataAccessApi
								.getSeqString("DSF_YLXH_SEQUENCE");
						BeanUtils.copyProperties(dYlxhdescribe,
								testObjective_XML);
						dYlxhdescribe.setId(new BigDecimal(dsfylxhseqString));
						if (!newTOSet.contains(dYlxhdescribe.getYlxh())) {
							dsftODateList.add(dYlxhdescribe);
						}
						newTOSet.add(dYlxhdescribe.getYlxh());
					}
				}
				// 用来放入本次导入的数据，来验证导入的数据中是否有重复的检验项目
				Set newTiSet = new HashSet();
				// 检验项目集合dsftiDateList
				List dsftiDateList = new ArrayList();
				for (Base_TestItem_XML base_TestItem_XML : testObjectives_XML
						.getBase_testitemList()) {
					if (!tiSet.contains(base_TestItem_XML.getTestitem())) {
						DsfTestitems dsfTestitems = new DsfTestitems();
						BeanUtils.copyProperties(dsfTestitems,
								base_TestItem_XML);
						String dsftseqString = dataAccessApi
								.getSeqString("DSF_TESTITEMS_SEQUENCE");
						dsfTestitems.setId(new BigDecimal(dsftseqString));
						dsfTestitems.setIndexId(base_TestItem_XML.getTestitem());
						if (!newTiSet.contains(dsfTestitems.getIndexId())) {
							dsftiDateList.add(dsfTestitems);
						}
						newTOSet.add(dsfTestitems.getIndexId());
					}
				}
				try {
					dataAccessApi.saveDataByList(dsftODateList,"DsfLYlxhdescribe");
					dataAccessApi.saveDataByList(dsftiDateList, "DsfTestitems");
				} catch (Exception e) {
					rXml.setCode("05");
					rXml.setMsg("上传数据失败");
					rXml.setContent("提交的数据保存时出现错误,请检查XML数据是否正确");
				}
				rXml.setCode("01");
				rXml.setMsg("上传数据成功");
				rXml.setContent("提交的数据已经全部上传成功");
			} else {
				rXml.setCode("02");
				rXml.setMsg("验证客户合法性失败");
				rXml.setContent("输入的验证信息失败,查无次客户");
			}
			String resultXml = XmlUtil.convertToXml(rXml, "utf-8");
			return resultXml;
		} catch (Exception e) {
			rXml.setCode("04");
			rXml.setMsg("参数不合法");
			rXml.setContent("输入的参数有误，请核对后再使用本接口");
			String resultXml = XmlUtil.convertToXml(rXml, "utf-8");
			return resultXml;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see common.StartAPI#init()
	 */
	@Override
	public void init() {
		service.main(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see common.StartAPI#inited()
	 */
	@Override
	public boolean inited() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see common.StartAPI#shutdown()
	 */
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
