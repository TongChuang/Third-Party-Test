package sysconf;

import common.SIEContext;
import common.datamodel.DsfControltestitems;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.util.CommonUtil;
import common.xmlmodel.SystemConfigSetting;
import common.xmlmodel.SystemConfigTable;
import dataaccess.DataAccessApi;
import sysconf.xml.XmlWriter;
import sysconf.xml.XmlWriterImpl;
import java.io.FileNotFoundException;
import java.util.*;



public class SysConfApiImpl
	implements SysConfApi {

	private DataAccessApi dataAccessApi = null;
	private SysConfHandler handler = null;
	private boolean inited = false;

	public SysConfApiImpl() {
		dataAccessApi = null;
		handler = null;
		inited = false;
	}

	public void init() {
		handler.initModule();
		inited = true;
	}
	
	public boolean inited() {
		return inited;
	}

	public void shutdown() {
	}

	public DataAccessApi getDataAccessApi() {
		return dataAccessApi;
	}

	public void setDataAccessApi(DataAccessApi dataAccessApi) {
		this.dataAccessApi = dataAccessApi;
	}

	public SysConfHandler getHandler() {
		return handler;
	}

	public void setHandler(SysConfHandler handler) {
		this.handler = handler;
	}

	public SystemConfigSetting getSystemConfig() throws FileNotFoundException {
		SystemConfigTable systemConfigTable = SIEContext.getSystemConfigTable();
		SystemConfigSetting config = (SystemConfigSetting)systemConfigTable.getConfigs().get(0);
		return config;
	}

	public void updateSystemConfig(SystemConfigSetting config) throws FileNotFoundException {
		String warHome = CommonUtil.getWarHome();
		XmlWriter writer = new XmlWriterImpl((new StringBuilder(String.valueOf(warHome))).append("/xml/").toString());
		SystemConfigTable systemConfigTable = new SystemConfigTable();
		List configs = new ArrayList();
		configs.add(config);
		systemConfigTable.setConfigs(configs);
		writer.setXmlConfig("system-config", systemConfigTable);
		SIEContext.setSystemConfigTable(systemConfigTable);
	}

	public String getWebServiceUrl() {
		return handler.getWebServiceUrl();
	}
	
	public List<DsfCustomerBaseInfo> getCustomerInfoList(String  clientnumber){
		return dataAccessApi.getCustomerInfoList(clientnumber);
	}
	public DsfCustomerBaseInfo getCustomerInfoById(String customerid){
		return dataAccessApi.getCustomerInfoById(customerid);
	}
	
	public void saveCustomerInfo(DsfCustomerBaseInfo dcbi){
		dataAccessApi.saveCustomerBaseInfo(dcbi);
	}
	public void deleteCustomerInfo(String customerid){
		dataAccessApi.deleteCustomerInfo(customerid);
	}
	
	public List<DsfCustomerBaseInfo> getCustomerInfoByCnameState(String customername,String state) {
		return dataAccessApi.getCustomerInfoByCnameState(customername,state);
	}
	/**
	 * 检验信息
	 */
	@Override
	public  List<DsfCustomerBaseInfo> getCustomerInfoByNo(String clientnumber,String customerid){
		return dataAccessApi.getCustomerInfoByNo(clientnumber,customerid);
	}
	@Override
	public  List<DsfLYlxhdescribe> getYlxhdescribe(String customerid){
		return dataAccessApi.getYlxhdescribe(customerid);
	}
	@Override
	public  List<DsfLYlxhdescribe> getYlxhdescribeByNo(String ylxh,String ylmc){
		return dataAccessApi.getYlxhdescribeByNo(ylxh,ylmc);
	}
	@Override
	public  void updateYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe){
		dataAccessApi.updateYlxhdescribe(lYlxhdescribe );
	}
	@Override
	public  void addYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe){
		dataAccessApi.addYlxhdescribe(lYlxhdescribe);
	}
	/**
	 * 检验项目对照
	 */
	@Override
	public List<DsfControltestitems> getControltestitemsByNo(String customeritems,String customeritemsname){
		return dataAccessApi.getControltestitemsByNo(customeritems,customeritemsname);
	}
}
