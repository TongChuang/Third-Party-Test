package dataaccess;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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

import dataaccess.dao.InspectionDao;
import dataaccess.dao.QueryStatsDao;
import dataaccess.dao.SecurityDao;
import dataaccess.dao.SysConfDao;
import dataaccess.dao.UpDownDao;

// Referenced classes of package dataaccess:
//			DataAccessApi

public class DataAccessApiImpl extends HibernateDaoSupport implements
		DataAccessApi {

	private SecurityDao securityDao = null;
	private SysConfDao sysconfDao = null;
	private UpDownDao updownDao = null;
	private QueryStatsDao queryStatsDao = null;
	private InspectionDao inspectionDao = null;

	public DataAccessApiImpl() {
		securityDao = null;
		sysconfDao = null;
		updownDao = null;
		queryStatsDao = null;
		inspectionDao = null;
	}

	public void setQueryStatsDao(QueryStatsDao queryStatsDao) {
		this.queryStatsDao = queryStatsDao;
	}

	public SysConfDao getSysconfDao() {
		return sysconfDao;
	}

	public void setSysconfDao(SysConfDao sysconfDao) {
		this.sysconfDao = sysconfDao;
	}

	public SecurityDao getSecurityDao() {
		return securityDao;
	}

	public void setSecurityDao(SecurityDao securityDao) {
		this.securityDao = securityDao;
	}

	public void setUpdownDao(UpDownDao updownDao) {
		this.updownDao = updownDao;
	}
	
	public void setInspectionDao(InspectionDao inspectionDao) {
		this.inspectionDao = inspectionDao;
	}

	public void init() {
	}

	public boolean inited() {
		return true;
	}

	public void shutdown() {
	}
	
	public String getSeqString(String seqName){
		return securityDao.getseqString(seqName);
	}
	
	
	
	public LabUser getUserByUserName(String userName){
		return securityDao.getUserByUserName(userName);
	}
	public void saveCustomerBaseInfo(DsfCustomerBaseInfo dsfc){
		securityDao.saveCustomerBaseInfo(dsfc);
	}
	public List<DsfCustomerBaseInfo> getCustomerInfo(){
		return	updownDao.getCustomerInfo();
	}
	
	public void saveData(Object t,String tableName){
		updownDao.saveData(t,tableName);
	}
	public void saveDataByList(List <Object>objectList,String tableName){
		updownDao.saveDataByList(objectList,tableName);
	}
	
	public List<LTestresult> queryExpData(String beginTime,String endTime,String customerid){
		return updownDao.queryExpData(beginTime,endTime,customerid);
	}
	

	public List getExpSampleNoData(String beginTime,String endTime,String customerid){
		return updownDao.getExpSampleNoData(beginTime,endTime,customerid);
	}
	public List <DsfLYlxhdescribe> getYlxhdescribeByYlxh(String ylxh,String customerid){
		return updownDao.getYlxhdescribeByYlxh(ylxh,customerid);
	}
	
	public List<DsfTestitems> getDsfTestItemsByTestItem(String dTestitems,String customerid){
		return updownDao.getDsfTestItemsByTestItem(dTestitems,customerid);
	}
	public List<LTestitem> getLTestItemsByTestItem(String testitems,String customerid){
		return updownDao.getLTestItemsByTestItem(testitems,customerid);
	}
	
	public Map<String, String> getConversionTestItem(String key){
		if("local".equals(key)){
			return updownDao.getLocal2CustomerConversionTestItem();
		}else{
			return updownDao.getCustomer2LocalConversionTestItem();
		}
	}
	
	public List<LSample> getSampleNoByLSample(List samplenoList){
		return updownDao.getSampleNoByLSample(samplenoList);
	}
	public DsfProcess getLProcessByLSampleId(BigDecimal sampleno){
		return updownDao.getLProcessByLSampleId(sampleno);
	}
	public DsfTestitems getDsfTestitemsById(BigDecimal sampleno){
		return updownDao.getDsfTestitemsById(sampleno);
	}
	public LSample getSampleByBarCode(String barcode){
		return updownDao.getSampleByBarCode(barcode);
	}
	
	
	//webservice
	public boolean checkDsfUserInfo(String customerid,String customerKey){
		return updownDao.checkDsfUserInfo(customerid,customerKey);
	}
	
	public List<LTestresult> getTestResultsByWebService(LTestresult ltr){
		return updownDao.getTestResultsByWebService(ltr);
	}
	
	
	
	
	public List<DsfCustomerBaseInfo> getCustomerInfoList(String clientnumber){
		return sysconfDao.getCustomerInfoList(clientnumber);
	}
	public DsfCustomerBaseInfo getCustomerInfoById(String customerid){
		return sysconfDao.getCustomerInfoById(customerid);
	}
	public void deleteCustomerInfo(String customerid){
		sysconfDao.deleteCustomerInfo(customerid);
	}
	
	public List<DsfCustomerBaseInfo> getBaseCustomerInfo(){
		return queryStatsDao.getBaseCustomerInfo();
	}
	
	public List<DsfCustomerBarCode> getNowCode(String customerid){
		return queryStatsDao.getNowCode(customerid);
	}
	
	public void saveBarCode(DsfCustomerBarCode dsfCustomerBarCode){
		queryStatsDao.saveBarCode(dsfCustomerBarCode);
	}
	
	
	
	public List<DsfCustomerBaseInfo> getCustomerInfoByCnameState(String customername,String state) {
		return sysconfDao.getCustomerInfoByCnameState(customername,state);
	}
	//检验信息
	public List<DsfCustomerBaseInfo> getCustomerInfoByNo(String clientnumber,String customerid){
		return sysconfDao.getCustomerInfoByNo(clientnumber,customerid);
	}
	public List<DsfLYlxhdescribe> getYlxhdescribe(String customerid){
		return sysconfDao.getYlxhdescribe(customerid);
	}
	public List<DsfLYlxhdescribe> getYlxhdescribeByNo(String ylxh,String ylmc){
		return sysconfDao.getYlxhdescribeByNo(ylxh,ylmc);
	}
	public void updateYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe){
		sysconfDao.updateYlxhdescribe(lYlxhdescribe);
	}
	public void addYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe){
		sysconfDao.addYlxhdescribe(lYlxhdescribe);
	}
	
	//检验项目对照
	public List<DsfControltestitems> getControltestitemsByNo(String customeritems,String customeritemsname){
		return sysconfDao.getControltestitemsByNo(customeritems,customeritemsname);
	}
}