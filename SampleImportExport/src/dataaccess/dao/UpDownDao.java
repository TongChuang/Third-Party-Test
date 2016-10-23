package dataaccess.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.DsfProcess;
import common.datamodel.LSample;
import common.datamodel.LTestitem;
import common.datamodel.LTestresult;
import dataaccess.help.DataAccessUtil;

public class UpDownDao extends HibernateDaoSupport {

	public List<DsfCustomerBaseInfo> getCustomerInfo(){
		return DataAccessUtil.getAllObjects("DsfCustomerBaseInfo", getHibernateTemplate());
	}
	public void saveData(Object t,String tableName){
		DataAccessUtil.saveOrUpdate(t, tableName, getHibernateTemplate());
	}
	public void saveDataByList(List <Object>objectList,String tableName){
		DataAccessUtil.saveOrUpdateAll(objectList, tableName, getHibernateTemplate(), tableName);
	}
	
	public List<LTestresult> queryExpData(String beginTime,String endTime,String customerid){
		String sqlString = "";
		if("".equals(beginTime)||"".equals(endTime)){
			sqlString = "from LTestresult where customerid='"+customerid+"' order by measuretime desc";
		}else{
			sqlString = "from LTestresult where customerid='"+customerid+"' and measuretime between to_date('"+beginTime+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-MM-dd HH24:mi:ss') order by measuretime desc";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return ((List) (new Vector()));
		}
	}
	
	public List getExpSampleNoData(String beginTime,String endTime,String customerid){
		String sqlString = "";
		if("".equals(beginTime)||"".equals(endTime)){
			sqlString = "select distinct sampleno from LTestresult where customerid='"+customerid+"' order by sampleno desc";
		}else{
			sqlString = "select distinct sampleno LTestresult where customerid='"+customerid+"' and measuretime between to_date('"+beginTime+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-MM-dd HH24:mi:ss') order by sampleno desc";
		}
		try {
			Query query = getSession().createQuery(sqlString);
			return query.list();
		} catch (DataAccessException e) {
			return ((List) (new Vector()));
		}
	}
	
	public List <DsfLYlxhdescribe> getYlxhdescribeByYlxh(String ylxh,String customerid){
		String sql ="from DsfLYlxhdescribe where customerid='"+customerid+"'";
		if(!"".equals(ylxh)){
			sql +=" and  ylxh='"+ylxh+"' ";
		}
		try {
			return getHibernateTemplate().find(sql);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	public List<DsfTestitems> getDsfTestItemsByTestItem(String dTestitems,String customerid){
		String sql ="from DsfTestitems where customerid='"+customerid+"'";
		if(!"".equals(dTestitems)){
			sql +=" and indexId='"+dTestitems+"' ";
		}
		try {
			return getHibernateTemplate().find(sql);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<LTestitem> getLTestItemsByTestItem(String testitems,String customerid){
		String sql ="from LTestitem where customerid='"+customerid+"'";
		if(!"".equals(testitems)){
			sql +=" and indexId='"+testitems+"' ";
		}
		try {
			return getHibernateTemplate().find(sql);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public Map<String, String> getLocal2CustomerConversionTestItem(){
		List <DsfControltestitems> ctiList = new ArrayList<DsfControltestitems>();
		Map <String,String>initMap = new HashMap<String, String>();
		ctiList = DataAccessUtil.getAllObjects("DsfControltestitems", getHibernateTemplate());
		if(ctiList!=null&&ctiList.size()>0){
			for (DsfControltestitems cti:ctiList) {
				initMap.put(cti.getLocalitems(), cti.getCustomeritems());
			}
		}
		return initMap;
	}
	
	public Map<String, String> getCustomer2LocalConversionTestItem(){
		List <DsfControltestitems> ctiList = new ArrayList<DsfControltestitems>();
		Map <String,String>initMap = new HashMap<String, String>();
		ctiList = DataAccessUtil.getAllObjects("DsfControltestitems", getHibernateTemplate());
		if(ctiList!=null&&ctiList.size()>0){
			for (DsfControltestitems cti:ctiList) {
				initMap.put(cti.getCustomeritems(),cti.getLocalitems());
			}
		}
		return initMap;
	}
	
	public List<LSample> getSampleNoByLSample(List samplenoList){
		return DataAccessUtil.getObjectsByColumWithIn(samplenoList, "sampleno", "LSample", getSession());
	}
	public LSample getSampleByBarCode(String barcode){
		return (LSample) DataAccessUtil.getObjectByColum(barcode, "dsfbarcode", "LSample", getHibernateTemplate());
	}
	
	public DsfProcess getLProcessByLSampleId(BigDecimal sampleno){
		String sql = "from LProcess where sampleId='"+sampleno+"'";
		try {
			List resultList = getHibernateTemplate().find(sql);
			if(null!=resultList&&resultList.size()>0){
				return (DsfProcess) resultList.get(0);
			}else {
				return null;
			}
		} catch (DataAccessException e) {
			return null;
		}	
	}
	public DsfTestitems getDsfTestitemsById(BigDecimal sampleno){
		return (DsfTestitems) DataAccessUtil.getObjectById(sampleno, "DsfTestitems", getHibernateTemplate());
	}
	
	//webservice
	public boolean checkDsfUserInfo(String customerid,String customerKey){
		boolean flag = false;
		String sql = "from DsfCustomerBaseInfo where customerid ='"+customerid+"' and customerKey='"+customerKey+"'";
		try {
			List resultList = getHibernateTemplate().find(sql);
			if(null!=resultList&&resultList.size()>0){
				flag = true;
			}
			return flag;
		} catch (DataAccessException e) {
			return flag;
		}	
	}
	
	public List<LTestresult> getTestResultsByWebService(LTestresult ltr){
		StringBuffer sql = new StringBuffer("from LTestresult where customerid ='"+ltr.getCustomerid()+"' ");
		if(null!=ltr.getDsfbarcode()){
			sql.append(" and dsfbarcode='"+ltr.getDsfbarcode()+"' ");
		}
		if(null!=ltr.getTestid()){
			sql.append(" and testid='"+ltr.getTestid()+"' ");
		}
		try {
			return getHibernateTemplate().find(sql.toString());
		} catch (DataAccessException e) {
			return null;
		}	
	}
}
