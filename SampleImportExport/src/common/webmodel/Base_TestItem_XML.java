package common.webmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author zjn
 * @createTime 2016-9-6
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class Base_TestItem_XML {
	private String testitem;
	private String name;
	private String customerid;

	public Base_TestItem_XML() {
	}

	public Base_TestItem_XML(String testitem, String name) {
		this.testitem = testitem;
		this.name = name;
	}

	public String getTestitem() {
		return testitem;
	}

	public void setTestitem(String testitem) {
		this.testitem = testitem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	@Override
	public String toString() {
		return "Base_TestItem_XML [testitem=" + testitem + ", name=" + name
				+ ", customerid=" + customerid + "]";
	}

	
}
