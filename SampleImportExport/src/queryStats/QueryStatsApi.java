/**
 * 
 */
package queryStats;

import java.util.List;	

import common.StartAPI;
import common.datamodel.DsfCustomerBaseInfo;

/**
 * @author zjn
 * @createTime 2016-10-3
 */
public interface QueryStatsApi extends StartAPI {
	
	public abstract List<DsfCustomerBaseInfo> getBaseCustomerInfo(String customerid) ;
	public abstract void saveData(DsfCustomerBaseInfo dcbi,String tableName) ;
	public abstract void saveAllData(List objectList,String tableName) ;
}
