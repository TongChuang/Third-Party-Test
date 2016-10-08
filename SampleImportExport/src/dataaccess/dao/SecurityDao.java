package dataaccess.dao;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.LabUser;
import dataaccess.help.DataAccessUtil;


public class SecurityDao extends HibernateDaoSupport {
	
	public static final String CREATE_USER = "createUser";

	public int getSystemLogCount() {
		String hql = "select count(*) from SystemLog";
		int count = ((Long) getHibernateTemplate().iterate(hql).next())
				.intValue();
		return count;
	}
	
		
	public LabUser getUserByUserName(String userName){
		return (LabUser) DataAccessUtil.getObjectByColum(userName, "username", "LabUser", getHibernateTemplate());
	}
	public void saveCustomerBaseInfo(DsfCustomerBaseInfo dsfc){
		DataAccessUtil.saveOrUpdate(dsfc, "DsfCustomerBaseInfo", getHibernateTemplate());
	}
	
	public String getseqString(String seqName){
		return DataAccessUtil.getNextSequence(seqName, getSession());
	}


}