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
 * @createTime 2016-9-6
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SampleData")
@XmlType(propOrder = {})
public class DataSet_XML {
	
	@XmlElement(name="SampleInfoList")
	private List<SampleInfoList_XML> sXmlList ;

	public DataSet_XML() {
	}

	public DataSet_XML(List<SampleInfoList_XML> sXmlList) {
		this.sXmlList = sXmlList;
	}

	public List<SampleInfoList_XML> getsXmlList() {
		return sXmlList;
	}

	public void setsXmlList(List<SampleInfoList_XML> sXmlList) {
		this.sXmlList = sXmlList;
	}
	
	
}
