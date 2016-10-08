package common.xmlmodel;

import java.io.Serializable;

public class SystemConfigSetting implements Serializable {

	private static final long serialVersionUID = 0x37bdd4b3818bdd0fL;
	private String hospital = null;
	private String bloodbank = null;
	private String webserviceUrl = null;
	private String accessCode = null;
	private String userId = null;

	public static final String STR_HOSPITAL = "医院";
	public static final String STR_BLOODBANK = "血站";
	public static final String STR_WebServiceUrl = "血站接口地址";
	public static final String STR_UserId = "接入用户ID";
	public static final String STR_AccessCode = "接入验证码";

	public SystemConfigSetting(String hospital, String bloodbank,
			String webserviceUrl, String accessCode, String userId) {
		this.hospital = hospital;
		this.webserviceUrl = webserviceUrl;
		this.accessCode = accessCode;
		this.userId = userId;
		this.bloodbank = bloodbank;
	}

	public SystemConfigSetting() {
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getWebserviceUrl() {
		return webserviceUrl;
	}

	public void setWebserviceUrl(String webserviceUrl) {
		this.webserviceUrl = webserviceUrl;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBloodbank() {
		return bloodbank;
	}

	public void setBloodbank(String bloodbank) {
		this.bloodbank = bloodbank;
	}

}