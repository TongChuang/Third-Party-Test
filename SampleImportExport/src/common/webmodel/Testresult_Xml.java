package common.webmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// default package

/**
 * LTestresultId entity. @author MyEclipse Persistence Tools
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class Testresult_Xml implements java.io.Serializable {

	// Fields

	private String sampleno;
	private String testid;
	private String measuretime;
	private String operator;
	private String refhi;
	private String reflo;
	private String sampletype;
	private String testresult;
	private String unit;
	private String testname;
	private String method;
	private String customerid;
	private String dsfbarcode;

	public Testresult_Xml() {
	}

	public Testresult_Xml(String sampleno, String testid, String measuretime,
			String operator, String refhi, String reflo, String sampletype,
			String testresult, String unit, String testname, String method,
			String customerid, String dsfbarcode) {
		this.sampleno = sampleno;
		this.testid = testid;
		this.measuretime = measuretime;
		this.operator = operator;
		this.refhi = refhi;
		this.reflo = reflo;
		this.sampletype = sampletype;
		this.testresult = testresult;
		this.unit = unit;
		this.testname = testname;
		this.method = method;
		this.customerid = customerid;
		this.dsfbarcode = dsfbarcode;
	}

	public String getSampleno() {
		return sampleno;
	}

	public void setSampleno(String sampleno) {
		this.sampleno = sampleno;
	}

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}

	public String getMeasuretime() {
		return measuretime;
	}

	public void setMeasuretime(String measuretime) {
		this.measuretime = measuretime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRefhi() {
		return refhi;
	}

	public void setRefhi(String refhi) {
		this.refhi = refhi;
	}

	public String getReflo() {
		return reflo;
	}

	public void setReflo(String reflo) {
		this.reflo = reflo;
	}

	public String getSampletype() {
		return sampletype;
	}

	public void setSampletype(String sampletype) {
		this.sampletype = sampletype;
	}

	public String getTestresult() {
		return testresult;
	}

	public void setTestresult(String testresult) {
		this.testresult = testresult;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getDsfbarcode() {
		return dsfbarcode;
	}

	public void setDsfbarcode(String dsfbarcode) {
		this.dsfbarcode = dsfbarcode;
	}

}