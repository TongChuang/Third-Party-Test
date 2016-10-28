package dataaccess;



import java.math.BigDecimal;	
import java.util.List;
import java.util.Map;

import common.StartAPI;
import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfProcess;
import common.datamodel.DsfTestitems;
import common.datamodel.LSample;
import common.datamodel.LTestitem;
import common.datamodel.LTestobjective;
import common.datamodel.LTestresult;
import common.datamodel.LabUser;

public interface DataAccessApi
	extends StartAPI {
	public abstract LabUser getUserByUserName(String userName);
	public void saveCustomerBaseInfo(DsfCustomerBaseInfo dsfc);
	
	public abstract String getSeqString(String seqName);
	
	//updown
	public abstract List<DsfCustomerBaseInfo> getCustomerInfo();
	public abstract void saveData(Object t,String tableName);
	public abstract void saveDataByList(List <Object>objectList,String tableName);
	public List<LTestresult> queryExpData(String beginTime,String endTime,String customerid);
	public abstract List getExpSampleNoData(String beginTime,String endTime,String customerid);
	public abstract List<LSample> getSampleNoByLSample(List samplenoList);
	public abstract LSample getSampleByBarCode(String barcode);
	public abstract List<LSample> getSamplesByBarCode(String barcode);
	public abstract DsfProcess getLProcessByLSampleId(BigDecimal sampleno);
	public abstract DsfTestitems getDsfTestitemsById(BigDecimal sampleno);
	public abstract List <DsfLYlxhdescribe> getYlxhdescribeByYlxh(String ylxh,String customerid);
	public abstract List<DsfTestitems> getDsfTestItemsByTestItem(String dTestitems,String customerid);
	public abstract List<LTestitem> getLTestItemsByTestItem(String testitems,String customerid);
	public abstract List<LTestresult> getLTestresultByNo(String sampleno);
	public abstract List<LSample> getSampleByTime(String beginTime,String endTime,String customerid);
	/**
	 * 如果KEY等于local就是本地转为客户的，否则就是客户的转为本地的
	 * @param key
	 * @return
	 */
	public abstract Map<String, String> getConversionTestItem(String key);
	
	
	//webservice
	/**
	 * 判断第三方客户连接接口的合法性
	 * @param customerid
	 * @param customerKey
	 * @return
	 */
	public abstract boolean checkDsfUserInfo(String customerid,String customerKey);
	/**
	 * 通过上传的参数对检验结果进行查询
	 * @param ltr
	 * @return
	 */
	public abstract List<LTestresult> getTestResultsByWebService(LTestresult ltr);
	
	/**
	 * 查询客户信息
	 * @return
	 */
	public abstract DsfCustomerBaseInfo getCustomerInfoById(String id);
	public abstract void deleteCustomerInfo(String id);
	public abstract List<DsfCustomerBaseInfo> getCustomerBaseInfoByCustomerId(String customerid);
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByName(String customername);
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByCnameState(String customername,String state);
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByNo(String customerid,String customername);

	/**
	 * 检验信息
	 */
	public abstract List<DsfLYlxhdescribe> getYlxhdescribe(String customerid);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribeById(String id);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribeByNo(String ylxh,String ylmc);
	public abstract void updateYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe);
	public abstract void addYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe);
	public abstract void deleteYlxhdescribe(BigDecimal id);
	public abstract List<DsfTestitems> getTestItemsByNo(String proList,String customerid);
	public abstract List<DsfTestitems> getTestItems();
	public abstract void saveTestObjective(String ylxh, String profiletest);
	public abstract String getSequence(String seqName);
	/**
	 * 检验项目对照
	 */
	public abstract List<DsfControltestitems> getControltestitemsByNo(String customeritems,String customeritemsname);
	public abstract List<DsfControltestitems> getControltestitems(String customerid);
	public abstract List<DsfControltestitems> getControltestitemsById(BigDecimal id);
	public abstract List<LTestitem> getLocalTestItems();
	public abstract List<LTestitem> getLocalTestItemsByNo(String customerid);
	public abstract void saveAll(List<DsfControltestitems> dcttList);
	public abstract List<LTestitem> getTestItemsByIndexId(String indexId);
	/**
	 * 前处理
	 */
	public abstract List<DsfLYlxhdescribe> getDsfTestObjectiveById(String customerid);
	public abstract int getSeralNumber(String dateAndSection);
	public abstract void saveAllSerialNumber(List<DsfLYlxhdescribe> dydList);
	public abstract List<String> getInspectionSectionByYLXH(String ylxh,String customerid);

	
	public abstract List<LabUser> getUserInfo(String username);

}