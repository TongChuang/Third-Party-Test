package dataaccess.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
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

	public DsfCustomerBaseInfo getCustomerInfoById(String customerid) {
		BigDecimal bigDecimal = new BigDecimal(customerid);
		return (DsfCustomerBaseInfo) DataAccessUtil.getObjectByColum(bigDecimal, "customerid", "DsfCustomerBaseInfo", getHibernateTemplate());
	}

	public void deleteCustomerInfo(String customerid) {
		DataAccessUtil.deleteObjectsByStrColum(customerid, "customerid", "DsfCustomerBaseInfo", getSession());
	}
	/**
	 * 检验信息
	 */
	public List<DsfCustomerBaseInfo> getCustomerInfoByNo(String clientnumber,String customerid){
		String sqlString = "";
		if("".equals(clientnumber)||"".equals(customerid)){
			sqlString = "from DsfCustomerBaseInfo";
		}else{
			sqlString = "from DsfCustomerBaseInfo where clientnumber='"+clientnumber+"' or customerid='"+customerid+"'";
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
	public List<DsfLYlxhdescribe> getYlxhdescribeByNo(String ylxh,String ylmc){
		String sqlString = "";
		if("".equals(ylxh)||"".equals(ylmc)){
			sqlString = "from DsfLYlxhdescribe";
		}else{
			sqlString = "from DsfLYlxhdescribe where customerid='"+ylxh+"' or ylmc='"+ylmc+"'";
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
	public void addYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe){
		DataAccessUtil.saveOrUpdate(lYlxhdescribe, "DsfLYlxhdescribe", getHibernateTemplate());
	}
	/**
	 * 检验项目对照
	 */

	public List<DsfControltestitems> getControltestitemsByNo(String customeritems,String customeritemsname){
		String sqlString = "";
		if("".equals(customeritems)||"".equals(customeritemsname)){
			
		}else{
			sqlString = "from DsfLYlxhdescribe";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}		
	}
}