package common.datamodel;

import java.util.Date;

// default package

/**
 * DsfTestResult entity. @author MyEclipse Persistence Tools
 */

public class DsfTestResult implements java.io.Serializable {

	// Fields

	private String sampleno;
	private String testid;
	private Date measuretime;
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

	public DsfTestResult() {
	}

	public DsfTestResult(String sampleno, String testid, Date measuretime,
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

	public Date getMeasuretime() {
		return measuretime;
	}

	public void setMeasuretime(Date measuretime) {
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