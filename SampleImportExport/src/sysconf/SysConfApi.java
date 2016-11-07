package sysconf;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import common.StartAPI;
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfInspectionItemControl;
import common.datamodel.DsfSampleTypeControl;
import common.datamodel.DsfTestitems;
import common.datamodel.DsfTestobjective;
import common.datamodel.LSampleType;
import common.datamodel.LTestitem;
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
	 * 样本类型对照
	 */
	public abstract List<LSampleType> getLSampleTypeById(String id);
	public abstract void saveAllDsfSampleTypeControl(List<DsfSampleTypeControl> dstList);
	public abstract List<DsfSampleTypeControl> getDsfSampleTypeControlByCustomerId(String customerid);
	
}