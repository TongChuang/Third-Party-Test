package dataaccess.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfInspectionItemControl;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfTestobjective;
import common.datamodel.DsfProcess;
import common.datamodel.DsfTestitems;
import common.datamodel.DsfSampleInfo;
import common.datamodel.LTestitem;
import common.datamodel.LTestobjective;
import common.datamodel.DsfTestResult;


import common.datamodel.LabUser;

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
	//根据LProcrss查询的SampleId去查询Sample信息
	public List<DsfSampleInfo> getLSample(String beginTime,String endTime){
		String sqlString = "";
		if("".equals(beginTime)||"".equals(endTime)){
			sqlString = "select {s.*} from DSF_PROCESS p ,DSF_SAMPLE_INFO s where p.sample_id = s.sampleno";
		}else{
			//sqlString = "select  from LProcess p join LSample s on p.sample_id = s.id where p.executetime between to_date('"+beginTime+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-MM-dd HH24:mi:ss')";
			sqlString = "select {s.*} from DSF_PROCESS p ,DSF_SAMPLE_INFO s where p.sample_id = s.sampleno "+
					"and p.collectiontime between to_date('"+beginTime+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-MM-dd HH24:mi:ss')";
		}
		try {
			return  getSession().createSQLQuery(sqlString).addEntity("s",DsfSampleInfo.class).list();
		} catch (DataAccessException e) {
			return ((List) (new Vector()));
		}
	}
	public List<DsfSampleInfo> getSampleByTime(String beginTime,String endTime,String customerid){
		String sqlString = "";
		if("".equals(beginTime)||"".equals(endTime)||null!=customerid){
			sqlString = "select {s.*} from DSF_PROCESS p ,DSF_SAMPLE_INFO s where p.sample_id = s.id and s.dsfcustomerid='"+customerid+"'";
		}else{
			//sqlString = "select  from LProcess p join LSample s on p.sample_id = s.id where p.executetime between to_date('"+beginTime+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-MM-dd HH24:mi:ss')";
			sqlString = "select {s.*} from DSF_PROCESS p ,DSF_SAMPLE_INFO s where p.sample_id = s.id and s.dsfcustomerid='"+customerid+"'"+
					"and p.collectiontime between to_date('"+beginTime+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-MM-dd HH24:mi:ss')";
		}
		try {
			return  getSession().createSQLQuery(sqlString).addEntity("s",DsfSampleInfo.class).list();
		} catch (DataAccessException e) {
			return ((List) (new Vector()));
		}
	}
	public List<DsfTestResult> queryExpData(String beginTime,String endTime,String customerid){
		String sqlString = "";
		if("".equals(beginTime)||"".equals(endTime)){
			sqlString = "from DsfTestResult where customerid='"+customerid+"' order by measuretime desc";
		}else{
			sqlString = "from DsfTestResult where customerid='"+customerid+"' and measuretime between to_date('"+beginTime+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-MM-dd HH24:mi:ss') order by measuretime desc";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return ((List) (new Vector()));
		}
	}
	public List<DsfTestResult> getLTestresultByNo(String sampleno){
		String sqlString = "";
		if(null!=sampleno){
			sqlString = "from DsfTestResult where sampleno='"+sampleno+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<DsfProcess> getSampleTime(String sampleno){
		String sqlString = "";
		if(sampleno==""){
			sqlString = "from DsfProcess";
		}else{
			sqlString = "from DsfProcess where sampleId='"+sampleno+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List getExpSampleNoData(String beginTime,String endTime,String customerid){
		String sqlString = "";
		if("".equals(beginTime)||"".equals(endTime)){
			sqlString = "select distinct sampleno from DsfTestResult where customerid='"+customerid+"' order by sampleno desc";
		}else{
			sqlString = "select distinct sampleno DsfTestResult where customerid='"+customerid+"' and measuretime between to_date('"+beginTime+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+endTime+"','yyyy-MM-dd HH24:mi:ss') order by sampleno desc";
		}
		try {
			Query query = getSession().createQuery(sqlString);
			return query.list();
		} catch (DataAccessException e) {
			return ((List) (new Vector()));
		}
	}
	
	public List <DsfTestobjective> getYlxhdescribeByYlxh(String ylxh,String customerid){
		String sql ="from DsfTestobjective where customerid='"+customerid+"'";
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
		List <DsfInspectionItemControl> ctiList = new ArrayList<DsfInspectionItemControl>();
		Map <String,String>initMap = new HashMap<String, String>();
		ctiList = DataAccessUtil.getAllObjects("DsfInspectionItemControl", getHibernateTemplate());
		if(ctiList!=null&&ctiList.size()>0){
			for (DsfInspectionItemControl cti:ctiList) {
				initMap.put(cti.getLocalitems(), cti.getCustomeritems());
			}
		}
		return initMap;
	}
	
	public Map<String, String> getCustomer2LocalConversionTestItem(){
		List <DsfInspectionItemControl> ctiList = new ArrayList<DsfInspectionItemControl>();
		Map <String,String>initMap = new HashMap<String, String>();
		ctiList = DataAccessUtil.getAllObjects("DsfInspectionItemControl", getHibernateTemplate());
		if(ctiList!=null&&ctiList.size()>0){
			for (DsfInspectionItemControl cti:ctiList) {
				initMap.put(cti.getCustomeritems(),cti.getLocalitems());
			}
		}
		return initMap;
	}
	
	public List<DsfSampleInfo> getSampleNoByLSample(List samplenoList){
		return DataAccessUtil.getObjectsByColumWithIn(samplenoList, "sampleno", "DSF_SAMPLE_INFO", getSession());
	}
	public DsfSampleInfo getSampleByBarCode(String barcode){
		return (DsfSampleInfo) DataAccessUtil.getObjectByColum(barcode, "localbarcode", "DSF_SAMPLE_INFO", getHibernateTemplate());
	}
	public List<DsfSampleInfo> getSamplesByBarCode(String barcode){
		String sqlString = "";
		if(null!=barcode){
			sqlString = "from DsfSampleInfo where localbarcode = '"+barcode+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
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
	
	public List<DsfTestResult> getTestResultsByWebService(DsfTestResult ltr){
		StringBuffer sql = new StringBuffer("from DsfTestResult where customerid ='"+ltr.getCustomerid()+"' ");
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
	/**
	 * 前处理
	 */
	public List<DsfTestobjective> getDsfTestObjectiveById(String customerid){
		String sqlString = "";
		if(null!=customerid){
			sqlString = "from DsfTestobjective where customerid = '"+customerid+"' order by id desc";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}	
	}
	public int getSeralNumber(String dateAndSection){
		////select count(*) from DSF_L_YLXHDESCRIBE where SUBSTR(serialnumber,1,10) = '20161024MYA';
		/*
		String[] strings1= {"SUBSTR(serialnumber,1,11)"};
		String[] strings2= {dateAndSection};
		return DataAccessUtil.cntByStrCols("DsfTestobjective", strings1, strings2, getSession());*/
		String sqlString = "";
		if(null!=dateAndSection){
			 sqlString = "select count(*) from DSF_SAMPLE_INFO where SUBSTR(sampleno,1,11) = '"+dateAndSection+"'";
		}
		try {
			return Integer.parseInt(getSession().createSQLQuery(sqlString).list().get(0).toString());
		} catch (DataAccessException e) {
			return -1;
		}	
	}
	public void saveAllSerialNumber(List<DsfTestobjective> dydList){
		DataAccessUtil.saveOrUpdateAll(((Collection) (dydList)), "DsfTestobjective", getHibernateTemplate(), "update");
	}
	public List<String> getInspectionSectionByYLXH(String ylxh,String customerid){
		String sqlString = "";
		if(null!=ylxh){
			sqlString = "from DSF_SAMPLE_INFO where ylxh = '"+ylxh+"' and customerid = '"+customerid+"' order by id desc";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}	
	}
	public List<LabUser> getUserInfo(String username){
		StringBuffer sql = new StringBuffer(" from  LabUser");
		if(!"".equals(username)){
			sql.append(" where username ='"+username+"'");
			return getHibernateTemplate().find(sql.toString());
		}else {
			return getHibernateTemplate().find(sql.toString());
		}
	}
	
	public List<DsfCustomerBaseInfo> getCustomerBaseInfoByCustomerId(String customerid){
		String sqlString = "";
		if("".equals(customerid)){
			sqlString = "from DsfCustomerBaseInfo";
		}else{
			sqlString = "from DsfCustomerBaseInfo where customerid '"+customerid+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}	
	}
	
	public DsfCustomerBaseInfo getCustomerInfoById(String id) {
		BigDecimal bigDecimal = new BigDecimal(id);
		return (DsfCustomerBaseInfo) DataAccessUtil.getObjectById(bigDecimal, "DsfCustomerBaseInfo", getHibernateTemplate()) ;
	}
}
