package sysconf.xml;

import java.io.FileNotFoundException;

public interface XmlWriter {
	public abstract void setXmlConfig(String s, Object obj) throws FileNotFoundException;
}