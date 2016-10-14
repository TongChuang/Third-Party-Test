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
import common.datamodel.LProcess;
import common.datamodel.LSample;
import common.datamodel.LTestresult;
import dataaccess.help.DataAccessUtil;

public class InspectionDao extends HibernateDaoSupport {

	//检验信息
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
}