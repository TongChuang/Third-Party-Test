/**
 * 
 */
package common.webmodel;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author zjn
 * @createTime 2016-9-22
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class TestObjective_XML {

	private String ylxh;
	private String profiletest;
	private String ylmc;

	

	public TestObjective_XML( String ylxh, String profiletest,
		 String ylmc) {
		this.ylxh = ylxh;
		this.profiletest = profiletest;
		this.ylmc = ylmc;
	}

	public TestObjective_XML() {
	}


	public String getYlxh() {
		return ylxh;
	}

	public void setYlxh(String ylxh) {
		this.ylxh = ylxh;
	}

	public String getProfiletest() {
		return profiletest;
	}

	public void setProfiletest(String profiletest) {
		this.profiletest = profiletest;
	}

	public String getYlmc() {
		return ylmc;
	}

	public void setYlmc(String ylmc) {
		this.ylmc = ylmc;
	}


}
