package inspection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import common.datamodel.*;
import dataaccess.DataAccessApi;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import security.SecurityHandler;


public class InspectionApiImpl implements InspectionApi {

	private DataAccessApi dataAccessApi = null;
	private boolean inited = false;
	
	public DataAccessApi getDataAccessApi() {
		return dataAccessApi;
	}

	public void setDataAccessApi(DataAccessApi dataAccessApi) {
		this.dataAccessApi = dataAccessApi;
	}

	public void init() {
		inited = true;
	}

	public boolean inited() {
		return inited;
	}

	public void shutdown() {
	}
	//检验信息
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
	//检验项目对照
	@Override
	public List<DsfLYlxhdescribe> getYlxhdescribeByYlxh(String ylxh,
			String customerid) {
		// TODO Auto-generated method stub
		return null;
	}

}