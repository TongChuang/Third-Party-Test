package sysconf.xml;

import java.io.FileNotFoundException;

/**
 * xmlReader½Ó¿Ú
 * 
 * @author builder
 * @created November 12, 2004
 * @modified [who date description]
 * @check [who date description]
 * @trace [Feature Id]
 * @version [1.1.1]
 */
public interface XmlReader {
	/**
	 * Gets the XmlConfig attribute of the XmlReader object
	 * 
	 * @param objName
	 *            Description of Parameter
	 * @return The XmlConfig value
	 * @exception OamConfigNotFoundException
	 *                Description of Exception
	 */
	public Object getXmlConfig(String objName) throws FileNotFoundException;
}
