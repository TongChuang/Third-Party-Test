package sysconf;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import sysconf.xml.XmlWriter;
import sysconf.xml.XmlWriterImpl;

import common.SIEContext;
import common.datamodel.DsfInspectionItemControl;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfTestobjective;
import common.datamodel.DsfTestitems;
import common.datamodel.LTestitem;
import common.datamodel.DsfTestCenterInfo;
import common.util.CommonUtil;
import common.xmlmodel.SystemConfigSetting;
import common.xmlmodel.SystemConfigTable;

import dataaccess.DataAccessApi;



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

	
	public List<DsfCustomerBaseInfo> getCustomerInfoList(String  customerid){
		return dataAccessApi.getCustomerBaseInfoByCustomerId(customerid);
	}
	public List<DsfCustomerBaseInfo> getCustomerBaseInfoByCustomerId(String clientnumber){
		return dataAccessApi.getCustomerBaseInfoByCustomerId(clientnumber);
	}	
	public DsfCustomerBaseInfo getCustomerInfoById(String customerid){
		return dataAccessApi.getCustomerInfoById(customerid);
	}
	public List<DsfCustomerBaseInfo> getCustomerInfoByName(String customername){
		return dataAccessApi.getCustomerInfoByName(customername);
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
	public  List<DsfCustomerBaseInfo> getCustomerInfoByCostomerId(String customerid){
		return dataAccessApi.getCustomerInfoByCustomerId(customerid);
	}
	@Override
	public  List<DsfTestobjective> getYlxhdescribe(String customerid){
		return dataAccessApi.getYlxhdescribe(customerid);
	}
	public List<DsfTestobjective> getYlxhdescribeById(String id){
		return dataAccessApi.getYlxhdescribeById(id);
	}
	@Override
	public  List<DsfTestobjective> getYlxhdescribeByNo(String ylxh,String ylmc){
		return dataAccessApi.getYlxhdescribeByNo(ylxh,ylmc);
	}
	@Override
	public  void updateYlxhdescribe(DsfTestobjective lYlxhdescribe){
		dataAccessApi.updateYlxhdescribe(lYlxhdescribe );
	}
	@Override
	public  void addYlxhdescribe(DsfTestobjective lYlxhdescribe){
		dataAccessApi.addYlxhdescribe(lYlxhdescribe);
	}
	@Override
	public void deleteYlxhdescribe(BigDecimal id){
		dataAccessApi.deleteYlxhdescribe(id);
	}
	@Override
	public List<DsfTestitems> getTestItemsByNo(String proList, String customerid){
		return dataAccessApi.getTestItemsByNo(proList,customerid);
	}
	@Override
	public List<DsfTestitems> getTestItems(){
		return dataAccessApi.getTestItems();
	}
	@Override
	public void saveTestObjective(String ylxh, String profiletest){
		dataAccessApi.saveTestObjective(ylxh, profiletest);
	}
	@Override
	public String getSequence(String seqName){
		return dataAccessApi.getSequence(seqName);
	}
	/**
	 * 检验项目对照
	 */
	@Override
	public List<DsfInspectionItemControl> getControltestitemsByNo(String customeritems,String customeritemsname){
		return dataAccessApi.getControltestitemsByNo(customeritems,customeritemsname);
	}
	@Override
	public List<DsfInspectionItemControl> getControltestitems(String customerid){
		return dataAccessApi.getControltestitems(customerid);
	}
	@Override
	public List<DsfInspectionItemControl> getControltestitemsById(BigDecimal id){
		return dataAccessApi.getControltestitemsById(id);
	}
	@Override
	public List<LTestitem> getLocalTestItems(){
		return dataAccessApi.getLocalTestItems();
	}
	@Override
	public List<LTestitem> getLocalTestItemsByNo(String customerid){
		return dataAccessApi.getLocalTestItemsByNo(customerid);
	}
	
	@Override
	public void saveAll(List<DsfInspectionItemControl> dcttList){
		dataAccessApi.saveAll(dcttList);
	}
	@Override
	public void saveData(Object t,String tableName){
		dataAccessApi.saveData(t,tableName);
	}
	@Override
	public List<LTestitem> getTestItemsByIndexId(String indexId){
		return dataAccessApi.getTestItemsByIndexId(indexId);
	}
	public List<DsfTestCenterInfo> getTestCenterInfoList(){
		return dataAccessApi.getTestCenterInfoList();
	}
	public void deleteTestCenterInfo(BigDecimal id){
		dataAccessApi.deleteTestCenterInfo(id);
	}
	public void updateDsfTestCenterInfo(DsfTestCenterInfo dsftestcenterinfo){
		dataAccessApi.updateDsfTestCenterInfo(dsftestcenterinfo );
	}
	public DsfTestCenterInfo getTestCenterInfoById(String id){
		return dataAccessApi.getTestCenterInfoById(id);
	}
}
