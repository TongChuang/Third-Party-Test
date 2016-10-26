package common.datamodel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DsfProcess entity. @author MyEclipse Persistence Tools
 */

public class DsfProcess implements java.io.Serializable {

	// Fields

	private BigDecimal id;//主键
	private Date printtime;//打印时间
	private String collectionpersonnel;//采集人
	private Date collectiontime;//采集时间
	private String inputpersonnel;//录入人
	private Date inputtime;//录入时间
	private String prehandlingpersonnel;//前处理人
	private Date preprocessingtime;//前处理时间
	private String printingstaff;//打印人
	private String sampleId;//样本id不是编号

	// Constructors

	/** default constructor */
	public DsfProcess() {
	}

	/** minimal constructor */
	public DsfProcess(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public DsfProcess(BigDecimal id, Date printtime, String collectionpersonnel, Date collectiontime, String inputpersonnel, Date inputtime,
			String prehandlingpersonnel, Date preprocessingtime, String printingstaff, String sampleId) {
		this.id = id;
		this.printtime = printtime;
		this.collectionpersonnel = collectionpersonnel;
		this.collectiontime = collectiontime;
		this.inputpersonnel = inputpersonnel;
		this.inputtime = inputtime;
		this.prehandlingpersonnel = prehandlingpersonnel;
		this.preprocessingtime = preprocessingtime;
		this.printingstaff = printingstaff;
		this.sampleId = sampleId;
	}

	// Property accessors

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getPrinttime() {
		return this.printtime;
	}

	public void setPrinttime(Date printtime) {
		this.printtime = printtime;
	}

	public String getCollectionpersonnel() {
		return this.collectionpersonnel;
	}

	public void setCollectionpersonnel(String collectionpersonnel) {
		this.collectionpersonnel = collectionpersonnel;
	}

	public Date getCollectiontime() {
		return this.collectiontime;
	}

	public void setCollectiontime(Date collectiontime) {
		this.collectiontime = collectiontime;
	}

	public String getInputpersonnel() {
		return this.inputpersonnel;
	}

	public void setInputpersonnel(String inputpersonnel) {
		this.inputpersonnel = inputpersonnel;
	}

	public Date getInputtime() {
		return this.inputtime;
	}

	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}

	public String getPrehandlingpersonnel() {
		return this.prehandlingpersonnel;
	}

	public void setPrehandlingpersonnel(String prehandlingpersonnel) {
		this.prehandlingpersonnel = prehandlingpersonnel;
	}

	public Date getPreprocessingtime() {
		return this.preprocessingtime;
	}

	public void setPreprocessingtime(Date preprocessingtime) {
		this.preprocessingtime = preprocessingtime;
	}

	public String getPrintingstaff() {
		return this.printingstaff;
	}

	public void setPrintingstaff(String printingstaff) {
		this.printingstaff = printingstaff;
	}

	public String getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

}