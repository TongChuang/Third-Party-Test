package dataaccess;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBarCode;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfProcess;
import common.datamodel.DsfTestitems;
import common.datamodel.LSample;
import common.datamodel.LTestitem;
import common.datamodel.LTestobjective;
import common.datamodel.LTestresult;
import common.datamodel.LabUser;

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

	public DataAccessApiImpl() {
		securityDao = null;
		sysconfDao = null;
		updownDao = null;
		queryStatsDao = null;
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
	
	public List<LSample> getSampleByTime(String beginTime,String endTime,String customerid){
		return updownDao.getSampleByTime(beginTime,endTime,customerid);
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
	public List<LSample> getSamplesByBarCode(String barcode){
		return updownDao.getSamplesByBarCode( barcode);
	}
	
	//webservice
	public boolean checkDsfUserInfo(String customerid,String customerKey){
		return updownDao.checkDsfUserInfo(customerid,customerKey);
	}
	
	public List<LTestresult> getTestResultsByWebService(LTestresult ltr){
		return updownDao.getTestResultsByWebService(ltr);
	}
	
	public List<LTestresult> getLTestresultByNo(String sampleno){
		return updownDao.getLTestresultByNo(sampleno);
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
		public List<DsfLYlxhdescribe> getYlxhdescribeById(String id){
			return sysconfDao.getYlxhdescribeById(id);
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
		public void deleteYlxhdescribe(BigDecimal id){
			sysconfDao.deleteYlxhdescribe(id);
		}
		public List<DsfTestitems> getTestItemsByNo(String proList){
			return sysconfDao.getTestItemsByNo(proList);
		}
		public List<DsfTestitems> getTestItems(){
			return sysconfDao.getTestItems();
		}
		public void saveTestObjective(String ylxh, String profiletest){
			sysconfDao.saveTestObjective(ylxh,profiletest);
		}
		public String getSequence(String seqName){
			return sysconfDao.getSequence(seqName);
		}
		//检验项目对照
		public List<DsfControltestitems> getControltestitemsByNo(String customeritems,String customeritemsname){
			return sysconfDao.getControltestitemsByNo(customeritems,customeritemsname);
		}
		public List<DsfControltestitems> getControltestitems(String customerid){
			return sysconfDao.getControltestitems(customerid);
		}
		public List<DsfControltestitems> getControltestitemsById(BigDecimal id){
			return sysconfDao.getControltestitemsById(id);
		}
		public List<LTestitem> getLocalTestItems(){
			return sysconfDao.getLocalTestItems();
		}
		public List<LTestitem> getLocalTestItemsByNo(String customerid){
			return sysconfDao.getLocalTestItemsByNo(customerid);
		}
		public void saveAll(List<DsfControltestitems> dcttList){
			sysconfDao.saveAll(dcttList);
		}

		public List<LTestitem> getTestItemsByIndexId(String indexId){
			return sysconfDao.getTestItemsByIndexId(indexId);
		}
		/**
		 * 前处理
		 */
		public List<DsfLYlxhdescribe> getDsfTestObjectiveById(String customerid){
			return updownDao.getDsfTestObjectiveById(customerid);
		}
		public int getSeralNumber(String dateAndSection){
			return updownDao.getSeralNumber(dateAndSection);
		}
		public void saveAllSerialNumber(List<DsfLYlxhdescribe> dydList){
			updownDao.saveAllSerialNumber(dydList);
		}
		public List<String> getInspectionSectionByYLXH(String ylxh,String customerid){
			return updownDao.getInspectionSectionByYLXH(ylxh,customerid);
		}

	public List<LabUser> getUserInfo(String username){
		return updownDao.getUserInfo(username);
	}
}