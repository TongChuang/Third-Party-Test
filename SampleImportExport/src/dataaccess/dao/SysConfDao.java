package dataaccess.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfCustomerBaseInfo;
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
}