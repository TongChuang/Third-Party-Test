package updown;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import common.StartAPI;			
import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.DsfProcess;
import common.datamodel.LSample;
import common.datamodel.LTestitem;
import common.datamodel.LTestobjective;
import common.datamodel.LTestresult;


public interface UpDownApi
	extends StartAPI {
	public abstract List<DsfCustomerBaseInfo> getCustomerInfo();
	public abstract void saveData(Object t,String tableName);
	public abstract void saveDataByList(List <Object>objectList,String tableName);
	public abstract List<LTestresult> queryExpData(String beginTime,String endTime,String customerid);
	/**
	 * 如果KEY等于local就是本地转为客户的，否则就是客户的转为本地的
	 * @param key
	 * @return
	 */
	public abstract Map<String, String> getConversionTestItem(String key);
	
	public abstract List<LSample> getSampleNoByLSample(List samplenoList);
	public abstract DsfProcess getLProcessByLSampleId(BigDecimal sampleno);
	public abstract DsfTestitems getDsfTestitemsById(BigDecimal id);
	public abstract List getExpSampleNoData(String beginTime,String endTime,String customerid);
	public abstract List <DsfLYlxhdescribe> getYlxhdescribeByYlxh(String ylxh,String customerid);
	public abstract List<DsfTestitems> getDsfTestItemsByTestItem(String testitems,String customerid);
	public abstract List<LTestitem> getLTestItemsByTestItem(String dTestitems,String customerid);
	public abstract LSample getSampleByBarCode(String barcode);
	public abstract List<LSample> getSamplesByBarCode(String barcode);
	public abstract List getCustomerInfo(String clientid);
	public abstract DsfCustomerBaseInfo getCustomerInfoById(String customerid);
	
	public abstract List<DsfLYlxhdescribe> getDsfTestObjectiveById(String customerid);
	public abstract List<LTestresult> getLTestresultByNo(String sampleno);
	
	//前处理
	//批量更新流水号
	public abstract void saveAllSerialNumber(List<DsfLYlxhdescribe> dydList);
	//获取流水数值
	public abstract int getSeralNumber(String dateAndSection);
	//获取检验段
	public abstract List<String> getInspectionSectionByYLXH(String ylxh,String customerid);
	
}