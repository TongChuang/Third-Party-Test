package dataaccess.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfCustomerBaseInfo;
import dataaccess.help.DataAccessUtil;



public class QueryStatsDao extends HibernateDaoSupport {

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

	public void saveData(Object t,String tableName){
		DataAccessUtil.saveOrUpdate(t, tableName, getHibernateTemplate());
	}
	
	public void saveDataByList(List <Object>objectList,String tableName){
		DataAccessUtil.saveOrUpdateAll(objectList, tableName, getHibernateTemplate(), tableName);
	}
}
