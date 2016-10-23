package common.xmlmodel;

import java.io.Serializable;

public class SystemConfigSetting implements Serializable {

	private static final long serialVersionUID = 0x37bdd4b3818bdd0fL;
	private String defaultPassword = null;
	private String updateServerAddress = null;
	private String ftpRoot = null;
	private String webserviceUrl = null;

	public static final String STR_DEFAULT_PASSWORD = "帐户默认密码";
	public static final String Update_Server_Address = "更新服务器地址";
	public static final String STR_ftp_ROOT = "ftp地址";
	public static final String Web_Service_Url = "本机webservice地址";

	public SystemConfigSetting(String defaultPassword, String updateServerAddress, String ftpRoot,String webserviceUrl) {
		this.defaultPassword = defaultPassword;
		this.updateServerAddress = updateServerAddress;
		this.ftpRoot = ftpRoot;
		this.webserviceUrl = webserviceUrl;
	}

	public SystemConfigSetting() {
	}

	public String getWebserviceUrl() {
		return webserviceUrl;
	}

	public void setWebserviceUrl(String webserviceUrl) {
		this.webserviceUrl = webserviceUrl;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	public String getUpdateServerAddress() {
		return updateServerAddress;
	}

	public void setUpdateServerAddress(String updateServerAddress) {
		this.updateServerAddress = updateServerAddress;
	}

	public String getFtpRoot() {
		return ftpRoot;
	}

	public void setFtpRoot(String ftpRoot) {
		this.ftpRoot = ftpRoot;
	}

}