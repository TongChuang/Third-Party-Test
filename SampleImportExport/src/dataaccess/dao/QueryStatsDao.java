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
import common.datamodel.DsfCustomerBarCode;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.LProcess;
import common.datamodel.LSample;
import common.datamodel.LTestresult;
import dataaccess.help.DataAccessUtil;

public class QueryStatsDao extends HibernateDaoSupport {

	public List<DsfCustomerBaseInfo> getBaseCustomerInfo() {
		return DataAccessUtil.getAllObjects("DsfCustomerBaseInfo", getHibernateTemplate());
	}

	public List<DsfCustomerBarCode> getNowCode(String customerid) {
		return DataAccessUtil.getAllObjects("DsfCustomerBarCode", getHibernateTemplate());
	}

	public void saveBarCode(DsfCustomerBarCode dsfCustomerBarCode) {
		DataAccessUtil.saveOrUpdate(dsfCustomerBarCode, "DsfCustomerBarCode", getHibernateTemplate());
	}

}
