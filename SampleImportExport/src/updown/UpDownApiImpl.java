package updown;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import common.datamodel.*;
import dataaccess.DataAccessApi;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import security.SecurityHandler;


public class UpDownApiImpl implements UpDownApi {

	private DataAccessApi dataAccessApi = null;
	private boolean inited = false;
	private SecurityHandler handler = null;

	public void init() {
		inited = true;
	}

	public boolean inited() {
		return inited;
	}

	public void shutdown() {
	}

	public void setHandler(SecurityHandler handler) {
		this.handler = handler;
	}

	public DataAccessApi getDataAccessApi() {
		return dataAccessApi;
	}

	public void setDataAccessApi(DataAccessApi dataAccessApi) {
		this.dataAccessApi = dataAccessApi;
	}
	  
	public List<DsfCustomerBaseInfo> getCustomerInfo() {
		return dataAccessApi.getCustomerInfo();
	}
	
	public void saveData(Object t,String tableName){
		dataAccessApi.saveData(t,tableName);
	}
	public void saveDataByList(List <Object>objectList,String tableName){
		dataAccessApi.saveDataByList(objectList,tableName);
	}
	
	public List<LTestresult> queryExpData(String beginTime,String endTime,String customerid){
		return dataAccessApi.queryExpData(beginTime,endTime,customerid);
	}
	public List getExpSampleNoData(String beginTime,String endTime,String customerid){
		return dataAccessApi.getExpSampleNoData(beginTime,endTime,customerid); 
	}
	public List <DsfLYlxhdescribe> getYlxhdescribeByYlxh(String ylxh,String customerid){
		return dataAccessApi.getYlxhdescribeByYlxh(ylxh,customerid);
	}
	
	public List<DsfTestitems> getDsfTestItemsByTestItem(String dTestitems,String customerid){
		return dataAccessApi.getDsfTestItemsByTestItem(dTestitems,customerid);
	}

	public List<LTestitem> getLTestItemsByTestItem(String testitems,String customerid){
		return dataAccessApi.getLTestItemsByTestItem(testitems,customerid);
	}
	public Map<String, String> getConversionTestItem(String key){
		return dataAccessApi.getConversionTestItem(key);
	}
	public List<LSample> getSampleNoByLSample(List samplenoList){
		return dataAccessApi.getSampleNoByLSample(samplenoList);
	}
	public DsfProcess getLProcessByLSampleId(BigDecimal sampleno){
		return dataAccessApi.getLProcessByLSampleId(sampleno);
	}
	public DsfTestitems getDsfTestitemsById(BigDecimal sampleno){
		return dataAccessApi.getDsfTestitemsById(sampleno);
	}
	
	public LSample getSampleByBarCode(String barcode){
		return dataAccessApi.getSampleByBarCode( barcode);
	}
	
	public List getCustomerInfo(String cid){
		return dataAccessApi.getCustomerInfoList(cid);
	}
	public DsfCustomerBaseInfo getCustomerInfoById(String customerid){
		return dataAccessApi.getCustomerInfoById(customerid);
	}

	

}