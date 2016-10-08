package security;

import common.datamodel.*;
import dataaccess.DataAccessApi;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import security.SecurityHandler;


public class SecurityApiImpl implements SecurityApi {

	private DataAccessApi dataAccessApi = null;
	private boolean inited = false;
	private SecurityHandler handler = null;

	public void init() {
		handler.initModule();
		inited = true;
	}

	public boolean inited() {
		return inited;
	}

	public void shutdown() {
	}

	public void setHandler(SecurityHandler handler) {
		this.handler = handler;
	}

	public DataAccessApi getDataAccessApi() {
		return dataAccessApi;
	}

	public void setDataAccessApi(DataAccessApi dataAccessApi) {
		this.dataAccessApi = dataAccessApi;
	}

	public boolean isInited() {
		return inited;
	}

	public void setInited(boolean inited) {
		this.inited = inited;
	}
	  
	public int checkUser(String userName, String password) {
		if (userName != null && userName.length() > 0 && password != null
				&& password.length() > 0) {
			LabUser user = dataAccessApi.getUserByUserName(userName);
			if (user == null){
				return 2;
			}
			//加密的密码验证
			BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
			if (!encode.matches(password, user.getPassword())){
				return 3;
			}
		}
		return 1;
	}

	public void removeOfflineUser(HttpSession session){
		session.removeAttribute("userName");
		session.invalidate();
	}
}