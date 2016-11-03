package common.datamodel;

import java.math.BigDecimal;

/**
 * DsfCustomerBaseInfo entity. @author MyEclipse Persistence Tools
 */

public class DsfTestCenterInfo implements java.io.Serializable {

	// Fields
	private BigDecimal id;
	private String name;
	private String address;
	private Integer phone;
	
	
	public DsfTestCenterInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DsfTestCenterInfo(BigDecimal id, String name, String address,
			Integer phone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}


	public BigDecimal getId() {
		return id;
	}


	public void setId(BigDecimal id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Integer getPhone() {
		return phone;
	}


	public void setPhone(Integer phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "DsfTestCenterInfo [id=" + id + ", name=" + name + ", address="
				+ address + ", phone=" + phone + "]";
	}
	
	
	
}