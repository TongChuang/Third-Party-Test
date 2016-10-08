package common.datamodel;
// default package

import java.math.BigDecimal;

/**
 * LabUser entity. @author MyEclipse Persistence Tools
 */

public class LabUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -43075296170313656L;
	private BigDecimal id;
	private Boolean accountExpired;
	private Boolean accountLocked;
	private String activecode;
	private String address;
	private String city;
	private String country;
	private String postalCode;
	private String province;
	private Long checknum;
	private Long collectnum;
	private Boolean credentialsExpired;
	private String department;
	private String email;
	private Boolean accountEnabled;
	private Long evaluatenum;
	private BigDecimal hospitalId;
	private String lastlab;
	private String lastprofile;
	private String name;
	private String password;
	private String passwordHint;
	private String pbsection;
	private String phoneNumber;
	private String username;
	private Long version;
	private String website;

	// Constructors

	/** default constructor */
	public LabUser() {
	}

	/** minimal constructor */
	public LabUser(BigDecimal id, Boolean accountExpired,
			Boolean accountLocked, Boolean credentialsExpired, String name,
			String password, String username) {
		this.id = id;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
		this.credentialsExpired = credentialsExpired;
		this.name = name;
		this.password = password;
		this.username = username;
	}

	/** full constructor */
	public LabUser(BigDecimal id, Boolean accountExpired,
			Boolean accountLocked, String activecode, String address,
			String city, String country, String postalCode, String province,
			Long checknum, Long collectnum, Boolean credentialsExpired,
			String department, String email, Boolean accountEnabled,
			Long evaluatenum, BigDecimal hospitalId, 
			String lastlab, String lastprofile, String name, String password,
			String passwordHint, String pbsection, String phoneNumber,
			String username, Long version, String website) {
		this.id = id;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
		this.activecode = activecode;
		this.address = address;
		this.city = city;
		this.country = country;
		this.postalCode = postalCode;
		this.province = province;
		this.checknum = checknum;
		this.collectnum = collectnum;
		this.credentialsExpired = credentialsExpired;
		this.department = department;
		this.email = email;
		this.accountEnabled = accountEnabled;
		this.evaluatenum = evaluatenum;
		this.hospitalId = hospitalId;
		this.lastlab = lastlab;
		this.lastprofile = lastprofile;
		this.name = name;
		this.password = password;
		this.passwordHint = passwordHint;
		this.pbsection = pbsection;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.version = version;
		this.website = website;
	}

	// Property accessors

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Boolean getAccountExpired() {
		return this.accountExpired;
	}

	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public Boolean getAccountLocked() {
		return this.accountLocked;
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public String getActivecode() {
		return this.activecode;
	}

	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Long getChecknum() {
		return this.checknum;
	}

	public void setChecknum(Long checknum) {
		this.checknum = checknum;
	}

	public Long getCollectnum() {
		return this.collectnum;
	}

	public void setCollectnum(Long collectnum) {
		this.collectnum = collectnum;
	}

	public Boolean getCredentialsExpired() {
		return this.credentialsExpired;
	}

	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAccountEnabled() {
		return this.accountEnabled;
	}

	public void setAccountEnabled(Boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	public Long getEvaluatenum() {
		return this.evaluatenum;
	}

	public void setEvaluatenum(Long evaluatenum) {
		this.evaluatenum = evaluatenum;
	}

	public BigDecimal getHospitalId() {
		return this.hospitalId;
	}

	public void setHospitalId(BigDecimal hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getLastlab() {
		return this.lastlab;
	}

	public void setLastlab(String lastlab) {
		this.lastlab = lastlab;
	}

	public String getLastprofile() {
		return this.lastprofile;
	}

	public void setLastprofile(String lastprofile) {
		this.lastprofile = lastprofile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordHint() {
		return this.passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getPbsection() {
		return this.pbsection;
	}

	public void setPbsection(String pbsection) {
		this.pbsection = pbsection;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}


}