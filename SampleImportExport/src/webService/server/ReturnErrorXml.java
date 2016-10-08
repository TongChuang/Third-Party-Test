/**
 * 
 */
package webService.server;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
/**
 * @author zjn
 * @createTime 2016-9-14
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ErrorXml")  
@XmlType(  propOrder = { })
public class ReturnErrorXml {
	public String code;
	public String msg;
	public String content;

	public ReturnErrorXml(String code, String msg, String content) {
		this.code = code;
		this.msg = msg;
		this.content = content;
	}

	public ReturnErrorXml() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
