/**
 * 
 */
package common.webmodel;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author zjn
 * @createTime 2016-9-22
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "BaseTestData")
@XmlType(propOrder = {})
public class TestObjectives_XML {
	@XmlElement(name = "CustomerId")
	private String customerid;

	@XmlElementWrapper(name = "TestObjectiveList")
	@XmlElement(name = "TestObjective")
	private List<TestObjective_XML> testObjectList;

	@XmlElementWrapper(name = "TestItemInfoList")
	@XmlElement(name = "TestItemInfo")
	private List<Base_TestItem_XML> base_testitemList;

	public TestObjectives_XML() {
	}

	public TestObjectives_XML(String customerid,
			List<TestObjective_XML> testObjectList,
			List<Base_TestItem_XML> base_testitemList) {
		this.customerid = customerid;
		this.testObjectList = testObjectList;
		this.base_testitemList = base_testitemList;
	}

	public List<TestObjective_XML> getTestObjectList() {
		return testObjectList;
	}

	public void setTestObjectList(List<TestObjective_XML> testObjectList) {
		this.testObjectList = testObjectList;
	}

	public List<Base_TestItem_XML> getBase_testitemList() {
		return base_testitemList;
	}

	public void setBase_testitemList(List<Base_TestItem_XML> base_testitemList) {
		this.base_testitemList = base_testitemList;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

}
