package inspection;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import common.StartAPI;			
import common.datamodel.DsfCustomerBaseInfo;
import common.datamodel.DsfLYlxhdescribe;
import common.datamodel.DsfTestitems;
import common.datamodel.LProcess;
import common.datamodel.LSample;
import common.datamodel.LTestresult;


public interface InspectionApi
	extends StartAPI {
	//检验信息
	public abstract List<DsfCustomerBaseInfo> getCustomerInfoByNo(String clientnumber,String customerid);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribe(String customerid);
	public abstract List<DsfLYlxhdescribe> getYlxhdescribeByNo(String ylxh,String ylmc);
	public abstract void updateYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe);
	public abstract void addYlxhdescribe(DsfLYlxhdescribe lYlxhdescribe);
	//检验项目显示
	
	
	//检验项目对照
	public abstract List <DsfLYlxhdescribe> getYlxhdescribeByYlxh(String ylxh,String customerid);
	
}