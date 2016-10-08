package updown;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import common.StartAPI;			
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.LProcess;
import common.datamodel.LSample;
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
	public abstract LProcess getLProcessByLSampleId(BigDecimal sampleno);
	public abstract DsfTestitems getDsfTestitemsByLSampleId(BigDecimal sampleno);
	public abstract List getExpSampleNoData(String beginTime,String endTime,String customerid);
	public abstract List <DsfLYlxhdescribe> getYlxhdescribeByYlxh(String ylxh,String customerid);
	public abstract List<DsfTestitems> getTestItemsByTestItem(String dTestitems,String customerid);
}