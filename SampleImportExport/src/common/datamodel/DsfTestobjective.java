package common.datamodel;
// default package

import java.math.BigDecimal;

/**
 * DsfTestobjective entity. @author MyEclipse Persistence Tools
 */

public class DsfTestobjective implements java.io.Serializable {

	// Fields
	private BigDecimal id;
	private String ylxh;
	private String profiletest;
	private String profiletest2;
	private String profiletest3;
	private String ylmc;
	private String customerid;
	private String inspectionsection;
	private String professionalgroup;

	// Constructors

	/** default constructor */
	public DsfTestobjective() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public DsfTestobjective(BigDecimal id, String ylxh, String profiletest,
			String profiletest2, String profiletest3, String ylmc,
			String customerid, String inspectionsection,
			String professionalgroup, String serialnumber) {
		super();
		this.id = id;
		this.ylxh = ylxh;
		this.profiletest = profiletest;
		this.profiletest2 = profiletest2;
		this.profiletest3 = profiletest3;
		this.ylmc = ylmc;
		this.customerid = customerid;
		this.inspectionsection = inspectionsection;
		this.professionalgroup = professionalgroup;
	}

	public DsfTestobjective(BigDecimal id, String ylxh, 
			String profiletest, String profiletest2, String profiletest3,
			String ylmc, String customerid) {
		super();
		this.id = id;
		this.ylxh = ylxh;
		this.profiletest = profiletest;
		this.profiletest2 = profiletest2;
		this.profiletest3 = profiletest3;
		this.ylmc = ylmc;
		this.customerid = customerid;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getYlxh() {
		return this.ylxh;
	}

	public void setYlxh(String ylxh) {
		this.ylxh = ylxh;
	}

	public String getProfiletest() {
		return this.profiletest;
	}

	public void setProfiletest(String profiletest) {
		this.profiletest = profiletest;
	}

	public String getProfiletest2() {
		return this.profiletest2;
	}

	public void setProfiletest2(String profiletest2) {
		this.profiletest2 = profiletest2;
	}

	public String getProfiletest3() {
		return this.profiletest3;
	}

	public void setProfiletest3(String profiletest3) {
		this.profiletest3 = profiletest3;
	}

	public String getYlmc() {
		return this.ylmc;
	}

	public void setYlmc(String ylmc) {
		this.ylmc = ylmc;
	}


	public String getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getInspectionsection() {
		return inspectionsection;
	}

	public void setInspectionsection(String inspectionsection) {
		this.inspectionsection = inspectionsection;
	}

	public String getProfessionalgroup() {
		return professionalgroup;
	}

	public void setProfessionalgroup(String professionalgroup) {
		this.professionalgroup = professionalgroup;
	}


	@Override
	public String toString() {
		return "DsfTestobjective [id=" + id + ", ylxh=" + ylxh
				+ ", profiletest=" + profiletest + ", profiletest2="
				+ profiletest2 + ", profiletest3=" + profiletest3 + ", ylmc="
				+ ylmc + ", customerid=" + customerid + ", inspectionsection="
				+ inspectionsection + ", professionalgroup="
				+ professionalgroup + ", serialnumber=" + "]";
	}
}