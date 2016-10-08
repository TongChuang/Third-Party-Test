/**
 * 
 */
package common.webmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author zjn
 * @createTime 2016-9-6
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class SampleInfo_XML {

	// 病人 就诊号
	private String patientid;
	// 病人名字
	private String patientname;
	// 生日
	private String birthday;
	// 性别
	private String sex;
	// 年龄
	private String age;
	// 病床号
	private String departBed;
	// 就诊方式（门诊、住院、急诊）
	private String stayhospitalmode;
	// 申请科室
	private String hossection;
	// 诊断
	private String diagnostic;
	// 检验目的
	private String inspectionname;
	// 检验目的id
	private String ylxh;
	// 样本类型 、来源（血液、粪便）
	private String sampletype;
	// 生理周期
	private String cycle;
	// 年龄单位（年月日）
	private String ageunit;
	// 条码号
	private String dsfbarcode;
	// 病人唯一号
	private String patientblh;
	

	public SampleInfo_XML() {
	}

	public SampleInfo_XML(String patientid, String patientname,
			String birthday, String sex, String age, String departBed,
			String stayhospitalmode, String hossection, String diagnostic,
			String inspectionname, String ylxh, String sampletype,
			String cycle, String ageunit, String dsfbarcode, String patientblh) {
		this.patientid = patientid;
		this.patientname = patientname;
		this.birthday = birthday;
		this.sex = sex;
		this.age = age;
		this.departBed = departBed;
		this.stayhospitalmode = stayhospitalmode;
		this.hossection = hossection;
		this.diagnostic = diagnostic;
		this.inspectionname = inspectionname;
		this.ylxh = ylxh;
		this.sampletype = sampletype;
		this.cycle = cycle;
		this.ageunit = ageunit;
		this.dsfbarcode = dsfbarcode;
		this.patientblh = patientblh;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDepartBed() {
		return departBed;
	}

	public void setDepartBed(String departBed) {
		this.departBed = departBed;
	}

	public String getStayhospitalmode() {
		return stayhospitalmode;
	}

	public void setStayhospitalmode(String stayhospitalmode) {
		this.stayhospitalmode = stayhospitalmode;
	}

	public String getHossection() {
		return hossection;
	}

	public void setHossection(String hossection) {
		this.hossection = hossection;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getInspectionname() {
		return inspectionname;
	}

	public void setInspectionname(String inspectionname) {
		this.inspectionname = inspectionname;
	}

	public String getYlxh() {
		return ylxh;
	}

	public void setYlxh(String ylxh) {
		this.ylxh = ylxh;
	}

	public String getSampletype() {
		return sampletype;
	}

	public void setSampletype(String sampletype) {
		this.sampletype = sampletype;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getAgeunit() {
		return ageunit;
	}

	public void setAgeunit(String ageunit) {
		this.ageunit = ageunit;
	}

	public String getDsfbarcode() {
		return dsfbarcode;
	}

	public void setDsfbarcode(String dsfbarcode) {
		this.dsfbarcode = dsfbarcode;
	}

	public String getPatientblh() {
		return patientblh;
	}

	public void setPatientblh(String patientblh) {
		this.patientblh = patientblh;
	}

}
