package common.datamodel;

/**
 * DsfSampleType entity. @author MyEclipse Persistence Tools
 */

public class DsfSampleType implements java.io.Serializable {

	// Fields

	private String id;
	private String sampleTypeName;
	private String sampleTypeId;
	private String customerId;

	// Constructors

	/** default constructor */
	public DsfSampleType() {
	}

	/** minimal constructor */
	public DsfSampleType(String id) {
		this.id = id;
	}

	/** full constructor */
	public DsfSampleType(String id, String sampleTypeName, String sampleTypeId,
			String customerId) {
		this.id = id;
		this.sampleTypeName = sampleTypeName;
		this.sampleTypeId = sampleTypeId;
		this.customerId = customerId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSampleTypeName() {
		return this.sampleTypeName;
	}

	public void setSampleTypeName(String sampleTypeName) {
		this.sampleTypeName = sampleTypeName;
	}

	public String getSampleTypeId() {
		return this.sampleTypeId;
	}

	public void setSampleTypeId(String sampleTypeId) {
		this.sampleTypeId = sampleTypeId;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "DsfSampleType [id=" + id + ", sampleTypeName=" + sampleTypeName
				+ ", sampleTypeId=" + sampleTypeId + ", customerId="
				+ customerId + "]";
	}

}