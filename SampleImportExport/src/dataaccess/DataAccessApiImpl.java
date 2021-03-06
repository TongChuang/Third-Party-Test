package dataaccess;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfInspectionItemControl;
import common.datamodel.DsfProcess;
import common.datamodel.DsfSampleInfo;
import common.datamodel.DsfSampleTypeControl;
import common.datamodel.DsfTestResult;
import common.datamodel.DsfTestitems;
import common.datamodel.DsfTestobjective;
import common.datamodel.LSampleType;
import common.datamodel.LTestitem;
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
	
	public List<DsfTestResult> queryExpData(String beginTime,String endTime,String customerid){
		return updownDao.queryExpData(beginTime,endTime,customerid);
	}
	
	public List<DsfSampleInfo> getSampleByTime(String beginTime,String endTime,String customerid){
		return updownDao.getSampleByTime(beginTime,endTime,customerid);
	}
	
	public List getExpSampleNoData(String beginTime,String endTime,String customerid){
		return updownDao.getExpSampleNoData(beginTime,endTime,customerid);
	}
	public List <DsfTestobjective> getYlxhdescribeByYlxh(String ylxh,String customerid){
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
	
	public List<DsfSampleInfo> getSampleNoByLSample(List samplenoList){
		return updownDao.getSampleNoByLSample(samplenoList);
	}
	public DsfProcess getLProcessByLSampleId(BigDecimal sampleno){
		return updownDao.getLProcessByLSampleId(sampleno);
	}
	public DsfTestitems getDsfTestitemsById(BigDecimal sampleno){
		return updownDao.getDsfTestitemsById(sampleno);
	}
	public DsfSampleInfo getSampleByBarCode(String barcode){
		return updownDao.getSampleByBarCode(barcode);
	}
	public List<DsfSampleInfo> getSamplesByBarCode(String barcode){
		return updownDao.getSamplesByBarCode( barcode);
	}
	
	public DsfCustomerBaseInfo getCustomerInfoById_updown(String id){
		return updownDao.getCustomerInfoById(id);
	}
	
	//webservice
	public boolean checkDsfUserInfo(String customerid,String customerKey){
		return updownDao.checkDsfUserInfo(customerid,customerKey);
	}
	
	public List<DsfTestResult> getTestResultsByWebService(DsfTestResult ltr){
		return updownDao.getTestResultsByWebService(ltr);
	}
	
	public List<DsfTestResult> getLTestresultByNo(String sampleno){
		return updownDao.getLTestresultByNo(sampleno);
	}
	public List<DsfProcess> getSampleTime(String sampleno){
		return updownDao.getSampleTime(sampleno);
	}
	
	//
	public void saveCustomerBaseInfo_sysconf(DsfCustomerBaseInfo dsfc){
		sysconfDao.saveCustomerBaseInfo(dsfc);
	}
	public List<DsfCustomerBaseInfo> getCustomerInfoList(String clientnumber){
		return sysconfDao.getCustomerInfoList(clientnumber);
	}
	public DsfCustomerBaseInfo getCustomerInfoById(String id){
		return sysconfDao.getCustomerInfoById(id);
	}
	public List<DsfCustomerBaseInfo> getCustomerInfoByName(String customername){
		return sysconfDao.getCustomerInfoByName(customername);
	}
	public void deleteCustomerInfo(String id){
		sysconfDao.deleteCustomerInfo(id);
	}
	public List<DsfCustomerBaseInfo> getCustomerBaseInfoByCustomerId(String clientnumber){
		return sysconfDao.getCustomerBaseInfoByCustomerId(clientnumber);
	}
	
	public List<DsfCustomerBaseInfo> getCustomerInfoByCnameState(String customername,String state) {
		return sysconfDao.getCustomerInfoByCnameState(customername,state);
	}
	//检验信息
		public List<DsfCustomerBaseInfo> getCustomerInfoByCustomerId(String customerid){
			return sysconfDao.getCustomerInfoByCostomerId(customerid);
		}
		public List<DsfTestobjective> getYlxhdescribe(String customerid){
			return sysconfDao.getYlxhdescribe(customerid);
		}
		public List<DsfTestobjective> getYlxhdescribeById(String id){
			return sysconfDao.getYlxhdescribeById(id);
		}
		public List<DsfTestobjective> getYlxhdescribeByNo(String ylxh,String ylmc){
			return sysconfDao.getYlxhdescribeByNo(ylxh,ylmc);
		}
		public void updateYlxhdescribe(DsfTestobjective lYlxhdescribe){
			sysconfDao.updateYlxhdescribe(lYlxhdescribe);
		}
		public void addYlxhdescribe(DsfTestobjective lYlxhdescribe){
			sysconfDao.addYlxhdescribe(lYlxhdescribe);
		}
		public void deleteYlxhdescribe(BigDecimal id){
			sysconfDao.deleteYlxhdescribe(id);
		}
		public List<DsfTestitems> getTestItemsByNo(String proList, String customerid){
			return sysconfDao.getTestItemsByNo(proList,customerid);
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
		public List<DsfInspectionItemControl> getControltestitemsByNo(String customeritems,String customeritemsname){
			return sysconfDao.getControltestitemsByNo(customeritems,customeritemsname);
		}
		public List<DsfInspectionItemControl> getControltestitems(String customerid){
			return sysconfDao.getControltestitems(customerid);
		}
		public List<DsfInspectionItemControl> getControltestitemsById(BigDecimal id){
			return sysconfDao.getControltestitemsById(id);
		}
		public List<LTestitem> getLocalTestItems(){
			return sysconfDao.getLocalTestItems();
		}
		public List<LTestitem> getLocalTestItemsByNo(String customerid){
			return sysconfDao.getLocalTestItemsByNo(customerid);
		}
		public void saveAll(List<DsfInspectionItemControl> dcttList){
			sysconfDao.saveAll(dcttList);
		}
		public List<LTestitem> getTestItemsByIndexId(String indexId){
			return sysconfDao.getTestItemsByIndexId(indexId);
		}
		//
		public void saveData_sysconf(Object t,String tableName){
			sysconfDao.saveData(t,tableName);
		}
		/**
		 * 前处理
		 */
		public List<DsfTestobjective> getDsfTestObjectiveById(String customerid){
			return updownDao.getDsfTestObjectiveById(customerid);
		}
		public int getSeralNumber(String dateAndSection){
			return updownDao.getSeralNumber(dateAndSection);
		}
		public void saveAllSerialNumber(List<DsfTestobjective> dydList){
			updownDao.saveAllSerialNumber(dydList);
		}
		public List<String> getInspectionSectionByYLXH(String ylxh,String customerid){
			return updownDao.getInspectionSectionByYLXH(ylxh,customerid);
		}

	    public List<LabUser> getUserInfo(String username){
		    return updownDao.getUserInfo(username);
	    }
	    //
	    public List<DsfCustomerBaseInfo> getCustomerBaseInfoByCustomerId_updown(String clientnumber){
			return updownDao.getCustomerBaseInfoByCustomerId(clientnumber);
		}
	
	/**
	 * queryStats
	 */
	public List<DsfCustomerBaseInfo> getCustomerBaseInfoByCustomerId_querystats(String clientnumber){
		return queryStatsDao.getCustomerBaseInfoByCustomerId(clientnumber);
	}
	public void saveData_querystats(Object t,String tableName){
		queryStatsDao.saveData(t,tableName);
	}
	public void saveDataByList_querystats(List <Object>objectList,String tableName){
		queryStatsDao.saveDataByList(objectList,tableName);
	}
	/**
	 * 样本类型对照
	 */
	public List<LSampleType> getLSampleTypeById(String id){
		return sysconfDao.getLSampleTypeById(id);
	}
	public void saveAllDsfSampleTypeControl(List<DsfSampleTypeControl> dstList){
		sysconfDao.saveAllDsfSampleTypeControl(dstList);
	}
	public List<DsfSampleTypeControl> getDsfSampleTypeControlByCustomerId(String customerid){
		return sysconfDao.getDsfSampleTypeControlByCustomerId(customerid);
	}
}