package common.datamodel;

/**
 * DsfSampleTypeControl entity. @author MyEclipse Persistence Tools
 */

public class DsfSampleTypeControl implements java.io.Serializable {

	// Fields

	private String id;
	private String dsfSampleTypeId;
	private String dsfSampleTypeName;
	private String lSampleTypeId;
	private String lSampleTypeName;
	private String customerId;

	// Constructors

	/** default constructor */
	public DsfSampleTypeControl() {
	}

	/** minimal constructor */
	public DsfSampleTypeControl(String id) {
		this.id = id;
	}

	/** full constructor */
	public DsfSampleTypeControl(String id, String dsfSampleTypeId,
			String dsfSampleTypeName, String lSampleTypeId,
			String lSampleTypeName, String customerId) {
		this.id = id;
		this.dsfSampleTypeId = dsfSampleTypeId;
		this.dsfSampleTypeName = dsfSampleTypeName;
		this.lSampleTypeId = lSampleTypeId;
		this.lSampleTypeName = lSampleTypeName;
		this.customerId = customerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDsfSampleTypeId() {
		return dsfSampleTypeId;
	}

	public void setDsfSampleTypeId(String dsfSampleTypeId) {
		this.dsfSampleTypeId = dsfSampleTypeId;
	}

	public String getDsfSampleTypeName() {
		return dsfSampleTypeName;
	}

	public void setDsfSampleTypeName(String dsfSampleTypeName) {
		this.dsfSampleTypeName = dsfSampleTypeName;
	}

	public String getlSampleTypeId() {
		return lSampleTypeId;
	}

	public void setlSampleTypeId(String lSampleTypeId) {
		this.lSampleTypeId = lSampleTypeId;
	}

	public String getlSampleTypeName() {
		return lSampleTypeName;
	}

	public void setlSampleTypeName(String lSampleTypeName) {
		this.lSampleTypeName = lSampleTypeName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "DsfSampleTypeControl [id=" + id + ", dsfSampleTypeId="
				+ dsfSampleTypeId + ", dsfSampleTypeName=" + dsfSampleTypeName
				+ ", lSampleTypeId=" + lSampleTypeId + ", lSampleTypeName="
				+ lSampleTypeName + ", customerId=" + customerId + "]";
	}
}