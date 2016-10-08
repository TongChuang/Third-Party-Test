package common.datamodel;

// default package

import java.math.BigDecimal;

/**
 * LProcessId entity. @author MyEclipse Persistence Tools
 */

public class LProcess implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private String executetime;
	private String executor;
	private String requester;
	private String requesttime;
	private BigDecimal sampleId;

	// Constructors

	/** default constructor */
	public LProcess() {
	}

	/** minimal constructor */
	public LProcess(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public LProcess(BigDecimal id, String executetime, String executor,
			String requester, String requesttime, BigDecimal sampleId) {
		this.id = id;
		this.executetime = executetime;
		this.executor = executor;
		this.requester = requester;
		this.requesttime = requesttime;
		this.sampleId = sampleId;
	}

	// Property accessors

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getExecutetime() {
		return this.executetime;
	}

	public void setExecutetime(String executetime) {
		this.executetime = executetime;
	}

	public String getExecutor() {
		return this.executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getRequester() {
		return this.requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getRequesttime() {
		return this.requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}

	public BigDecimal getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(BigDecimal sampleId) {
		this.sampleId = sampleId;
	}

}