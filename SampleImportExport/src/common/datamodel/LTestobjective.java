package common.datamodel;

import java.math.BigDecimal;

/**
 * LTestobjective entity. @author MyEclipse Persistence Tools
 */

public class LTestobjective implements java.io.Serializable {

	// Fields

	private BigDecimal ylxh;
	private String ksdm;
	private String profiletest;
	private String profiletest2;
	private String profiletest3;
	private String ylmc;
	private String english;
	private BigDecimal hospitalid;
	private Long mzpb;
	private String note;
	private String pinyin;
	private String price;
	private String section;
	private Long sfhb;
	private String unit;
	private String wubi;
	private Long zypb;
	private String qbgdd;
	private String qbgsj;
	private String yblx;
	private String jyxmfl;
	private Long tcpb;
	private String sglx;
	private String bbl;
	private String cjbw;
	private Boolean sgsl;
	private String segment;
	private String nightsegment;
	private String template;

	// Constructors

	/** default constructor */
	public LTestobjective() {
	}

	/** minimal constructor */
	public LTestobjective(BigDecimal ylxh) {
		this.ylxh = ylxh;
	}

	/** full constructor */
	public LTestobjective(BigDecimal ylxh, String ksdm, String profiletest, String profiletest2, String profiletest3, String ylmc, String english,
			BigDecimal hospitalid, Long mzpb, String note, String pinyin, String price, String section, Long sfhb, String unit, String wubi,
			Long zypb, String qbgdd, String qbgsj, String yblx, String jyxmfl, Long tcpb, String sglx, String bbl, String cjbw, Boolean sgsl,
			String segment, String nightsegment, String template) {
		this.ylxh = ylxh;
		this.ksdm = ksdm;
		this.profiletest = profiletest;
		this.profiletest2 = profiletest2;
		this.profiletest3 = profiletest3;
		this.ylmc = ylmc;
		this.english = english;
		this.hospitalid = hospitalid;
		this.mzpb = mzpb;
		this.note = note;
		this.pinyin = pinyin;
		this.price = price;
		this.section = section;
		this.sfhb = sfhb;
		this.unit = unit;
		this.wubi = wubi;
		this.zypb = zypb;
		this.qbgdd = qbgdd;
		this.qbgsj = qbgsj;
		this.yblx = yblx;
		this.jyxmfl = jyxmfl;
		this.tcpb = tcpb;
		this.sglx = sglx;
		this.bbl = bbl;
		this.cjbw = cjbw;
		this.sgsl = sgsl;
		this.segment = segment;
		this.nightsegment = nightsegment;
		this.template = template;
	}

	// Property accessors

	public BigDecimal getYlxh() {
		return this.ylxh;
	}

	public void setYlxh(BigDecimal ylxh) {
		this.ylxh = ylxh;
	}

	public String getKsdm() {
		return this.ksdm;
	}

	public void setKsdm(String ksdm) {
		this.ksdm = ksdm;
	}

	public String getProfiletest() {
		return this.profiletest;
	}

	public void setProfiletest(String profiletest) {
		this.profiletest = profiletest;
	}

	public String getProfiletest2() {
		return this.profiletest2;
	}

	public void setProfiletest2(String profiletest2) {
		this.profiletest2 = profiletest2;
	}

	public String getProfiletest3() {
		return this.profiletest3;
	}

	public void setProfiletest3(String profiletest3) {
		this.profiletest3 = profiletest3;
	}

	public String getYlmc() {
		return this.ylmc;
	}

	public void setYlmc(String ylmc) {
		this.ylmc = ylmc;
	}

	public String getEnglish() {
		return this.english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public BigDecimal getHospitalid() {
		return this.hospitalid;
	}

	public void setHospitalid(BigDecimal hospitalid) {
		this.hospitalid = hospitalid;
	}

	public Long getMzpb() {
		return this.mzpb;
	}

	public void setMzpb(Long mzpb) {
		this.mzpb = mzpb;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Long getSfhb() {
		return this.sfhb;
	}

	public void setSfhb(Long sfhb) {
		this.sfhb = sfhb;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getWubi() {
		return this.wubi;
	}

	public void setWubi(String wubi) {
		this.wubi = wubi;
	}

	public Long getZypb() {
		return this.zypb;
	}

	public void setZypb(Long zypb) {
		this.zypb = zypb;
	}

	public String getQbgdd() {
		return this.qbgdd;
	}

	public void setQbgdd(String qbgdd) {
		this.qbgdd = qbgdd;
	}

	public String getQbgsj() {
		return this.qbgsj;
	}

	public void setQbgsj(String qbgsj) {
		this.qbgsj = qbgsj;
	}

	public String getYblx() {
		return this.yblx;
	}

	public void setYblx(String yblx) {
		this.yblx = yblx;
	}

	public String getJyxmfl() {
		return this.jyxmfl;
	}

	public void setJyxmfl(String jyxmfl) {
		this.jyxmfl = jyxmfl;
	}

	public Long getTcpb() {
		return this.tcpb;
	}

	public void setTcpb(Long tcpb) {
		this.tcpb = tcpb;
	}

	public String getSglx() {
		return this.sglx;
	}

	public void setSglx(String sglx) {
		this.sglx = sglx;
	}

	public String getBbl() {
		return this.bbl;
	}

	public void setBbl(String bbl) {
		this.bbl = bbl;
	}

	public String getCjbw() {
		return this.cjbw;
	}

	public void setCjbw(String cjbw) {
		this.cjbw = cjbw;
	}

	public Boolean getSgsl() {
		return this.sgsl;
	}

	public void setSgsl(Boolean sgsl) {
		this.sgsl = sgsl;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getNightsegment() {
		return this.nightsegment;
	}

	public void setNightsegment(String nightsegment) {
		this.nightsegment = nightsegment;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}