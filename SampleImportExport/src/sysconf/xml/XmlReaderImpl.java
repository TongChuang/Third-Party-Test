package sysconf.xml;

import java.io.FileInputStream;		
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import common.util.CommonUtil;


public class XmlReaderImpl implements XmlReader {
	/** Description of the Field */
	private String path;
	private Logger logger = CommonUtil.getLogger();

	/**
	 * Constructor for the XmlReaderImpl object �õ��ļ�·��
	 * 
	 * @param path
	 *            Description of Parameter
	 */
	public XmlReaderImpl(String path) {
		this.path = path;
	}

	/**
	 * Gets the XmlConfig attribute of the XmlReaderImpl object
	 * 
	 * @param objName
	 *            xml file name without -data.xml/-mapping.xml
	 * @return xml unmarshal object
	 * @exception OamConfigNotFoundException
	 *                Description of the Exception
	 */
	public Object getXmlConfig(String objName) throws FileNotFoundException {

		Object obj = null;
		FileInputStream fis = null;

		// FileReader reader = null;

		try {
			// -- Load a mapping file
			fis = new FileInputStream(path + objName + "-mapping.xml");

			InputSource is = new InputSource(fis);
			Mapping mapping = new Mapping();

			mapping.loadMapping(is);

			Unmarshaller un = new Unmarshaller(mapping);

			InputSource dataIs = new InputSource(new FileInputStream(path
					+ objName + "-data.xml"));

			obj = un.unmarshal(dataIs);
		} catch (FileNotFoundException fe) {
			throw fe;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ioe) {
				logger.error(ioe.getMessage(), ioe);
			}
		}

		return obj;
	}
}
