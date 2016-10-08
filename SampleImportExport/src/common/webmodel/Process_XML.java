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
	// 开单人
	private String requester;
	// 开单时间
	private String requesttime;
	// 采集人
	private String executor;
	// 采集时间
	private String executetime;
	// 条码号
	private String dsfbarcode;

	public Process_XML() {
		
	}

	public Process_XML(String requester, String requesttime, String executor,
			String dsfbarcode, String executetime) {
		this.requester = requester;
		this.requesttime = requesttime;
		this.executor = executor;
		this.executetime = executetime;
		this.dsfbarcode = dsfbarcode;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getExecutetime() {
		return executetime;
	}

	public void setExecutetime(String executetime) {
		this.executetime = executetime;
	}

	public String getDsfbarcode() {
		return dsfbarcode;
	}

	public void setDsfbarcode(String dsfbarcode) {
		this.dsfbarcode = dsfbarcode;
	}

}
