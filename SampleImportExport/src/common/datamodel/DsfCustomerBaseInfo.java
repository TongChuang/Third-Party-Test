package common.datamodel;

import java.math.BigDecimal;

/**
 * DsfCustomerBaseInfo entity. @author MyEclipse Persistence Tools
 */

public class DsfCustomerBaseInfo implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private String customername;
	private String address;
	private String customerid;
	private String currentbarcode;
	private String customerKey;
	private String basicinfostate ;

	// Constructors

	/** default constructor */
	public DsfCustomerBaseInfo() {
	}

	public DsfCustomerBaseInfo(BigDecimal id, String customername, String address, String customerid, String currentbarcode, String customerKey,
			String basicinfostate) {
		this.id = id;
		this.customername = customername;
		this.address = address;
		this.customerid = customerid;
		this.currentbarcode = currentbarcode;
		this.customerKey = customerKey;
		this.basicinfostate = basicinfostate;
	}



	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCurrentbarcode() {
		return currentbarcode;
	}

	public void setCurrentbarcode(String currentbarcode) {
		this.currentbarcode = currentbarcode;
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

	public String getBasicinfostate() {
		return basicinfostate;
	}

	public void setBasicinfostate(String basicinfostate) {
		this.basicinfostate = basicinfostate;
	}

	@Override
	public String toString() {
		return "DsfCustomerBaseInfo [id=" + id + ", customername="
				+ customername + ", address=" + address + ", customerid="
				+ customerid + ", currentbarcode=" + currentbarcode
				+ ", customerKey=" + customerKey + ", basicinfostate="
				+ basicinfostate + "]";
	}

	// Property accessors
	

}