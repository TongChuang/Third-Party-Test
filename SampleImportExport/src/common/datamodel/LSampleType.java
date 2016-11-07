package common.datamodel;

/**
 * LSampleType entity. @author MyEclipse Persistence Tools
 */

public class LSampleType implements java.io.Serializable {

	// Fields

	private String id;
	private String sampleTypeName;
	private String sampleTypeId;

	// Constructors

	/** default constructor */
	public LSampleType() {
	}

	/** minimal constructor */
	public LSampleType(String id) {
		this.id = id;
	}

	/** full constructor */
	public LSampleType(String id, String sampleTypeName, String sampleTypeId) {
		this.id = id;
		this.sampleTypeName = sampleTypeName;
		this.sampleTypeId = sampleTypeId;
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
	@Override
	public String toString() {
		return "LSampleType [id=" + id + ", sampleTypeName=" + sampleTypeName
				+ ", sampleTypeId=" + sampleTypeId + "]";
	}
	
}