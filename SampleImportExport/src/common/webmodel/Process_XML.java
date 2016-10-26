/**
 * 
 */
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
public class Process_XML {
	// 采集人
	private String collectionpersonnel;
	// 采集时间
	private String collectiontime;
	// 条码号
	private String dsfbarcode;

	public Process_XML() {
		
	}

	public Process_XML(String collectionpersonnel, String collectiontime, String dsfbarcode) {
		this.collectionpersonnel = collectionpersonnel;
		this.collectiontime = collectiontime;
		this.dsfbarcode = dsfbarcode;
	}

	public String getCollectionpersonnel() {
		return collectionpersonnel;
	}

	public void setCollectionpersonnel(String collectionpersonnel) {
		this.collectionpersonnel = collectionpersonnel;
	}

	public String getCollectiontime() {
		return collectiontime;
	}

	public void setCollectiontime(String collectiontime) {
		this.collectiontime = collectiontime;
	}

	public String getDsfbarcode() {
		return dsfbarcode;
	}

	public void setDsfbarcode(String dsfbarcode) {
		this.dsfbarcode = dsfbarcode;
	}

}
