/**
 * 
 */
package common.webmodel;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * @author zjn
 * @createTime 2016-9-6
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class SampleInfoList_XML {

	@XmlElement(name = "Process")
	private Process_XML process;

	@XmlElementWrapper(name = "TestItemInfoList")
	@XmlElement(name = "TestItemInfo")
	private List<TestItem_XML> testItem_XMLs;

	@XmlElement(name = "SampleInfo")
	private SampleInfo_XML sampleInfo;

	@XmlElementWrapper(name = "TestresultList")
	@XmlElement(name = "Testresult")
	private List<Testresult_Xml> testresult_Xmls;

	public SampleInfoList_XML() {
	}

	public SampleInfoList_XML(Process_XML process,
			List<TestItem_XML> testItem_XMLs, SampleInfo_XML sampleInfo,
			List<Testresult_Xml> testresult_Xmls) {
		this.process = process;
		this.testItem_XMLs = testItem_XMLs;
		this.sampleInfo = sampleInfo;
		this.testresult_Xmls = testresult_Xmls;
	}

	public List<TestItem_XML> getTestItem_XMLs() {
		return testItem_XMLs;
	}

	public void setTestItem_XMLs(List<TestItem_XML> testItem_XMLs) {
		this.testItem_XMLs = testItem_XMLs;
	}

	public List<Testresult_Xml> getTestresult_Xmls() {
		return testresult_Xmls;
	}

	public void setTestresult_Xmls(List<Testresult_Xml> testresult_Xmls) {
		this.testresult_Xmls = testresult_Xmls;
	}

	public Process_XML getProcess() {
		return process;
	}

	public void setProcess(Process_XML process) {
		this.process = process;
	}

	public SampleInfo_XML getSampleInfo() {
		return sampleInfo;
	}

	public void setSampleInfo(SampleInfo_XML sampleInfo) {
		this.sampleInfo = sampleInfo;
	}

}
