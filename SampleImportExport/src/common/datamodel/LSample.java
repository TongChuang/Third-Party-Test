package common.datamodel;

// default package

import java.math.BigDecimal;

/**
 * LSample entity. @author MyEclipse Persistence Tools
 */

public class LSample implements java.io.Serializable {

	// Fields
	private BigDecimal id;
	private String age;
	private String birthday;
	private Long cycle = 0L;
	private String departBed;
	private String diagnostic;
	private Long hasimages = 0L;
	private String hossection;
	private String inspectionname;
	private Long iswriteback = 0L;
	private Long modifyflag = 0L;
	private String patientid;
	private String patientblh;
	private String patientname;
	private String sampleno = "0";
	private Long samplestatus = 3L;
	private String sampletype;
	private String sex;
	private Long stayhospitalmode = 4L;
	private Long writeback = 0L;
	private String ylxh;
	private String ageunit;
	private String dsfcustomerid;
	private String dsfbarcode;
	private String localbarcode;
	private String imgurl;

	// Constructors

	/** default constructor */
	public LSample() {
	}

	/** full constructor */
	public LSample(BigDecimal id, String age, String birthday, Long cycle,
			String departBed, String diagnostic, Long hasimages,
			String hossection, String inspectionname, Long iswriteback,
			Long modifyflag, String patientid, String patientblh,
			String patientname, String sampleno, Long samplestatus,
			String sampletype, String sex, Long stayhospitalmode,
			Long writeback, String ylxh, String ageunit, String dsfcustomerid,
			String dsfbarcode,String localbarcode,String imgurl) {
		this.id = id;
		this.age = age;
		this.birthday = birthday;
		this.cycle = cycle;
		this.departBed = departBed;
		this.diagnostic = diagnostic;
		this.hasimages = hasimages;
		this.hossection = hossection;
		this.inspectionname = inspectionname;
		this.iswriteback = iswriteback;
		this.modifyflag = modifyflag;
		this.patientid = patientid;
		this.patientblh = patientblh;
		this.patientname = patientname;
		this.sampleno = sampleno;
		this.samplestatus = samplestatus;
		this.sampletype = sampletype;
		this.sex = sex;
		this.stayhospitalmode = stayhospitalmode;
		this.writeback = writeback;
		this.ylxh = ylxh;
		this.ageunit = ageunit;
		this.dsfcustomerid = dsfcustomerid;
		this.dsfbarcode = dsfbarcode;
		this.localbarcode = localbarcode;
		this.imgurl = imgurl;		
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getLocalbarcode() {
		return localbarcode;
	}

	public void setLocalbarcode(String localbarcode) {
		this.localbarcode = localbarcode;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Long getCycle() {
		return cycle;
	}

	public void setCycle(Long cycle) {
		this.cycle = cycle;
	}

	public String getDepartBed() {
		return departBed;
	}

	public void setDepartBed(String departBed) {
		this.departBed = departBed;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public Long getHasimages() {
		return hasimages;
	}

	public void setHasimages(Long hasimages) {
		this.hasimages = hasimages;
	}

	public String getHossection() {
		return hossection;
	}

	public void setHossection(String hossection) {
		this.hossection = hossection;
	}

	public String getInspectionname() {
		return inspectionname;
	}

	public void setInspectionname(String inspectionname) {
		this.inspectionname = inspectionname;
	}

	public Long getIswriteback() {
		return iswriteback;
	}

	public void setIswriteback(Long iswriteback) {
		this.iswriteback = iswriteback;
	}

	public Long getModifyflag() {
		return modifyflag;
	}

	public void setModifyflag(Long modifyflag) {
		this.modifyflag = modifyflag;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getPatientblh() {
		return patientblh;
	}

	public void setPatientblh(String patientblh) {
		this.patientblh = patientblh;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getSampleno() {
		return sampleno;
	}

	public void setSampleno(String sampleno) {
		this.sampleno = sampleno;
	}

	public Long getSamplestatus() {
		return samplestatus;
	}

	public void setSamplestatus(Long samplestatus) {
		this.samplestatus = samplestatus;
	}

	public String getSampletype() {
		return sampletype;
	}

	public void setSampletype(String sampletype) {
		this.sampletype = sampletype;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getStayhospitalmode() {
		return stayhospitalmode;
	}

	public void setStayhospitalmode(Long stayhospitalmode) {
		this.stayhospitalmode = stayhospitalmode;
	}

	public Long getWriteback() {
		return writeback;
	}

	public void setWriteback(Long writeback) {
		this.writeback = writeback;
	}

	public String getYlxh() {
		return ylxh;
	}

	public void setYlxh(String ylxh) {
		this.ylxh = ylxh;
	}

	public String getAgeunit() {
		return ageunit;
	}

	public void setAgeunit(String ageunit) {
		this.ageunit = ageunit;
	}

	public String getDsfcustomerid() {
		return dsfcustomerid;
	}

	public void setDsfcustomerid(String dsfcustomerid) {
		this.dsfcustomerid = dsfcustomerid;
	}

	public String getDsfbarcode() {
		return dsfbarcode;
	}

	public void setDsfbarcode(String dsfbarcode) {
		this.dsfbarcode = dsfbarcode;
	}

}