/**
 * 
 */
package queryStats;

import java.util.List;

import common.datamodel.DsfCustomerBaseInfo;

import dataaccess.DataAccessApi;

/**
 * @author zjn
 * @createTime 2016-10-3
 */
public class QueryStatsApiImpl implements QueryStatsApi {

	private boolean inited = false;
	private DataAccessApi dataAccessApi = null;

	public QueryStatsApiImpl() {
		inited = false;
	}

	public void setDataAccessApi(DataAccessApi dataAccessApi) {
		this.dataAccessApi = dataAccessApi;
	}
	
	
	public List<DsfCustomerBaseInfo> getBaseCustomerInfo(){
		return dataAccessApi.getBaseCustomerInfo();
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see common.StartAPI#init()
	 */
	@Override
	public void init() {
		inited = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see common.StartAPI#inited()
	 */
	@Override
	public boolean inited() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see common.StartAPI#shutdown()
	 */
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
