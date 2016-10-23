package sysconf;

import common.SIEContext;
import common.util.CommonUtil;
import common.xmlmodel.*;
import sysconf.xml.XmlReader;
import sysconf.xml.XmlReaderImpl;
import java.io.FileNotFoundException;
import org.apache.log4j.Logger;

public class SysConfHandler {

	private Logger logger = null;

	public SysConfHandler() {
		logger = null;
		logger = CommonUtil.getLogger();
	}

	public void initModule() {
		System.out.println("**************init sysconf module**********************");
		initSystemConfigTable();
	}



	private void initSystemConfigTable() {
		System.out.println("**************init system-config-table**********************");
		String warHome = CommonUtil.getWarHome();
		XmlReader reader = new XmlReaderImpl((new StringBuilder(String.valueOf(warHome))).append("/xml/").toString());
		SystemConfigTable systemConfigTable = null;
		try {
			systemConfigTable = (SystemConfigTable)reader.getXmlConfig("system-config");
		}
		catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		SIEContext.setSystemConfigTable(systemConfigTable);
	}

	

	public String getWebServiceUrl() {
		SystemConfigSetting setting = (SystemConfigSetting)SIEContext.getSystemConfigTable().getConfigs().get(0);
		String webserviceUrl = "";
		webserviceUrl = setting.getWebserviceUrl();
		return webserviceUrl;
	}
	
	

}