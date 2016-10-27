package sysconf;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import common.StartAPI;
import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.LTestitem;
import common.xmlmodel.SystemConfigSetting;

public interface SysConfApi
	extends StartAPI {

	public abstract SystemConfigSetting getSystemConfig() throws FileNotFoundException;
	public abstract void updateSystemConfig(SystemConfigSetting systemconfigsetting) throws FileNotFoundException;
	public abstract String getWebServiceUrl();
	/**
	 * 获取客户信息
	 * param clientnumber 如果为""表示查找全部用户，如果有值就表示查询想对应的数据
	 */
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoList(String clientnumber);
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByCnameState(String customername,String state);
	public abstract DsfCustomerBaseInfo getCustomerInfoById(String customerid);
	public abstract void deleteCustomerInfo(String customerid);
	public abstract void saveCustomerInfo(DsfCustomerBaseInfo dcbi);
	public abstract List<DsfCustomerBaseInfo> getCustomerBaseInfoByCustomerId(String clientnumber);
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByName(String customername);
	/**
	 * 获取检验信息
	 * 
	 */
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByNo(String clientnumber,String customerid);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribe(String customerid);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribeById(String id);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribeByNo(String ylxh,String ylmc);
	public abstract void updateYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe);
	public abstract void addYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe);
	public abstract void deleteYlxhdescribe(BigDecimal id);
	public abstract List<DsfTestitems> getTestItemsByNo(String proList, String customerid);
	public abstract List<DsfTestitems> getTestItems();
	public abstract void saveTestObjective(String ylxh, String profiletest);
	public abstract String getSequence(String seqName);
	/**
	 * 检验项目对照
	 * 
	 */
	public abstract List<DsfControltestitems> getControltestitemsByNo(String customeritems,String customeritemsname);
	public abstract List<DsfControltestitems> getControltestitems(String customerid);
	public abstract List<DsfControltestitems> getControltestitemsById(BigDecimal id);
	public abstract List<LTestitem> getLocalTestItems();
	public abstract void saveAll(List<DsfControltestitems> dcttList);
	public abstract List<LTestitem> getLocalTestItemsByNo(String customerid);
	public abstract void saveData(Object t,String tableName);
	public abstract List<LTestitem> getTestItemsByIndexId(String indexId);
}