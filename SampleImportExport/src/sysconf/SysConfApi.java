package sysconf;

import common.StartAPI;		
import common.datamodel.DsfCustomerBaseInfo;
import common.xmlmodel.SystemConfigSetting;
import java.io.FileNotFoundException;
import java.util.List;

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
}