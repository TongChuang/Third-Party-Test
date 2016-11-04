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
	private String address;
	private String email;
	private String name;
	private String password;
	private String passwordHint;
	private String phoneNumber;
	private String username;

	// Constructors

	/** default constructor */
	public LabUser() {
	}

	/** minimal constructor */
	public LabUser(BigDecimal id, Boolean accountExpired,
			Boolean accountLocked, Boolean credentialsExpired, String name,
			String password, String username) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.username = username;
	}

	/** full constructor */
	public LabUser(BigDecimal id, String address, String email, String name,
			String password, String passwordHint, String phoneNumber,
			String username) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
		this.name = name;
		this.password = password;
		this.passwordHint = passwordHint;
		this.phoneNumber = phoneNumber;
		this.username = username;
	}


	// Property accessors

	public BigDecimal getId() {
		return this.id;
	}

	
	public void setId(BigDecimal id) {
		this.id = id;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}




	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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



}