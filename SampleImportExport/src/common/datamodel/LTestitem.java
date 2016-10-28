package common.datamodel;

import java.math.BigDecimal;

/**
 * LTestitem entity. @author MyEclipse Persistence Tools
 */

public class LTestitem implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private String createTime;
	private String createUser;
	private String description;
	private Long algorithm;
	private String english;
	private String enumData;
	private String guide;
	private String importance;
	private String indexId;
	private Long isprint;
	private String knowledgename;
	private String method;
	private String modifyTime;
	private String modifyUser;
	private String name;
	private Long needhistory;
	private Long printord;
	private String sampleFrom;
	private String type;
	private String unit;
	private String labdepartment;
	private String instrument;
	private String defaultvalue;
	private String principle;
	private String workcriterion;
	private String increasedhint;
	private String decreasedhint;
	private String notes;
	private String inuredate;
	private String outdate;
	private String outdateoperator;
	private String tea;
	private String ccv;
	private String testclass;
	private BigDecimal qcneed;
	private String methodname;
	private BigDecimal testkind;
	private String dictionaries;

	// Constructors

	/** default constructor */
	public LTestitem() {
	}

	/** minimal constructor */
	public LTestitem(BigDecimal id, String indexId, String name) {
		this.id = id;
		this.indexId = indexId;
		this.name = name;
	}

	/** full constructor */
	public LTestitem(BigDecimal id, String createTime, String createUser, String description, Long algorithm, String english, String enumData,
			String guide, String importance, String indexId, Long isprint, String knowledgename, String method, String modifyTime,
			String modifyUser, String name, Long needhistory, Long printord, String sampleFrom, String type, String unit, String labdepartment,
			String instrument, String defaultvalue, String principle, String workcriterion, String increasedhint, String decreasedhint, String notes,
			String inuredate, String outdate, String outdateoperator, String tea, String ccv, String testclass, BigDecimal qcneed, String methodname,
			BigDecimal testkind, String dictionaries) {
		this.id = id;
		this.createTime = createTime;
		this.createUser = createUser;
		this.description = description;
		this.algorithm = algorithm;
		this.english = english;
		this.enumData = enumData;
		this.guide = guide;
		this.importance = importance;
		this.indexId = indexId;
		this.isprint = isprint;
		this.knowledgename = knowledgename;
		this.method = method;
		this.modifyTime = modifyTime;
		this.modifyUser = modifyUser;
		this.name = name;
		this.needhistory = needhistory;
		this.printord = printord;
		this.sampleFrom = sampleFrom;
		this.type = type;
		this.unit = unit;
		this.labdepartment = labdepartment;
		this.instrument = instrument;
		this.defaultvalue = defaultvalue;
		this.principle = principle;
		this.workcriterion = workcriterion;
		this.increasedhint = increasedhint;
		this.decreasedhint = decreasedhint;
		this.notes = notes;
		this.inuredate = inuredate;
		this.outdate = outdate;
		this.outdateoperator = outdateoperator;
		this.tea = tea;
		this.ccv = ccv;
		this.testclass = testclass;
		this.qcneed = qcneed;
		this.methodname = methodname;
		this.testkind = testkind;
		this.dictionaries = dictionaries;
	}

	// Property accessors

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAlgorithm() {
		return this.algorithm;
	}

	public void setAlgorithm(Long algorithm) {
		this.algorithm = algorithm;
	}

	public String getEnglish() {
		return this.english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getEnumData() {
		return this.enumData;
	}

	public void setEnumData(String enumData) {
		this.enumData = enumData;
	}

	public String getGuide() {
		return this.guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public String getImportance() {
		return this.importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getIndexId() {
		return this.indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public Long getIsprint() {
		return this.isprint;
	}

	public void setIsprint(Long isprint) {
		this.isprint = isprint;
	}

	public String getKnowledgename() {
		return this.knowledgename;
	}

	public void setKnowledgename(String knowledgename) {
		this.knowledgename = knowledgename;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNeedhistory() {
		return this.needhistory;
	}

	public void setNeedhistory(Long needhistory) {
		this.needhistory = needhistory;
	}

	public Long getPrintord() {
		return this.printord;
	}

	public void setPrintord(Long printord) {
		this.printord = printord;
	}

	public String getSampleFrom() {
		return this.sampleFrom;
	}

	public void setSampleFrom(String sampleFrom) {
		this.sampleFrom = sampleFrom;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLabdepartment() {
		return this.labdepartment;
	}

	public void setLabdepartment(String labdepartment) {
		this.labdepartment = labdepartment;
	}

	public String getInstrument() {
		return this.instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getDefaultvalue() {
		return this.defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getPrinciple() {
		return this.principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	public String getWorkcriterion() {
		return this.workcriterion;
	}

	public void setWorkcriterion(String workcriterion) {
		this.workcriterion = workcriterion;
	}

	public String getIncreasedhint() {
		return this.increasedhint;
	}

	public void setIncreasedhint(String increasedhint) {
		this.increasedhint = increasedhint;
	}

	public String getDecreasedhint() {
		return this.decreasedhint;
	}

	public void setDecreasedhint(String decreasedhint) {
		this.decreasedhint = decreasedhint;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getInuredate() {
		return this.inuredate;
	}

	public void setInuredate(String inuredate) {
		this.inuredate = inuredate;
	}

	public String getOutdate() {
		return this.outdate;
	}

	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}

	public String getOutdateoperator() {
		return this.outdateoperator;
	}

	public void setOutdateoperator(String outdateoperator) {
		this.outdateoperator = outdateoperator;
	}

	public String getTea() {
		return this.tea;
	}

	public void setTea(String tea) {
		this.tea = tea;
	}

	public String getCcv() {
		return this.ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public String getTestclass() {
		return this.testclass;
	}

	public void setTestclass(String testclass) {
		this.testclass = testclass;
	}

	public BigDecimal getQcneed() {
		return this.qcneed;
	}

	public void setQcneed(BigDecimal qcneed) {
		this.qcneed = qcneed;
	}

	public String getMethodname() {
		return this.methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	public BigDecimal getTestkind() {
		return this.testkind;
	}

	public void setTestkind(BigDecimal testkind) {
		this.testkind = testkind;
	}

	public String getDictionaries() {
		return this.dictionaries;
	}

	public void setDictionaries(String dictionaries) {
		this.dictionaries = dictionaries;
	}

	@Override
	public String toString() {
		return "LTestitem [id=" + id + ", createTime=" + createTime
				+ ", createUser=" + createUser + ", description=" + description
				+ ", algorithm=" + algorithm + ", english=" + english
				+ ", enumData=" + enumData + ", guide=" + guide
				+ ", importance=" + importance + ", indexId=" + indexId
				+ ", isprint=" + isprint + ", knowledgename=" + knowledgename
				+ ", method=" + method + ", modifyTime=" + modifyTime
				+ ", modifyUser=" + modifyUser + ", name=" + name
				+ ", needhistory=" + needhistory + ", printord=" + printord
				+ ", sampleFrom=" + sampleFrom + ", type=" + type + ", unit="
				+ unit + ", labdepartment=" + labdepartment + ", instrument="
				+ instrument + ", defaultvalue=" + defaultvalue
				+ ", principle=" + principle + ", workcriterion="
				+ workcriterion + ", increasedhint=" + increasedhint
				+ ", decreasedhint=" + decreasedhint + ", notes=" + notes
				+ ", inuredate=" + inuredate + ", outdate=" + outdate
				+ ", outdateoperator=" + outdateoperator + ", tea=" + tea
				+ ", ccv=" + ccv + ", testclass=" + testclass + ", qcneed="
				+ qcneed + ", methodname=" + methodname + ", testkind="
				+ testkind + ", dictionaries=" + dictionaries + "]";
	}

}