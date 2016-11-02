/**
 * 
 */
package webService.server.help;

import common.datamodel.DsfTestitems;
import common.datamodel.DsfProcess;
import common.datamodel.DsfSampleInfo;
import common.webmodel.SampleInfo_XML;
import common.webmodel.TestItem_XML;

/**
 * @author zjn
 * @createTime 2016-9-14
 */
public class ConversionBean {

	public static DsfSampleInfo cSample(DsfSampleInfo ls,SampleInfo_XML sXml) {
		ls.setAge(sXml.getAge());
		ls.setAgeunit(sXml.getAgeunit());
		ls.setBirthday(sXml.getBirthday());
		ls.setDepartBed(sXml.getDepartBed());
		ls.setDiagnostic(sXml.getDiagnostic());
		ls.setDsfbarcode(sXml.getDsfbarcode());
		ls.setHossection(sXml.getHossection());
		ls.setInspectionname(sXml.getInspectionname());
		ls.setPatientblh(sXml.getPatientblh());
		ls.setPatientid(sXml.getPatientid());
		ls.setPatientname(sXml.getPatientname());
		ls.setSampletype(sXml.getSampletype());
		ls.setSex(sXml.getSex());
		ls.setYlxh(sXml.getYlxh());
		return ls;
	}

}
