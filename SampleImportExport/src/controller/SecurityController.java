package controller;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import security.SecurityApi;

import dataaccess.DataAccessApi;

public class SecurityController extends MultiActionController {
	private DataAccessApi dataAccessApi = null;
	private SecurityApi securityApi = null;

	public SecurityController() {
	}

	public void setDataAccessApi(DataAccessApi dataAccessApi) {
		this.dataAccessApi = dataAccessApi;
	}

	public void setSecurityApi(SecurityApi securityApi) {
		this.securityApi = securityApi;
	}

}