package sysconf;

import common.StartAPI;		
import common.xmlmodel.SystemConfigSetting;
import java.io.FileNotFoundException;
import java.util.List;

public interface SysConfApi
	extends StartAPI {

	public abstract SystemConfigSetting getSystemConfig() throws FileNotFoundException;
	public abstract void updateSystemConfig(SystemConfigSetting systemconfigsetting) throws FileNotFoundException;
	public abstract String getWebServiceUrl();
	public abstract String getHospital();
}