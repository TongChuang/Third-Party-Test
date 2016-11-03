package dataaccess.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.datamodel.DsfInspectionItemControl;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfTestobjective;
import common.datamodel.DsfTestitems;
import common.datamodel.LTestitem;
import common.datamodel.DsfTestCenterInfo;

import dataaccess.help.DataAccessUtil;

public class SysConfDao extends HibernateDaoSupport {

	/**
	 * @param customername
	 * @param state
	 * @return
	 */
	public List<DsfCustomerBaseInfo> getCustomerInfoByCnameState(
			String customername, String state) {
		if ("".equals(state)) {
			return DataAccessUtil.getObjectsByColum(customername,
					"customername", "DsfCustomerBaseInfo",
					getHibernateTemplate());
		} else {
			String sqlString = "from DsfCustomerBaseInfo where customername like '%"
					+ customername + "%'";
			try {
				return getHibernateTemplate().find(sqlString);
			} catch (DataAccessException e) {
				return ((List) (new Vector()));
			}
		}
	}

	public List<DsfCustomerBaseInfo> getCustomerInfoList(String customerid) {
		if (!"".equals(customerid)) {
			return DataAccessUtil.getObjectsByColum(customerid, "customerid",
					"DsfCustomerBaseInfo", getHibernateTemplate());
		} else {
			return DataAccessUtil.getAllObjects("DsfCustomerBaseInfo",
					getHibernateTemplate());
		}
	}

	public List<DsfCustomerBaseInfo> getCustomerInfoByName(String customername) {
		if (!"".equals(customername)) {
			return DataAccessUtil.getObjectsByColum(customername,
					"customername", "DsfCustomerBaseInfo",
					getHibernateTemplate());
		} else {
			return null;
		}
	}

	public DsfCustomerBaseInfo getCustomerInfoById(String id) {
		BigDecimal bigDecimal = new BigDecimal(id);
		return (DsfCustomerBaseInfo) DataAccessUtil.getObjectById(bigDecimal,
				"DsfCustomerBaseInfo", getHibernateTemplate());
	}

	public void deleteCustomerInfo(String id) {
		DataAccessUtil.deleteObjectsByStrColum(id, "id", "DsfCustomerBaseInfo",
				getSession());
	}

