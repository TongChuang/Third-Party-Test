package common;

import sysconf.SysConfApi;
import dataaccess.dao.*;
import dataaccess.DataAccessApi;
import dataaccess.dao.*;
import security.SecurityApi;
import updown.UpDownApi;
import webService.server.SIE_ServiceApi;

public class SIEBeanFactory {

	private static DataAccessApi dataAccessApi = null;
	private static SecurityApi securityApi = null;
	private static SysConfApi sysConfApi = null;
	private static UpDownApi upDownApi = null;
	private static SIE_ServiceApi serviceApi = null;
	
	public SIEBeanFactory() {
	}

	public static void initModules() {
		dataAccessApi = (DataAccessApi) getBean("dataAccessApi");
		securityApi = (SecurityApi) getBean("securityApi");
		sysConfApi = (SysConfApi) getBean("sysConfApi");
		upDownApi = (UpDownApi) getBean("upDownApi");
		serviceApi = (SIE_ServiceApi) getBean("serviceApi");
		dataAccessApi.init();
		securityApi.init();
		sysConfApi.init();
		upDownApi.init();
		serviceApi.init();
	}

	public static void shutDownModules() {
		securityApi.shutdown();
		dataAccessApi.shutdown();
		sysConfApi.shutdown();
		upDownApi.shutdown();
		serviceApi.shutdown();
	}

	public static Object getBean(String beanName) {
		return SIEContext.getSpringContext().getBean(beanName);
	}

	public static DataAccessApi getDataAccessApi() {
		return dataAccessApi;
	}

	public static SecurityApi getSecurityApi() {
		return securityApi;
	}

	public static SysConfApi getSysConfApi() {
		return sysConfApi;
	}
	
	public static UpDownApi getUpDownApi() {
		return upDownApi;
	}

	public static SecurityDao getSecurityDao() {
		return (SecurityDao) getBean("securityDao");
	}

	public static SysConfDao getSysConfDao() {
		return (SysConfDao) getBean("sysconfDao");
	}
	
	public static UpDownDao getUpDownDao(){
		return (UpDownDao) getBean("updownDao");
	}

	public static SIE_ServiceApi getServiceApi() {
		return serviceApi;
	}

}