package dataaccess;



import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import common.StartAPI;
import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBarCode;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.DsfProcess;
import common.datamodel.LSample;
import common.datamodel.LTestitem;
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
	public abstract DsfProcess getLProcessByLSampleId(BigDecimal sampleno);
	public abstract DsfTestitems getDsfTestitemsById(BigDecimal sampleno);
	public abstract List <DsfLYlxhdescribe> getYlxhdescribeByYlxh(String ylxh,String customerid);
	public abstract List<DsfTestitems> getDsfTestItemsByTestItem(String dTestitems,String customerid);
	public abstract List<LTestitem> getLTestItemsByTestItem(String testitems,String customerid);
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
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoList(String  clientnumber);
	
	public abstract DsfCustomerBaseInfo getCustomerInfoById(String id);
	
	public abstract void deleteCustomerInfo(String customerid);
	public abstract List<DsfCustomerBaseInfo> getBaseCustomerInfo();
	/**
	 * 查询客户当前条码打印到那一个数值
	 * @return
	 */
	public abstract List<DsfCustomerBarCode> getNowCode(String customerid);
	/**
	 * 保存条码信息
	 * @param dsfCustomerBarCode
	 */
	public abstract void saveBarCode(DsfCustomerBarCode dsfCustomerBarCode);
	
	
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByCnameState(String customername,String state);
	/**
	 * 检验信息
	 */
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByNo(String clientnumber,String customerid);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribe(String customerid);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribeByNo(String ylxh,String ylmc);
	public abstract void updateYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe);
	public abstract void addYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe);
	
	/**
	 * 检验项目对照
	 */
	public abstract List<DsfControltestitems> getControltestitemsByNo(String customeritems,String customeritemsname);
}