	public List<DsfCustomerBaseInfo> getCustomerBaseInfoByCustomerId(
			String customerid) {
		String sqlString = "";
		if ("".equals(customerid)) {
			sqlString = "from DsfCustomerBaseInfo";
		} else {
			sqlString = "from DsfCustomerBaseInfo where customerid '"
					+ customerid + "'";
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
	public List<DsfCustomerBaseInfo> getCustomerInfoByCostomerId(
			String customerid) {
		String sqlString = "";
		if ("".equals(customerid)) {
			sqlString = "from DsfCustomerBaseInfo";
		} else {
			sqlString = "from DsfCustomerBaseInfo where customerid ='"
					+ customerid + "'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<DsfTestobjective> getYlxhdescribe(String customerid){
		String sqlString = "";
		if ("".equals(customerid)) {
			return null;
		}else{
			sqlString = "from DsfTestobjective where customerid='"+customerid+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<DsfTestobjective> getYlxhdescribeById(String id){
		String sqlString = "";
		if ("".equals(id)) {
			return null;
		}else{
			sqlString = "from DsfTestobjective where id="+id;
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<DsfTestobjective> getYlxhdescribeByNo(String ylxh,String ylmc){
		String sqlString = "";
		if("".equals(ylxh)||"".equals(ylmc)){
			sqlString = "from DsfTestobjective";
		}else{
			sqlString = "from DsfTestobjective where ylxh like '%"+ylxh+"%' or ylmc like '%"+ylmc+"%'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public void updateYlxhdescribe(DsfTestobjective lYlxhdescribe){
		String sqlString = "";
		if("".equals(lYlxhdescribe)){
			
		}else{
			sqlString = "from DsfTestobjective";
		}
		try {
			getHibernateTemplate().update(lYlxhdescribe);
		} catch (DataAccessException e) {

		}
	}
	public void deleteYlxhdescribe(BigDecimal id){
		DataAccessUtil.deleteObjectsByStrColum(id, "id", "DsfTestobjective", getSession());
	}
	public void addYlxhdescribe(DsfTestobjective lYlxhdescribe){
		DataAccessUtil.saveOrUpdate(lYlxhdescribe, "DsfTestobjective", getHibernateTemplate());
	}

	public List<DsfTestitems> getTestItemsByNo(String proList, String customerid) {
		String sqlString = "";
		if (!"".equals(proList) || !"".equals(customerid)) {
			sqlString = "from DsfTestitems where indexId in(" + proList
					+ ") and customerid = '" + customerid + "'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}

	public List<DsfTestitems> getTestItems() {
		String sqlString = "";
		sqlString = "from DsfTestitems";
		// sqlString = "from LTestitem";
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}

	public void saveTestObjective(String ylxh, String profiletest) {
		String hql = "";
		try {
			hql = "update DsfTestobjective yl set yl.profiletest = '"+profiletest+"' where yl.ylxh = '"+ylxh+"'";
			Query query = getSession().createQuery(hql);
			query.executeUpdate();
		} catch (DataAccessException e) {
		}
	}

	public String getSequence(String seqName) {
		return DataAccessUtil.getNextSequence(seqName, getSession());
	}

	/**
	 * 检验项目对照
	 */
	public List<DsfInspectionItemControl> getControltestitemsByNo(String customeritems,String customeritemsname){
		String sqlString = "";
		if("".equals(customeritems)||"".equals(customeritemsname)){
			sqlString = "from DsfInspectionItemControl where customeritems like '%"+customeritems+"%' or customeritemsname like'%"+customeritemsname+"%'";
		}else{
			sqlString = "from DsfInspectionItemControl";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<DsfInspectionItemControl> getControltestitemsById(BigDecimal id){
		String sqlString = "";
		if("".equals(id)){
			
		}else{
			sqlString = "from DsfInspectionItemControl where id = "+id;
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public List<DsfInspectionItemControl> getControltestitems(String customerid){
		String sqlString = "";
		if(!"".equals(customerid)){
			sqlString = "from DsfInspectionItemControl where customerid='"+customerid+"'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}

	public List<LTestitem> getLocalTestItems() {
		try {
			return DataAccessUtil.getAllObjects("LTestitem",
					getHibernateTemplate());
		} catch (DataAccessException e) {
			return null;
		}
	}

	public List<LTestitem> getLocalTestItemsByNo(String customerid) {
		String sqlString = "";
		if (!"".equals(customerid)) {
			sqlString = "from LTestitem where customerid='" + customerid + "'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}

	public List<LTestitem> getTestItemsByIndexId(String indexId) {
		String sqlString = "";
		if (!"".equals(indexId)) {
			sqlString = "from LTestitem where indexId='" + indexId + "'";
		}
		try {
			return getHibernateTemplate().find(sqlString);
		} catch (DataAccessException e) {
			return null;
		}
	}
	public void saveAll(List<DsfInspectionItemControl> dcttList){
		DataAccessUtil.saveOrUpdateAll(((Collection) (dcttList)),
				"DsfControltestitems", getHibernateTemplate(), "update");
	}

	public List<DsfTestCenterInfo> getTestCenterInfoList() {
		return DataAccessUtil.getAllObjects("DsfTestCenterInfo",
				getHibernateTemplate());
	}

	public void deleteTestCenterInfo(BigDecimal id) {
		DataAccessUtil.deleteObjectsByStrColum(id, "id", "DsfTestCenterInfo",
				getSession());
	}

	public void updateDsfTestCenterInfo(DsfTestCenterInfo dsftestcenterinfo) {
		String sqlString = "";
		/*
		 * if("".equals(DsfTestCenterInfo)){
		 * 
		 * }else{ sqlString = "from DsfTestCenterInfo "; } try {
		 * getHibernateTemplate().update(DsfTestCenterInfo); } catch
		 * (DataAccessException e) {
		 * 
		 * }
		 */
		try {
			/*
			 * sqlString = "update DsfTestCenterInfo tc set tc.name='" +
			 * DsfTestCenterInfo.getName() + "'tc.address='" +
			 * DsfTestCenterInfo.getAddress() + "'tc.phone='" +
			 * DsfTestCenterInfo.getPhone() + "' where id=1"; Query query =
			 * getSession().createQuery(sqlString); query.executeUpdate();
			 */
			DataAccessUtil.updateObject(dsftestcenterinfo, "DsfTestCenterInfo",
					getHibernateTemplate());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public DsfTestCenterInfo getTestCenterInfoById(String id) {
		try {
			return (DsfTestCenterInfo) DataAccessUtil.getObjectById(new BigDecimal(id), "DsfTestCenterInfo",
					getHibernateTemplate());
		} catch (DataAccessException e) {
			return null;
		}
	}
	public void saveCustomerBaseInfo(DsfCustomerBaseInfo dsfc){
		DataAccessUtil.saveOrUpdate(dsfc, "DsfCustomerBaseInfo", getHibernateTemplate());
	}
}