package dataaccess;

import dataaccess.dao.QueryStatsDao;
import dataaccess.dao.SecurityDao;
import dataaccess.dao.SysConfDao;
import dataaccess.dao.UpDownDao;
import dataaccess.help.DataAccessUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.LProcess;
import common.datamodel.LSample;
import common.datamodel.LTestresult;
import common.datamodel.LabUser;

import security.OnlineUserMgr;

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
	

	public List getExpSampleNoData(String beginTime,String endTime,String customerid){
		return updownDao.getExpSampleNoData(beginTime,endTime,customerid);
	}
	public List <DsfLYlxhdescribe> getYlxhdescribeByYlxh(String ylxh,String customerid){
		return updownDao.getYlxhdescribeByYlxh(ylxh,customerid);
	}
	
	public List<DsfTestitems> getTestItemsByTestItem(String dTestitems,String customerid){
		return updownDao.getTestItemsByTestItem(dTestitems,customerid);
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
	public LProcess getLProcessByLSampleId(BigDecimal sampleno){
		return updownDao.getLProcessByLSampleId(sampleno);
	}
	public DsfTestitems getDsfTestitemsByLSampleId(BigDecimal sampleno){
		return updownDao.getDsfTestitemsByLSampleId(sampleno);
	}
	
	
	
	//webservice
	public boolean checkDsfUserInfo(String customerid,String customerKey){
		return updownDao.checkDsfUserInfo(customerid,customerKey);
	}
	
	public List<LTestresult> getTestResultsByWebService(LTestresult ltr){
		return updownDao.getTestResultsByWebService(ltr);
	}
	
	
	
	
	public List<DsfCustomerBaseInfo> getBaseCustomerInfo(){
		return queryStatsDao.getBaseCustomerInfo();
	}

}