package common.datamodel;

import java.math.BigDecimal;

/**
 * DsfCustomerBaseInfo entity. @author MyEclipse Persistence Tools
 */

public class DsfCustomerBaseInfo implements java.io.Serializable {

	// Fields

	private BigDecimal customerid;
	private String customername;
	private String address;
	private String clientnumber;
	private BigDecimal sequence;
	private String customerKey;

	// Constructors

	/** default constructor */
	public DsfCustomerBaseInfo() {
	}

	/** minimal constructor */
	public DsfCustomerBaseInfo(BigDecimal customerid) {
		this.customerid = customerid;
	}

	/** full constructor */
	public DsfCustomerBaseInfo(BigDecimal customerid, String customername,
			String customerKey, String address, String clientnumber,
			BigDecimal sequence) {
		this.customerid = customerid;
		this.customername = customername;
		this.address = address;
		this.clientnumber = clientnumber;
		this.sequence = sequence;
		this.customerKey = customerKey;
	}

	// Property accessors

	public BigDecimal getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(BigDecimal customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return this.customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClientnumber() {
		return this.clientnumber;
	}

	public void setClientnumber(String clientnumber) {
		this.clientnumber = clientnumber;
	}

	public BigDecimal getSequence() {
		return this.sequence;
	}

	public void setSequence(BigDecimal sequence) {
		this.sequence = sequence;
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

}