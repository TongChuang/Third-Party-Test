package sysconf;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import com.sun.accessibility.internal.resources.accessibility;

import common.StartAPI;
import common.datamodel.DsfInspectionItemControl;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfTestobjective;
import common.datamodel.DsfTestitems;
import common.datamodel.LTestitem;
import common.datamodel.DsfTestCenterInfo;
import common.datamodel.LabUser;
import common.xmlmodel.SystemConfigSetting;

public interface SysConfApi
	extends StartAPI {

	public abstract SystemConfigSetting getSystemConfig() throws FileNotFoundException;
	public abstract void updateSystemConfig(SystemConfigSetting systemconfigsetting) throws FileNotFoundException;
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
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByCostomerId(String customerid);
	public abstract List<DsfTestobjective> getYlxhdescribe(String customerid);
	public abstract List<DsfTestobjective> getYlxhdescribeById(String id);
	public abstract List<DsfTestobjective> getYlxhdescribeByNo(String ylxh,String ylmc);
	public abstract void updateYlxhdescribe(DsfTestobjective lYlxhdescribe);
	public abstract void addYlxhdescribe(DsfTestobjective lYlxhdescribe);
	public abstract void deleteYlxhdescribe(BigDecimal id);
	public abstract List<DsfTestitems> getTestItemsByNo(String proList, String customerid);
	public abstract List<DsfTestitems> getTestItems();
	public abstract void saveTestObjective(String ylxh, String profiletest);
	public abstract String getSequence(String seqName);
	/**
	 * 检验项目对照
	 * 
	 */
	public abstract List<DsfInspectionItemControl> getControltestitemsByNo(String customeritems,String customeritemsname);
	public abstract List<DsfInspectionItemControl> getControltestitems(String customerid);
	public abstract List<DsfInspectionItemControl> getControltestitemsById(BigDecimal id);
	public abstract List<LTestitem> getLocalTestItems();
	public abstract void saveAll(List<DsfInspectionItemControl> dcttList);
	public abstract List<LTestitem> getLocalTestItemsByNo(String customerid);
	public abstract void saveData(Object t,String tableName);
	public abstract List<LTestitem> getTestItemsByIndexId(String indexId);
	
	/**
	 * 检验单位信息
	 * 
	 */
	
	public abstract List<DsfTestCenterInfo> getTestCenterInfoList();
	public abstract void deleteTestCenterInfo(BigDecimal id);
	public abstract void updateDsfTestCenterInfo(DsfTestCenterInfo dsftestcenterinfo);
	public abstract DsfTestCenterInfo getTestCenterInfoById(String id);
	
	/**
	 * 系统账号管理
	 */
	public abstract List<LabUser> getLabUserList();
	public abstract void deleteLabUser(BigDecimal id);
	public abstract void updateLabUser(LabUser labuser);
	public abstract LabUser getLabUserById(String id);
}