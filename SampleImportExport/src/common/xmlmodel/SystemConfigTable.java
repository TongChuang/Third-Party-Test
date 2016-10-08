package common.xmlmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SystemConfigTable
	implements Serializable {

	private static final long serialVersionUID = 0x8e52fb7c7f3a5fabL;
	public static final String fileName = "system-config";
	private List configs = null;

	public SystemConfigTable() {
		configs = null;
	}

	public SystemConfigTable(List configs) {
		this.configs = null;
		this.configs = configs;
	}

	public List getConfigs() {
		return configs;
	}

	public void setConfigs(List configs) {
		this.configs = configs;
		if (this.configs == null)
			this.configs = new ArrayList();
	}
}