package dataaccess.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.LTestitem;

import dataaccess.help.DataAccessUtil;

public class SysConfDao extends HibernateDaoSupport {

	/**
	 * @param customername
	 * @param state
	 * @return
	 */
	public List<DsfCustomerBaseInfo> getCustomerInfoByCnameState(String customername, String state) {
		if ("".equals(state)) {
			return DataAccessUtil.getObjectsByColum(customername, "customername", "DsfCustomerBaseInfo", getHibernateTemplate());
		} else {
			String sqlString = "from DsfCustomerBaseInfo where customername like '%" + customername + "%'";
			try {
				return getHibernateTemplate().find(sqlString);
			} catch (DataAccessException e) {
				return ((List) (new Vector()));
			}
		}
	}

	public List<DsfCustomerBaseInfo> getCustomerInfoList(String clientnumber) {
		if (!"".equals(clientnumber)) {
			return DataAccessUtil.getObjectsByColum(clientnumber, "clientnumber", "DsfCustomerBaseInfo", getHibernateTemplate());
		} else {
			return DataAccessUtil.getAllObjects("DsfCustomerBaseInfo", getHibernateTemplate());
		}
	}
	public List<DsfCustomerBaseInfo> getCustomerInfoByName(String customername){
		if (!"".equals(customername)) {
			return DataAccessUtil.getObjectsByColum(customername, "customername", "DsfCustomerBaseInfo", getHibernateTemplate());
		}else{
			return null;
		}
	}

	public DsfCustomerBaseInfo getCustomerInfoById(String id) {
		BigDecimal bigDecimal = new BigDecimal(id);
		return (DsfCustomerBaseInfo) DataAccessUtil.getObjectById(bigDecimal, "DsfCustomerBaseInfo", getHibernateTemplate()) ;
	}

	public void deleteCustomerInfo(String id) {
		DataAccessUtil.deleteObjectsByStrColum(id, "id", "DsfCustomerBaseInfo", getSession());
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
	/**
	 * 检验信息
	 */
	public List<DsfCustomerBaseInfo> getCustomerInfoByNo(String customerid,String customername){
		String sqlString = "";
		if("".equals(customerid)&&"".equals(customername)){
			sqlString = "from DsfCustomerBaseInfo";
		}else if(!"".equals(customerid)&&!"".equals(customername)){
			sqlString = "from DsfCustomerBaseInfo where customerid like '%"+customerid+"%' or customername like'%"+customername+"%'";
		}else {
			sqlString = "from DsfCustomerBaseInfo where customername ='"+customername+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}	
	}
	public List<DsfLYlxhdescribe> getYlxhdescribe(String customerid){
		String sqlString = "";
		if("".equals(customerid)){
			return null;
		}else{
			sqlString = "from DsfLYlxhdescribe where customerid='"+customerid+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<DsfLYlxhdescribe> getYlxhdescribeById(String id){
		String sqlString = "";
		if("".equals(id)){
			return null;
		}else{
			sqlString = "from DsfLYlxhdescribe where id="+id;
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<DsfLYlxhdescribe> getYlxhdescribeByNo(String ylxh,String ylmc){
		String sqlString = "";
		if("".equals(ylxh)||"".equals(ylmc)){
			sqlString = "from DsfLYlxhdescribe";
		}else{
			sqlString = "from DsfLYlxhdescribe where ylxh like '%"+ylxh+"%' or ylmc like '%"+ylmc+"%'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public void updateYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe){
		String sqlString = "";
		if("".equals(lYlxhdescribe)){
			
		}else{
			sqlString = "from DsfLYlxhdescribe";
		}
		try {
			getHibernateTemplate().update(lYlxhdescribe);
		} catch (DataAccessException e) {
			
		}
	}
	public void deleteYlxhdescribe(BigDecimal id){
		DataAccessUtil.deleteObjectsByStrColum(id, "id", "DsfLYlxhdescribe", getSession());
	}
	public void addYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe){
		DataAccessUtil.saveOrUpdate(lYlxhdescribe, "DsfLYlxhdescribe", getHibernateTemplate());
	}
	public List<DsfTestitems> getTestItemsByNo(String proList,String customerid){
		String sqlString = "";
		if(!"".equals(proList)||!"".equals(customerid)){
			sqlString = "from DsfTestitems where indexId in("+proList+") and customerid = '"+customerid+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<DsfTestitems> getTestItems(){
		String sqlString = "";
		sqlString = "from DsfTestitems";
		//sqlString = "from LTestitem";
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public void saveTestObjective(String ylxh, String profiletest){
		String hql = "";
		try {
			hql = "update DsfLYlxhdescribe yl set yl.profiletest = '"+profiletest+"' where yl.ylxh = '"+ylxh+"'";
			Query query = getSession().createQuery(hql);
			query.executeUpdate();
		} catch (DataAccessException e) {
		}
	}
	public String getSequence(String seqName){
		return DataAccessUtil.getNextSequence(seqName,getSession());
	}
	/**
	 * 检验项目对照
	 */
	public List<DsfControltestitems> getControltestitemsByNo(String customeritems,String customeritemsname){
		String sqlString = "";
		if("".equals(customeritems)||"".equals(customeritemsname)){
			sqlString = "from DsfControltestitems where customeritems like '%"+customeritems+"%' or customeritemsname like'%"+customeritemsname+"%'";
		}else{
			sqlString = "from DsfControltestitems";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}		
	}
	public List<DsfControltestitems> getControltestitemsById(BigDecimal id){
		String sqlString = "";
		if("".equals(id)){
			
		}else{
			sqlString = "from DsfControltestitems where id = "+id;
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}		
	}
	public List<DsfControltestitems> getControltestitems(String customerid){
		String sqlString = "";
		if(!"".equals(customerid)){
			sqlString = "from DsfControltestitems where customerid='"+customerid+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<LTestitem> getLocalTestItems(){
		try {
			return DataAccessUtil.getAllObjects("LTestitem", getHibernateTemplate());
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<LTestitem> getLocalTestItemsByNo(String customerid){
		String sqlString = "";
		if(!"".equals(customerid)){
			sqlString = "from LTestitem where customerid='"+customerid+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<LTestitem> getTestItemsByIndexId(String indexId){
		String sqlString = "";
		if(!"".equals(indexId)){
			sqlString = "from LTestitem where indexId='"+indexId+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public void saveAll(List<DsfControltestitems> dcttList){
		DataAccessUtil.saveOrUpdateAll(((Collection) (dcttList)), "DsfControltestitems", getHibernateTemplate(), "update");

	}
}