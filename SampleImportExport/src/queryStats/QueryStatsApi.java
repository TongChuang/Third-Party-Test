/**
 * 
 */
package queryStats;

import java.util.List;

import common.StartAPI;
import common.datamodel.DsfCustomerBarCode;
import common.datamodel.DsfCustomerBaseInfo;

/**
 * @author zjn
 * @createTime 2016-10-3
 */
public interface QueryStatsApi extends StartAPI {
	
	public abstract List<DsfCustomerBaseInfo> getBaseCustomerInfo() ;
	public abstract List<DsfCustomerBarCode> getNowCode(String customerid);
	public abstract void saveBarCode(DsfCustomerBarCode dsfCustomerBarCode);
}
