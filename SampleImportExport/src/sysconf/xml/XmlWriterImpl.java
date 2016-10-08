package sysconf.xml;

import java.io.FileInputStream;	
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.jdom.input.DOMBuilder;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import common.util.CommonUtil;

/**
 * xml文件写入实现类
 * 
 * @author builder
 * @created November 12, 2004
 * @modified [who date description]
 * @check [who date description]
 * @trace [Feature Id]
 * @version [1.1.1]
 */
public class XmlWriterImpl implements XmlWriter {
	/** Description of the Field */
	private String path;
	private Logger logger = CommonUtil.getLogger();

	/**
	 * Constructor for the XmlWriterImpl object 获得路径
	 * 
	 * @param path
	 *            Description of Parameter
	 */
	public XmlWriterImpl(String path) {
		this.path = path;
	}

	/**
	 * Sets the XmlConfig attribute of the XmlWriterImpl object
	 * 
	 * @param moduleName
	 *            The new XmlConfig value
	 * @param obj
	 *            The new XmlConfig value
	 * @exception OamException
	 *                Description of Exception
	 */
	public void setXmlConfig(String moduleName, Object obj)
			throws FileNotFoundException {

		FileInputStream fis = null;
		OutputStreamWriter osw = null;

		try {
			Mapping mapping = new Mapping();

			fis = new FileInputStream(path + moduleName + "-mapping.xml");

			InputSource is = new InputSource(fis);

			mapping.loadMapping(is);

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Marshaller ma = new Marshaller(doc);

			ma.setMapping(mapping);
			ma.marshal(obj);

			XMLOutputter outputter = new XMLOutputter();
			DOMBuilder domBuilder = new DOMBuilder();

			org.jdom.Document document = domBuilder.build(doc);
			FileOutputStream fos = new FileOutputStream(path + moduleName
					+ "-data.xml");

			osw = new OutputStreamWriter(fos, "UTF-8");
			outputter.output(document, osw);
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					
					logger.error(e.getMessage(), e);
				}
			}
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
}
