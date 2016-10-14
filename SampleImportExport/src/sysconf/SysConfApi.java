package sysconf;

import java.io.FileNotFoundException;
import java.util.List;

import common.StartAPI;
import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.xmlmodel.SystemConfigSetting;

public interface SysConfApi
	extends StartAPI {

	public abstract SystemConfigSetting getSystemConfig() throws FileNotFoundException;
	public abstract void updateSystemConfig(SystemConfigSetting systemconfigsetting) throws FileNotFoundException;
	public abstract String getWebServiceUrl();
	public abstract String getHospital();
	/**
	 * 获取客户信息
	 * param clientnumber 如果为""表示查找全部用户，如果有值就表示查询想对应的数据
	 */
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoList(String clientnumber);
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByCnameState(String customername,String state);
	public abstract DsfCustomerBaseInfo getCustomerInfoById(String customerid);
	public abstract void deleteCustomerInfo(String customerid);
	public abstract void saveCustomerInfo(DsfCustomerBaseInfo dcbi);
	/**
	 * 获取检验信息
	 * 
	 */
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByNo(String clientnumber,String customerid);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribe(String customerid);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribeByNo(String ylxh,String ylmc);
	public abstract void updateYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe);
	public abstract void addYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe);
	//检验项目显示
	
	/**
	 * 检验项目对照
	 * 
	 */
	
	public abstract List<DsfControltestitems> getControltestitemsByNo(String customeritems,String customeritemsname);
	//关联项目
	//public abstract List<DsfControltestitems> getRefItems();
	
}