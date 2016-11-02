package common.datamodel;

import java.math.BigDecimal;

/**
 * DsfControltestitems entity. @author MyEclipse Persistence Tools
 */

public class DsfInspectionItemControl implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private String customerid;
	private String localitems;
	private String customeritems;
	private String customeritemsname;
	private String localitemsname;

	// Constructors

	/** default constructor */
	public DsfInspectionItemControl() {
	}

	/** minimal constructor */
	public DsfInspectionItemControl(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public DsfInspectionItemControl(BigDecimal id, String customerid,
			String localitems, String customeritems, String customeritemsname,
			String localitemsname) {
		this.id = id;
		this.customerid = customerid;
		this.localitems = localitems;
		this.customeritems = customeritems;
		this.customeritemsname = customeritemsname;
		this.localitemsname = localitemsname;
	}

	// Property accessors

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getLocalitems() {
		return this.localitems;
	}

	public void setLocalitems(String localitems) {
		this.localitems = localitems;
	}

	public String getCustomeritems() {
		return this.customeritems;
	}

	public void setCustomeritems(String customeritems) {
		this.customeritems = customeritems;
	}

	public String getCustomeritemsname() {
		return this.customeritemsname;
	}

	public void setCustomeritemsname(String customeritemsname) {
		this.customeritemsname = customeritemsname;
	}

	public String getLocalitemsname() {
		return this.localitemsname;
	}

	public void setLocalitemsname(String localitemsname) {
		this.localitemsname = localitemsname;
	}

	@Override
	public String toString() {
		return "DsfControltestitems [id=" + id + ", customerid=" + customerid
				+ ", localitems=" + localitems + ", customeritems="
				+ customeritems + ", customeritemsname=" + customeritemsname
				+ ", localitemsname=" + localitemsname + "]";
	}

}